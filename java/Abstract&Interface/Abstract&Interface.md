# 1. 추상클래스 (abstract class)
> 클래스를 설계도에 비유한다면, 추상클래스는 미완성 설계도에 비유할 수 있다.
> 클래스가 미완성이라는 것은, 미완성 메서드(추상메서드)를 포함하고 있다는 의미이다.

##### 추상
> 낱낱의 구체적 표상이나 개념에서 공통된 성질을 뽑아 이를 일반적인 개념으로 파악하는 정신 작용

##### 추상화
> 클래스간의 공통점을 찾아내서 공통의 조상을 만드는 작업

##### 구체화 
> 상속을 통해 클래스를 구현, 확장하는 작업 

* 추상클래스로는 인스턴스는 생성할 수 없다.
* 추상클래스는 상속을 통해서 자손클래스에 의해서만 완성될 수 있다.
* 추상클래스는 추상메서드를 가지고 있는 것일뿐 일반 클래스와 똑같이 생성자,멤버변수,메서드도 가질 수 있다.

<br><br><br><br>

### 1.2 추상메서드
> 선언부만 작성하고 구현부는 작성하지 않은 채로 남겨 둔 것이 추상메서드이다.

* 메서드의 내용이 상속받는 클래스에 따라 달라질 수 있기 때문에 조상 클래스에서는 선언부만 작성한다.

* 추상메서드에는 abstract라는 키워드를 붙여주고 추상메서드는 구현부가 없으므로 {} 대신 ;를 붙여준다. 

```java
package AbstractAndInterface;

abstract class Car {
    int price;
    String kind;

    public Car(int price, String kind) {
        this.price = price;
        this.kind = kind;
    }

    abstract void drive();
    abstract void stop();
}

class K5 extends Car {
    public K5() {
        super(2500, "sedan");
    }

    @Override
    public void stop() {
        System.out.println("멈췄습니다");
    }

    @Override
    public void drive() {
        System.out.println("");
    }
}

public class AbstractTest {
    public static void main(String[] args) {
        Car car = new K5();
    }
}
```


<br><br><br><br>

# 2. 인터페이스 (interface)
> 일종의 추상클래스이며, 인터페이스는 추상클래스처럼 추상메서드를 갖지만 추상클래스보다 추상화 정도가 높아서  
> 추상클래스와 달리 몸톰을 갖춘 일반 메서드 또는 멤버변수를 구성원으로 가질수 없다.

> 추상클래스를 '미완성 설계도'라고 한다면, 인터페이스는 구현된것은 아무것도 없고 밑그림만 그려져 있는 '기본 설계도'라 할수 있다.  
> 그러므로, 인터페이스 그자체로 사용되기 보다는 다른 클래스를 작성하는데 도움 줄 목적으로 작성된다.

제약사항
* 모든 멤버변수는 public static final 이어야하며, 이를 생략할수 있따.
* 모든 메서드는 public abstract 이어야 하며, 이를 생략할 수 있따.
* 단, static 메서드와 default 메서드는 예외 (java 8~)


```java
// List 인터페이스 내부의 default 메서드
default void replaceAll(UnaryOperator<E> operator) {
    Objects.requireNonNull(operator);
    final ListIterator<E> li = this.listIterator();
    while (li.hasNext()) {
        li.set(operator.apply(li.next()));
    }
}
// List 인터페이스 내부의 static 메서드
static <E> List<E> of() {
    return (List<E>) ImmutableCollections.EMPTY_LIST;
}
```

```java
package AbstractAndInterface;

class Tv {
    protected boolean power;
    protected int channel;

    public void power() {power = !power;}
    public void channelUp() {channel++;}
    public void channelDown() {channel--;}
}

class VCR {
    public void play() {System.out.println("PLAY");}
    public void stop() {System.out.println("STOP");}
}

interface IVCR {
    public abstract void stop();

    // pulic abstract가 생략되어있다.
    void play();
}

class TVCR extends Tv implements IVCR {
    VCR vcr = new VCR();

    @Override
    public void stop() {vcr.stop();}
    @Override
    public void play() {vcr.play();}
}

public class InterfaceTest {
    public static void main(String[] args) {
        TVCR tvcr = new TVCR();
        tvcr.play();
        tvcr.stop();
    }
}
```

