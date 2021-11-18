# Web server & WAS

## Static pages (정적 컨텐츠)

- image, html, css, javascript 파일과 같이 컴퓨터에 저장되어 있는 파일들을 의미한다.
- 웹 서버에서 요청에 알맞은 파일을 반환하며, 항상 동일한 페이지를 반환한다. (말 그대로 정적인 컨텐츠)
- 웹 서버에서 제공한다.

<br>

## Dynamic pages (동적 컨텐츠)

- 들어온 요청에 맞게 동적으로 만들어진 컨텐츠를 의미한다.
- 데이터베이스, 서버 내 로직 등을 활용해 만들어진 컨텐츠를 반환한다.
- 웹 어플리케이션 서버에서 제공한다.
- WAS
Web Server + web Container
WAS는 프로그램의 동적인 결과를 웹 브라우에게 전송하는 역할
웹 서버없이 WAS가 제공한 웹 서버의 기능으로도 정적,동적인 컨텐츠를 모두 제공가능
WAS초창기에는 WAS의 성능이 부실하여 Apache와 함께 설치했으나 지금은 WAS만으로 충분하다.

<br><br>

## 컨테이너

동적인 데이터들을 처리하여 정적인 페이지로 생성해주는 소프트웨어 모듈

1. Web Server는 웹 브라우저 클라이언트(사용자)로부터 HTTP 요청을 받는다.
2. Web Server는 클라이언트의 요청(Request)을 WAS에 보낸다.
3. WAS는 관련된 Servlet을 메모리에 올린다.
4. WAS는 web.xml을 참조하여 해당 Servlet에 대한 Thread를 생성한다. (Thread Pool 이용)
5. HttpServletRequest와 HttpServletResponse 객체를 생성하여 Servlet에 전달한다.
5-1. Thread는 Servlet의 service() 메서드를 호출한다.
5-2. service() 메서드는 요청에 맞게 doGet() 또는 doPost() 메서드를 호출한다.
6. protected doGet(HttpServletRequest request, HttpServletResponse response)
7. doGet() 또는 doPost() 메서드는 인자에 맞게 생성된 적절한 동적 페이지를 Response 객체에 담아 WAS에 전달한다.
8. WAS는 Response 객체를 HttpResponse 형태로 바꾸어 Web Server에 전달한다.
9. 생성된 Thread를 종료하고, HttpServletRequest와 HttpServletResponse 객체를 제거한다.

<br><br>

## Web Server

웹 서버는 보통 정적인 콘텐츠를 웹 브라우저에게 전송하는 역할
SSL대한 암복호화 처리에 Web server 를 사용
여러 대의 WAS를 연결해 로드 밸런싱 용도로 사용할 수 있다.

아파치에는 CGI (Common Gateway Interface) 를 제공하는데, 인터페이스로서, 웹 서버 상에서 프로그램을 동작시키기 위한 방법을 정의한 프로그램(스크립트) 입니다.

단, 자바는 CGI로 구현되어 있지 않다.
자바 자체가 무겁고, Common 라이브러리와 jEE라는 플랫폼때문에 톰캣이라는 Default servlet 을 통해 정적인 파일을 제공해주기 때문에 웹 서버의 역할을 할수 있다.

기능)

1. 인증
2. 정적 콘텐츠 관리
3. HTTPS 지원
4. 콘텐츠 압축
5. 가상 호스팅
6. 대용량 파일 지원
7. 대역폭 스로틀링

<br><br>

## WAS vs Web Server

1. WAS도 보통 자체적으로 웹 서버 기능을 내장하고 있다.
2. 현재는 WAS가 가지고 있는 웹 서버도 정적인 콘텐츠를 처리하는 데 있어서 성능상 큰 차이가 없습니다.
3. 규모가 커질수록 웹 서버와 WAS를 분리합니다.
4. 자원 이용의 효율성 및 장애 극복, 배포 및 유지보수의 편의성을 위해 웹서버와 WAS를 대체로 분리한다.

<br><br>

## 웹 서버를 사용 시 장점

1. 웹 서버는 WAS보다 간단한 구조로 설정.
2. 대용량웹앱일때는 서버가 여러대인경우가 많다.
3. WAS자체에 문제가 발생할 경우 WAS재시작.
4. 문제가 있는 WAS재시작할 때, 앞단의 웹서버에서 먼저 해당 WAS이용못하도록 막고 재시작하면 해당 웹앱 유저는 WAS 문제 발생 유무를 모르고 이용함. 이를 장애 극복 기능이라고 한다.
5. 대용량 웹앱에선 무중단으로 운영하기위해 상당히 중요한 기능이다.