# 1. JVM 이란
>JVM (Java Virtual Machine) 은  프로그램을 실행하기 위해 물리적 머신(즉, 컴퓨터)와 유사한 머신을 소프트웨어로 구현한것을 말한다고 할수 있으며,
JVM 역할은 자바 애플리케이션을 클래스 로더를 통해 읽어 들여 자바 API와 함께 실행하는 것이다.
JVM은 JAVA와 OS사이에서 중개자 역할을 수행하여 JAVA가 OS에 구애받지 않고 재사용을 가능하게 해준다. (즉, JAVA는 운영체제에 종속적이지 않고 독립적이다.)

<br><br>
<img src="https://user-images.githubusercontent.com/51253582/127321049-1fff035f-7c5a-470e-bac2-e1c85e573ed9.png"  width="500" height="300">
<img src="https://github.com/ryunian/Study/blob/master/image/java_compile.png?raw=true" width="500" height="300">

그림을 보면 C언어는 운영체제마다 각각에 맞는 컴파일러가 있어야 되지만, JAVA 는 그 운영체제에 맞는 JVM 만 있으면 잘 동작한다.
그러나 이러한 형태가 장점이 될수도 있고 단점이 될 수 있다.

장점)


* 운영체제에 독립적이다. 즉, 한 번만 작성하면 어디에서든 실행할 수 있다. (Write Once Run AnyWhere)
* JVM 의 GC(가비지 콜렉터) 를 통해 자동으로 메모리를 관리해준다.
* C/C++ 등의 전통적인 언어는 플랫폼에 따라 int 형의 크기가 변한다. JVM은 기본 자료형을 명확하게 정의하여 호환성을 유지하고 플랫폼 독립성을 보장한다. 


단점)

* Java Virtual Machine이 반드시 완벽하게 로딩되어야 하기 때문에 프로그램의 초기 시작 시간이 완전한 이진 코드로 컴파일된 프로그램을 실행하는 것에 비해 오래 걸린다.
* 특정 운영체제의 특수한 기능을 호출하거나 하드웨어를 제어하는 등의 일은 JVM으로 할 수 없으며, JNI 같은 Native 코드를 호출하기 위한 인터페이스를 거쳐야 한다.
* GC가 일어날때 GC쓰레드를 제외한 모든 스레드가 정지하는 Stop the world 가 발생한다.    
   
   
<br><br>
# 2. 구조
<img src="https://github.com/ryunian/Study/blob/master/image/JVM.jpg?raw=true" width="700" height="500">

<br><br>
#### 2.1 자바프로그램 실행과정
> 1. 프로그램이 실행되면 JVM은 OS로부터 이 프로그램이 필요로 하는 메모리를 할당받는다.
   JVM은 이 메모리를 용도에 따라 여러 영역으로 나누어 관리한다.
> 2. 자바 컴파일러(javac)가 자바 소스코드(.java)를 읽어들여 자바 바이트코드(.class)로 변환시킨다.
> 3. Class Loader를 통해 class파일들을 JVM으로 로딩한다.
> 4. 로딩된 class파일들은 Execution engine을 통해 해석된다.
> 5. 해석된 바이트코드는 Runtime Data Areas 에 배치되어 실질적인 수행이 이루어지게 된다.
> 이러한 실행과정 속에서 JVM은 필요에 따라 Thread Synchronization과 GC같은 관리작업을 수행한다.

<br><br>
### 2.2.1. Class Loader
> JVM내로 클래스(.class파일)를 로드하고, 링크를 통해 배치하는 작업을 수행하는 모듈이다. Runtime 시에 동적으로 클래스를 로드한다. jar파일 내 저장된 클래스들을 JVM위에 탑재하고 사용하지 않는 클래스들은 메모리에서 삭제한다. (컴파일러 역할) 자바는 동적코드, 컴파일 타임이 아니라 런타임에 참조한다. 즉, 클래스를 처음으로 참조할 때, 해당 클래스를 로드하고 링크한다는 것이다.  그 역할을 클래스 로더가 수행한다.    
> 로딩 - 링크 - 초기화 순으로 진행되며   
> 1. 로딩 : 클래스를 읽어오는 과정   
> 2. 링크 : 레퍼런스 연결 과정   
> 3. 초기화 : static 값 초기화하고 변수 할당하는 과정

