# HTTP

## 1. HTTP 란

> 웹 상에서 웹 서버 및 웹브라우저 상호 간의 데이터 전송을 위한 응용계층 프로토콜  
> 포트 번호는 80포트를 사용하며, 입력시 생략이 가능하다. (HTTPS의 경우 443)
> 
> 거의 모든 형태의 데이터를 전송이 가능하며,  
> 주로 TCP를 사용하고 HTTP/3 부터 UDP를 사용한다

    URI (uniform Resource identifier)
    웹에서 각각의 리소스를 식별하기 위한 방법

    URL : 특정 서버의 한 리소스에 대한 구체적인 위치를 기술
    URN : 리소스의 위치에 영향을 받지 않는 유일무이한 이름을 사용 (잘 사용되지 않음)

<br><br>

## 2. HTTP 특징
- 클라이언트 서버 구조  
모든 자원들을 서버에서 관리하면서 클라이언트 요청에 따라 필요한 정보를 제공하는 모델

- 장점
    - 서비스의 구성이 직관적이다. (즉, 클라이언트의 역할과 서버의 역할이 구분된다.)
    - 확장성이 좋으며 유지보수에 용이하다.

- 단점
    - 해킹공격과 디도스 및 랜섬웨어 공격에 취약하다.

<br><br>

### 2.1 무상태 (Stateless)
> 연결을 끊는 순간 클라이언트와 서버의 통신은 끝나며 상태정보를 유지하지 않는다.

<br>

장점) 
1. 서버 설계, 구현 및 아키텍쳐가 간단하다.
2. 통신하고 있는 서버가 장애가 발생해도 로드밸런싱을 통해 새로운 서버와 연결하여 문제없이 처리가 가능하다.
3. 서버 확장성이 높다 (트래픽이 급증함에 따라 서버를 scale out을 하여, 서버장애없이 트래픽을 받을수 있게 된다.)

<br>

단점)
1. 매 요청시 마다 상태정보를 전달해야 하기 때문에 그만큼 네트워크 리소스를 소비해야한다.
2. 상태유지가 꼭 필요한 경우(로그인)가 있다. (쿠키, 세션으로 유지시킨다)


    Stateful  
    
    서버는 각 요청에 제공한 정보와 이전 요청에서 저장된 정보를 기반으로 요청을 처리합니다.
    서버는 이전 요청을 처리하는 동안 생성된 상태 정보에 액세스하여 유지보수해야 합니다.
    
   <br>

    Scaling (Scale up , Scale out)
    
    ▶ Scale Up
    서버 자체의 Spec을 향상시킴으로써 성능을 향상시킨다.
    서버 자체의 갱신이 빈번하여 정합성 유지가 어려운경우 Scale Up이 효과적이다.

    성능 향상에 한계가 있으며 성능 증가에 따른 비용부담이 크다. 대신 관리 비용이나 운영 이슈아 Scale Out 에 비해 적어 난이도는 쉬운편이다.
    대신 서버 한대가 부담하는 양이 많기 때문에 다양한 이유로 서버에 문제가 생길 경우 큰 타격이 불가피하다.

    

    ▶ Scale Out
    서버의 개수를 늘려 동시 처리 능력을 향상시킨다.
    단순한 처리의 동시 수행에 있어 Load 처리가 버거운 경우 적합하다.
    특히 Sharding DB를 Scale Out 하게 된다면 기존의 샤딩 알고리즘과 충돌이 생길수도 있고, 원하는 대로 부하의 분산이 안생길 수 있으니 주의해야한다.

    스케일 아웃은 일반적으로 저렴한 서버 여러 대를 사용하므로 가격에 비해 뛰어난 확장성 덕분에 효율이 좋지만 대수가 늘어날 수록 관리가 힘들어지는 부분이 있고,
    아키텍쳐의 설계 단에서부터 고려되어야 할 필요가 있다.

<img src="https://github.com/ryunian/Study/blob/master/image/scaleup.png?raw=true" width="500" height="300"/>
<img src="https://github.com/ryunian/Study/blob/master/image/scaleout.png?raw=true" width="500" height="300"/>
<br><br>

### 2.2 비 연결성 (connectionless)
- HTTP는 기본이 연결을 유지하지 않는 모델이다.
- 일반적으로 초 단위 이하의 빠른 속도로 응답
- 실제 수천명이 서비스를 사용해도 실제 서버에서 동시에 처리하는 요청은 적다.
- 서버 자원을 매우 효율적으로 사용할 수 있다.

<br>

