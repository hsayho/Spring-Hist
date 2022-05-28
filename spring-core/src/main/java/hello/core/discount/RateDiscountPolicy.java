package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
@MainDiscountPolicy // 직접 만든 어노테이션
public class RateDiscountPolicy implements DiscountPolicy{



    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return price/10;
        return 0;
    }
}
