import java.util.*;

public class CustomArrayList {
    private int capacity = 10;
    private int size = 0;
    private int[] arr = new int[capacity];

    public CustomArrayList() {}

    public void add(int element){
        if(size == capacity){
            grow(size + 1);
        }
        arr[size++] = element;
    }

    public void set(int index, int element){
        arr[index] = element;
    }

    public int get(int index){
        return arr[index];
    }

    private void grow(int minCapacity){
        int oldCapacity = arr.length;
        int newCapacity = oldCapacity + Math.max(oldCapacity >> 1, minCapacity - oldCapacity);
        capacity = newCapacity;
        arr = Arrays.copyOf(arr, newCapacity);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(arr[i]).append(", ");
        }
        sb.setLength(sb.length()-2);
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        CustomArrayList list = new CustomArrayList();
        for (int i = 1; i <= 20; i++) {
            list.add(i);
        }
        list.set(10, 100);
        System.out.println(list.isEmpty());
        System.out.println(list.get(10));
        System.out.println(list.size());
        System.out.println(list);
    }
}

