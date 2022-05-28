package spring.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.hellotrace.HelloTraceV1;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderServiceV0;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId){
        TraceStatus traceStatus = null; // Exception에서 접근할 수 있도록 try-catch문 외곽으로 뺌
        try {
            traceStatus = trace.begin("OrderController.request()");
            orderServiceV0.orderItem(itemId);
            trace.end(traceStatus);
            return "ok";
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e; // 예외를 꼭 다시 던져 주어야 한다. 그렇지 않으면 예외를 먹어버리고, 이후에 정상 흐름으로 동작한다.
        }
    }


}
