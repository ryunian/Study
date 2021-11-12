# Mybatis

- 쿼리 기반 웹 어플리케이션을 개발할 때 가장 많이 사용되는 SQL 매퍼(Mapper) 프레임워크이다.

- 기존 preparedStatement 등을 사용해서 직접 JDBC를 사용할 경우 반복적으로 작성해야 할 코드가 많고 (connection 처리, 자원해제 등) 서비스 로직 코드와 쿼리를 분리하기가 어렵다.

- 커넥션 풀의 설정 등 개발자가 신경 써야 할 부분이 많아 어려움이 있다.

- SQL을 XML 파일에 작성하기 떄문에, SQL의 변환이 자유롭고 가독성이 좋다.

<br><br>

## 특징

1. 쉬운 접근성과 코드의 간결함
    - Mybatis는 그저 XML에 쿼리를 작성하면되지만, JPA는 러닝커브가 높다.
2. SQL문과 프로그래밍 코드의 분리
3. 다양한 프로그래머 언어로 구현가능
4. Mybatis는 모든 쿼리를 preparedstatement로 실행한다.

<br><br>

## Mybatis 아키텍쳐

<img src="https://github.com/ryunian/Study/blob/master/image/mybatis1.png?raw=true" width="700px" height="500px">

## 흐름

<img src="https://github.com/ryunian/Study/blob/master/image/mybatis2.png?raw=true" width="700px" height="500px">

<br><br>

#### 응용 프로그램 시작시 수행

1. 응용 프로그램이 SqlSessionFactoryBuilder를 위해 SqlSessionFactory를 빌드하도록 요청한다.
2. SqlSessionFactoryGBuilder는 SqlSessionFactory를 생성하기 위한 Mybatis 구성 파일을 읽는다.
3. SqlSessionFactoryBuilder는 MyBatis 구성 파일의 정의에 따라 SqlSessionFactory를 생성한다.

<br><br>

#### 클라이언트의 요청에 대해 수행

1. 클라이언트가 응용 프로그램에 대한 프로세스를 요청한다.
2. 응용 프로그램은 SqlSessionFactoryBuilder를 사용하여 빌드된 SqlSessionFactory에서 SqlSession을 가져온다.
3. SqlSessionFactory는 SqlSession을 생성하고 이를 어플리케이션에 반환한다.
4. 응용 프로그램이 SqlSession에서 매퍼 인터페이스의 구현 개체를 가져온다.
5. 응용 프로그램이 매퍼 인터페이스 메서드를 호출한다.
6. 매퍼 인터페이스의 구현 개체가 SqlSession 메서드를 호출하고 SQL 실행을 요청한다.
7. SqlSession은 매핑 파일에서 실행할 SQL을 가져와 SQL을 실행한다.

- SqlSessionFactoryBuilder : Mybatis 구성 파일을 읽고 생성하는 SqlSessionFactory구성 요소
- SqlSessionFactory : SqlSession을 생성
- SqlSession : SQL 실행 및 트랜잭션 제어를 위한 API를 제공