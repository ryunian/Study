# 1. Class
> 현실 객체가 갖는 속성 및 기능을 추상화하여 정의하는 것으로 객체를 만들기 위한 설계도 역할을 한다.
> 클래스는 new 키워드를 통해 인스턴스하여 객체가 된다.
> 이러한 객체는 동적 메모리 할당 영역(heap 영역)에 생성되며 GC의 관리 대상이 된다.
> 모든 클래스의 최상위 클래스는 Object 클래스이며 클래스를 선언할떄 생략되어 있는 부분이다.

<br>

##### 1.1 용어
1. 객체 : 객체와 관련있는 변수와 함수를 묶어서 만든 사용자 정의 자료형이다.
2. 필드(멤버변수) : 클래스에서 선언된 변수로 속성이라고도 부른다.
    > 속성을 담당하기 떄문에 변수의 이름은 명사로 하는것이 일반적이다.
3. 메소드 : 클래스에서 정의된 함수로 객체의 기능을 나타낸다. 
    > 기능을 담당하기 때문에 메소드의 이름은 동사로 시작하는것이 일반적이다.

<br>

##### 1.2 클래스 선언 및 인스턴스
```java
class Parent { // extends Object 생략 해도 된다.
    int x;
    
    // 생성자가 1개도 없을시 컴파일과정에서 기본생성자가 생성된다.
    // 바이트코드를 열어보면 추가되어있다.
    // 물론 이 소스에서 Main 클래스의 Main(){} 또한 생성된다.
    // Parent(){}
}
public class Main {
    public static void main(String[] args) {
        Parent parent = new Parent();
        // 이러한 메소드는 정의 하지 않았지만 모든 클래스의 최상위 클래스는
        // Object 이므로 Object에서 toString을 정의하고 있기 떄문에 사용이 가능하다.
        parent.toString(); 
    }
}
```
<br><br>
### 2. 접근제어자
<img src="https://github.com/ryunian/Study/blob/master/image/AccessModifier.png?raw=true" width="800" height="300">

<br><br>
### 3. 상속
> 상속이란 상위클래스에서 정의한 필드와 메서드를 하위클래스도 동일하게 사용할 수 있게 물려받는 것이다.    

<br>

##### 3.1 장점
>   * 유지보수가 쉽다
>   * 중복이 적어진다
>   * 확장성이 용이해진다
>   * 모듈화를 통해 재사용이 가능해진다

<br>

##### 3.2 특징
* 자바는 다중 상속을 지원하지 않는다.  
* 상속의 횟수에 제한이 없다.
* 자바의 최상위 부모 클래스는 java.lang.Object 이다

<br>

##### 3.3 다중 상속의 문제점
> * 상속받은 복수의 부모 클래스에 중복되는 필드나 메서드가 존재할수 있따.
> * 같은 클래스를 두 번이상 상속받을수 있다.
> * 부모클래스에 접근할 방법이 애매해진다.


```java
class Parent { 
    int x = 0;
    Parent() {
        System.out.println("make parent");
    }
    public int getX() {return x;}
}

class Child extends Parent {
    int x = 1;
    Child() {
        System.out.println("make child");
    }
    public int getX() {return this.x + 1;}
}

public class Main {
    public static void main(String[] args) {
        Parent test = new Child(); // 생성순서 : Object - Parent - Child
        System.out.println(test.x); // Parent 에서 선언한 x의 값 0이 출력된다
        System.out.println(test.getX()); // Child 가 정의한 getX가 실행된다.

        // 하위 타입으로 변경하므로 묵시적 캐스팅해야한다.
        // 단, 메모리에 그 타입이 있는지 체크해야한다. (instanceOf) 
        Child test2 = (Child) test;
        System.out.println(test2.x); // Child 에서 선언한 x의 값 1이 출력된다.  
    }
}

```
<br><br>
### 4. 오버라이딩 (overriding)
> 1. 상위 클래스 혹은 인터페이스에 존재하는 메소드를 하위 클래스에서 필요에 맞게 재정의하는 것을 의미한다.  
> 2. 자바의 경우는 오버라이딩 시 동적바인딩된다.  
    * 동적 바인딩(Dynamic Binding)이란 실행할 메서드를 컴파일 시간에 결정하지 않고 실행 시간에 결정하는 것을 말한다.  
> 3. 메소드의 이름, 인자 타입, 개수, 리턴타입등이 모두 같아야 함.  
> 4. 동적바인딩 되기 떄문에 메모리에 부모타입에 자식클래스 또한 할당되어 있을경우 자식클래스에서 재정의한 메소드가 실행된다.  (다이나믹 메소드 디스패치 (Dynamic Method Dispatch))
> 5. 접근제어자는 부모보다 축소될수 없다.  
> 6. 예외 발생은 부모보다 넓어질수 없다.  

<br><br>
### 5. 오버로딩 (overloading)  
> 1. 메소드의 이름과 return 타입은 동일하지만, 매개변수만 다른 메소드를 만드는 것을 의미한다.    
> 2. 자바의 경우 정적으로 바인딩된다.  
> 3. 메소드의 이름은 반드시 같지만, 인자의 개수나 타입은 달라야 된다.  
  
### this
### super