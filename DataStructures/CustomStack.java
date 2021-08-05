public class CustomStack extends CustomLinkedList {
    public void push(int element) {
        addLast(element);
    }

    public int pop() {
        int ret = get(size() - 1);
        removeLast();
        return ret;
    }

    public int peek() {
        return get(size() - 1);
    }

    public static void main(String[] args) {
        CustomStack stack = new CustomStack();
        System.out.println("stack isEmpty() : " + stack.isEmpty());
        // stack isEmpty() : true

        for (int i = 1; i <= 10; i++) {
            stack.push(i);
        }
        System.out.println("stack : " + stack.toString());
        // stack : [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        stack.pop();
        stack.pop();
        stack.pop();

        System.out.println("stack : " + stack.toString());
        // stack : [1, 2, 3, 4, 5, 6, 7]

        System.out.println("stack peek() : " + stack.peek());
        // stack peek() : 7

        stack.push(10);

        System.out.println("stack size() : " + stack.size());
        // stack size() : 7

        System.out.println("stack peek() : " + stack.peek());
        // stack peek() : 10

    }
}
