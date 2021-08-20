# Hashmap.java 소스 파해치기

<br>

### Operations 
* int size() 
    > map 크기 반환
* boolean isEmpty() 
    > Map이 비어있는지 반환
* V get(Object key) 
    > getNode()를 통해 노드를 찾아서 노드 반환
* boolean containsKey(Object key) 
    > getNode()를 통해 값이 존재하는지 반환
* V put(K key, V value) 
    > putVal()을 호출하여 값을 저장
* void putAll(Map<? extends K, ? extends V> m) 
    > putMapEntries()를 통해 값들을 저장
* V remove(Object key) 
    > removeNode()를 통해 key일치하면 Node를 삭제하고 삭제된 값 반환
* boolean remove(Object key, Object value) 
    > removeNode() 통해 키와 값이 일치한 Node를 삭제하고 삭제한 여부에 따라 true/false 반환
* void clear() 
    > table 배열을 순회하며 전부 null으로 초기화한다.
* containsValue(Object value) 
    > 해당 값이 있는지 검색한다, 값은 해쉬코드를 통해 저장하지 않기때문에 전체를 탐색해야한다 시간복잡도 O(n)
* Set<K> keySet() 
    > key에 관한 Set을 반환한다. keySet은 부모인 AbstractMap에서 정의했기 때문에 존재한다.
* Set<Map.Entry<K,V>> entrySet() 
    > 캐시된 entrySet을 반환한다.
* Collection<V> values() 
    > 값 들을 Collection타입으로 반환한다.
* V getOrDefault(Object key, V defaultValue) 
    > getNode()를 통해 Node를 찾고 Node가 null이 아니면 Node를 반환하고 null이면 defaultValue를 반환한다.
* V putIfAbsent(K key, V value) 
    > putVal을 통해 값이 존재하지 않을때만 저장하고 그 값을 반환
* boolean replace(K key, V oldValue, V newValue) 
    > getNode()를 통해 값을 찾아서 기존값이 oldValue와 같으면 newValue로 수정한뒤 성공여부 반환
* V replace(K key, V value) 
    > getNode()를 통해 값을 찾아서 기존 값을 Value로 수정한뒤 기존 값을 반환
* V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
    > 지정된 Key의 값이 없을 경우 Function의 리턴 값을 Value로 설정하고 추가, 저장된 Value를 반환
* V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
    > 지정된 Key의 값이 있을 경우 Function의 리턴 값을 Value로 변경, 저장된 Value를 반환
* V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
    > 지정된 Key의 값에 따라 Value를 어떻게 연산할지 정의, Value(리턴 값)을 반환
* V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
    > 지정된 Key의 값이 없으면 key-value값을 추가, key의 값이 있으면 기존 값과 매개변수로 받은 값을 BiFunction 연산한뒤 저장된 값을 반환
* forEach(BiConsumer<? super K, ? super V> action)
    > 전체 Node를 BiConsumer가 정의한 연산 실행
* replaceAll(BiFunction<? super K, ? super V, ? extends V> function)
    > 모든 Key에 대해 BiFunction 실행
* Object clone()
    > 현재 Map을 얕은 복사한 객체를 반환

### 상수
* int DEFAULT_INITIAL_CAPACITY 
    > 기본 크기
* int MAXIMUM_CAPACITY 
    > 최대 크기 2^30 , 2^31은 int값을 벗어남으로 30으로 해야한다.
* float DEFAULT_LOAD_FACTOR 
    > 기본 loadFactor 값
* int TREEIFY_THRESHOLD 
    > Tree로 만드는 경계값
* int UNTREEIFY_THRESHOLD 
    > List로 만드는 경계값
* int MIN_TREEIFY_CAPACITY 
    > 최소 테이블 용량

<br>

### Fields
* Node<K,V>[] table; 
    > 키-값을 저장하는 배열
* Set<Map.Entry<K,V>> entrySet; 
    `> 캐시된 entrySet 저장, AbstractMap 필드에 사용
* int size; 
    > 키-값이 저장된 수
* int modCount; 
    > 구조적으로 수정된 횟수
* int threshold; 
    > 크기를 조정할 다음 크기 값(size * loadFactor).
* float loadFactor 
    > Hash table 전체에서 얼마나 원소가 차 있는지를 나타내는 수치
                          
<br>

### static
* class Node<K,V> implements Map.Entry<K,V>   
    > Tree,List에 저장되는 자료형  
* int hash(Object key)  
    > 보조 해시 함수, 상위 16비트 값을 XOR 연산한다.  
     개념상 해시 버킷 인덱스를 계산할 때에는 index = X.hashCode % M 처럼 나머지 연산을 사용하는 것이 맞지만,   
     M값이 2^a 일때는 해시 함수의 하위 a비트 만을 취한것과 값이 같다.  
     따라서 나머지 연산대신 '1<<a-1' (2^a-1) 와 비트 논리곱(AND) 연산을 사용하면 수행이 훨씬 빠르다.
* Class<?> comparableClassFor(Object x) 
    > 대상 객체가 Comparable 을 구현했는지 체크한다.
* int compareComparables(Class<?> kc, Object k, Object x) 
    > compare 메소드
* int tableSizeFor(int cap) 
    > 새로운 테이블 크기를 계산하는 메소드

<br>

### etc
* void putMapEntries(Map<? extends K, ? extends V> m, boolean evict)
    > Map.put All 및 Map 생성자들이 호출해서 Map에 값을 삽입하는 메소드
* Node<K,V> getNode(int hash, Object key)
    > hash값을 계산하여 table배열의 인덱스를 찾고 배열 내부에서 List 및 Tree에 따라 탐색을 하여 Node를 반환한다.
* V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict)
    > map에 값을 넣는 메소드, 모든 put/compute 관련 메소드는 이 메소드를 호출한다.  
     onlyIfAbsent 가 true일 경우 기존 값을 변경하지 않는 모드   
     evict 가 true 일 경우 새롭게 노드가 생성되는 모드
* Node<K,V> removeNode(int hash, Object key, Object value,boolean matchValue, boolean movable)
    > 모든 remove 호출은 해당 메소드를 호출하며, List인지 Tree인지에 따라 해당 노드를 삭제한다.  
* Node<K,V>[] resize()
    > 테이블 크기를 초기화하거나 2배로 늘린다.
* void treeifyBin(Node<K,V>[] tab, int hash)
    > 상수값에 조정된 수치에 따라 호출되며, linkedList 구현된것을 Red-Black Tree 로 만든다.
* prepareArray(T[] a)  
    > Array 형태로 바꾼것을 반환한다.
* keysToArray(T[] a)
    > 모든 key들을 Array 형태로 바꾼것을 반환한다.
* valuesToArray(T[] a)
    > 모든 key들의 값들을 Array 형태로 바꾼 것을 반환한다.

### Tree bins
* class TreeNode<K,V> extends LinkedHashMap.Entry<K,V>  
    > Red-Black 트리를 만드는 클래스, Red Black 구성 규칙에 따라 균형을 유지하기 위한 메소드 등이 있다.  
    LinkedHashMap.Entry는 HashMap.Node를 상속한 클래스다.  
    따라서 TreeNode 객체를 table 배열에 저장할 수 있다.  