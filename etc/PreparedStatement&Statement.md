# PreparedStatement 와 statement
> 데이터베이스로 쿼리문을 전송할 때 사용하는 java.sql 패키지의 내부에 있는 인터페이스로  
> Statement와 PreparedStatement가 있습니다.
>
> 두 가지 모두 try-catch문을 통해 자원을 반납해야되며 기본적으로 AutoCloseable이 상속되어 있기 때문에 Try-with-resources 을통해 자원 반납이 가능하다. 

<br><br>

## Statement

- Statement 객체는 Statement 인터페이스를 구현한 객체를 Connection 클래스의 createStatement( ) 메소드를 호출함으로써 얻어진다.
- Statement 객체가 생성되면 executeQuery( ) 메소드를 호출하여 SQL문을 실행시킬 수 있다. 메소드의 인수로 SQL문을 담은 String객체를 전달한다.
- Statement는 정적인 쿼리문을 처리할 수 있다. 즉 쿼리문에 값이 미리 입력되어 있어야 한다.

<br><br>

## PreparedStatement

> Statement를 확장한 인터페이스로 미리 컴파일된 SQL문을 나타내는 객체이다.
SQL문은 미리 컴파일되어 PreparedStatement 객체에 저장되며,
이 객체를 사용하면 미리 컴파일된 쿼리문장을 여러번 효율적으로 실행할 수 있다.
> 
- PreparedStatement 객체는 Connection 객체의 preparedStatement( ) 메소드를 사용해서 생성한다. 이 메소드는 인수로 SQL문을 담은 String객체가 필요하다.
- SQL문장이 미리 컴파일되고, 실행 시간동안 인수값을 위한 공간을 확보할 수 있다는 점에서 Statement 객체와 다르다.
- Statement 객체의 SQL은 실행될 때 매번 서버에서 분석해야 하는 반면, PreparedStatement 객체는 한 번 분석되면 재사용이 용이하다.
- 각각의 인수에 대해 위치홀더(placeholder)를 사용하여 SQL문장을 정의할 수 있게 해준다. 위치홀더는 ? 로 표현된다.
- 동일한 SQL문을 특정 값만 바꾸어서 여러 번 실행해야 할 때, 인수가 많아서 SQL문을 정리해야 될 필요가 있을 때 사용하면 유용하다.

<br><br>

## 차이점

* 보안
    * statement 는 사용자가 입력한 값을 가지고 SQL문을 만들기 떄문에 보안이 취약하다 (SQL inject 공격을 받을수 있다.)
 
 
      ex) String pw = 1234;
        "Select * from user where user_pw ="+ pw 으로 쿼리가 만들어질떄
        pw 값이 "1234 or 1=1" 로 보낼경우 1234이 pw값이 아니더라도 값이 조회가 된다.


* 바이너리 데이터
    * 문자열로 SQL문을 만들기 때문에 바이너리 데이터를 삽입할 수 없다.
 

* 가독성
    * String을 이용해서 SQL문을 만들기 떄문에 쿼리문장이 잘못됬는지 확인하기가 어렵다.
    * 하지만 statement 보다 가독성이 좋을뿐이지 PrearedStatement 또한 가독성이 좋지 못하다.  
        (자원해제와 더 불어 Mybatis나 JPA를 사용하는 이유)

* 실행속도
    * 속도는 큰차이가 없으며 해당 쿼리문을 반복적으로 사용할 경우 PrearedStatement 는 미리 컴파일이 되어 있으므로,재컴파일을 하지 않는다.

    
        SQL Injection
        해커에 의해 조작된 SQL 쿼리문이 데이터베이스에 그대로 전달되어 비정상적 명령을 실행하는 공격 기법