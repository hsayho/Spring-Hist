package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService(){   // 외부에서 객체를 생성하는 것을 막기위해

    }

    public void login() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
