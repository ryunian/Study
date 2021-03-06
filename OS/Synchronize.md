# 1. 동기화
### 1.1 Race Condition
   두 개 이상의 concurrent 한 스레드들이 공유된 자원에 접근하려고 할때 , 동기화된 메커니즘없이 접근하려고 하는 상황
   
<br><br>
    
### 1.2 임계영역
   동일한 자원을 동시에 접근하는 작업을 실행하는 코드영역을 임계영역 (critical section) 이라고 한다.  
   이러한 영역에 동기화 매커니즘을 사용하지 않고 접근할 시 (Race Condition), 나타나는 문제를 임계영역문제라고 한다. 
    
   * 문재 해결을 위한 기본 조건 : 상호배제, 진행, 한정된 대기
        1. 상호배제 (Mutual Exclusion)
            어떤 프로세스가 임계영역을 실행하고 있을 때, 다른 프로세스는 임계영역을 실행할 수 없다.
            즉, 어떤 프로세스가 임계 영역을 실행할 떄, 다른 프로세스는 코드 실행을 못하게 처리해줘야 한다.
        
        2. 진행 (Progress)
            임계 영역에 실행되고 있는 프로세스가 없을 경우, 임계 영역을 실행하고자 기다리는 프로세스는 임계영역을 실행할수 있어야한다.
            즉, 기다리고 있는 프로세스들에 대한 처리를 해줘야한다.
        3. 한정 대기 (Bounded Waiting)
            임계 영역을 실행하고자 하는 프로세스가 무한정으로 대기하면 안된다.
            즉, 제한된 대기시간을 가져야 한다.
    
<br><br>
    
### 1.3 Busy Waiting
* A와 B라는 두개의 쓰레드가 공유 자원을 사용하려고 할때 쓰레드 A가 공유 자원을 먼저 사용하게 되면 쓰레드 B는 쓰레드 A가 사용을 마칠때 까지 기다려야 한다.  
이때 쓰레드 B는 쓰레드 A가 공유 자원 사용을 끝냈는지 계속 무한 루프를 돌면서 확인하게 되는데 이것을 Busy Waiting 이라고 한다.

* 지속적으로 확인하는 Busy Waiting이 아닌 block 방법으로 문제를 해결할 수 있다. (단, Wait Queue에 넣는 자원 비용 + Context Switching 비용 의 오버헤드가 발생한다.)

* 스핀락(Spin-lock)과 이것을 동일하게 생각하지만, 엄밀히 말하자면 스핀락이 바쁜 대기 개념을 이용한 것이다.

##### 언제 사용하는 가?  
   * 자원의 권한을 얻는데 많은 시간이 소요되지 않는 상황인 경우.
   * Context Switching 비용보다 성능적으로 더 우수한 상황인 경우.

##### 단점 
   * 권한 획득을 위해 많은 CPU를 낭비한다.
   
<br><br>
   
### 1.4 LOCK
* 하나의 스레드나 프로세스가 자원을 사용하고 있는 동안에는 잠궈서 접근을 못하게 하는 방식

* 동시에 공유 자원에 접근하는 것을 막기 위해 임계영역에 진입하는 프로세스나 스레드는 Lock을 흭득하고 임계영역을 빠져나올떄,  
    Lock 을 방출함으로써 동시에 접근이 되지 않도록 한다.
    
* 락은 크게 인터럽트 제어 방법(controlling interrupts)과 소프트웨어적 방법론으로만 제작하는 방법(software-only algorithm)과  
    하드웨어를 활용하여 제작하는 방법(Hardware atomic Instructions) 세 가지가 있습니다. 

<br><br>

