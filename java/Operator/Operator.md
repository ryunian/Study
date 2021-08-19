# 연산자

<img src="https://github.com/ryunian/Study/blob/master/image/operator.png?raw=true" width="700" height="500">

<br><br>
``` java
public class Operator {
    public static void main(String[] args) {
        // int 는 8바이트 이므로 32bit 로 나타낸다.
        int x = 10; // 00000000 00000000 00000000 00001010
        int y = 3;  // 00000000 00000000 00000000 00000011
        int z = -1; // 11111111 11111111 11111111 11111111

        // 산술연산자 ( + - * / % )
        System.out.println(x + y);  // 13
        System.out.println(x - y);  // 7
        System.out.println(x * y);  // 30
        System.out.println(x / y);  // 3
        System.out.println(x % y);  // 1

        // 비트연산 (NOT, AND, OR, XOR)
        System.out.println(~x);     // -11
        System.out.println(x & y);  // 2
        System.out.println(x | y);  // 11
        System.out.println(x ^ y);  // 9

        // 관계연산 ( == != <= >=  > <)
        System.out.println(x == y); // false
        System.out.println(x != y); // true
        System.out.println(x <= y); // false
        System.out.println(x >= y); // true
        System.out.println(x > y);  // true
        System.out.println(x < y);  // false
    }
}
```
<br><br>
### instanceof
> 참조변수가 참조하고 있는 인스턴스의 실제 타입을 알아보기 위해 instanceof 연산자를 사용합니다. (주로 캐스팅하기 위해 확인한다)  
> 주로 조건문에 사용되며, instanceof의 왼쪽에는 참조변수를 오른쪽에는 타입(클래스명)이 피연산자로 위치합니다.    
> 그리고 연산의 결과로 boolean값인 true, false 중의 하나를 반환 합니다.   
> 해당 객체의 클래스를 알아보기 위해서는 getClass()를 사용한다.  

```java
public class Main {
    public static void main(String[] args) {
        // instatceof
        // 객체가 특정 클래스 / 인터페이스 유형인지 여부를 확인합니다.
        // 객체 타입을 확인하는데 사용하는 연산자.
        Parent parent = new Parent();
        Parent oldChild = new Child();
        Child youngChild = new Child();
        
        if (parent instanceof Child) { // false
            // instanceof 연산자를 통해
            // ClassCastException 예외가 나오는것을 방지해준다.
            parent = (Child) parent;
        }
        if (oldChild instanceof Child) {  // true
            oldChild = (Child) oldChild;
        }
        System.out.println(youngChild instanceof Parent); // true
    }
}

class Parent {}

class Child extends Parent {}
```
<br><br>
### 3항 연산자
> 조건식 ? 반환값1(true) : 반환값2(false)  
* 코드를 짧게 쓸수 있다는 장점이 있습니다.
* 무분별하게 사용하면 가독성을 해칠수도 있으며
* if문에 비해 성능적으로 더 우수한건 아닙니다.
