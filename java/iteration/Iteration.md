# 반복문이란?
> 어떠한 명령을 일정한 횟수만큼 반복하여 수행하도록 하는 명령문이다.  
> 종류로는 while, do-while, for, for-each 이 있다.


### while
```java
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
```

### do-while
```java
public class dowhile {
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
```

### for
```java
public class For {
    public static void main(String[] args) {
        // for(초기화식 ; 조건식 ; 증감식)
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello");
        }

        // 꼭 하나의 초기화식 등을 가질 필요는 없다.
        for (int i = 0, j = 1; i < 10; i++, j++) {
            System.out.println(i+" "+j);
        }
        
        // 무한루프
        for (;;) {}
    }
}
```

### for-each
```java
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
```
> * 자바 5버전부터 나왔다.  
> * 배열/리스트의 크기만큼 반복하기 떄문에 일정부분만 반복하기는 힘드나,  
> 일반적인 for 문은 배열에 접근할떄 ArrayIndexOutOfBoundsException 이 발생할수 있는거에 비해 forEach 는 버그발생 가능성이 낮다.
> * 일반적으로 for문 보다는 속도가 느리지만 가독성 등 기타 장점떄문에 자주 쓰이며,   
> linkedList 를 forEach 로 통해 탐색할 경우 내부적으로 iterator 를 쓰기때문에 장점이 된다.


### 반복문에 사용하는 키워드들
> label, break, continue 
```java
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
```