단점 :통신을 할떄마다 TCP/IP 연결을 새로 맺어야한다 (3 way handshake를 통한 오버헤드)

<br><br>

### 2.3 비지속연결과 지속연결
- 비지속연결은 각 요청객체마다 새로운 연결이 설정되어야 함.
    - TCP버퍼가 할당되어야 하고 TCP변수들이 클라이언트와 서버 양쪽에 유지 되어야 함으로 웹서버에 심각한 부담을 준다.
    - 또한 각 객체마다 2RTT를 필요로 하는데 이를 별도로 진행해야 함으로 성능이 떨어짐.

- 지속연결은 서버가 클라이언트의 요청의 대해서 응답한 후 TCP연결을 유지하는 방식으로,  
    같은 클라이언트-서버간의 요청-응답이 동일한 TCP연결을 통해 이루어지는 연결방식.
- 지속연결의 이점은 TCP 커넥션의 연결과 종료를 반복되는 오버헤드를 줄여주어 서버 부담이 줄어든다.
- HTTP의 default mode는 파이프라이닝을 이용한 지속연결이다.

        
    RTT (round-trip time )
      패킷이 클라이언트로부터 서버까지 가고, 다시 클라이언트에게 돌아오는데 걸리는 시간

<br><br><br>

## 3. HTTP 버전

### 3.1 HTTP/0.9

- HTTP 가장 초기 버전으로, 원래 버전 번호가 없다.
- 단순히 HTML 파일만 전송하는 프로토콜
- 사용가능 메서드 : GET
- HTTP 헤더가 존재하지 않음

<br><br>

### 3.2 HTTP/1.0

- 헤더를 통해 HTTP 버전 코드를 송신하고, 상태 코드를 회신 받을 수 있음
- 헤더의 Content-Type 구분을 통해 HTML이 아닌 다른 형태의 문서 전송 가능
- 사용가능 메서드 : GET, POST, PUT

<br><br>

### HTTP 1.1

- Keep Alive 추가 
  - 커넥션이 재사용될 수 있게 하여 재연결 지연 감소
- 파이프라이닝 추가
  - 첫번째 요청에 대한 응답이 완전히 전송되기 이전에 두번쨰 요청 전송 가능
  - 커뮤니케이션 레이턴시를 감소
- Host 헤더 추가
- 청크된 응답 지원
- 캐시 제어 메커니즘 추가
- 사용가능 메서드 : GET, POST, PUT, DELETE, TRACE, OPTIONS
- HTTP 1.1의 단점을 해결하기 위한 방법들
    - Image spriting
    - Domain Sharing
    - Minify css/ javascript
    - SPDY


    Pipelining
    
    하나의 커넥션에서 응답을 기다리지 않고 순차적인 여러 요청을 연속적으로 보내고
    그 순서에 맞춰 응답을 받는 방식으로 지연 시간을 줄이는 방법

<br>

    HOL Blocking
    
    HOL은 Head of Line의 줄임말로서 앞선 요청에 의해 뒤에 요청이 지연되는 것을 의미합니다.
    HTTP Pipelining  통해 한 번에 여러 개의 이미지를 요청하는 경우를 생각해봅시다.
    가장 앞에 요청한 이미지가 응답이 지연되면 두, 세번째 이미지도 지연이 발생합니다.

    TCP 안에 여러 개의 HTTP 요청이 왔으므로 완료된 응답부터 보내면 되지 않을까라고 생각할 수 있지만 서버는 TCP에서 요청을 받은 순서대로 응답을 해야합니다.

<br>

    무거운 Header

    클라이언트와 서버 간에 수 많은 http 요청이 발생할 것이고 header의 정보는 대부분 동일합니다.
    하지만 HTTP 1.1에서는 이러한 헤더를 중복해서 계속 보낼 뿐 아니라 cookie 정보 역시 매 요청마다 헤더에 포함되어 전송됩니다.

    즉 불필요한 데이터를 주고 받는데 네트워크 자원이 소비되는 문제가 발생합니다.

<br><br>

### HTTP 2.0
- 구글이 만든 SPDY를 기반으로 표준화
- 멀티플레싱 : 여러개의 HTTP 요청/응답을 하나의 TCP연결에서 보냄
- HPACK : 기법을 통해서 요청/응답 헤더를 압축
- 흐름 제어 : 복수의 스트림이 단일 연결에서 전송되지만 각각의 흐름 제어를 통해서 클라이언트에서 받아들일수 있을 만큼만 전송 가능
- TLS 내재화 : HTTP/2 는 스펙상 평문으로도 보낼 수 있지만 실제 브라우저는 모두 TLS위에서만 동작한다.
- 서버 푸시 : 클라이언트가 명시적으로 요청하지 않아도 서버가 선제적으로 보낼 수 있음
- QoS : 요청에 대해 우선순위 부여 가능


    Multiplexed Streams
    
    HTTP 1.1의 HTTP Pipelining 의 개선안으로 하나의 Connection으로 동시에 여러 개의 메세지를 주고 받을 수 있다.
    또한 응답은 요청 순서에 상관없이 Stream으로 받기 때문에 HOL Blocking 도 발생하지 않는다.

