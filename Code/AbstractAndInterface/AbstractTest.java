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
        System.out.println("운전중입니다.");
    }
}

public class AbstractTest {
    public static void main(String[] args) {
        Car car = new K5();
        car.drive();
    }
}
