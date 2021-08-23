package String;

public class Main {
    public static void main(String[] args) {
        String x = "JAVA";
        String y = "JAVA";
        String z = new String("JAVA");

        // true, String pool에 "JAVA"라는 문자열이 저장되어 있고 x와 y는 String Pool에 있는 문자열을 가르킨다.
        System.out.println(x == y);
        // false, 그에 반해 z는 new 키워드를 통해 만들어졌기 때문에, 힙영역에 새롭게 만들어 졌다.
        System.out.println(x == z); // false
        // 그렇기 때문에 String의 비교는 equals를 통해 해야한다.
        System.out.println(x.equals(z)); // true
    }
}
