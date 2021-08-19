package Test;

class A{
    static void hello(){
        System.out.println("hello");
    }
}

public class test3 {
    void hello(){
        System.out.println("hello");
    }
    public static void main(String[] args) {
        A.hello(); // 정상
//        hello(); // 인스턴스를 하거나 스태틱을 붙여워야합니다.
    }
}
