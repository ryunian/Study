package Operator;

public class Operator {
    public static void main(String[] args) {
        // int 는 8바이트 이므로 32bit 로 나타낸다.
        int x = 10; // 00000000 00000000 00000000 00001010
        int y = 3;  // 00000000 00000000 00000000 00000011
        int z = -1; // 11111111 11111111 11111111 11111111

        // 산술연산자 ( + - * / % )
        System.out.println(x + y);  // 13
        System.out.println(x - y);  // 7
        System.out.println(x * y);  // 30
        System.out.println(x / y);  // 3
        System.out.println(x % y);  // 1

        // 비트연산 (NOT, AND, OR, XOR)
        System.out.println(~x);     // -11
        System.out.println(x & y);  // 2
        System.out.println(x | y);  // 11
        System.out.println(x ^ y);  // 9

        // 관계연산 ( == != <= >=  > <)
        System.out.println(x == y); // false
        System.out.println(x != y); // true
        System.out.println(x <= y); // false
        System.out.println(x >= y); // true
        System.out.println(x > y);  // true
        System.out.println(x < y);  // false
    }
}

