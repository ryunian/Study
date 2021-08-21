### 프로그램 오류
> 프로그램이 실행 중 어떠한 원인에 의해 오작동하거나, 비정상적으로 종료되는 경우가 있다 . 이러한 원인을 프로그램 에러 또는 오류라고 한다.  
> 종류로는 컴파일 에러, 런타임 에러, 논리적 에러가 있다.  
* 컴파일 에러 : 컴파일 시에 발생하는 에러  
* 런타임 에러 : 실행 시에 발생하는 에러  
* 논리적 에러 : 실행은 되지만, 의도와 다르게 동작하는 것  

### 에러(Error)와 예외(Exception)
* Java 는 실행 시(runtime) 발생할 수 있는 프로그램 오류를 '에러(error)'와 '예외(exception)' 으로 구분하였다.  
* Error와 Exception으로 끝나는 오류의 가장 큰 차이는 프로그램 안에서 발생했는지, 밖에서 발생했는지 여부이다.   
* 하지만, 더 큰 차이는 프로그램이 멈추어 버리느냐 계속 실행할 수 있느냐의 차이이다.   
* 더 정확하게 말하면 Error는 프로세스에 영향을 주고, Exception은 쓰레드에만 영향을 준다.  
* 에러(error)의 종류로는 OutOfMemoryError, StackOverFlowError 등이 있다.  


<img src="https://github.com/ryunian/Study/blob/master/image/Throable.png?raw=true" width="800" height="500">

<br><br>

### 예외(exception)
<img src="https://github.com/ryunian/Study/blob/master/image/exception.png?raw=true" width="700" height="300">  

* 자바에서는 실행 시 발생할 수 있는 오류(에러 및 예외)를 클래스로 정의하였다 (즉, Obejct의 자손이다)  
* 모든 예외는 Exception클래스는 상속하며, unchecked Exception 과 cheked Exception으로 나뉜다.  
* unchecked Exception  
	> RuntimeException 과 이것을 상속하는 예외를 unchecked exception이라고 한다.    
	 해당 예외는 컴파일러가 체크를 해주지 않기 떄문에 런타임시점에 발생한다.  
	 종류로는 RuntimeException, NullPointerException, ArithmeticException, IndexOutOfBoundsException, ClassCastExcetion, IllegalArgumentException 등이 있다.    	
	 이러한 예외는 개발자의 실수에 의해서 발생하는게 크기 떄문에 개발자는 try-catch문을 사용한다기보단 최대한 해당 예외가 발생하지 않는 안전한 소스를 작성하는게 중요하다.    

* checked exception
	> 나머지 예외 클래스와 그 하위 객체들을 필수 예외라하여 반드시 체크해줘야하는 checked exception이 있다.  
     이러한 예외들은 try-catch 문을 통해서 예외처리를 해주거나 throws 를 통해 메소드 밖으로 던질 수 있다.  
     만약 예외처리를 안해줄 경우, 컴파일 에러가 발생한다.  


### 예외 처리 하기 try - catch
* 정의 : 프로그램 실행 시 발생할 수 있는 예외에 대비한 코드를 작성하는 것
* 목적 : 프로그램의 비정상 종료를 막고, 정상적인 실행상태를 유지하는 것

```java
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
```


### 메서드에 예외 선언하기
```java 
void method() throws Exception1, Exception2 {}
```
> 메서드에 예외를 선언하려면, 메서드의 선언부에 키워드 throws 를 사용해서 메서드 내에서 발생할 수 있는 예외를 적어주기만 하면된다.  
> 그리고 예외가 여러개일 경우는 쉼표로 구분한다.  
> 자바에서는 메서드를 작성할 떄 예외를 메서드의 선언부에 명시하여 이 메서드를 사용하는 쪽에서 예외를 처리하도록 강요하기 떄문에  
> 견고한 프로그램 코드를 작성할 수 있도록 도와준다. ( 프로그래머들의 짐을 덜어준다고 하지만 강제되는 예외처리 때문에 불편함을 느낀다.)  
> 그렇기 때문에 try-catch문을 남발하지 않고 적절한곳에 checked exception 과 unchecked exception 과 잘 구분하여 처리해주어야 한다.  


