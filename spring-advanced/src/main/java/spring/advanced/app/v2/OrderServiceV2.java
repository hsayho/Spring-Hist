package spring.advanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.advanced.trace.TraceId;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.hellotrace.HelloTraceV1;
import spring.advanced.trace.hellotrace.HelloTraceV2;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemID){
        TraceStatus traceStatus = null; // Exception에서 접근할 수 있도록 try-catch문 외곽으로 뺌
        try {
            //traceStatus = trace.begin("OrderService.orderItem");
            traceStatus = trace.beginSync(traceId, "OrderService.orderItem");
            //orderRepository.save(itemID);
            orderRepository.save(traceStatus.getTraceId(), itemID);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e; // 예외를 꼭 다시 던져 주어야 한다. 그렇지 않으면 예외를 먹어버리고, 이후에 정상 흐름으로 동작한다.
        }
    }
}
