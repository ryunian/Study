package OS;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        final Reseouce resource = new Reseouce(3);
        for (int i = 1; i <= 10; i++) {
            Thread t = new Thread(() -> resource.use());
            t.start();
        }
    }

    static class Reseouce {
        private final Semaphore semaphore;
        private final int maxThread;

        public Reseouce(int maxThread) {
            this.semaphore = new Semaphore(maxThread);
            this.maxThread = maxThread;
        }

        public void use() {
            try {
                // Thread 가 세마포에게 시작을 알림
                semaphore.acquire();

                System.out.printf("[%s] %d개의 쓰레드가 점유중\n"
                        , Thread.currentThread().getName()
                        , (maxThread - semaphore.availablePermits()));
                // semaphore.availablePermits() 사용가능한 Thread의 숫자

                Thread.sleep((long) (Math.random() * 10000));

                semaphore.release(); // Thread 가 세마포에게 종료를 알림
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