### finally 블럭
> finally 블럭은 예외의 발생여부에 상관없이 실행되어야할 코드를 포함시킬 목적으로 사용된다.  
> try-catch 문의 끝에 선택적으로 덧붙여 사용할수 있으며, try-catch-finally 의 순서로 구성된다.  
> try-catch 문에서 return 이 발생되도 finally 는 실행이 된다.  
> 단 System.exit() 등 같은 경우는 finally 는 실행되지 않는다.  

```java
public class Finally {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int sum = 0;
        try {
            for (int i = 0; i < 5; i++) {
                sum += arr[i];
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return;
        } finally {
            sum = -1;
        }
        System.out.println(sum); // sum : -1
    }
}
```


### 사용자정의 예외 만들기
> 기존의 정의된 예외 클래스 외에 필요에 따라 새로운 예외 클래스를 정의할수 있다.  
> 보통 Exception 클래스 또는 RuntimeException 클래스부터 상속받아 클래스를 만든다.

```java
public class CustomException extends Exception{
    public CustomException(String message) {
        super(message);
    }
    public CustomException() {
    }
}
```


### 예외 되던지기 (exception re-throwing)
> 예외를 처리한 후에 인위적으로 다시 발생시키는 것을 '예외 되던지기 (exception re-throwing)' 라고한다.  

> 하나의 예외에 대해서 예외가 발생한 메서드와 이를 호출한 메서드 양쪽 모두에서 처리해줘야 할 작업이 있을 떄 사용되며,  
> 주의할점으론, 예외가 발생할 메서드에서는 try-catch문을 사용해서 예외처리를 해줌과 동시에 메서드의 선언부에 발생할 예외를 throws에 지정해줘야 한다.   
```java
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
```

### 자동 자원 반환 try-with-resources문
> 주로 입출력에 사용되는 클래스 중에서는 사용한 후에 꼭 닫아 줘야 하는 것들이 있다. (jdbc 도 닫아줘야 한다.)  
> 그래야 사용했던 자원이 반환되기 떄문이다.  
> try-with-resources문문의 괄호 안에 객체를 생성하는 문장을 넣으면, 이 객체는 따로 close()를 호출하지 않아도 try블럭을 벗어나는 순간 자동적으로 close()가 호출된다.  
> 그 다음에 catch블럭 또는 finally블럭이 수행된다.  
> 단 이처럼 close()가 호출될 수 있으려면, 클래스가 AutoCloseable 이라는 인터페이스를 구현한 것이어야만 한다.  

```java
public interface Closeable extends AutoCloseable{}
public class WithResources {
    public static void main(String[] args) {
        // BufferedReader 와 InputStreamReader 은 AutoCloseable 인터페이스를 구현하고 있다.
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 연결된 예외(chained exception)
> 한 예외가 다른 예외를 발생시킬 수도 있다. 예외 A가 예외 B를 발생시켯다면, A를 B의 '원인 예외(cause exception)'라고 한다.    
* 연결된 예외를 사용하는 이유  
    > 여러가지 예외를 하나의 큰 분류의 예외로 묶어서 다루기 위해.  
     checked 예외를 unchecked 예외로 바꿀수 있게 된다.   
     즉, Exception 예외 객체를 상속하는 하위 예외 객체들을 RuntimeException으로 감싸서 unchecked 예외로 만들 수 있는데,     
     이 기능을 사용해 더이상 억지로 try-catch로 처리해줄 필요가 없어진다.

* 사용법
    > 1. 연결할 새로운 예외 객체를 생성한다.
    > 2. 새로운 예외객체에 initCause 메서드에 인자값으로 기존 연결 될 예외 객체를 넣어준다.  
        (initCause는 Throwble 클래스에 정의 되어 있다.)
    > 3. throw 키워드로 연결할 예외 객체를 던진다.