package Test;

class D{
    int x;

    public D(int x) {
        this.x = x;
    }
}

public class test4 {
    public static void main(String[] args) {
        D test1 = new D(1);
        D test2 = new D(1);
        System.out.println(test1 == test2); // fasle
        // equals 를 오버라이딩해야 한다.
    }
}
