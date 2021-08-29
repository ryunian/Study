# 스케줄러

스케줄러란 어떤 프로세스에게 자원을 할당할지를 결정하는 운영체제 커널의 코드를 지칭한다.

<br><br>

## 장기 스케줄러

- 작업 스케줄러라고도 부르며, 어떤 프로세스를 준비 큐에 진입시킬지 결정하는 역할을 한다.
- 처음 프로세스가 생성되면 시작 상태를 거쳐 준비 상태에 이르게 되는데 장기 스케줄러는 시작 상태의 프로세스들 중 어떠한 프로세스를 준비 큐에 삽입할것인지 결정하는 역할을 맡은다.

※ 메모리가 충분한 현대의 시분할 시스템에서 사용되는 운영체제에서는 일반적으로 장기 스케줄러를 두지 않는 경우가 대부분이다.

<br><br>

## 단기 스케줄러

- CPU 스케줄러라고도 하며, 준비 상태의 프로세스 중에서 어떤 프로세스를 다음번에 실행 상태로 만들 것인지 결정한다.

- 시스템에서 타이머 인터럽트가 발생하면 단기 스케줄러가 호출된다.

<br><br>

## 중기 스케줄러

- 중기 스케줄러는 너무 많은 프로세스에게 메모리를 할당해 시스템의 성능이 저하되는 경우 이를 해결하기 위해 메모리에 적재된 프로세스의 수를 동적으로 조절하기 위해 추가된 스케줄러이다.
- 메모리에 올라와 있는 프로세스 중 일부를 선정해 이들로부터 메모리를 빼앗아 그 내용을 디스크의 스왑영역에 저장한다(스왑아웃)
- 즉, 중기 스케줄러는 프로세스당 보유 메모리양이 지나치게 적어진 경우 이를 완화시키기 위해 일부 프로세스를 메모리에서 디스크로 스왑 아웃 시키는 역할을 수행한다.
- 봉쇄 상태인 프로세스들의 경우 당장 CPU를 흭득할 가능성이 없기 때문에 메모리를 보유하고 있는 것또한 큰 의미가 없기 때문에 가장 우선적이게 된다.
- 타이머 인터럽트가 발생해 준비 큐로 이동하는 프로세스를 추가적으로 스왑 아웃 시킨다.

<br><br>

## 용어

- CPU 이용률
전체 시간 중에서 CPU가 일을 한 시간의 비율
- 처리량
주어진 시간 동안 준비큐에서 기다리고 있는 프로세스 중 몇개를 끝마쳤는지
- 소요시간
프로세스가 CPU를 요청한 시점부터 자신이 원하는 만큼 CPU를 다쓰고 CPU버스트가 끝날때까지 걸린 시간
- 대기시간
CPU 버스트 기간 중 프로세스가 준비큐에서 CPU를 얻기 위해 기다린 시간의 합
- 응답시간
프로세스가 준비 큐에 들어온 후 첫 번쨰 CPU를 흭득하기까지 기다린 시간

<br><br>

### 디스패쳐

새로운 프로세스가 CPU를 할당받고 작업을 수행할 수 있도록 환경설정을 하는 운영체제의 코드를 디스패처라고 부른다.

- 현재 수행중이던 프로세스의 문맥을 PCB에 저장
- 다음 프로세스의 문맥을 PCB로부터 복원
- 사용자모드로 변경하여 프로세스에게 CPU를 넘기는 과정 수행

디스패처가 하나의 프로세스를 정지시키고 다른 프로세스에게 CPU를 전달하기 까지 걸리는 시간을 디스패치 지연시간이라고 한다. (문맥교환 오버헤드에 포함된다)

<br><br>

## 스케줄링 알고리즘

### 1. 선입선출 스케줄링

선입선출(FCFS) 스케줄링은 프로세스가 준비 큐에 도착한 시간 순서대로 CPU를 할당하는 방식을 말한다.
ex) 은행, 공항, 화장실

단점 : CPU 버스트가 긴 프로세스가 먼저 도착했을 경우 그뒤의 짧은 프로세스에게 잠깐만 CPU를 할당하게 되면 이 프로세스들이 CPU버스트를 마친 뒤 I/O작업을 연이어 수행할수 있는데 긴 프로세스 하나 때문에 대기시간을 물론 I/O 장치들의 이용률까지도 동반 하락하게 된다. (콘보이현상)

- 콘보이 현상

    CPU 버스트가 짧은 프로세스가 CPU 버스트가 긴 프로세스보다 나중에 도착해 오랜 시간을 기다려야 하는 현상을 콘보이 현상이라고 한다.

<br><br>

### 2. SFJ (최단작업 우선 스케줄링)

SJF 는 CPU버스트가 가장 짧은 프로세스에게 제일 먼저 CPU를 할당하는 방식이다.
SJF 스케줄링 알고리즘은 평균 대기시간을 가장 짧게 하는 최적 알고리즘으로 알려져 있다.
비선점형 , 선점형(SRTF) 방식이 있다.

