package Exception;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 0};
        int sum;

        try {
            sum = arr[0] / arr[1];
            System.out.println(sum);

            // 예외 발생시키기
            // 예외도 객체이므로 new 키워드를 통해 인스턴스화 해서 throw 키워드를 통해 예외를 발생시킬수 있다.
            throw new NullPointerException();
        } catch (ArithmeticException | IndexOutOfBoundsException e) {
            // 위처럼 | 기호를 이용하여 하나의 catch블럭으로 처리할수 있다.
            // 단, 두 예외 클래스가 조상과 자손 관계에 있다면 에러가 발생한다.
            e.printStackTrace();
            System.out.println("에러발생");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println("try-catch로 인해 에러처리 하였으므로 실행된다.");
    }
}
