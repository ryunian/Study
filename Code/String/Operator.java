package String;

public class Operator {
    public static void main(String[] args) {
        // 명령어
        // javap -v -p -s -sysinfo -constants Operator.class
        String s = "str";
        String str = s + s + s + s + s + s + s + s + s + s
                + s + s + s + s + s + s + s + s + s + s
                + s + s + s + s + s + s + s + s + s + s
                + s + s + s + s + s + s + s + s + s + s
                + s + s + s + s + s + s + s + s + s + s
                + s + s + s + s + s + s + s + s + s + s;


        int loop = 100;
        double start, end;
        String tmp = "";
        StringBuilder sb = new StringBuilder();


        // Test
        // StringBuilder = 8600.0
        start = System.nanoTime();
        for (int i = 0; i < loop; i++) {
            sb.append(s);
        }
        end = System.nanoTime();
        System.out.println("StringBuilder = " + (end - start));

        // String = 459700.0
        start = System.nanoTime();
        for (int i = 0; i < loop; i++) {
            tmp += s;
        }
        end = System.nanoTime();
        System.out.println("String = " + (end - start));
    }
}
