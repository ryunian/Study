# DNS

> DomainName은 기억하기 쉽다는 장점이 있으나, 라우터가 처리하는 데 어려움이 있다.   
> 그렇기 때문에 IP 주소로 변환을 해줘야하는데 이떄, DNS를 사용한다.

- DNS는 DNS 서버들의 계층구조로 구현된 분산 데이터베이스이다.
- 호스트가 분산 데이터베이스로 질의하도록 허락하는 어플리케이션 계층 프로토콜이다.
- DNS 서버는 주로 BIND (Berkeley Internet Name Domain) 소프트웨어를 수행하는 유닉스 컴퓨터이다.

    
        BIND(berkeley internet name domain)
        DNS를 구현한 소프트웨어의 하나로 가장 오래된 도메인 서비스 시스템으로
        유닉스(unix) 및 리눅스(linux), 윈도우 등 거의 모든 플랫폼을 지원하는 DNS의 모든 기능을 갖춘 소프트웨어 입니다.

- UDP를 사용하며, 재귀적질의(recursive query)와 반복적 질의(iterative query)를 사용하여 매핑한다.

<br><br>

<img src="https://github.com/ryunian/Study/blob/master/image/Domain.png?raw=true" width="500px" height="200px">

<br><br>

* 루트 DNS 서버

    13개의 DNS 루트 네임서버가 모든 재귀 확인자에 알려져 있으며 이들은 재귀 확인자가 DNS 레코드를 요청하는 과정의 첫 단계입니다.
    루트 서버는 도메인 이름을 포함한 재귀 확인자의 쿼리를 수용하며 루트 네임서버는 해당 도메인의 확장자(.com,. net, .org, etc.)에 따라 재귀 확인자를 TLD 네임서버에 보내 응답합니다.

* TLD 네임서버

    TLD 네임서버는 .com, .net 또는 URL의 마지막 점 뒤에 오는 것 같은 일반적인 도메인 확장자를 공유하는 모든 도메인 이름의 정보를 유지한다.

    TLD 서버들은 책임 DNS 서버들에 대한 IP주소들을 제공한다

* 책임 DNS 서버

    책임 DNS 서버는 호스트 네임을 IP주소로 매핑하는 공개적인 DNS 레코드를 제공하는 역할을 한다.

<br><br><br><br>

## 5.1 DNS 쿼리 유형

<img src="https://github.com/ryunian/Study/blob/master/image/DNS1.png?raw=true" width="700px" height="500px">

<br><br>

### 5.1.1 반복적 질의

1) 로컬 DNS에게 요청을 함 -> 로컬은 해당 정보를 가지고 있지 않다면 루트 DNS 서버에게 요청하는 IP 알고 있냐고 물어본다.

2) 루트 DNS는 자기는 모르지만 아마 최상위 DNS 서버(TLD)는 알고 있을 거라고 로컬 DNS에게 알려준다.

3) 로컬 DNS는 다시 최상위 DNS 서버에게 질의를 한다. -> 자기도 모르는데 책임 DNS 서버는 알고 있을꺼라고 알려준다.

4) 로컬 DNS는 책임 DNS 서버에게 알고 있냐고 물어봄 -> 책임 DNS는 알고 있기 때문에 해당 IP 주소를 알려준다.

5) 로컬 DNS는 받아온 정보를 사용자에게 최종적으로 알려주고 자신의 DNS 레코드에 나중에 똑같은 요청에대한 신속한 처리를 위해 저장한다.


<img src="https://github.com/ryunian/Study/blob/master/image/DNS2.png?raw=true" width="700px" height="500px">

<br><br>

**2. 재귀적 질의**

1) 로컬 DNS에게 요청을 함 -> 로컬은 해당 정보를 가지고 있지 않다면 루트 DNS 서버에게 요청하는 IP 알고 있냐고 질의한다.

2) 루트 DNS 서버는 모르기 때문에 최상위 DNS 서버에게 질의한다.

3) 최상위 DNS 서버도 모르기 때문에 책임 DNS 서버에게 질의한다.

4) 책임 DNS 서버는 알고 있기 때문에 알려준다. (최상위 DNS에게 다시)

5) 최상위 DNS는 받은 정보를 다시 루트 DNS에게 알려준다

6) 루트 DNS는 로컬 DNS에게 받은 정보를 알려준다

7) 로컬 DNS는 최종적으로 사용자에게 받은 정보를 전달하고 자신의 DNS 레코드에 해당 정보를 추가한다..

<br><br>

## 5.2 DNS 캐싱

- DNS는 지연 성능 향상과 네트워크의 DNS 메시지 수를 줄이기 위해 캐싱을 사용한다.

- 질의 사슬에서 DNS 서버가 DNS 응답을 받았을 때 로컬 메모리에 응답에 대한 정보를 저장할 수 있다.

- 그렇기 떄문에 호스트 네임과 IP 주소 쌍이 DNS 서버에 저장되고 다른 호스트 네임으로 부터 같은 질의가 DNS 서버로 도착한다면,  
    DNS 서버는 호스트 네임에 대한 책임이 없을 떄조차 원하는 IP주소를 제공할 수 있다.

- 호스트 DNS와 IP 주소 사이의 매핑과 호스트는 영구적인 것이 아니기 때문에 DNS 서버는 어떤기간 (흔히 2일) 이후에 저장된 정보를 제거한다.

<br><br>

## 5.3 AWS의 DNS 구조

<img src="https://github.com/ryunian/Study/blob/master/image/AWS_DNS.png?raw=true" width="700px" height="500px">