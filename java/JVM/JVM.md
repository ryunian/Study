# JVM 이란
>JVM (Java Virtual Machine) 은  프로그램을 실행하기 위해 물리적 머신(즉, 컴퓨터)와 유사한 머신을 소프트웨어로 구현한것을 말한다고 할수 있으며,
JVM 역할은 자바 애플리케이션을 클래스 로더를 통해 읽어 들여 자바 API와 함께 실행하는 것이다.
JVM은 JAVA와 OS사이에서 중개자 역할을 수행하여 JAVA가 OS에 구애받지 않고 재사용을 가능하게 해준다. (즉, JAVA는 운영체제에 종속적이지 않고 독립적이다.)


<img src="https://user-images.githubusercontent.com/51253582/127321049-1fff035f-7c5a-470e-bac2-e1c85e573ed9.png"  width="500" height="300">
<img src="https://github.com/ryunian/Study/blob/master/image/java_compile.png?raw=true" width="500" height="300">

그림을 보면 C언어는 운영체제마다 각각에 맞는 컴파일러가 있어야 되지만, JAVA는 그 운영체제에 맞는 JVM만 있으면 잘 동작한다.
그러나 이러한 형태가 장점이 될수도 있고 단점이 될수 있다.

장점)


* 운영체제에 독립적이다. 즉, 한 번만 작성하면 어디에서든 실행할 수 있다. (Write Once Run AnyWhere)
* JVM 의 GC(가비지 콜렉터) 를 통해 자동으로 메모리를 관리해준다.
* C/C++ 등의 전통적인 언어는 플랫폼에 따라 int 형의 크기가 변한다. JVM은 기본 자료형을 명확하게 정의하여 호환성을 유지하고 플랫폼 독립성을 보장한다. 


단점)

* Java Virtual Machine이 반드시 완벽하게 로딩되어야 하기 때문에 프로그램의 초기 시작 시간이 완전한 이진 코드로 컴파일된 프로그램을 실행하는 것에 비해 오래 걸린다.
* 특정 운영체제의 특수한 기능을 호출하거나 하드웨어를 제어하는 등의 일은 JVM으로 할 수 없으며, JNI 같은 Native 코드를 호출하기 위한 인터페이스를 거쳐야 한다.
* Full GC가 일어날때 GC쓰레드를 제외한 모든 스레드가 정지하는 Stop the world 가 발생한다.



*****
# 구조





