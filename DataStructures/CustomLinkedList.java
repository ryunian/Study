public class CustomLinkedList {
    private Node first;
    private Node last;
    private int size = 0;

    public CustomLinkedList() {
    }

    public void add(int element) {
        if (size == 0) {
            addFirst(element);
        } else {
            addLast(element);
        }
    }

    public void addFirst(int element) {
        Node f = first;
        Node newNode = new Node(null, f, element);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    public void addLast(int element) {
        Node l = last;
        Node newNode = new Node(l, null, element);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public void add(int index, int element) {
        if (index == size) {
            addLast(element);
        } else if (index < size) {
            Node next = findNode(index);
            Node prev = next.prev;
            Node newNode = new Node(prev, next, element);
            next.prev = newNode;

            if (prev == null) {
                first = newNode;
            } else {
                prev.next = newNode;
            }
            size++;
        }
    }

    private Node findNode(int index) {
        if (index < (size >> 1)) {
            Node x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    public void remove(int index) {
        unlink(findNode(index));
    }

    public void remove(Object value) {
        for (Node x = first; x != null; x = x.next) {
            if (x.value == (int) value) {
                unlink(x);
                return;
            }
        }
    }

    public void removeFirst() {
        unlink(first);
    }

    public void removeLast() {
        unlink(last);
    }

    public int get(int index) {
        return findNode(index).value;
    }

    public void set(int index, int value) {
        findNode(index).value = value;
    }


    void unlink(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }
        node = null;
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (Node cur = first; cur != null; ) {
            Node next = cur.next;
            cur.next = null;
            cur.prev = null;
            cur = next;
        }
        first = last = null;
        size = 0;
    }

    private static class Node {
        Node prev;
        Node next;
        int value;

        public Node(Node prev, Node next, int value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }

    @Override
    public String toString() {
        if(isEmpty()) return "Empty";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node x = first; x != null; x = x.next) {
            sb.append(x.value).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("Show List : " + list);
        // Show List : [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        list.remove(5);
        System.out.println("Remove Idx(5) : " + list);
        // Remove Idx(5) : [0, 1, 2, 3, 4, 6, 7, 8, 9]

        list.removeFirst();
        System.out.println("Remove First : " + list);
        // Remove First : [1, 2, 3, 4, 6, 7, 8, 9]

        list.removeLast();
        System.out.println("Remove Last : " + list);
        // Remove Last : [1, 2, 3, 4, 6, 7, 8]

        list.remove((Object) 4);
        System.out.println("Remove Value(4) : " + list);
        // Remove Value(4) : [1, 2, 3, 6, 7, 8]

        list.add(3, 3);
        System.out.println("Add Idx(3) Value(3) : " + list);
        // Add Idx(3) Value(3) : [1, 2, 3, 3, 6, 7, 8]

        list.addFirst(10);
        System.out.println("AddFirst Value(10) : " + list);
        // AddFirst Value(10) : [10, 1, 2, 3, 3, 6, 7, 8]

        list.addLast(-1);
        System.out.println("AddLast Value(-1) : " + list);
        // AddLast Value(-1) : [10, 1, 2, 3, 3, 6, 7, 8, -1]

        System.out.println("Get Idx(5) : " + list.get(5));
        // Get Idx(5) : 6

        list.set(5, 5);
        System.out.println("Set Idx(5) Value(5) : " + list.get(5));
        // Set Idx(5) Value(5) : 5

        System.out.println("Show List : " + list);
        // Show List : [10, 1, 2, 3, 3, 5, 7, 8, -1]

        System.out.println("Size : " + list.size());
        // Size : 9

        list.clear();

        System.out.println("isEmpty : " + list.isEmpty());
        // isEmpty : false
    }
}
