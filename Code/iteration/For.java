package iteration;

public class For {
    public static void main(String[] args) {
        // for(초기화식 ; 조건식 ; 증감식)
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello");
        }

        // 꼭 하나의 초기화, 하나의 증감식을 가질 필요는 없다.
        for (int i = 0, j = 1; i < 10; i++, j++) {
            System.out.println(i+" "+j);
        }

        // 무한루프
        for (;;) {}
    }
}
