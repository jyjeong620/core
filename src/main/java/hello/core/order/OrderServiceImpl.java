package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final 이 붙은 필드를 모아서 생성자를 자동으로 만들어줌(밑에 주석처리한부분)
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; //= new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy; // new FixDiscountPolicy(); // 이렇게하면 인터페이스 뿐만아니라 구현체도 같이 의존하게 된다.

    /* @Qualifier 를 사용한 예제*/
//    @Autowired  //생성자가 한개면 생략가능
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
    @Autowired  //생성자가 한개면 생략가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
