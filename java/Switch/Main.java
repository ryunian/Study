package Switch;

public class Main {
    public static void main(String[] args) {
        // Switch
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
        if (day.equals("Sunday") || day.equals("Saturday")) {
            System.out.println("놀자");
        } else {
            System.out.println("일하자");
        }


        // JAVA 12 Switch
        switch (day) {
            case "Friday" -> System.out.println("불금!");
            case "Saturday" -> System.out.println("불토!");
            case "Sunday" -> System.out.println("벌써 일요일이라니");
        }


        // JAVA 13 Switch
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