일반적인 시분할 환경에서는 중간에 새로운 프로세스가 도착하는 경우가 발생하므로 선점형 방식이 평균 대기시간을 가장 많이 줄일 수 있다. 그러나, 기아현상이 발생한다

새로운 프로세스가 도달할떄마다 스케줄링을 다시하기 떄문에 CPU사용시간을 측정할수가 없다.

- 기아현상 (starvation)

    자신보다 우선순위 높은 프로세스나 CPU버스트가 짧은 프로세스 떄문에 오랫동안 CPU 할당을 받지 못하고 무한정 대기하는 것

<br><br>

### 3. 우선순위 스케줄링

준비 큐에서 기다리는 프로세스들 중 우선순위가 가장 높은 프로세스에게 제일 먼저 CPU를 할당하는 방식을 말한다. 우선순위는 우선순위 값을 통해 이루어진다.
비선점, 선점형 방식 각각 구현 가능

우선순위 스케줄링 또한 SFJ 처럼 기아현상이 발생할수 있으며, 이를 해결하기 위해 Aging 기법을 사용한다.

- Aging

    기아현상을 해결하기 위한 기법으로 오랫동안 기다린 프로세스에게 우선순위를 높여주는 방법
     ex) 노약자석

<br><br>

### 4. 라운드 로빈 스케줄링

각 프로세스가 CPU를 연속적으로 사용할 수 있는 시간(할당 시간)을 제한하여, 이 시간이 경과되면 해당 프로세스로부터 CPU를 회수해 준비 큐에 줄 서 있는 다른 프로세스에게 CPU를 할당한다.

할당시간이 너무 짧으면 CPU를 사용하는 프로세스가 빈번하게 교쳬되어 문맥교환의 오버헤드가 커진다.
할당시간이 너무 길면 FCFS 스케줄링과 같아지게 된다.
SJF 방식보다 평균 대기시간이 길지만 응답시간은 짧다

할당시간이 만료되어 CPU를 회수하는 방법으로는 타이머 인터럽트를 사용하게 된다.

ex) DNS, 로드밸런싱

장점 : 대화형 프로세스의 빠른 응답시간을 보장할 수 있다.

### 5. 멀티레벨 큐 (다단계 큐)

준비 큐를 여러 개로 분할해 관리하는 스케줄링 기법
멀티레벨 큐는 일반적으로 성격이 다른 프로세스들을 별도로 관리하고, 프로세스의 성격에 맞는 스케줄링을 적용하기 위해 준비 큐(전위큐, 후위큐)를 별도로 두게된다. 

- 전위큐 : 대화형 작업을 담기위한 큐, RR
- 후위큐 : 계산 위주의 작업을 담기위한 큐, FCFS

큐 자체에 대한 스케줄링(고정 우선순위, Time Slice)이 필요하다.

<br><br>

### 6. 멀티레벨 피드백 큐 (Multilevel Feedback Queue)

CPU를 기다리는 프로세스를 여러 큐에 줄세운다는 측면에서 멀티레벨 큐와 동일하나, 프로세스가 하나의 큐에서 다른 큐로 이동가능하다는 점이 다르다.

<br><br>

### 7. 다중처리기 스케줄링

CPU가 여러개인 시스템을 다중처리기 시스템이라고 부른다.
일부 CPU에 작업이 편중되는 현상이 발생하므로 다중처리기 스케줄링에서는 CPU별 부하가 적절히 분산되도록 하는 부하균형(load balancing) 메커니즘을 필요로 한다.

- 대칭형 다중처리 : 각 CPU가 각자 알아서 스케줄링을 결정하는 방식
- 비대칭형 다중처리 : 하나의 CPU가 다른 모든 CPU의 스케줄링 및 데이터 접근을 책임지고 나머지 CPU는 거기에 따라 움직이는 방식

<br><br>

### 8. 실시간 스케줄링

시분할 시스템에서는 각 작업마다 주어진 데드라인이 있어 정해진 데드라인 안에 반드시 작업을 처리해야한다.

- 경성 실시간 시스템

    정해진 시간안에 반드시 작업이 완료되도록 스케줄링해야한다.

- 연성 실시간 시스템

    데드라인이 존재하기는 하지만 데드라인을 지키지 못했다고 해서 위험한 상황이 발생하지는 않는다. (멀티미디어 스트리밍)

실시간 환경에서는 먼저 온 요청을 처리하기보다는 데드라인이 얼마 남지 않는 요청을 먼저 처리하는 EDF(Earlist Deadline First)스케줄링을 널리 사용한다.

<br><br>

### 9. 리눅스의 CPU스케줄링은 무엇을 쓸까?

Linux 의 디폴트 스케줄링 알고리즘은 완전 공평 스케줄러(Completely Fair Schduler, CFS)이며 real-time scheduling은 SCHED_FIFO와 SCHED_RR 이다.