##### 1.1.1 소프트웨어 알고리즘
   1. 데커 알고리즘  
        상호배제를 하는 최초의 알고리즘으로 turn과 flag 두 가지를 사용하여 구현한 것이다.  
        먼저 깃발을 들고 확인한 다음 turn을 확인하는 방법이다. turn을 확인하고 자신의 차례가 아니면 깃발을 내리고 자신의 차례면 기다린다.  
        자기 차례가 오면 깃발을 들고 임계영역에 진입하고, 끝나면 깃발내리고 차례를 넘겨준다.
   
   2. 다익스트라 알고리즘  
        프로세스 n개의 상호배제 문제를 해결한 최초의 알고리즘이다.  
        CS에 진입하기 위해 단계를 2단계로 나누어 단계마다 진입할 수 있는 프로세스를 걸러내어   
        최종적으로 하나의 프로세스만 CS에 접근할 수 있게 구현되었습니다.  
   
   3. 램퍼드의 베이커리 알고리즘  
        프로세스 n개의 상호 배제 문제를 해결한 알고리즘이다.  
        bakery 알고리즘은 프로세스에게 고유한 번호를 부여하고,  
        번호를 기준으로 우선순위를 정하여 우선순위가 높은 프로세스가 먼저 임계 구역에 진입하도록 구현되었다.
   
   3. 피터슨 알고리즘  
        flag와 turn이라는 변수로 임계영역에 들어갈 프로세스(혹은 스레드)를 결정하는 방식이다.  
    	데커 알고리즘과 상당히 유사하지만 상대방(다른 프로세스 혹은 스레드)에게 진입기회를 양보한다는 차이가 있다.   
    	flag값은 프로세스 중 누가 임계영역에 진입할 것인지 나타내는 변수이고,   
    	turn 변수는 누가 임계영역에 들어갈 차례인지 나타내는 변수이다.  
    	 
     
   
<br><br>
   
##### 1.1.2 더 이상 쪼개지지 않는 하드웨어 명령어로 구현하는 방법
   1. Test-and-Set  
        > 원자성을 가진 하드웨어 명령어를 이용하여 락을 구현한다. 락 소유가 가능할 때까지 루프를 돌며 대기하기 때문에 스핀 락이라고 부른다
         
   2. Compare-and-Swap  
        > 현재 쓰레드에 저장된 값과 메인 메모리에 저장된 값을 비교하여 일치하는 경우 새로운 값으로 교체하고 일치하지 않는다면 실패하고 재시도를 한다
        
   3. Load-Linked(LL) and Store-Conditional(SC)  
        > Load-Linked를 통해 현재 lock이 free인지 확인한다.(free가 아니라면 spin 한다.) 그리고 store-conditional 을 통해 lock 소유한다.   
        이때 핵심은 lock의 free 여부를 확인한 이후 lock을 소유하는 사이에 인터럽트가 발생하여 다른 스레드가 lock을 소유하는 경우가 발생한다는 점이다.
        그럼 다음 기회를 기다려야 한다.
   
   4. Fetch-and-Add  
        > 티켓 락이란 여러 스레드에 티켓을 주고, 락이 free 되었을 때, 차례와 티켓이 동일하면 락을 소유하는 방법이다.   
        가장 큰 차이점은 모든 스레드들이 순서대로 진행하게 된다는 점이다.
   
<br><br>
   
##### 1.1.3 인터럽트를 disable하고 enable하는 방법
   * 단일 프로세서 시스템의 경우엔 disabling interrupt로 상호배제 가능
   * 하지만 application 레벨에서 이를 악용하여 interrupt를 막아서 control을 독점할 수 있음
   * 멀티 프로세서에서는 동작하지 않음

<br><br>

