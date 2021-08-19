# 1. Garbage Collection 란?
> C언어에서 malloc(), realloc() 등의 함수를 통하여 프로그래머가 힙 영역에 동적할당하고 free() 함수를 통해 할당을 해제하는 작업을 프로그래머가 직접한다. 
> 하지만 자바에서는 그 작업을 JVM에서 전적으로 맡고 스스로 수행한다.  
> 이 기능을 가비지 컬렉션(Garbage Collection)이라고 부른다.  

> Java GC는 객체가 가비지인지 판별하기 위해서 reachability라는 개념을 사용한다. 어떤 객체에 유효한 참조가 있으면 'reachable'로, 없으면 'unreachable'로 구별하고, unreachable 객체를 가비지로 간주해 GC를 수행한다. 한 객체는 여러 다른 객체를 참조하고, 참조된 다른 객체들도 마찬가지로 또 다른 객체들을 참조할 수 있으므로 객체들은 참조 사슬을 이룬다. 이런 상황에서 유효한 참조 여부를 파악하려면 항상 유효한 최초의 참조가 있어야 하는데 이를 객체 참조의 root set이라고 한다.

<img src="https://github.com/ryunian/Study/blob/master/image/Garbage-collection.png?raw=true" width="700" height="500">

<br><br>
### 2. weak generational hypothesis   
* 대부분의 객체는 금방 접근 불가능 상태(unreachable)가 됨   
* 오래된 객체에서 젊은 객체로의 참조는 아주 적게 존재  
> 가비지 컬렉터는 위 두가지 가설 하에 만들어졌다 (전재조건)

<br><br>
### 3. Stop the World
> stop-the-world란, GC을 실행하기 위해 JVM이 애플리케이션 실행을 멈추는 것이다. stop-the-world가 발생하면 GC를 실행하는 쓰레드를 제외한 나머지 쓰레드는 모두 작업을 멈춘다.   
> GC 작업을 완료한 이후에야 중단했던 작업을 다시 시작한다. 어떤 GC 알고리즘을 사용하더라도 stop-the-world는 발생한다. 대개의 경우 GC 튜닝이란 이 stop-the-world 시간을 줄이는 것이다.

<br><br>
### 4.1 Young Generation
* Eden
* Survivor 1/2
> 1. 새로 생성한 대부분의 객체는 Eden 영역에 위치한다.   
> 2. Eden 영역에서 GC가 한 번 발생한 후 살아남은 객체는 Survivor 영역 중 하나로 이동된다.   
> 3. Eden 영역에서 GC가 발생하면 이미 살아남은 객체가 존재하는 Survivor 영역으로 객체가 계속 쌓인다.   
> 4. 하나의 Survivor 영역이 가득 차게 되면 그 중에서 살아남은 객체를 다른 Survivor 영역으로 이동한다. 그리고 가득 찬 Survivor 영역은 아무 데이터도 없는 상태로 된다.   
> 5. 이 과정을 반복하다가 계속해서 살아남아 있는 객체는 Old 영역으로 이동하게 된다.   

<br><br>
### 4.2 Old generation
> 접근 불가능 상태로 되지 않아 Young 영역에서 살아남은 객체가 여기로 복사된다. 대부분 Young 영역보다 크게 할당하며, 크기가 큰 만큼 Young 영역보다 GC는 적게 발생한다.    
> 이 영역에서 객체가 사라질 때 Major GC(혹은 Full GC)가 발생한다고 말한다.   

<br><br>
### 4.3 MetaSpace 
>  Java 8에서 JVM 메모리 구조적인 개선 사항으로 Perm 영역이 Metaspace 영역으로 전환되고 기존 Perm 영역은 사라지게 되었다.   
>  Metaspace 영역은 Heap이 아닌 Native 메모리 영역으로 취급하게 된다.   
>  (Heap 영역은 JVM에 의해 관리된 영역이며, Native 메모리는 OS 레벨에서 관리하는 영역으로 구분된다)   
>  Metaspace가 Native 메모리를 이용함으로서 개발자는 영역 확보의 상한을 크게 의식할 필요가 없어지게 되었다.   
>  즉, 각종 메타 정보를 OS가 관리하는 영역으로 옮겨 Perm 영역의 사이즈 제한을 없앤 것이라 할 수 있다.


