package Exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WithResources {
    public static void main(String[] args) {
        // BufferedReader 와 InputStreamReader 은 AutoCloseable 인터페이스를 구현하고 있다.
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
