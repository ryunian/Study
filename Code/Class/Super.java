package Class;

class Animal {
    // 동물의 숫자 상속되면 안된다.
    private int AnimalCnt = 0;
    String name;
    int weight;
    int age;
    int x;

    public Animal(String name, int weight, int age) {
        this.name = name;
        this.weight = weight;
        this.age = age;
        AnimalCnt++;
    }

    public int getAnimalCnt() {
        return AnimalCnt;
    }
}

class Cat extends Animal {
    int x;

    public Cat(int x, String name, int weight, int age) {
        super(name, weight, age);
        this.x = x;

        // this.name = name;
        // 물론 상속을 했기때문에 Cat 에도 name 은 존재한다.
        // 그러나 AnimalCnt 는 private 이기 떄문에 접근할 수 없다.
    }

    public void printX() {
        System.out.println("super.x = " + super.x);
        System.out.println("x = " + x);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                '}';
    }
}

public class Super {
    public static void main(String[] args) {
        Animal cat = new Cat(1, "루나", 3, 5);

        System.out.println(cat); // Cat{name='루나', weight=3, age=5}
        System.out.println("Animal Cnt = " + cat.getAnimalCnt());
        // Animal Cnt = 1

        if (cat instanceof Cat) {
            Cat realCat = (Cat) cat;
            realCat.printX();
            // super.x = 0
            // x = 1
        }
    }
}
