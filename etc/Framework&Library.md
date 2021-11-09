# Framework vs Library

## 1. Framework

> 프레임워크란, 프로그램을 구축할 수 있는 뼈대(기반)을 제공하며,  
> 재사용가능하고 확장 가능한 클래스와 인터페이스 및 라이브러리를 제공해주며  
> 개발자는 프레임워크가 제공하는 가이드라인을 따르며 코드를 작성해야 한다.  
> 
>
> 즉, 소프트웨어의 구체적인 부분에 해당하는 설계와 구현을 재사용이 가능하게끔 일련의 협업화된 형태로 클래스들을 제공하는 것이다.

<br>

장점)
- 디자인 패턴을 적절하게 사용하는 데 도움이 된다.
- 어플리케이션 개발에 필요한 시간이 단축된다.
- 버그 발생 가능성을 처리해줌으로써 퀄리티가 향상되고, 유지보수가 쉽다.

<br>

단점)
- 러닝커브가 높다.
- 라이브러리에 비해 다른 프레임워크로의 교체가 까다롭다.
    - Java의 json 라이브러리인 Jackson 을 사용하다가 Gson으로 변경하는데 큰 문제는 없으나  
    mybatis에서 JPA로 변경하는데는 큰 작업이 소모된다.
    
<br><br>

## 2. Library
> 개발 시 활용 가능한 도구들을 모아 모듈화 한것으로,   
> 자주 사용되는 로직을 재사용하기 편리하도록 잘 정리한 일련의 코드들의 집합이라고 할 수 있다.

<br><br>

## 3. Library vs Framework

- 라이브러리와 프레임워크를 사용함으로써 생산성이 높아진다는 공통점이 있다.

- 차이점으로는 '자유성' 이라고 볼 수 있다.
    - Framework는 해당 framework가 제시하는 가이드라인을 따라야하며, 라이브러리는 개발자가 호출하고 싶을때 마치 도서관에서 필요한 책만 빌리듯이 가져다가 쓰면된다.

- 두 단어의 좁은 의미에서 굳이 비교해서 차이를 보자면 제어의역전(IoC) 라고 할 수 있다.
- 그렇다고 해서 모든 프레임워크는 IoC를 포함하고 있는가 하면 답은 '아니요'다. 
아래의 사례를 보자.


    📌 (stackoverflow 발췌 / 링크 2번) 
    After thinking carefully about this subject for over a year 
    I reject the IoC principle as the defining difference between a framework and a library.
    There ARE a large number of popular authors who say that it is, 
    but there are an almost equal number of people who say that it isn't.
    There are simply too many 'Frameworks' out there which DO NOT use IoC to say that it is the defining principle
    

    번역기)
    1년 넘게 이 주제에 대해 곰곰이 생각해 본 결과,
    나는 프레임워크와 도서관 사이의 결정적인 차이점으로서의 IoC 원칙을 거부한다.
    그렇게 말하는 인기 있는 작가들이 많지만, 그렇지 않다고 말하는 사람들은 거의 같다.
    IoC를 사용하지 않는 '프레임워크'가 너무 많아 이를 정의하는 원칙이라고 말할 수 없다.


<br><br>

### 사례1) JAVA의 Collection Framework

> 콜렉션 프레임워크는 인터페이스와 클래스들의 집합으로 일반적으로 재사용 가능한 데이터 구조를 표현한다.
> 
> 이름을 보아 Framework 라고 지칭하고 있다. 하지만 Collection Framework 엔 IoC가 없다.
> 물론 아래 위키피디아의 설명을 보면 프레임워크라고 하지만 라이브러리 방식으로 작동한다고 한다. 
>
> (위키피디아 : [https://en.m.wikipedia.org/wiki/Java_collections_framework](https://en.m.wikipedia.org/wiki/Java_collections_framework)) 

<br><br>

### 사례2) Bootstrap

<img src="https://github.com/ryunian/Study/blob/master/image/bootstrap.png?raw=true" width="800px" height="200px">

<br><br>

> 부트스트랩의 소개글을 보면 Bootstrap을 프레임워크라고 부르고 있다.  
>
> 부트스트랩 또한 IoC가 없으며 Collection Framework 에 비해 (JDK 1.2 1998년 출시)  
> 나온지 오래되지 않았음에도 (2011년 출시)  Framework 라고 소개하고 있다.


<br><br>

출처)  
[https://stackoverflow.com/questions/25732413/does-every-framework-rely-on-ioc](https://stackoverflow.com/questions/25732413/does-every-framework-rely-on-ioc)  
[https://stackoverflow.com/questions/148747/what-is-the-difference-between-a-framework-and-a-library?rq=1](https://stackoverflow.com/questions/148747/what-is-the-difference-between-a-framework-and-a-library?rq=1)  
[https://stackoverflow.com/questions/3057526/framework-vs-toolkit-vs-library](https://stackoverflow.com/questions/3057526/framework-vs-toolkit-vs-library)  