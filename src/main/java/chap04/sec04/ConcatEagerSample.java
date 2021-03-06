package chap04.sec04;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-77 concatEager(sources) 예제 */
public class ConcatEagerSample {
  
  public static void main(String[] args) throws Exception {
    // 결합 대상
    Flowable<Long> flowable1 =
        // 300밀리초마다 데이터를 통지한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 5건까지 통지한다
            .take(5);
    
    // 결합 대상
    Flowable<Long> flowable2 =
        // 500밀리초마다 데이터를 통지한다
        Flowable.interval(500L, TimeUnit.MILLISECONDS)
            // 5건까지 통지한다
            .take(5)
            // 100을 더한다
            .map(data -> data + 100L);
    
    // 여러 개의 Flowable을 결합한다
    List<Flowable<Long>> sources = Arrays.asList(flowable1, flowable2);
    Flowable<Long> result = Flowable.concatEager(sources);
    
    // 구독한다
    result.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(3000L);
  }
  
}