<br>

    Stream Prioritization
    응답에 대한 우선순위를 정해 우선순위가 높을수록 응답을 빨리 한다.

<br>

    Server Push
    HTML문서 상에 필요한 리소스를 클라이언트 요청없이 보내줄 수 있다

<br>

    Header Compression
    
    HTTP 1.1의 경우 이전 요청과 중복되는 Header도 똑같이 전송하느라 네트워크 자원을 불필요하게 낭비하는 문제가 있었다.
    HTTP 2.0의 경우, Header Table과 Huffman Encoding을 사용하는 HPACK 압축방식으로 이를 개선하였다.

    클라이언트와 서버는 각각 Header Table을 관리하고 이전 요청과 동일한 필드는 table의 index만 보내고,  
    변경되는 값은 Huffman Encoding 후 보냄으로서 Header의 크기를 경령화 하였다.

<br><br>

### HTTP 3.0

- QUIC (Quick UDP Internet Connections) 도입
- UDP에서 구현 된 TCP + TLS + HTTP/2와 유사
- QUIC의 3-handshake: 속도가 더 빨라지진 않았지만 인증, 암호화 매커니즘 포함

<br><br><br>

## 4. HTTP 메서드

<img src="https://github.com/ryunian/Study/blob/master/image/httpmethod.png?raw=true" width="800" height="400"/>
<br>

- GET 👍
    > 리소스 조회  
    서버에 전달하고 싶은 데이터는 query(쿼리 파라미터, 쿼리 스트링)를 통해서 전달  
    메시지 바디를 사용해서 데이터를 전달할 수 있지만, 지원하지 않는 곳이 많아서 권장x

<br>

- POST 👍

    > 요청 데이터 처리, 주로 등록에 사용
    메시지 바디를 통해 서버로 요청 데이터 전달

    - POST 메서드는 대상 리소스가 리소스의 고유 한 의미 체계에 따라 요청에 포함된 표현을 처리하도록 요청한다.

    - 역할
        - 새 리소스 생성(등록)
        - 요청 데이터 처리
        - 다른 메서드로 처리하기 애매한 경우

<br>

- PUT 👍

    > 리소스를 대체, 해당 리소스가 없으면 생성

    POST와는 다르게 클라이언트가 리소스 위치를 알고 URI를 지정한다.   
    (즉, 클라이언트가 리소스를 식별)  
    또한 PUT의 경우 멱등하지만 POST는 멱등하지 않는다.

<br>

- PATCH 👍
    > 리소스 부분 변경

    PUT의 경우 자원전체를 갱신하는 의미지만,   
    PATCH는 해당 자원의 일부를 교체하는 의미로 사용된다.  
    
<br>

- DELETE 👍
    > 리소스 삭제

<br>

- HEAD
    > Get과 동일하지만 메시지 부분을 제외하고, 상태 줄과 헤더만 반환

    정보확인, 헬스체크, 버전확인 등에 사용된다.

<br>

- OPTIONS
    > 대상 리소스에 대한 통신 가능 옵션(메서드)을 설명 

    주로 CORS에서 사용된다.

<br>

- CONNECT
    > 대상 자원으로 식별되는 서버에 대한 터널을 설정

<br>

- TRACE
    > 대상 리소스에 대한 경로를 따라 메시지 루프백 테스트를 수행

<br><br><br>

## 5. HTTP 속성

### 5.1 Safe

- 호출해도 리소스를 변경하지 않는다.

- 주의사항
    - 안전은 해당 리소스만 고려한다.
    - 즉, 계속 호출해서 로그 같은게 쌓여서 장애가 발생시는 고려하지 않는다.

<br><br>

### 5.2 멱등(Idempotent)

- 한 번 호출하든 두 번 호출하든 100번 호출하든 결과가 똑같다

    f ( f(x) ) = f(x)

