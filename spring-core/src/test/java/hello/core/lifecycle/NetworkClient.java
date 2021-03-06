package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 빈 생명주기 테스트 코드
// 애플리케이션 시작 시 ,네트워크 연결하고 종료 시 연결 해제
public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url){
        this.url = url;
    }
    // 서비스 시작 시 호출
    public void connect(){
        System.out.println("connected " + url);
    }

    public void call(String message){
        System.out.println("call: "+url+" message = "+message);
    }
    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("closed "+url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
