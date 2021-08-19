package iteration;

public class etc {
    public static void main(String[] args) {
        loop:
        for (int i = 0; i <= 4; i++) {
            // continue 키워드를 만날 경우 아래의 연산은 수행하지 않고 증감식으로 간다.
            if (i == 0) continue;
            System.out.printf("%d : ", i);
            for (int j = i; j < i + 3; j++) {
                // break; 는 현 시점에서 가장 가까운 반복문을 종료하는 것이며
                // label 을 이용하여 반복문의 라벨명을 지정해주면
                // break label; 을 통해서 원하는 반복문을 종료할수 있다.
                System.out.print(j + " ");
                if (j == 2) break;
                if (j == 3) break loop;
            }
            System.out.println();
        }
        /*
        출력 :
        1 : 1 2
        2 : 2
        3 : 3
        0은 continue 를 만났기 때문에 출력되지 않는다.
        i=1,j=2 일떄 break 가 구행되어 가장 가까운 반복문이 종료되었다.
        i=3,j=3 일떄 break loop 가 수행되어 loop 반복문이 종료되었다.
         */
    }
}
