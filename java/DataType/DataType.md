# JAVA의 데이터 타입
<img src="https://github.com/ryunian/Study/blob/master/image/DataTypes.png?raw=true" width="900" height="600">

# 1. Primitive Type
<img src="https://github.com/ryunian/Study/blob/master/image/java_primitive_type.png?raw=true" width="600" height="300">

 > boolean은 실제론 사이즈가 정확히 정의되지 않았습니다 JVM에 따라 다릅니다.

# 1.1 형변환
<img src="https://github.com/ryunian/Study/blob/master/image/java_type_cast.jpg?raw=true" width="600" hegith="300">


``` java
public class Cast{
  public static void main(String[] args){
    int a = 100;
    double b = a;     // b = 100.0
        
    double c = 10.5;
    int d = (int) c;  // d = 10
  }
}
```
> * 작은 데이터범위에서 큰 데이터범위로 캐스팅할땐 묵시적캐스팅이 일어나며 데이터 손실이 없다.   
> * 그러나 큰데이터 범위에서 작은 데이터범위로 캐스팅할땐 명시적캐스팅을 해야 하며 데이터손실이 일어난다. (코드에서 c 의 0.5값이 d 에서 손실되었다)

# 2. Non-Primitive Type (Reference type)
* 기본형 타입을 제외한 타입들이 모두 참조형 타입이다.
* 빈 객체를 의미하는 Null이 존재한다. 그리고 기본값이 Null이다.
* 값이 저장되어 있는 곳의 주소값을 저장하는 공간으로 힙 (Heap) 메모리에 저장됩니다.
* 메모리상에 할당되는 각각의 공간에 실제 값이 들어가면 Primitive 타입이고, 다른 것을 참조하기 위한 주소값이 들어가면 Reference Type 이다.
* 클래스 타입, 인터페이스 타입, 배열 타입, 열거 타입이 있습니다.

# 2.1 배열의 선언
``` java
public class Array{
  public static void main(String[] args){
    int size = 10;
    int[][] arr1 = new int[size][size]; // 1
    int[] arr2[] = new int[size][size]; // 2
    int arr3[][] = new int[size][size]; // 3
    
    // 가급적 1번의 방법을 사용하도록 하자    
  }
}
```

# 타입추론 Var 란?
> * Java 10 부터 지원한 기능이다   
> * 개발자가 변수의 타입을 명시적으로 적어주지 않고도, 컴파일러가 알아서 이 변수의 타입을 대입된 리터럴로 추론하는 것이다.
> * 아래의 예시를 보면 instanceof 를 통해 객체타입 확인할시 str 은 String 클래스에 포함된다고 알수 있다.

``` java
public class Array{
  public static void main(String[] args){
    var str = "Heloo";
    System.out.println(str instanceof String); // true
    int var = 1; // 가능
  }
}
```

#### 주의할 점
* 초기화 값이 있는 "지역변수"로만 선어이 가능하다
* var 는 키워드가 아니다. 위 코드를 확인해보면 변수명으로 var가 가능하다 (키워드는 변수명으로 사용이 불가능하다)
* var 는 런타임 오버헤드가 없다. 
  > * 컴파일 시점에 var를 초기화된 값을 보고 추론에서 바이트코드에 명시적으로 int는 int이며 String은 String이다.  
  > * 타입추론 변수를 읽을때마다 타입을 알아내기위한 연산을 하지않는다. 즉, JS 같은 언어와 같이 중간에 다른 타입의 값으로 바꿀수 없다.  
* 배열을 선언할 떄, var 대신 타입을 명시해줘야 한다.
* 람다식에는 명시적인 타입을 지정해줘야 한다.


