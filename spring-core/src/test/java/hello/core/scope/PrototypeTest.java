package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest { // 싱글톤이 아닌 프로토타입 스코
    
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean b1 = ac.getBean(PrototypeBean.class);
        PrototypeBean b2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + b1);
        System.out.println("prototypeBean2 = " + b2);

        ac.close(); // 스프링 컨테이너를 닫아도 프로토타입 스코프는 destroy 메서드가 실행되지 않음
    }
    
    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