- 멱등 메서드
    1. GET : 한번 조회하든, 두 번 조회하든 같은 결과가 조회된다.
    2. PUT : 결과를 대체한다. 따라서 같은 요청을 여러번 해도 최종 결과는 같다.
    3. DELETE : 결과를 삭제한다. 같은 요청을 여러번 해도 삭제된 결과는 똑같다.
    4. POST : "멱등이 아니다", 두 번 호출하면 같은 결제가 중복해서 발생할 수 있다.

- 활용
    - 자동 복구 메커니즘
    - 서버가 TIMEOUT 등으로 정상 응답을 못주었을 떄, 클라이언트가 같은 요청을 다시 해도 되는가? 판단하는 근거

- 주의사항
    - 멱등은 외부 요인으로 중간에 리소스가 변경되는 것 까지는 고려하지는 않는다.

<br><br>

<br><br>

### 5.3 캐시 가능 (Cacheable)

- 응답 결과 리소스를 캐시해서 사용해도 되는가?
- GET, HEAD, POST, PATCH 캐시가능

- 실제로는 GET, HEAD 정도만 캐시로 사용
- POST, PATCH는 본문 내용까지 캐시 키로 고려해야 되는데 구현이 쉽지않음


<br><br><br>

## 6. HTTP 상태코드
> 클라이언트가 보낸 요청의 처리 상태를 응답에서 알려주는 기능

<br><br>

### 6.1  Infomational 1xx
> 요청이 수신되어 처리중

<br><br>

### 6.2 Successful 2xx
> 요청 정상 처리

- **200 OK** 👍
    - 성공적으로 처리했을 때 쓰인다
- **201 Created** 👍
    - 요청이 성공적으로 처리되어서 리소스가 만들어졌음을 의미한다.
- 202 Accepted
    - 요청이 받아들여졌지만 처리되지 않았음을 의미한다.
    - 배치(batch) 처리 같은 곳에서 사용
- 204 No Content
    - 성공적으로 처리했지만 컨텐츠를 제공하지는 않는다.
- 206 Partial Content
    - 컨텐츠의 일부 부분만 제공한다.
    - 보통 클라이언트에서 시작 범위나 다운로드할 범위를 지정한 경우 자동으로 해당 부분만 제공할 때 사용하는 코드이다.

<br><br>

### 6.3 Redirection 3xx
> 요청을 완료하려면 추가 행동이 필요
3xx 응답의 결과에 Location 헤더가 있으면, Location 위치로 자동 이동(리다이렉트)

<br>

- **영구 리다이렉션**

    > 특정 리소스의 URI가 영구적으로 이동되며 원래의 URL 사용하지 않고 검색 엔지 등에서도 변경을 인지한다.

    - 301 Moved Permanently 👍
        - 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음
    - 308 Permanent Redirect
        - 301과 기능은 같음
        - 리다이렉트시 요청 메서드와 본문 유지 (HTTP 메소드의 변경을 허용하지 않는다.)

<br>

- **일시 리다이렉션**

    > 리소스의 URI가 일시적으로 변경되므로 검색 엔진 등에서 URL을 변경하면 안된다.

    - 302 Found 👍
        - 리다이렉트시 요청 메서드가 Get으로 변하고 본문이 제거될 수 있음
    - 307 Temporary Redirect
        - 302와 기능은 같음
        - 리다이렉트시 요청 메서드와 본문유지 (HTTP 메소드의 변경을 허용하지 않는다.)
    - 303 See Other
        - 302와 기능은 같음
        - 리다이렉트시 요청 메서드가 GET으로 변경



    PRG: Post / Redirect / Get
    
    POST로 주문후에 새로 고침으로 인한 중복 주문 방지
    POST로 주문후에 주문 결과 화면을 GET 메서드로 리다이렉트
    새로고침해도 결과 화면을 GET으로 조회
    중복 주문 대신에 결과 화면만 GET으로 다시 요청

<br>

- **특수 리다이렉션** : 결과 대신 캐시를 사용
    - 300 Multiple Choices
        - 안쓴다
    - 304 Not Modified 👍
        - 캐시를 목적으로 사용
        - 클라이언트에게 리소스가 수정되지 않았음을 알려준다.

            따라서 클라이언트는 로컬 PC에 저장된 캐시를 재사용한다. (캐시로 리다이렉트)

        - 304 응답은 응답에 메시지 바디를 포함하면 안된다. (로컬 캐시를 사용해야 하므로)
        - 조건부 GET, HEAD 요청시 사용

<br><br>

### 6.4 4xx (Clinet Error)

> 클라이언트 오류, 잘못된 문법 등으로 서버가 요청을 수행할수 없음

- 400 Bad Request 👍
    - 클라이언트가 잘못된 요청을 해서 서버가 요청을 처리할 수 없음
