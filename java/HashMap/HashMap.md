### 1.0 HashMap 과 HashTable
> HashMap 과 HashTable는 '키에 대한 해시 값을 사용하여 값을 저장하고 조회하며,  
> 키-값 쌍의 개수에 따라 동적으로 크기가 증가하는 associate array' 라고 할 수 있다.

<br>

### 1.1 왜 HashMap을 쓸까?
> 자바개발자는 API 를 쓴다고 하면 HashTable 은 쓰지않고 HashMap 만 쓴다. 왜 그럴까?

 * HashMap 과 HashTable 은 Map 인터페이스에 속하는 구현체이다. 정확히 말하자면 HashMap은 AbstractMap을 상속한 클래스이다.
 
 * 일반적으로 성능차이가 나기떄문에 Vector 를 쓰지않고 ArrayList 를 쓰는것처럼 사용한다.  
 
 * 정확하게 보자면 HashMap은 보조 해시 함수(Additional Hash Function)를 사용하기 때문에 보조 해시 함수를 사용하지 않는 HashTable에 비하여 해시 충돌(hash collision)이 덜 발생할 수 있다는 점이다.
  HashMap은 지속적으로 개선되고 있으며, HashTable은 그저 하위 호환성을 제공하는 것에 그친다.
    
 * 앞서 말한 Vector 처럼 기본적으로 동기화가 되어 있다는 특징이 있다. (그렇기 떄문에 쓸때없는 리소스를 잡아먹는다.)    
 그러나 동기화를 위해서 Vector 랑 HashTable 을 쓸 필요는 없다. Collections.synchronized...를 사용하거나 적절한 곳에서 synchronized 키워드를 사용하면 된다.

<br><br>

### 1.2 해시 분포와 해시 충돌
* Wrapper 클래스는 객체가 나타내려는 값 자체를 해시 값으로 사용할 수 있기 때문에 완전한 해시 함수(perfect hash functions) 대상으로 삼을 수 있다.  
* String 이나 POJO(plain old java object)에 대하여 완전한 해시 함수를 제작하는 것은 사실상 불가능하다.
HashMap은 기본적으로 각 객체의 hashCode() 메서드가 반환하는 값을 사용하는 데, 결과 자료형은 int다 (String.hashCode():int)  
32비트 정수 자료형으로는 완전한 자료 해시 함수를 만들 수 없다. 논리적으로 생성 가능한 객체의 수가 2^32보다 많을 수 있기 때문이며, 
또한 모든 HashMap 객체에서 O(1)을 보장하기 위해 랜덤 접근이 가능하게 하려면 원소가 2^32인 배열을 모든 HashMap이 가지고 있어야 하기 때문이다.

* 해시 함수가 얼마나 해시 충돌을 회피하도록 잘 구현했어도 해시충돌은 일어난다. 이를 해결하기 위해 키-값 쌍 데이터를 잘 저장하고 조회할 수 있게 하는 방식에는 대표적으로 두 가지가 있는데, 
하나는 Open Addressing이고, 다른 하나는 Separate Chaining이다
 
 
<br><br>


### 1.3.1 Open Addressing
데이터를 삽입하려는 해시 버킷이 이미 사용 중인 경우 다른 해시 버킷에 해당 데이터를 삽입하는 방식이다.  
데이터를 저장/조회할 해시 버킷을 찾을 때에는 Linear Probing, Quadratic Probing 등의 방법을 사용한다.  
공개 주소 방식이라고도 불리는 이 알고리즘은 충돌(Collision)이 발생하면 데이터를 저장할 장소를 찾아 헤멘다. 
Worst Case 경우 비어있는 버킷을 찾지 못하고 탐색을 시작한 위치까지 되돌아 올수 있다. 이 과정에서도 여러 방법이 존재한다.

1. 선형 조사법 (Linear Probing) : 순차적으로 탐색하며 비어있는 버킷을 찾을 때까지 계속 진행된다.
	- 충돌이 발생한 주소를 시작으로 차례로 저장되기 때문에 한번 충돌이 발생하기 시작하면 충돌이 발생한 주소 주위로 자료가 뭉치는 군집화 현상이 발생할 가능성이 크다.
	
	
2. 제곱 조사법 (Quadratic Probing) : 2차 함수를 이용해 탐색할 위치를 찾는다.
	- 여전히 군집화 현상이 발생할수 있으며, 해시테이블의 모든 주소를 조사하기 위해서는 해시테이블의 크기가 반드시 소수여야 한다는 제약 사항이 존재한다.
	
	
3. 이중 해싱 (Double Hashing Probing) : 하나의 해쉬 함수에서 충돌이 발생하면 2차 해쉬 함수를 이용해 새로운 주소를 할당한다.
	- 추가 해싱함수를 사용하여 같은 해싱 함수 값을 가지더라도 다른 조사 순서를 가지도록 하여 2차 집중이 줄어든다. 그러나 1,2에 비해 많은 연산량을 요구한다.

<br><br>

### 1.3.2 Separate Chaining
일반적으로 Open Addressing 은 Separate Chaining 보다 느리다. 
Open Addressing의 경우 해시 버킷을 채운 밀도가 높아질수록 Worst Case 발생 빈도가 더 높아지기 때문이다 (애초에 충돌이 한번도 발생하지 않는다고 하더라도 int의 범위 만큼의 데이터만 저장이 가능하다)  
반면 Separate Chaining 방식의 경우 해시 충돌이 잘 발생하지 않도록 보조 해시 함수를 통해 조정할수 있다면 Worst Case에 가까워 지는 빈도를 줄일 수 있다.  


