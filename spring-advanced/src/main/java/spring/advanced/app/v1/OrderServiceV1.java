package spring.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.hellotrace.HelloTraceV1;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepositoryV0;
    private final HelloTraceV1 trace;

    public void orderItem(String itemID){
        TraceStatus traceStatus = null; // Exception에서 접근할 수 있도록 try-catch문 외곽으로 뺌
        try {
            traceStatus = trace.begin("OrderService.orderItem");
            orderRepositoryV0.save(itemID);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e; // 예외를 꼭 다시 던져 주어야 한다. 그렇지 않으면 예외를 먹어버리고, 이후에 정상 흐름으로 동작한다.
        }
    }
}