### 1.5 뮤텍스 (Mutex)
   * Locking 메커니즘으로 오직 하나의 쓰레드만이 동일한 시점에 뮤텍스를 얻어 임계영역에 들어올수있으며,
    이 쓰레드만이 임계영역에서 나갈 때 뮤텍스를 해제할 수 있다.
    
   * 동작
    공유자원 X에 대해서 쓰레드A 와 쓰레드B 에 접근하여 각각 수정,조회하는 작업을 한다면, 이 경우 두 쓰레드간 동기화가 되어야 Thread-Safe 하다.  
    그래서 쓰레드 A가 수정하는 경우는 다른 스레드가 그 값을 수정/조회하지 못하도록 막아놓고(LOCK: mutex를 set한다)
    그 작업이 끝나면 잠금을 풀어주어(Unlock: mutex를 clear한다) 쓰레드B가 공유자원 X에 대해서 조회되는 로직을 처리한다.
    
    
   ※ Thread-safe     
        멀티 스레드 프로그래밍에서 일반적으로 어떤 함수나 변수, 혹은 객체가 여러 스레드로부터 동시에 접근이 이루어져도 프로그램의 실행에 문제가 없음을 뜻한다  
        하나의 함수가 한 스레드로부터 호출되어 실행 중일 때,   
        다른 스레드가 그 함수를 호출하여 동시에 함께 실행되더라도 각 스레드에서의 함수의 수행 결과가 올바로 나오는 것으로 정의한다.
 

<br><br>

### 1.6 세마포 (세마포어 , Semaphore)
   * 세마포는 Signaling 메커니즘이라는 점에서 뮤텍스와 다르다.   
   세마포는 락을 걸지 않은 쓰레드도 Signal을 보내 락을 해제할 수 있다는 점에서, wait 함수를 호출한 쓰레드만이 signal 함수를 호출할 수 있는 뮤텍스와 다르다.

   * 세마포는 동기화를 위해 wait(P)와 signal(V)이라는 2개의 atomic operations를 사용한다.

   * wait를 호출하면 세마포어의 카운트를 1줄이고, 세마포어의 카운트가 0보다 작거나 같아질 경우에 락이 실행된다. 

   * 세마포의 cnt 값은 지정한 만큼 한 공유 자원에 대해 여러 스레드가 돌게 할 수도 있다.
   
   * 초기에는 busy wating을 이용하는 방법이였지만, 단점을 보안하여 block 과 wakeup 방식으로 sleep lock을 구현한다.
   
<br>
   
##### 1.6.1 종류
   * 카운팅 세마포
           세마포어의 카운트가 양의 정수값을 가지며, 설정한 값만큼 쓰레드를 허용하고,
           그 이상의 쓰레드가 자원에 접근하면 락이 실행된다.
           
   * 이진 세마포
           이진 세마포어는 세마포어의 카운트가 1이며 Mutex '처럼' 사용될 수 있다.

<br>

##### 1.6.2 단점
   * wait함수(P), signal함수(V)는 서로 독립적이기 떄문에 잘못 사용하는 경우 문제가 발생한다.
   * 고급 언어에서 동기화를 제공해야 한다.
    
<br>
    