<br><br>

### 2.1 인터페이스의 장점
1. 개발시간을 단축시킬 수 있따.
> 메서드를 호출하는 쪽에서는 메서드의 내용에 관계없이 선언부만 알면 된다.
> 인터페이스를 구현하는 클래스가 작성될 때까지 기다리지 않고도 양쪽에서 동시에 개발을 진행할수 있다.


2. 표준화가 가능하다.
> 기본틀을 인터페이스로 작성한 다음, 개발자에게 인터페이스를 구현하여 프로그램을 작성하도록 함으로써  
> 일관되고 정형화된 프로그램의 개발이 가능하다.

3. 서로 관계없는 클래스들에게 관계를 맺어줄수 있다.
> 서로 상속관계에 있지도 않고, 같은 조상클래스를 가지고 있지 않는 관계에   
> 인터페이스를 공통적으로 구현하도록 함으로써, 관계를 맺어줄수 있다.

4. 독립적인 프로그래밍이 가능하다.
> 즉, 클래스간 결합도를 낮출 수 있다. 코드의 종속성은 줄이고 유지보수성을 높이도록 해준다.

<br><br>

### 2.2 디폴트 메서드
> 디폴트 메서드는 추상 메서드의 기본적인 구현을 제공하는 메서드로,  
> 추상 메서드가 아니기 떄문에 디폴트 메서드가 새로 추가되어도 해당 인터페이스를 구현한 클래스를 변경하지 않아도 된다.  
> Java8에서 인터페이스에서 디폴트 메서드를 허용하는 이유는 기존 인터페이스를 확장해서 새로운 기능을 추가하기 위해서이다.


디폴트 메서드가 기존의 메서드와 이름이 중복되어 충동하는 경우
* 여러 인터페이스의 디폴트 메서드 간의 충돌
    - 인터페이스를 구현한 클래스에서 디폴트 메서드를 오버라이딩 해야한다.
    
* 디폴트 메서드와 조상 클래스의 메서드 간의 충돌
    - 조상 클래스의 메서드가 상속되고, 디폴트 메서드는 무시된다.
    
<br><br>
    
### 2.3 private 메소드
> Java 9버전에 추가되었다.
> 공통되는 중복 코드를 인터페이스의 private 메소드에 작성함으로써 상속받은 구현 클래스가 보지 못하도록 제한이 가능하다.

특징
* 메소드 구현부가 존재
* static, non-static 모두 가능
* private이기 떄문에 당연히 override가 불가능하며 오직 선언해둔 인터페이스 내부에서만 사용가능

장점
* 중복 코드 감소, 코드 재사용성 증가
* 자식 클래스가 필요로 하는 메소드만 구현 가능

<br><br><br><br>
    
# 3. 추상화 클래스와 인터페이스
##### 사용하는 이유
* 설계시 선언해 두면 개발할 떄 기능을 구현하는 데에만 집중할수 있다.
* 개발자의 역량에 따른 메소드의 이름과 매개 변수 선언의 격차를 줄일 수 있다.
* 공통적인 인터페이스와 abstract 클래스를 선언해놓으면, 선언과 구현을 구분할 수 있다.

<br><br>

##### 차이점
* 추상화 클래스는 단일 상속(extends) 이지만 인터페이스는 다중 구현(implements) 이 가능하다.

* 추상화 클래스는 일반메서드 + 추상화메서드(0개 이상)으로 이루어져 있지만, 인터페이스는 모든 메서드가 추상화 메서드다.  
(단, 자바8버전부터 static 메서드와 default 메서드가 사용이 가능해져서 이러한 차이점은 없는 거로 본다) 

* 추상화 클래스는 상태를 가질수 있지만 인터페이스는 상수만 가질수 있으므로 상태를 나타낼 수 없다.

* 상속은 슈퍼클래스의 기능을 이용하거나 확장하기 위해서 사용되고, 다중 상속의 모호성 때문에 하나만 상속받을수 있다.  
인터페이스는 해당 인터페이스를 구현한 객체들에 대해서 동일한 동작을 약속하기 위해 존재한다.