public class CustomStackWithArray {
    private int[] arr;
    private int capacity;
    private int top = -1;

    public CustomStackWithArray(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
    }

    public void push(int element) {
        if (isFull()) return;
        arr[++top] = element;
    }

    public int pop() {
        if (top == -1) return -1;

        int ret = peek();
        arr[top--] = 0;
        return ret;
    }

    public int peek() {
        if (isEmpty()) return -1;
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == this.capacity - 1;
    }

    public int size() {
        return top + 1;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Stack is Empty";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= top; i++) {
            sb.append(arr[i]).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");

        return sb.toString();
    }

    public static void main(String[] args) {
        int size = 10;
        CustomStackWithArray stack = new CustomStackWithArray(size);
        for (int i = 1; i <= 10; i++) {
            stack.push(i);
        }

        System.out.println("stack toString() : " + stack.toString());
        // stack toString() : [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        System.out.println("stack peek() : " + stack.peek());
        // stack peek() : 10

        System.out.println("stack isFull() : " + stack.isFull());
        // stack isFull() : true

        stack.pop();
        stack.pop();
        stack.pop();

        System.out.println("stack toString() : " + stack.toString());
        // stack toString() : [1, 2, 3, 4, 5, 6, 7]

        System.out.println("stack peek() : " + stack.peek());
        // stack peek() : 7

        System.out.println("stack size() : " + stack.size());
        // stack size() : 7

        int max = stack.size();
        for (int i = 0; i < max; i++) {
            stack.pop();
        }

        System.out.println("stack toString() : " + stack.toString());
        // stack toString() : Stack is Empty

        System.out.println("stack isEmpty() : " + stack.isEmpty());
        // stack isEmpty() : true
    }
}
