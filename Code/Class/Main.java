package Class;

class Parent { // extends Object 생략 해도 된다.
    int x = 0;


    // 생략해도 되며, 생성자가 1개도 없을시 컴파일과정에서 기본생성자가 생성된다.
    // 바이트코드를 열어보면 추가되어있다.
    // 물론 이 소스에서 Main 클래스의 Main(){} 또한 생성된다.
    Parent() {
        System.out.println("make parent");
    }


    public int getX() {
        return x;
    }
}

class Child extends Parent {
    int x = 1;

    Child() {
        System.out.println("make child");
    }

    public int getX() {
        return this.x + 1;
    }
    public void setX(int x) {
        this.x = x;
    }
}


public class Main {
    public static void main(String[] args) {
        Parent parent = new Parent();
        // 이러한 메소드는 정의 하지 않았지만 모든 클래스의 최고 조상은
        // Object이므로 Object에서 toString을 정의하고 있기 떄문에 사용이 가능하다.
        parent.toString();


        Parent test = new Child(); // 생성순서 : Object - Parent - Child
        System.out.println(test.x); // Parent 에서 선언한 x의 값 0이 출력된다
        System.out.println(test.getX()); // Child 가 정의한 getX가 실행된다.

        Child test2 = (Child) test; // 묵시적 캐스팅
        System.out.println(test2.x); // Child 에서 선언한 x의 값 1이 출력된다.
    }
}
