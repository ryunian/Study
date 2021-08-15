package iteration;

import java.util.Arrays;

public class Foreach {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};

        // 타입은 대상 element 타입과 같아야 한다. (타입추론인 var 를 사용하면 수월하다)
        // 매 반복마다 하나씩 순서대로 대상 element 을 순서대로 읽어와서 변수에 저장한다.
        for(int x : arr){
            System.out.println(x);
            x = 0; // 그러므로 이러한 식을 해당배열에 있는 element 에 접근하는게 아니다
        }
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5]

        // stream
        Arrays.stream(arr).forEach(s -> System.out.println(s));
    }
}