<br><br>
### 5. Old 영역에 대한 GC 종류
* Serial GC
* Parallel GC
* Parallel Old GC(Parallel Compacting GC)
* Concurrent Mark & Sweep GC(이하 CMS)   
  > -XX: UseConcMarkSweepGC
  >  1. initial Mark > Concurrent Mark > Remark > Concurrent Sweep 방식으로 진행됨
  >  2. Concurrent Mark, Concurrent Sweep의 경우 다른 Thread가 실행중인 상태에서 동시에 진행됨
  >  3. STW 시간이 짧음
  >  4. 모든 Application의 response time이 중요할 때 사용하며, 대신 메모리와 CPU 사용량 높음
  >  5 .기본적으로 Compaction 작업이 제공되지 않음





* G1(Garbage First) GC
  > JAVA 6부터 나와서 JAVA 9에 정식으로 기본 GC가 되었다.   
  > 링크를 첨부하는게 좋다고 판단하여 링크 남기겠습니다.   
[[링크1]] [[링크2]] [[링크3]]


<br><br>
### 6. 기타 용어 설명
* Minor GC : Young 영역에서 일어나는 GC   


* Major GC : Old 영역에서 일어나는 GC   


* Full GC : Heeap 전체에서 일어나는 GC를 통틀어서 이루는 말   


* initial Mark : 클래스 로더에서 가장 가까운 객체 중 살아 있는 객체만 찾는 것으로 끝낸다   


* Concurrent Mark : 이전 단계에서 살아있다고 확인한 객체에서 참조하고 있는 객체들을 따라가면서 확인한다. 이 단계의 특징은 다른 스레드가 실행 중인 상태에서 동시에 진행된다는 것   

* Remark : Concurrent Mark 단계에서 새로 추가되거나 참조가 끊긴 객체를 확인한다.

* Concurrent Sweep : 쓰레기를 정리하는 작업을 실행한다. 이 작업도 다른 스레드가 실행되고 있는 상황에서 진행한다.

* Compaction : 각 객체들이 연속되게 쌓이도록 힙의 가장 앞 부분부터 채워서 객체가 존재하는 부분과 객체가 없는 부분으로 나눈다

<br><br>
### 7. 추가
#####Reference
* java.lang.ref 패키지를 이용하여 reachable 객체들을 strongly reachable, softly reachable, weakly reachable, phantomly reachable로 더 자세히 구별하여 GC 때의 동작을 다르게 지정할 수 있게 되었다. 다시 말해, GC 대상 여부를 판별하는 부분에 사용자 코드가 개입할 수 있게 되었다.


* strongly reachable: root set으로부터 시작해서 어떤 reference object도 중간에 끼지 않은 상태로 참조 가능한 객체, 다시 말해, 객체까지 도달하는 여러 참조 사슬 중 reference object가 없는 사슬이 하나라도 있는 객체
			 
* softly reachable: strongly reachable 객체가 아닌 객체 중에서 weak reference, phantom reference 없이 soft reference만 통과하는 참조 사슬이 하나라도 있는 객체
			 
* weakly reachable: strongly reachable 객체도 softly reachable 객체도 아닌 객체 중에서, phantom reference 없이 weak reference만 통과하는 참조 사슬이 하나라도 있는 객체
			 
* phantomly reachable: strongly reachable 객체, softly reachable 객체, weakly reachable 객체 모두 해당되지 않는 객체. 이 객체는 파이널라이즈(finalize)되었지만 아직 메모리가 회수되지 않은 상태이다.
			 
* unreachable: root set으로부터 시작되는 참조 사슬로 참조되지 않는 객체

* [Java Reference와 GC] 

출처 :   
https://d2.naver.com/helloworld/1329   
https://donghyeon.dev/java/2020/03/31/%EC%9E%90%EB%B0%94%EC%9D%98-JVM-%EA%B5%AC%EC%A1%B0%EC%99%80-Garbage-Collection/

[링크1]: https://imp51.tistory.com/entry/G1-GC-Garbage-First-Garbage-Collector-Tuning
[링크2]: https://johngrib.github.io/wiki/java-g1gc/
[링크3]: https://code-factory.tistory.com/48
[Java Reference와 GC]: https://d2.naver.com/helloworld/329631