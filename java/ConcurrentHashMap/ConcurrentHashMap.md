# ConcurrentHashMap

기존에 사용하는 HashMap은 MultiThread 환경에선 Thread-safe하지 않는다.

그렇다면 HashMap 구조의 자료구조를 Thread-safe하게 쓰는 방법으론 3가지 방법이 있다.

1. HashTable

2. Collections.synchronizedMap *메소드로 HashMap 생성*

3. CouncurrentHashMap


<br><br>

## 1. HashTable

- HashTable은 JDK 1.0 부터 있었던 자료구조이며, 모든 메소드에 `synchronized` 키워드가 붙어 있기 떄문에 Thread-Safe 하다.

- HashMap 분석을 할때 정리했었지만 기본적으로 레거시코드를 위한 자료구조이다. 

- 그렇기 때문에 업데이트가 안되서 성능상 불리하다.

- 실제 충돌난 버킷에 대해서 HashTable은 LinkedList만을 이용해서 Separate Chaining을 구현했으며,  
    충돌난 버킷의 Node를 탐색할떄 항상 O(n)의 시간복잡도를 가지게 된다. 

- (물론 HashMap또한 LinkedList를 사용하지만, 크기가 커지면 Red-Black Tree로 변경한다.)

<br><br><br><br>

## 2. Collections.synchronizedMap *메소드로 HashMap 생성*

- Collections.synchronizedMap 을 사용해서 HashMap을 사용하게 되면 메소드를 사용할떄 아래와 같은 형식으로 호출이 된다. 

- 이 또한 Thread-Safe 하게 자료구조를 사용할수 있지만 모든 메소드에 `synchronized` 키워드가 붙어 있으므로 

- 병목현상 이 발생하게 되므로 성능상 불리한 점이 있다.

     (메소드에 접근하게 되면 다른 쓰레드는 Lock을 얻을 때까지 기다려야 하기 때문이다.)

<br>

``` java
// (mutex는 Object 타입의 변수명이다.)
public V get(Object key) {
    synchronized (mutex) {return m.get(key);}
}
```

<br><br>

## 3. ConcurrentHashMap

- Thread-Safe 를 보장하면서 위의 자료구조 보다 높은 성능을 보여주는 자료구조이다.

- Key, value에 null 값은 허용하지 않는다.

- 추가/삭제하는 put, remove 등의 메소드에는 부분적으로 동기화 처리가 되어 있으며, Get 에는 동기화 처리가 안되어 있어서 값을 조회할때 높은 성능을 보여준다.

- Get(조회) 메소드는 항상 최신 데이터를 조회한다.
    - `volatile` 키워드를 통해서 cpu cache에 저장하는것이 아니라 메인 메모리에 저장하기 때문에  
        가시성 문제를 없게 만들어서 업데이트가 완료된(최신) 데이터만 조회한다.
    
- 추가 / 삭제하는 메소드 또한 기본적으로 CAS 알고리즘을 사용하여 빈 버킷에 대해서는 동기화 로직(Lock) 없이 처리한다.

- 그러나, 비어있지 않은 버킷에 대해서는 `synchronized` 키워드를 사용해서 동기화 문제를 해결한다. 해당 키워드 내부 로직은 HashMap 과 동일하다.

<br><br>

## 3.1 ConcurrentHashMap.putVal 분석

