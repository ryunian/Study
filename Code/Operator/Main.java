package Operator;

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