##### 1.6.3 세마포를 활용한 java 소스
```java
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        final Reseouce resource = new Reseouce(3);
        for (int i = 1; i <= 10; i++) {
            Thread t = new Thread(() -> resource.use());
            t.start();
        }
    }

    static class Reseouce {
        private final Semaphore semaphore;
        private final int maxThread;

        public Reseouce(int maxThread) {
            this.semaphore = new Semaphore(maxThread);
            this.maxThread = maxThread;
        }

        public void use() {
            try {
                // Thread 가 세마포에게 시작을 알림
                semaphore.acquire();

                System.out.printf("[%s] %d개의 쓰레드가 점유중\n"
                        , Thread.currentThread().getName()
                        , (maxThread - semaphore.availablePermits()));
                // semaphore.availablePermits() 사용가능한 Thread의 숫자

                Thread.sleep((long) (Math.random() * 10000));

                semaphore.release(); // Thread 가 세마포에게 종료를 알림
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

<br><br><br>

### 1.7 모니터
   > 개발자의 코드를 상호배제 하게끔 만든 추상화된 데이터 형태이다.  
   > 공유자원에 접근하기 위한 키 흭득과 자원 사용 후 해제를 모두 처리한다.  
   > 
   > 베타동기, 조건동기라는 두개의 queue를 활용하여 임계영역 접근 문제를 해결한다.  
   > 세마포어에 비해서 모니터 쪽이 공유자원에 접근할 수 있는 키의 획득과 해제를 모두 처리해서 간단하다.  
 
 
   * 베타동기 : 하나의 스레드만 공유자원에 접근하게 해준다. 공유자원을 사용하는 스레드가 존재하면, 베타동기에서 대기한다. (synchronized)  
   * 조긴동기 : 공유자원을 사용하는 스레드가 Block을 당하면서, 새로운 스레드가 진입하게 해준다. (notify, notifyAll, wait)

<br>

예시 )
> Synchronized가 메소드에 선언되어있고,  
>
> 쓰레드A가 이미 Lock을 획득해서 Critical Section(메소드)을 수행중이라고 가정하자.  
> 
> 쓰레드B 가 동일한 메소드를 수행하기 위해 해당 Object의 Lock을 획득해야 한다.
> 
> 이 Lock 이 반환 될 때까지 대기를 해야하는데 그 때 사용되는게 바로 Monitor다.  
> 
> 쓰레드A 가 Lock 을 반환하면 쓰레드B는 기다렸다가 Lock을 획득하게 된다.  
> 
> 그리고 Critical Section인 메소드를 수행할 수 있게 된다.  
> 
> 물론 Synchronized 키워드를 사용했을 때 자동적으로 수행되는 내부 동작이고, 별도로 명시적인 Monitor를 구현할 수도 있다.  
> 
> Monitor는 이렇게 Mutex(Lock)과 Condition Variables을 이용해서 Mutual Exclustion을 해결하고 있다.  
> 
> 그 외 Monitor의 다른 정의로는 공유자원에 안전하게 접근하기 위해 Mutual Exclusion가 랩핑된 Thread-Safe한 클래스, 객체, 모듈들을 의미하기도 한다.  

<br><br><br><br>

# 2. 데드락
<img src="https://github.com/ryunian/Study/blob/master/image/deadLock2.png?raw=true" width="500px" height="400px">
<img src="https://github.com/ryunian/Study/blob/master/image/deadLock1.png?raw=true" width="500px" height="400px">

### 2.1 식사하는 철학자 문제
   > 1. 일정 시간 생각을 한다.  
   > 2. 왼쪽 포크가 사용 가능해질 때까지 대기한다. 만약 사용 가능하다면 집어든다.  
   > 3. 오른쪽 포크가 사용 가능해질 때까지 대기한다. 만약 사용 가능하다면 집어든다.  
   > 4. 양쪽의 포크를 잡으면 일정 시간만큼 식사를 한다.  
   > 5. 오른쪽 포크를 내려놓는다.  
   > 6. 왼쪽 포크를 내려놓는다.  
   > 7. 다시 1번으로 돌아간다.  

<br><br>

### 2.2 정의
두 개 이상의 프로세스가 각자 먼저 확보한 자원을 가진 채 상대방의 자원을 필요로 할 경우, 외부로부터의 조치가 없는 한 이들은 아무 일도 못하고 계속 기다리는 현상  
데드락은 아무리 좋은 동기화 프로세스를 사용해도 발생하며, 특정 코딩 규칙을 사용하는 등을 이용하여 교착상태가 일으킬 가능성을 줄여야 한다.  

발생 조건 : 상호배제, 점유대기, 비선점, 순환대기 중 전부를 만족할 시 발생한다.

* 상호 배제 (Mutual exclusion)  
        자원은 한 번에 한 프로세스만이 사용할 수 있어야 한다
        
* 점유 대기 (Hold and wait)  
    	최소한 하나의 자원을 점유하고 있으면서 다른 프로세스에 할당되어 사용하고 있는 자원을 추가로 점유하기 위해 대기하는 프로세스가 있어야 한다.  
    
* 비선점 (No preemption)  
        다른 프로세스에 할당된 자원은 사용이 끝날 때까지 강제로 빼앗을 수 없어야 한다.      
    
* 순환 대기 (Circular wait)  
        프로세스의 집합 {P0 ~ Pn}에서 P0는 P1이 점유한 자원을 대기하고 P1은 P2가 점유한 자원을 대기하고 
        P2 ~ Pn-1은 Pn이 점유한 자원을 대기하며 Pn은 P0가 점유한 자원을 요구해야 한다.

<br><br>

### 2.3 예방 (Prevention)
> 교착 상태 발생 조건 중 하나를 제거하면서 해결한다. (자원 낭비가 심하다)

1) 상호 배제 (Mutual exclusion) 부정
	여러 개의 프로세스가 공유 자원을 사용할 수 있도록 한다.

2) 점유 대기 (Hold and wait) 부정
         프로세스가 실행되기 전 필요한 모든 자원을 할당한다.

3) 비선점 (No preemption) 부정
         자원을 점유하고 있는 프로세스가 다른 자원을 요구할 때 점유하고 있는 자원을 반납하고, 요구한 자원을 사용하기 위해 기다리게 한다.

4) 순환 대기 (Circular wait) 부정
         자원에 고유한 번호를 할당하고, 번호 순서대로 자원을 요구하도록 한다.

<br><br>

### 2.4 회피 (Avoidance)
> 교착 상태 발생 시 피해나가는 방법 (은행원 알고리즘)

   - 은행원 알고리즘 :
        프로세스가 자원을 요구할 떄, 시스템은 자원을 할당한 후에도 안정 상태로 남아 있게 되는지 사전에 검사하여 교착 상태 회피
				
		단점  
		    최대 자원 요구량을 미리 알아야하며, 쉽게 구현이 가능하지만, 추가비용이 크다.
			프로세스는 유한한 시간에 자원을 반납해야 한다.

<br><br>

### 2.5 교착 상태 탐지 및 회복
> 교착 상태가 되도록 허용한 다음에 회복시키는 방법  
대부분의 시스템은 교착 상태가 잘 발생하지 않으며, 교착 상태 예방, 회피, 탐지, 복구하는 것은 비용이 많이 든다.

##### 2.5.1 탐지 (Detection)

> 자원 할당 그래프를 통해 교착 상태를 탐지, 자원 요청 시, 탐지 알고리즘을 실행시켜 그에 대한 오버헤드가 발생

<br><br>

##### 2.5.2 회복 (Recovery)
> 교착 상태를 일으킨 프로세스를 종료하거나, 할당된 자원을 해제시켜 회복시키는 방법  

1. 프로세스를 종료하는 방법  
    * 교착 상태의 프로세스를 모두 중지  
    * 교착 상태가 제거될 떄까지 한 프로세스씩 중지

2. 자원을 선점하는 방법
    * 교착 상태의 프로세스가 점유하고 있는 자원을 선점하여 다른 프로세스에게 할당하며, 해당 프로세스를 일시 정지 시키는 방법  
    * 우선 순위가 낮은 프로세스, 수행된 횟수가 적은 프로세스 등을 위주로 프로세스의 자원을 선점한다.  
    
    
<br><br>
<hr>
<br><br>

Q. 뮤텍스와 세마포는 자신만 해제가 가능하다 O, X
- X, 뮤텍스는 lock을 가진 스레드만 뮤텍스를 해제가 가능하지만, 세마포는 락을 걸지 않는 쓰레드도 Signal을 보내 락을 해제할수 있다.

Q. A와 B가 화장실을 갈라고한다. 그런데 화장실이 하나뿐이고 카운터에서 열쇠를 받아 가야한다.
    이떄, 뮤텍스와 세마포 중 어떤것을 적용해야 할까?
- 뮤텍스, 이진세마포 또한 안된다. 세마포는 lock(여기선 열쇠)를 가지지 않는 스레드가 해제가 가능하기 떄문이다.


Q. 식사를 하는 철학자에 대한 해결법을 말해보시오  
- 포크 수가 철학자의 수보다 많으면 된다.
- n명이 앉을 수 있는 테이블에서 철학자를 n-1명만 앉힘  
- 한 철학자가 젓가락 두개를 모두 집을 수 있는 상황에서만 포크 집도록 허용  
- 누군가는 왼쪽 포크을 먼저 집지 않고 오른쪽 포크을 먼저 집도록 허용  