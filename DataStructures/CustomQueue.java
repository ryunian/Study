public class CustomQueue extends CustomLinkedList {
    public void add(int element) {
        addLast(element);
    }

    public int poll() {
        int ret = get(0);
        removeFirst();
        return ret;
    }

    public int peek() {
        return get(0);
    }

    // CustomLinkedList 에서 toString 이 정의 되어 있으므로 따로 추가 하지 않음
    // @Override
    // public String toString() {}

    public static void main(String[] args) {
        CustomQueue queue = new CustomQueue();
        for (int i = 1; i <= 10; i++) {
            queue.add(i);
        }

        System.out.println("queue : " + queue.toString());
        // queue : [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        System.out.println("queue size() : "+ queue.size());
        // queue size() : 10

        System.out.println("queue poll() : " + queue.poll());
        // queue poll() : 1

        System.out.println("queue poll() : " + queue.poll());
        // queue poll() : 2

        System.out.println("queue : " + queue.toString());
        // queue : [3, 4, 5, 6, 7, 8, 9, 10]

        System.out.println("queue peek() : " + queue.peek());
        // queue peek() : 3

        System.out.println("queue : " + queue.toString());
        // queue : [3, 4, 5, 6, 7, 8, 9, 10]

        queue.clear();

        System.out.println("queue : " + queue.toString());
        // queue : Empty

        System.out.println("queue isEmpty() : " + queue.isEmpty());
        // queue isEmpty() : true


    }
}
