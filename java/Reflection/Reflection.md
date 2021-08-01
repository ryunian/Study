# Reflection (리플렉션) 이란?
> 이미 로딩이 완료된 클래스에서 또 다른 클래스를 동적으로 로딩(Dynamic Loading)하여 생성자(Constructor), 멤버 필드(Member Variables) 그리고 멤버 메서드(Member Method) 등을 사용할 수 있도록 합니다.
> 즉, 컴파일 시간(Compile Time)이 아니라 실행 시간(Run Time)에 동적으로 특정 클래스의 정보를 객체화를 통해 분석 및 추출해낼 수 있는 프로그래밍 기법이라고 표현할 수 있습니다.
> 자바의 리플렉션(Reflection)은 클래스, 인터페이스, 메소드들을 찾을 수 있고, 객체를 생성하거나 변수를 변경할 수 있고 메소드를 호출할 수도 있습니다.    
```java
public class Test {
    public static void main(String[] args) {
        Class c = Members.class;

        // Members 의 메서드
        Method[] m = c.getMethods();

        // Members 의 필드
        Field[] f = c.getFields();

        // Members 의 생성자
        Constructor[] cs = c.getConstructors();

        // Members 의 인터페이스
        Class[] inter = c.getInterfaces();

        // Members 의 수퍼클래스
        Class superClass = c.getSuperclass();
    }
}
```   
### 사용처
* Java Serialization
> 객체를 직렬화(Serialization) 해야 할 경우 Serializable 인터페이스를 구현한다.    
> 그리고, 직렬화된 객체를 읽기 위해서는 java.io.ObjectInputStream 클래스의 readObject() 메서드를 이용한다.    
> 필요에 따라 Serializable 인터페이스를 구현한 클래스가 readObject() 메서드를 구현할 수도 있다.    
> 이때, java.io.ObjectInputStream 클래스의 readObject() 메서드는 내부적으로 Reflection을 이용하여 직렬화된 객체의 readObject() 메서드를 호출한다.
* Apache Commons BeanUtils library  
* Spring @Autowired


### 이슈
1. Reflection을 사용한 코드는 느리다  
    > 적절히 사용한 Reflection은 오히려 성능을 향상시킬 수 있으며, 또한 많은 이득을 제공한다.  
2. Reflection을 이용하여 개발한 프로그램은 에러가 발생하기 쉽고 디버깅이 어렵다
    >  Reflection은 컴파일 시 타입 체킹을 할 수 없다. 따라서, 런타임시 잘못된 파라미터로 인해 런타임 에러가 발생하기 쉽다.  
3. Reflection을 사용한 코드는 복잡하다
    > Reflection을 사용한 코드는 일반적인 객체 생성, 메서드 호출 코드에 비교하면 복잡한 것이 사실이다.
    > 하지만 클래스의 타입을 비교하여 객체를 생성하는 코드의 경우, 대량의 if/else문을 사용하는 것보다 Reflection을 이용하여 재사용 가능한 컴포넌트로 만든다면, 오히려 코드를 단순화한다.
4. Compile vs. Run-time Type Checking
    > Reflection은 실제 클래스 없이 클래스의 이름 또는 메서드의 이름만을 이용한다. 따라서, 개발 단계 또는 컴파일 단계에는 Type Checking을 하지 않는다.
    > 대신, 실행시 발생할 수 있는 Exception을 처리하기 위한 try/catch문을 추가해야 한다.


출처 :    
https://kellis.tistory.com/70   
https://codechacha.com/ko/reflection/   
https://kmongcom.wordpress.com/2014/03/15/%EC%9E%90%EB%B0%94-%EB%A6%AC%ED%94%8C%EB%A0%89%EC%85%98%EC%97%90-%EB%8C%80%ED%95%9C-%EC%98%A4%ED%95%B4%EC%99%80-%EC%A7%84%EC%8B%A4/