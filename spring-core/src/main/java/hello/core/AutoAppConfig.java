package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // 기존 Configuration 어노테이션이 붙은 Config 파일의 충돌을 피하기 위해 excludeFilter 사용
public class AutoAppConfig {

    // 수동 빈 등록 vs 자동 빈 등록 충돌일 경우에는 수동 빈 등록이 오버라이딩 한다.
    // 하지만 현실에서는 여러 설정들이 꼬여서 버그가 발생하게 됨.
    // 최신 스프링부트는 기본값으로 이를 막음.
    //@Bean(name = "memoryMemberRepository")
    //MemoryMemberRepository memberRepository(){
        //return new MemoryMemberRepository();
    //}

}
