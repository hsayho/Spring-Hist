package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appconfig = new AppConfig();
        memberService = appconfig.memberService();
    }

    @Test
    void join(){
        // given

        Member member = new Member(1L, "sayho", Grade.VIP);
        AppConfig appConfig = new AppConfig();
        // when

        MemberService memberService = appConfig.memberService();
        // MemberService memberService = new MemberServiceImpl();
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then

        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
