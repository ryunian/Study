package Test;

// 1.
abstract class B {
    abstract void getOS();

    void getDog() {
        System.out.println("멍");
    }
}


class C extends B {
    void getDog() {
        System.out.println("월월");
    }
    void getOS() {
        System.out.println("Win");
    }
    void getOS2(){
        System.out.println("linux");
    }
}


public class test5 {
    public static void main(String[] args) {
        B test = new C();
        // 2.
        test.getDog(); // 월월

        // 3.
        System.out.println(test instanceof C); // true
        System.out.println(test instanceof B); // true

        // 4.
        test = (C) test;
        test.getDog(); // 월월
    }
}



















