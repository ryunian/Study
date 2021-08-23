# String 
> JAVA에서 문자열을 표현하는 것은 String이다.  
> String 은 java.lang.String에 위치하는 클래스이며 final과 Immutable 의 특징을 가진다.  
> String은 특별히 heap 영역에 String constant pool이라는 곳이 있으며, 리터컬값이 저장된다.  
> 이에 반해 C같은 경우는 변수형 문자열과 상수형 문자열이 나뉜다.  

<br>

### String은 왜 final 혹은 Immutable 인가?
* String pool
    * JAVA 디자이너는 JAVA 어플리케이션에서 가장 많이 사용되는 데이터 타입이 String이 될것이라고 예측했다.  
    * 그리고 그것이 최적화가 필요한 이유라는 것을 알았다. 최적화 방법 첫째로, String pool에 String 리터럴을 포함하는 것이다. 목표는 String 객체를 공유해, 템프러리하게 생성된 String 객체를 줄여주는 것이었다. 공유를 하기 위해선 String 클래스는 Immutable class 이어야 한다.    
    * String을 Immutable하게 하는것으로, String 리터럴의 공유가 가능하게 된다. 즉, String pool의 주요한 아이디어는 String을 final 또는 Immutable하게 해야만 Java에서 String pool을 구현할 수 있다.    



* Security  
    * String은 다수의 Java 클래스에 매개 변수로 널리 사용되고 있다.  
        ex1) 네트워크 연결 시, 호스트 및 포트  
        ex2) 데이터베이스 연결에 필요한 URL  
        ex3) 파일을 읽어들이기 위한 파일이나 디렉토리 경로  
    * 만약 String이 Immutable 하지 않다면, 사용자는 시스템의 특정 파일에 대한 액세스 권한을 얻은 후 PATH의 변경이 가능하게 되며, 이것은 심각한 보안 문제를 일으킨다.   
    또한 mutable String을 인수로 취하는 리플렉션에서도 마찬가지로 보안 문제를 일으킨다.  

* Use of String in Class Loading Mechanism  
    * String을 final이나 Immutable해야하는 다른 이유는 class loading mechnism에서 자주 사용되기 떄문이라는 점도 있다.   
    * String이 Immutable하지 않다고 하면 공격자는 이점을 이용하게 되며, java.io.Reader 등 Java 표준 클래스의 로드 요청시에 악성 com.unknown.DataStolenReader 클래스로 변경하게 할 수 있다.   
    * String이 final이나 Immutable함으로써 적어도 JVM은 올바른 클래스들을 로드할 수 있게 된다.  

* Multithreading Benefits  
    * Concurrency나 멀티 스레드는 Java의 핵심 기능이므로 String 객체의 스레드 안전성을 고려할 때 당연한 이유가 된다.   
    * String 널리 쓰이게 될 것으로 예측 되었기 때문에, Immutable함으로써 외부에서 동기화 필요를 없애고 여러 스레드 간에 String을 공유하는 부분에서 코드를 깨끗하게 정리해 준다.   
    * 이 기능은 복잡하고 오류가 발생하기 쉬운 concurrency 코드를 쉽고 간단하게 해준다. String은 concurrency하기 때문에 스레드간에 공유가 가능하고, 결과적으로 읽기 쉬운 코드가 된다.  
    * 그렇다면 String str = "java"; str = str + 1; 같은 코드는 어떻게 될까?  
        > String은 불변객체이기 떄문에 변경되지 않고 새롭게 메모리에 java1 이라는 객체가 생성됩니다.  
         그러나 str은 메모리에 있는 String의 주소를 참조를 하고 있기 때문에 이 값만 변경됩니다.  
         즉, 이 주소값은 스레드로 부터 안전하지 않기 때문에 String과는 상관없이 스레드에 안전하지 않습니다  
         이를 해결하기 위해선 모니터, 잠금, 세마포어, 동기화 키워드 등을 이용해야합니다.    