```java
// ConcurrentHashMap 에서 내부적으로 데이터를 삽입할떄 사용하는 메소드
final V putVal(K key, V value, boolean onlyIfAbsent) {
        // ConcurrentHashMap은 key와 value가 null이 올 수 없다.
        if (key == null || value == null) throw new NullPointerException();

        // 보조 해시 함수(supplement hash function)
        // '키'의 해시 값을 변형하여, 해시 충돌 가능성을 줄이는 것이다.
        int hash = spread(key.hashCode());

        int binCount = 0;
				
        // 무한루프 (값이 저장되면 종료된다.)
        // table은 hashcode가 같은 Node가 저장된 가변 배열이다.
        for (Node<K,V>[] tab = table;;) {
            Node<K,V> f;
            int n, i, fh; K fk; V fv;

            // 테이블이 비어있으면 생성해준다.
            if (tab == null || (n = tab.length) == 0)
                tab = initTable();

            // 새로운 노드를 삽입하기 위해, 해당 버킷 값을 가져와(tabAt 함수)
            // 비어 있는지 확인한다.(== null)
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
                // CAS 알고리즘을 이용해서 Node가 담고 있는 volatile 변수에 접근
                // volatile 키워드를 사용해서 변수를 생성했기 때문에 cpu cache에 저장되는것이 아니라
                // 메인메모리에 직접적으로 저장되므로 가시성 문제가 없다.
                // 기대값(null) 이 같은지 확인하여 같을 경우 데이터를 삽입하고 
                // 다를 경우 무한루프를 돌리면서 busy waiting을 한다
                // casTabAt 메소드는 내부적으로 Unsafe 클래스에서 구현된 CAS를 사용하고 있다. (java 11)
                if (casTabAt(tab, i, null, new Node<K,V>(hash, key, value)))
                    break;                   // no lock when adding to empty bin
            }
            // 해당 버킷이 새로운 테이블로 이동됬는지 체크하고 이동이 되었으면 새로운 테이블 주소를 반환해 준다.
            // HashMap은 새로운 테이블을 만들떄 함꺼번에 copy하는 방식이지만,
            // ConcurrentHashmap은 기존 테이블에서 새로운 테이블로 버킷을 하나씩 전송(transfer) 한다
            // 이 과정에서 다른 스레드가 버킷 전송에 참여할 수도 있다. 전송이 모두 끝나면 크기가 2배인 nextTable 이 새로운 배열이 된다.
            // 변수 sizeCtl 과 resizeStamp 메서드를 통해 resizing 과정이 중복으로 일어나지 않도록 방지한다.
            else if ((fh = f.hash) == MOVED)
                tab = helpTransfer(tab, f);
            // hashcode와 equals를 통해서 해당 값이 같은지 체크해서 같으면 종료(반환)한다.
            else if (onlyIfAbsent // check first node without acquiring lock
                     && fh == hash
                     && ((fk = f.key) == key || (fk != null && key.equals(fk)))
                     && (fv = f.val) != null)
                return fv;
            else {
                V oldVal = null;
                // 해당 버킷에 Separate Chaining형식으로 데이터를 삽입해야할 경우
                // synchronized 키워드를 이용해서 버킷을 잠금시킨다.
                synchronized (f) {
                    if (tabAt(tab, i) == f) {
                        if (fh >= 0) {
                            binCount = 1;
                            // linkedList 방식으로 node를 탐색하면서 삽입/수정을 한다.
                            for (Node<K,V> e = f;; ++binCount) {
                                K ek;
								// 수정
                                if (e.hash == hash &&
                                    ((ek = e.key) == key ||
                                     (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                Node<K,V> pred = e;
								// 삽입
                                if ((e = e.next) == null) {
                                    pred.next = new Node<K,V>(hash, key, value);
                                    break;
                                }
                            }
                        }
                        // 버킷의 구조가 Red-Black Tree로 이루어져있을 경우 
                        // ConcurrentHashMap 또한 HashMap처럼 버킷의 크기가 작을 경우 LinkedList 구조로 만들다가
                        // 크기가 크면 Red-Black Tree로 변경한다. 
                        // 상수(TREEIFY_THRESHOLD, UNTREEIFY_THRESHOLD)에 따라 구조가 변경된다.
                        else if (f instanceof TreeBin) {
                            Node<K,V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                        else if (f instanceof ReservationNode)
                            throw new IllegalStateException("Recursive update");
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        treeifyBin(tab, i);
                    if (oldVal != null)
                        return oldVal;
                    break;
                }
            }
        }
        // count를 추가하여 값에 따라 
        // 새로운 배열을 생성시키는 함수(trasfer) 를 실행시킨다.
        addCount(1L, binCount);
        return null;
    }
```

<br><br>

## 3.1 멀티쓰레드 환경에서 get과 put 테스트 
링크 >> [ConcurrentHashMapTest.java](https://github.com/ryunian/Study/blob/624fc19ec17b8b3da67f95f366f9a160c24c6985/Code/ConcurrentHashMap/ConcurrentHashMapTest.java)

~~테스트의 정확성엔 의문이 들었지만~~  
여러번 실행을 해봄으로 인해 HashMap과 ConcurrentHashMap 결과의 차이를 알게 되었다.

<br><br>
<hr>

[출처]

[https://pplenty.tistory.com/17](https://pplenty.tistory.com/17)

[https://junghyungil.tistory.com/104](https://junghyungil.tistory.com/104)

[https://applefarm.tistory.com/100](https://applefarm.tistory.com/100)

[https://sujl95.tistory.com/63](https://sujl95.tistory.com/63)

[https://devlog-wjdrbs96.tistory.com/269](https://devlog-wjdrbs96.tistory.com/269)