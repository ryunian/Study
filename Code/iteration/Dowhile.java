package iteration;

public class Dowhile {
    public static void main(String[] args) {
        int x = 0;
        do{
            // do-while 문은 한번을 우선 실행시킨다음
            // 조건식의 true / false 여부를 체크한다.
            x++;
        }while (false);
        System.out.println(x); // 1
    }
}
