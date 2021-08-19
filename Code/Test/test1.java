package Test;

public class test1 {
    public static void main(String[] args) {

        int a = Integer.MAX_VALUE;
        int b = a + 1;
        long c = a + 1;
        System.out.println(b == c); // true
        // b 와 c에는 a의 -2^31 값이 들어가 있다

        float d = 3.14f;
        int e = (int) d; // 명시적으로 형변환을 해야한다.
        long f = (long) d; // 명시적으로 형변환을 해야한다.

        char g = 'g';
        int h = g;
        System.out.println(g == h); // true
        System.out.println(g - g); // 0
        System.out.println(h); // g의 아스키코드 값

        // 5. 문제점 및 설명
        String i = "abc";
        String j = "abc";
        String k = new String("abc");
        System.out.println(i == j);
        System.out.println(i == k);
        // ==는 object 클래스에서 선언된 equals 메소드를 통해 주소값을 비교한다.
        // 사용자정의 클래스일경우 equals를 오버라이딩하거나 String의 경우 equlas 메소드를 사용해야한다.

        // 6. 객체가 만들어지는 횟수는? 2
        i = "cba";
        String l = i + j;
    }
}
