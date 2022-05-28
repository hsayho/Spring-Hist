package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) { // url은 빈이 생성되는 시점에는 알 수 없으므로, 외부에서 setter로 입력받는다.
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"] "+"["+requestURL+"] "+message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope benan created : " + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope benan closed : " + this);
    }
}
