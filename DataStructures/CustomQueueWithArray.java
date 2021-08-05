
public class CustomQueueWithArray {
    private int[] arr;
    private int capacity;
    private int index = -1;

    public CustomQueueWithArray(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
    }

    public void add(int element) {
        if (index == capacity) return;
        arr[++index] = element;
    }

    public int poll() {
        if (isEmpty()) return -1;
        int ret = arr[0];
        index--;
        for (int i = 0; i <= index; i++) {
            arr[i] = arr[i + 1];
        }
        return ret;
    }

    public int peek() {
        if (isEmpty()) return -1;
        return arr[0];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return index + 1;
    }

    public void clear(){
        for (int i = 0; i < index; i++) {
            arr[i] = 0;
        }
        index = -1;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Empty";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= index; i++) {
            sb.append(arr[i]).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        int capacity = 10;
        CustomQueueWithArray queue = new CustomQueueWithArray(capacity);

        for (int i = 1; i <= 5; i++) {
            queue.add(i);
        }
        System.out.println("queue : " + queue.toString());
        // queue : [1, 2, 3, 4, 5]

        System.out.println("queue size() : "+ queue.size());
        // queue size() : 5

        System.out.println("queue poll() : " + queue.poll());
        // queue poll() : 1

        System.out.println("queue poll() : " + queue.poll());
        // queue poll() : 2

        System.out.println("queue : " + queue.toString());
        // queue : [3, 4, 5]

        System.out.println("queue peek() : " + queue.peek());
        // queue peek() : 3

        System.out.println("queue : " + queue.toString());
        // queue : [3, 4, 5]

        queue.poll();
        queue.poll();

        System.out.println("queue : " + queue.toString());
        // queue : [5]

        queue.clear();

        System.out.println("queue isEmpty() : " + queue.isEmpty());
        // queue isEmpty() : true
    }
}