- 401 Unauthorized 👍
    - 클라이언트가 해당 리소스에 대한 인증이 필요함
    - 인증(Authentication) 되지 않음
    - 401 오류 발생 시 응답에 WWW-Authenticate 헤더와 함께 인증 방법을 설명
- 403 Forbidden 👍
    - 서버가 요청을 이해했지만 승인을 거부함
    - 주로 인증 자격 증명은 있지만, 접근 권한이 불충분한 경우
- 404 Not Found 👍
    - 요청 리소스가 서버에 없음
    - 또는 클라이언트가 권한이 부족한 리소스에 접근할 때 해당 리소스를 숨기고 싶을떄

<br><br>

### 6.5 5xx (Server Error) :

> 서버 오류, 서버가 정상 요청을 처리하지 못함
서버에 문제가 있기 떄문에 재시도 하면 성공할 수도 있음

5xx오류는 비즈니스 로직상 불가능한 것을 처리하는게 아닌 서버 내부적으로 문제가 발생하였을떄 발생해야 하며, 왠만하면 발생시키면 안된다.

- 500 Internal Server Error 👍
    - 서버 내부 문제로 오류 발생
    - 각종 에러로 비정상 종료를 하는 경우 이 응답코드를 보낸다.
- 502 Bad Gateway
    - 게이트웨이 불량
    - 게이트웨이가 연결된 서버로부터 잘못된 응답을 받았을 때 사용된다.
- 503 Service Unavailable
    - 서버가 일시적인 과부화 또는 예정된 작업으로 잠시 요청을 처리할 수 없음
    - Retry-After 헤더 필드로 얼마뒤에 복구되는지 보낼 수도 있음
- 504 Gateway Timeout
    - 게이트웨이 시간초과
    - 게이트웨이가 연결된 서버로부터 응답을 받을 수 없었을 때 사용된다.

<br><br><br>

## 7. HTTP 메시지 구조

### 7.1 Request

- 요청 라인 : GET / HTTP/1.1
    - 요청 메소드 : GET, POST, PUT, DELETE
    - 요청 URL
    - HTTP 버전
- 요청 헤더 : 키-값 방식으로 들어감
    - Accept : 클라이언트가 받을 수 있는 컨텐츠
    - Cookie : 쿠키
    - Content-Type : 메세지 바디 종류
    - Content-Length : 메세지 바디 길이
    - If-Modified-Since : 특정 날짜 이후에 변경됐을 때만
- 요청 바디(엔티티)

Content-Type과 Body

1. form 형태 : URLEncoded 방식
    - application/x-www-form-urlencoded
    - 메세지 바디 : 쿼리 문자열
2. json 형태
    - application/json
3. 멀티파트 형태 : 이진파일 넘길 때 사용, 하나의 메세지 바디에 파트를 나눠서 작성
    - boundary는 파트 구분자
    - multipart/form-data: boundary=frontier

<br>

### 7.2 Response

#### 메세지 구조

- 응답 라인
    - 버전
    - 상태 코드
    - 상태 메세지 : HTTP/1.1 200 OK
- 응답 헤더
    - Content-Type : 바디 데이터의 타입
    - Content-Length : 바디 데이터 크기
    - Set-Cookie : 쿠키 설정
    - ETag : 엔티티 태그
- 응답 바디 : HTML, JSON, Octet Stream 등이 있다.

<br>

#### 서버가 사용하는 헤더라인
1. Connection : close : 클라이언트에게 메시지를 보낸후 TCP 연결을 닫는데 사용한다.

2. Date : HTTP 응답이 서버에 의해 생성되고 보낸 날짜와 시간을 나타낸다. 
(서버가 파일 시스템으로부터 객체를 추출하고 응답 메시지에 그 객체를 삽입하여 응답 메시지를 보낸 시간을 의미한다.)

3. Server : 메시지가 아파치 웹 서버에 의해 만들어졌음을 나타낸다. HTTP요청 메시지의 User-agent: 헤더 라인과 비슷하다.

4. Last-Modified : 객체가 생성되거나 마지막으로 수정된 시간과 날짜를 나타낸다.
(로컬 클라이언트와 네트워크 캐시서버(프록시서버)에서의 객체 캐싱에 필요하다)

5. Content-Length : 송신되는 객체의 바이트 수를 나타낸다.

6. Content-Type : 개체 몸체 내부의 객체가 HTML 텍스트인 것을 나타낸다.
객체 타입은 파일 확장자로 나타내는 것이 아니라 공식적으로 Content-type: 헤더로 나타낸다.