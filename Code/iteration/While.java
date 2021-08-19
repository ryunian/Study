package iteration;

public class While {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10){ // 해당 조건이 true 일 경우 반복한다.
            System.out.println("안녕하세요");
            x++;
        }
        while (true){ // 무한반복
            x++;

            // while의 조건이 true 이므로 계속해서 반복이 진행된다.
            // 그렇기 떄문에 조건문을 통해서 탈출조건을 넣어줘야한다.
            // 해당 조건이 없을경우 아래의 출력문에 도달할수 없으므로 컴파일에러가 난다.
            if(x == 10) break;
            // 또한 break 대신 return 으로 인해 무한루프를 벗어날수 있으나,
            // 출력문에 도달할수 없으므로 컴파일 에러나 난다.
            // if(x == 10 ) return;
        }
        System.out.println("Hello");
    }
}
