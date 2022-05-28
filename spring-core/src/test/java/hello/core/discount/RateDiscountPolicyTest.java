package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        // given
        DiscountPolicy discountPolicy = new RateDiscountPolicy();
        Member member = new Member(1L, "sayho", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(1000);
    }

    @Test   // 실패 케이스에 대해서도 꼭 테스트를 진행해야 한다.
    @DisplayName("VIP가 아니면 할인 적용이 없어야 한다.")
    void vip_x(){
        DiscountPolicy discountPolicy = new RateDiscountPolicy();
        Member member = new Member(1L, "sayho", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(0);
    }
}