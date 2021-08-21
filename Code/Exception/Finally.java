package Exception;

public class Finally {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int sum = 0;
        try {
            for (int i = 0; i < 5; i++) {
                sum += arr[i];
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return;
        } finally {
            sum = -1;
        }
        System.out.println(sum);
    }
}