* Optimization and Performance  
클래스를 Immutable한 경우의 장점은 클래스가 일단 생성되면 변경이 불가능하다는 점이다. 
이 점은 캐시 등 많은 성능 최적화의 길을 열수 있다 String은 자신이 변경되지 않는다는 것을 알고 있기 때문에 String 해시 코드를 캐시한다.
해시코드의 계산은 Lazy하게 수행되고 일단 생성되면 캐시된다. 
따라서 String 은 Map기반으로 해시가 자주 사용하는 경우에는 성능이 향상된다. 
해시코드 캐시는 String이 자신의 내용에 의존하기 때문에 Immutable이나 final 없이는 불가능 하다.

<br>

### 장점)
1. 오브젝트의 상태가 변경되지 않기 때문에 해시기반 컬렉션내에 저장될떄, 상태 변경의 위험이 없다.
2. String은 Immutable하므로 외부에서 동기화를 고려하지 않고 스레드간 안전하게 공유할수 있따.
3. 2번의 이유로 인해 코드의 가독성이 높아진다.

<br>

### 단점)
1. Immutable하다는 특성때문에 객체를 많이 생성하게 되면 GC에 영향을 미친다 (= 비용 소모가 크다)
	* 이 점을 보안하기 위한 String pool에 리터럴 값을 저장하고 사용하기 위해 new String보단 리터럴값을 사용하는게 좋다.
2. 자바 6이하의 버전은 PermGen 영역에 String Poll을 두었기 때문에 OutOfmemoryError문제가 있었다.
	하지만 자바7이상에서 부터 String pool을 힙영역에 두었기 때문에 이러한 문제는 해결했다.
3. String은 final class이기 때문에 상속을 못하는 단점이 있다.

<br>

### intern 메소드
* String은 String constant pool 에 리터럴값이 저장된다고 했다. 
* 그렇다면 어떻게 저장될까? String 클래스는 내부적으로 String constant pool에 리터럴값이 조회,저장을 하기 위한 C언어로 짜여진 native intern이라는 메소드가 있다.
* 리터럴을 이용할떄, String은 내부적으로 intern 메서드가 어떻게 동작하는지 알아보자
	1. intern을 위한 JVM의 네이티브 함수를 호출한다.
	2. StringTable 클래스에서 이후의 intern 과정을 처리한다.
	3. StringTable에서는 shared_table과 the_table을 정적 필드로 관리한다.
	4. shared_table에서 문자열을 검색하여 찾으면 반환한다.
	5. shared_table에서 찾지 못한 경우 the_table에서 검색한다.
	6. the_table에서도 찾지 못하면 문자열을 the_table에 추가하고 이를 반환한다.

* 만약 리터럴을 사용하지 않고 new String()으로 생성하게 되면 String constant pool에 값이 있다고 해도 heap영역에 객체가 생성된다.
* 그렇기 때문에 리터럴로 생성된 두 String의 == 비교는 true가 나오고 new String()으로 생성된 String은 ==비교를 통해 false값이 나오게된다 (객체의 논리연산은 객체의 주소를 비교한다.)

```java
public class Main {
    public static void main(String[] args) {
        String x = "JAVA";
        String y = "JAVA";
        String z = new String("JAVA");

        // true, String pool에 "JAVA"라는 문자열이 저장되어 있고 x와 y는 String Pool에 있는 문자열을 가르킨다.
        System.out.println(x == y);
        // false, 그에 반해 z는 new 키워드를 통해 만들어졌기 때문에, 힙영역에 새롭게 만들어 졌다.
        System.out.println(x == z); // false
        // 그렇기 때문에 String의 비교는 equals를 통해 해야한다.
        System.out.println(x.equals(z)); // true
    }
}
```

<br>

### String의 + 연산
> String은 String객체를 조작하기 위해 '+' 연산자가 오버로딩 되었기 때문에 사용이 가능하다.   
> 그렇다면 '+'연산자를 통해 연산을 할 경우 내부적으로 어떻게 진행될까?  
> String은 기본적으로 불변 객체기 때문에 a + b + c 연산을 할때 ab 라는 객체가 생성되고 abc 라는 객체가 생성되는 순서로 진행된다.  
> 그렇기때문에 반복문을 통해 String에 대해 + 연산을 한다는 것은 엄청나게 자원을 소모하는 것이므로 피해야 한다.  

