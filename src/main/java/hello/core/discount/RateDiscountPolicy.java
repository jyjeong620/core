package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")      //Bean 이름이 동일할경우 추가 구분자를 붙여줘 구분할수 있게해줌
@Primary
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
