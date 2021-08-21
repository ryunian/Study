# Switch vs If
> * branch statement는 레지스터 2개를 비교해서(혹은 레지스터와 상수를 비교해서)   
    특정 메모리 번지로 이동할 것이냐 말것이냐?를 결정합니다.  
> * jump statement는 즉시 특정 메모리 번지로 이동하는 기능을 합니다.  

<br>

### Switch 문 (jump statement 기반)
> * 조건 만족? 안만족? 이 아니라, switch문 시작 시 입력받은 값을 확인하는
> * instruction만 필요하다(점프테이블에서 해당값으로 찾아가므로).
> * 따져야 할 조건이 많은 경우 switch문을 쓰는 것이 유리합니다.

<br>

## If 문 (branch statement 기반)
> * 점프 테이블을 만드는 오버헤드가 없다.
> * if 혹은 else if를 만날 때마다 조건을 만족하는지 안하는지를 확인하기 위한 인스트럭션이 계속해서 필요됨.
> * 따져야 할 조건의 수가 적을 경우 if-else를 쓰는 것이 유리함.


```java
public class Main {
    public static void main(String[] args) {
        // Conditional
        String day = "Sunday";
        switch (day) {
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":
                System.out.println("일하자");
                break;
            case "Saturday":
            case "Sunday":
                System.out.println("놀자");
                break;
        }

        // if
        if(day.equals("Sunday") || day.equals("Saturday")){
            System.out.println("놀자");
        }else {
            System.out.println("일하자");
        }
    }
}
```

> 하지만 하드웨어의 성능이 계속 증가 하고 있는 현재는 성능의 차이보단 가독성을 따져서 선택을 하는게 올바르다.

<br>

### JAVA 12 에서의 Switch문
```java
public class Main {
    public static void main(String[] args) {
        String day = "Sunday";
        // JAVA 12 Conditional
        switch (day) {
            case "Friday" -> System.out.println("불금!");
            case "Saturday" -> System.out.println("불토!");
            case "Sunday" -> System.out.println("벌써 일요일이라니");
        }
    }
}
```
> * 람다 사용이 가능해졌다.
> * 그러나 람다와 기존 케이스문을 혼용해서 사용할수가 없다.

<br>

### JAVA 13 에서의 Conditional 문
```java
public class Main {
    public static void main(String[] args) {
        String day = "Sunday";
        // JAVA 13 Conditional
        String result = switch (day){
            case "Monday" -> "일하자";
            case "Friday" -> "불금";
            case "Sunday" -> "일요일!";
            default -> throw new IllegalStateException("Unexpected value: " + day);
        };
        System.out.println(result); // 일요일!

        
        // var는 키워드가 아니기 떄문에 변수명으로 사용가능하다.
        // yield 를 이용해서 리턴을 할수가 있다!
        var var = switch (day){
            case "Monday" : yield "월요일";
            case "Friday" : yield "금요일";
            case "Sunday" : yield "일요일";
            default:
                throw new IllegalStateException("Unexpected value: " + day);
        };
        System.out.println(var); // 일요일
    }
}
```


