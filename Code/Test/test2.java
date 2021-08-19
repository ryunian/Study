package Test;

class Y {
    int x = 1;
    void asdf(){

    }
    void play() {
        this.show();
        System.out.println("Test.A");
    }

    void show() {
        System.out.println("Test.B");
    }
}

class X extends Y {
    int x = 2;
    void show() {
        super.show();
        System.out.println("Test.C");
    }
}

public class test2 {
    public static void main(String[] args) {
        X a = new X();
        // 1.
        a.play(); // BCA

        // 2.
        Y b = new X();
        System.out.println(b.x); // 1


        // 3.
        Y c = new Y();
        c = (X) c; // ClassCastException
    }
}
