package spring.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.hellotrace.HelloTraceV1;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId){

        TraceStatus traceStatus = null; // Exception에서 접근할 수 있도록 try-catch문 외곽으로 뺌
        try {
            traceStatus = trace.begin("OrderRepository.save");
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e; // 예외를 꼭 다시 던져 주어야 한다. 그렇지 않으면 예외를 먹어버리고, 이후에 정상 흐름으로 동작한다.
        }

    }
    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
