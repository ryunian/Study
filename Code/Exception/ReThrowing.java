package Exception;

public class ReThrowing {
    public static void main(String[] args) {
        try {
            method1();
        }catch (Exception e){
            System.out.println("main 메서드에서 예외가 처리되었습니다.");
            try {
                method2();
            }catch (Exception e2){
                System.out.println("main 메서드에서 예외가 처리되었습니다.");
            }
        }
        // method1() 메서드에서 예외가 처리되었습니다.
        // main 메서드에서 예외가 처리되었습니다.
        // method2() 이 호출되엇습니다
        // finally
    }

    private static int method2() throws Exception{
        try {
            System.out.println("method2() 이 호출되엇습니다");
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            // return 문이 필요하지만 return문대신 예외를 호출한 메서드로 전달
            throw new Exception();
        }finally {
            System.out.println("finally");
        }
    }

    private static void method1() throws Exception{
        try {
            throw new Exception();
        }catch (Exception e){
            System.out.println("method1() 메서드에서 예외가 처리되었습니다.");
            throw e;
        }
    }
}
