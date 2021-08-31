# 1. Package
> 자바에서 패키지란, 비슷한 성격의 클래스들을 모아 만든 자바의 디렉토리이다.  
> 최상단에 package 키워드를 이용해 자바의 클래스의 경로를 명시해주는 것이 "네임스페이스" 이다.

* 하나의 소스파일에는 첫 번째 문장으로 단 한번의 패키지 선언만을 허용한다.
* 모든 클래스는 반드시 하나의 패키지에 속해야 한다.
* 패키지는 점(.)을 구분자로 하여 계층 구조로 구성할 수 있다.
* 패키지는 물리적으로 클래스 파일(.class)을 포함하는 하나의 디렉토리이다.

<br><br>

### 1.1 Built-in 패키지
> 자바에서는 자바 개발자를 위해 여러가지 많은 패키지 및 클래스를 제공해준다. 
> 이러한 패키지들을 Built-in Package 라고 한다.

* java.lang 같은 패키지의 경우 import 를 하지 않아도 default로 사용할 수 있다.  
(모든 소스파일에는 묵시적으로 import문이 선언되어 있기 때문이다.)

* 종류 : java.lang , java.io, java.util, java.awt, java.net 등

<br><br>

# 2. import
> 다른 패키지의 클래스를 사용하기 위해 패키지명이 포함된 클래스 이름을 사용해야한다.
> 하지만, 매번 패키지명을 붙여서 작성하는 불편함을 위해 import 문을 이용해서 클래스의 패키지를 미리 명시할수 있다.
> ex) import java.util.ArrayList;

* 같은 패키지에서 여러 개의 클래스가 사용될떄, import를 여러번 사용하는 대신 ' 패키지명.* ' 이용해서 해당 패키지를 전부 import 할수 있다.

* ' * ' 을 사용하면 컴파일러는 해당 패키지에서 일치하는 클래스 이름을 찾아야 하는 수고를 더 할 뿐이지만  
    실행 시 성능상의 차이는 전혀없다.
    
    
<br><br>
    
# 2.1 static import 
> static import 문을 사용하면 static 멤버를 호출할 때 클래스 이름을 생략할 수 있다.
> 특정 클래스의 static 멤버를 자주 사용할 떄 편리하다.

<br><br>

# 3. CLASSPATH 환경변수
> 클래스를 찾기위한 경로이다.

JVM이 프로그램을 실행할 떄, 클래스 파일을 찾는데 기준이 되는 파일경로를 말한다.
1. 소스코드(.java)를 컴파일하면 소스 코드가 바이트 코드(.class)로 변환된다.
2. java runtime으로 이 .class 파일에 포함된 명령을 실행하려면 먼저 이 파일을 찾을 수 있어야한다.  
이떄, class 파일을 찾을 때 classpath에 지정된 경로를 사용된다.
3. classpath는 .class 파일이 포함된 디렉토리와 파일을 콜론으로 구분한 목록이다.
4. java runtime은 이 classpath에 지정된 경로를 모두 검색해서 특정 클래스에 대한 코드가 포함된 .class 파일을 찾는다.
5. 찾으려는 클래스 코드가 포함된 .class 파일을 찾으면 첫 번째로 찾을 파일을 사용한다.

classpath 를 지정할 수 있는 방법
* 환경 변수 CLASSPATH를 사용하는 방법
* java runtime에 -classpath 플래그를 사용하는 방법