* StringBuilder 와 StringBuffer
> 그렇다면 + 연산을 해야한다면 어떻게 해야할까?   
> 바로 StringBuilder와 StringBuffer 클래스를 사용한다 이 클래스는 String과 다르게 mutable 한 특징을 가지고 있다.  
> 그렇기 때문에 append()라는 메서드를 통해 해당 문자열을 객체 생성없이 붙여줄수 있다.  
> 아래 소스를 보자면 String 과 StringBuilder 를 가볍게 100번정도 돌려봣다. 시간을 보자면 거의 50배 정도 차이가 난다는 점을 볼수 있다.
> StringBuffer 와 StringBuilder의 차이점은 멀티스레드 상황에서 thread-safe 하다는 점이다. 그렇기 때문에 동기화에 대한 필요 유무에 따라 나눠서 사용하면 된다.
```java
public class Operator {
    public static void main(String[] args) {
        int loop = 100;
        double start, end;
        String tmp = "";
        StringBuilder sb = new StringBuilder();


        // Test
        // StringBuilder = 8600.0
        start = System.nanoTime();
        for (int i = 0; i < loop; i++) {
            sb.append(s);
        }
        end = System.nanoTime();
        System.out.println("StringBuilder = " + (end - start));

        // String = 459700.0
        start = System.nanoTime();
        for (int i = 0; i < loop; i++) {
            tmp += s;
        }
        end = System.nanoTime();
        System.out.println("String = " + (end - start));
    }
}
```
 
<br>

### 사실... + 연산도 StringBuilder 로 된다
> 사실 위에서 예시를 든 a + b + c 같은 경우 내부적으로 Stringbuilder로 변환해서 new Stringbuilder().append(a).append(b).append(c)같은 형식으로 바뀐다. 
> 물론 반복문을 통해 연산을 할 경우는 위에 든 예시같이 진행되며, String str = a + b + c ; 같이 나열되어 있을떄만 내부적으로 바뀐다.
> 아래 소스는 예시를 위해 javap 명령어를 통해 바이트코드를 실행시켜 보았다.
```java
public class Operator {
    public static void main(String[] args) {
        // 명령어
        // javap -v -p -s -sysinfo -constants Operator.class
        String s = "str";
        String str = s + s + s + s + s + s + s + s + s + s
                + s + s + s + s + s + s + s + s + s + s
                + s + s + s + s + s + s + s + s + s + s
                + s + s + s + s + s + s + s + s + s + s
                + s + s + s + s + s + s + s + s + s + s
                + s + s + s + s + s + s + s + s + s + s;
    }
}
```
<img src="https://github.com/ryunian/Study/blob/master/image/javap8.png?raw=true" width="800" height="500">
<img src="https://github.com/ryunian/Study/blob/master/image/javap14.png?raw=true" width="800" height="500">

* 첫번쨰 사진을 보자면 정상적으로 내부적으로 StringBuilder 로 변환되었다.
* 그러나 두번째 사진은 StringBuilder 로 변환되지 않고 일일히 String 객체가 생성되서 합쳐졌다.
* 이유가 뭘까? 정확한 이유는 잘 모르겠다.... JVM에 따라 동작이 달라보인다 첫번쨰 사진은 Zulu 1.8 버전이며 두번째 사진은 Java 14버전이다.

* 결론   
    JAVA의 String 객체는 소모 비용이 크다. 그렇기 때문에 + 연산을 할떄 주의를 해줘야 하며,   
    StringBuilder 를 사용하거나 만약에 사용한다고 하더라도 JDK버전에 따라 StringBuilder로 변환되는지 체크하고 사용해야 할 것 같다.   
    물론 반복문을 통해서 문자열을 조작할땐 StringBuilder/StringBuffer 를 사용해야 한다.










출처  
https://www.mimul.com/blog/why-string-class-has-made-immutable-or-final-java/  
https://pjh3749.tistory.com/255  
https://www.latera.kr/blog/2019-02-09-java-string-intern/  
https://blog.ggaman.com/918  