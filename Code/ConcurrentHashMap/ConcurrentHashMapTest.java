package ConcurrentHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

// 입출력으로 인한 오버헤드때문에 정확하지 않을 가능성이 높음
// 그러므로 최대한 나노타임으로 측정을 하면서 조회 / 삽입 연산뒤에서 시간을 측정하였음
// 출력 형식 : 시간 | Thread Name | 삽입 / 조회
public class ConcurrentHashMapTest {
//    static Map<Integer, Integer> map = new HashMap<>();
    static Map<Integer, Integer> map = new ConcurrentHashMap<>();
    public static void main(String[] args) {
        init();
        Thread thread1 = new Thread(new Put(1, 1), "put1");
        Thread thread2 = new Thread(new Put(2, 2), "put2");
        Thread thread3 = new Thread(new Put(2, 3), "put3");
        Thread thread4 = new Thread(new Put(1, 4), "put4");
        Thread thread5 = new Thread(new Put(3, 5), "put5");

        Thread thread6 = new Thread(new Get(), "get1");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread6.start();
    }

    private static void init() {
        for (int i = 0; i <=10; i++) {
            map.put(i, 0);
        }
    }

    static class Put implements Runnable {
        private Random random;
        private int str;

        public Put(int seed, int str) {
            this.random = new Random(seed);
            this.str = str;
        }

        @Override
        public void run() {
            while (true) {
                int key = random.nextInt(10);
                map.compute(key, (k,v) -> (v + str));
                long time = System.nanoTime();
                System.out.println(time + " " + Thread.currentThread().getName() + " " + "[ key = " + key + " value = " + str + " ]");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Get implements Runnable {
        @Override
        public void run() {
            while (true) {
                String out = map.toString();
                long time = System.nanoTime();
                System.out.println(time + " " + Thread.currentThread().getName() + " " + out);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}