<br><br>
### 2.2.2. Execution Engine
* [Garbage collector]
* [JIT]
* 인터프리터
> 실행 엔진은 자바 바이트 코드를 명령어 단위로 읽어서 실행한다. 하지만 이 방식은 인터프리터 언어의 단점을 그대로 갖고 있다. 한 줄 씩 수행하기 때문에 느리다는 것이다.

<br><br>
### 2.2.3. Runtime Data Areas
* Method Area (Static area)
   > JVM이 읽어들인 클래스와 인터페이스 대한 런타임 상수 풀, 멤버 변수(필드), 클래스 변수(Static 변수), 생성자와 메소드를 저장하는 공간이다.
   > 1) Field Information : 멤버변수의 이름, 데이터 타입, 접근 제어자에 대한 정보
   > 2) Method Information : 메소드의 이름, 리턴타입, 매개변수, 접근제어자에 대한 정보
   > 3) Type Information : class인지 interface인지의 여부 저장 /Type의 속성, 전체 이름, 
        super class의 전체 이름(interface 이거나 object인 경우 제외)

* Stack Area
   > 프로그램 실행과정에서 임시로 할당되었다가 메소드를 빠져나가면 바로 소멸되는 특성의 데이터를 저장하기 위한 영역이다. 각종 형태의 변수나 임시 데이터, 스레드나 메소드의 정보를 저장한다.
   > 메소드 호출 시마다 각각의 스택 프레임(그 메서드만을 위한 공간)이 생성된다. 메서드 수행이 끝나면 프레임 별로 삭제를 한다. 
   > 메소드 안에서 사용되는 값들(local variable)을 저장한다. 또 호출된 메소드의 매개변수, 지역변수, 리턴 값 및 연산 시 일어나는 값들을 임시로 저장한다.

* Heap Area
   > Heap 영역에는 주로 긴 생명주기를 가지는 데이터들이 저장된다. (대부분의 오브젝트는 크기가 크고, 서로 다른 코드블럭에서 공유되는 경우가 많다)
   > 애플리케이션의 모든 메모리 중 stack 에 있는 데이터를 제외한 부분이라고 보면 된다.
   > 모든 Object 타입(Integer, String, ArrayList, ...)은 heap 영역에 생성된다.
   > 몇개의 스레드가 존재하든 상관없이 단 하나의 heap 영역만 존재한다.
   > Heap 영역에 있는 오브젝트들을 가리키는 레퍼런스 변수가 stack 에 올라가게 된다.
   > Heap 영역에 있는 오브젝트는 GC의 대상이 된다.
   > java8 이후, Permanent 영역을 자바8에서부터는 Metaspace라는 영역으로 대체하였고, Static Object는 Metaspace영역이 아닌 Heap영역으로 옮겨져 최대한 GC의 대상이 되도록 변경됬다.

* PC Register
   > Thread 가 시작될 때 생성되며 생성될 때마다 생성되는 공간으로 스레드마다 하나씩 존재한다. 
   > Thread가 어떤 부분을 어떤 명령으로 실행해야할 지에 대한 기록을 하는 부분으로 현재 수행 중인 JVM 명령의 주소를 갖는다.

* Native Method Stack
   > 자바 프로그램이 컴파일되어 생성되는 바이트 코드가 아닌 실제 실행할 수 있는 기계어로 작성된 프로그램을 실행시키는 영역이다. 
   > JAVA가 아닌 다른 언어로 작성된 코드를 위한 공간이다. JAVA Native Interface를 통해 바이트 코드로 전환하여 저장하게 된다. 
   > 일반 프로그램처럼 커널이 스택을 잡아 독자적으로 프로그램을 실행시키는 영역이다. 이 부분을 통해 C code를 실행시켜 Kernel에 접근할 수 있다.


<br><br>
### 2.3 Compiler 기술들
* Hot Spot Detection
* Method inlining
* [reflection]



[Garbage collector]: Garbage%20Collection.md
[JIT]: JIT.md
[reflection]: https://github.com/ryunian/Study/blob/master/java/Reflection/Reflection.md
