package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderserviceTest {

    MemberService memberService;
    Orderservice orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder(){
        Member member = new Member(1L, "sayho", Grade.VIP);
        memberService.join(member);

        Order itemA = orderService.createOrder(1L, "itemA", 20000);
        Assertions.assertThat(itemA.getDiscountPrice()).isEqualTo(2000);
    }
}