* linkedList : 각각의 버킷들을 연결리스트로 만들어 충돌이 발생하면 해당 버킷의 list에 추가하는 방식이다.
연결리스트의 특징을 그대로 이어받아 삭제 또는 삽입이 간단하지만 단점도 물려받아 작은 데이터를 저장할떄 연결리스트 자체의 오버헤드가 부담된다.   
또 다른 특징으로는, 버킷을 계속해서 사용하는 Open Address 방식에 비해 테이블의 확장을 늦출 수 있다.

* Red-Black Tree : 기본적인 알고리즘은 Separate Chaining 방식과 동일하며 연결 리스트 대신 트리를 사용하는 방식이다. 
연결리스트를 사용할것인가와 트리를 사용할 것인가에 대한 기준은 하나의 해시 버킷에 할당된 key-value 쌍의 개수이다. 데이터의 개수가 적다면 연결리스트를 사용하는 것이 좋다. 
그러나 데이터가 많을 떄는 Red-Black Tree의 삽입,삭제,조회 모두 O(logN) 이기 떄문에 트리를 사용하는 것이 좋다. 그러나, 트리는 기본적으로 메모리 사용량이 많다. 
데이터 개수가 적을때 Worst Case 를 살펴보면 트리와 연결리스트의 성능상 차이가 거의 없다. 따라서 메모리 측면을 봤을때 데이터 개수가 적을 때는 연결리스트를 사용하는 것이 좋다. 

<br><br>

### 1.4 그래서 무엇을 쓸것 인가?
결론적으로 두개의 방식 모두 Worst Case 는 O(M) 이며,  
충돌을 방지하는 방법들은 데이터의 규칙성(클러스터링)을 방지하기 위한 방식이지만 공간을 많이 사용한다는 치명적인 단점이 있다. 

<br>

### 1.4.1 Open Addressing  장단점
* 장점)  
    1. 연속된 공간에 데이터를 저장하기 때문에 Separate Chaining에 비하여 캐시 효율이 높다. 즉, 데이터 개수가 적다면 성능이 좋다.
    
* 단점)   
    1. 배열의 크기가 커질수록(M 값이 커질수록) 캐시 효율이라는 Open Addressing의 장점은 사라진다. 배열의 크기가 커지면, 캐시 적중률(hit ratio)이 낮아진다.
    2. 삭제 연산이 비효율적이다.  
        Open Addressing은 데이터를 삭제할 때 더미 데이터를 넣어준다.  
        그냥 값이 없으면 다음을 찾으면 되지 않느냐 할수도 있지만. 만약 해당 데이터가 없으면 무한반복이 일어나기 떄문에 더미 데이터를 넣어줘야한다.  
        그러나 이러한 더미데이터의해서 많은 버킷을 연속적으로 검색을 해야되기 때문에 더미 데이터가 일정수를 넘었을때 새로운 Array를 만들어서, 다시 리빌딩을 해야하기 때문에 비효율적이다  .

<br> 
 
### 1.4.2 Separate Chaining 장단점
* 장점)
    1. 해시 충돌이 잘 발생하지 않도록 '조정'할 수 있다면 Worst Case 또는 Worst Case에 가까운 일이 발생하는 것을 줄일 수 있다  
    2. 일정 이상 데이터가 쌓이면 Open Addressing 보다 빠르다.
    3. 해시 테이블의 확장이 필요없고 간단하게 구현이 가능하며, 손쉽게 삭제할 수 있다는 장점이 있다
       

* 단점)   
    1. 데이터의 수가 많아지면 동일한 버킷에 chaining되는 데이터가 많아지며 그에 따라 캐시의 효율성이 감소한다는 단점

<br> 

### 1.4.3 결론
JAVA 에서는 HashMap에서 remove() 메서드는 매우 빈번하게 호출되기 떄문에  
Separate Chaining 방식으로 HashMap을 구현했으며, 버킷의 크기에 따라 linkedList / Red-Black Tree 를 선택한다.    
TREEIFY_THRESHOLD / UNTREEIFY_THRESHOLD 의 상수값 각각 8 과 6 에 따라서 해당 자료구조로 변경한다.  
8과 6인경우는 만약 차이가 1이라면 어떤 한 키-값 쌍이 반복되어 삽입/삭제되는 경우 불필요하게 트리와 링크드 리스트를 변경하는 일이 반복되어 성능 저하가 발생할 수 있기 때문이다.


### 1.5 [HashMap.java 소스분석]



<br>

출처  
https://bcho.tistory.com/1072  
https://d2.naver.com/helloworld/831311  
https://velog.io/@cyranocoding/Hash-Hashing-Hash-Table%ED%95%B4%EC%8B%9C-%ED%95%B4%EC%8B%B1-%ED%95%B4%EC%8B%9C%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0%EC%9D%98-%EC%9D%B4%ED%95%B4-6ijyonph6o

[HashMap.java 소스분석]:https://github.com/ryunian/Study/blob/master/java/HashMap/method.md#hashmapjava-%EC%86%8C%EC%8A%A4-%ED%8C%8C%ED%95%B4%EC%B9%98%EA%B8%B0
