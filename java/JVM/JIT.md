# JIT 란?
> JIT (Just-in-Time) C에서 하는 것처럼 프로그램을 실행하기 전에 처음 한 번 컴파일하는 대신, 프로그램을 실행하는 시점에서 필요한 부분을 즉석으로 컴파일하는 방식을 말한다.   
> JIT 컴파일러는 같은 코드를 매번 해석하는 대신 처음 실행될 때 인터프리트를 하면서 자주 쓰이는 **[코드를 캐싱]** 한 뒤 (JVM 경우, 메소드 영역에 있는 코드 캐시(Code Cache) 공간에 JIT로 컴파일된 기계어 코드를 캐싱한다.)   
> 이후에는 캐싱된 코드를 가져다 쓰기 때문에 인터프리터의 느린 실행 속도를 개선할 수 있다.   
>> JIT 컴파일러가 바이트 코드를 컴파일하는 데 걸리는 시간은 전체 실행 시간에 추가되며 JIT에 의해 **컴파일 된 메서드가 자주 호출되지 않으면** 바이트 코드를 실행하는 **인터프리터보다 실행 시간이 길어질 수 있습니다.**   

<img src="https://github.com/ryunian/Study/blob/master/image/jit.png?raw=true" width="700" height="500">


# 유형
> VM 개발자들은 흔히 c1(컴파일러 1, 클라이언트 컴파일러)와 c2(컴파일러 2, 서버 컴파일러)라는 이름으로 부릅니다.


#### 클라이언트 측 컴파일러
 > 사용 가능한 리소스가 적고 대부분의 경우 애플리케이션 시작 시간에 민감한 클라이언트 측 애플리케이션을 위해 설계되었습니다. C1은 코드 프로파일 링에 성능 카운터를 사용하여 간단하고 상대적으로 방해가되지 않는 최적화를 가능하게합니다.
* 클라언트 모드 총 세단계   
  1. 바이트코드를 해석해서 최적화를 쉽게 하기 위해, HIR이라고 하는 정적인 바이트코드 표현을 만듬   
  2. HIR로부터 플랫폼에 종속적인 중간표현식 (LIR) 을 만듬   
  3. LIR을 사용해 기계어 생성   


#### 서버 측 컴파일러
> 서버 측 엔터프라이즈 Java 애플리케이션과 같은 장기 실행 애플리케이션의 경우 클라이언트 측 컴파일러로는 충분하지 않을 수 있습니다. 대신 C2와 같은 서버 측 컴파일러를 사용할 수 있습니다. C2는 일반적으로 -server시작 명령 줄에 JVM 시작 옵션 을 추가하여 활성화됩니다 . 대부분의 서버 측 프로그램은 오랫동안 실행될 것으로 예상되므로 C2를 활성화하면 단기 실행되는 경량 클라이언트 응용 프로그램보다 더 많은 프로파일 링 데이터를 수집 할 수 있습니다. 따라서 더 고급 최적화 기술과 알고리즘을 적용 할 수 있습니다.
1. 일반적인 컴파일러 최적화 기술들을 이용해 일단 코드들을 최적화 한다.
    * 죽은 코드 삭제(Dead Code Elimination), loop 변수의 끌어올리기(Loop invariants hoisting), 공통 부분식 제거(Common Subexpression Elimination), 상수 지연(Constant propagation), 전역 코드 이동(Global Code motion) 등   


2. 자바에 최적화된 최적화를 수행한다.   
    * Null Check 삭제, 배열의 Range Check 삭제, 예외처리 경로 최적화.   
    * 대단위 RICS 레지스터들을 최대한 활용하기 위한, Graph연산을 통한 register할당   
    * 이런 과정을 통해 상대적으로 느린 속도로 JIT이 수행된다. 하지만 코드의 수행은 더욱 빠르다.   

출처 :    
https://aboullaite.me/understanding-jit-compiler-just-in-time-compiler/   
https://jeongjin984.github.io/posts/JVM/   
https://www.javacodegeeks.com/2011/07/jvm-options-client-vs-server.html   
https://velog.io/@youngerjesus/자바-JIT-컴파일러
