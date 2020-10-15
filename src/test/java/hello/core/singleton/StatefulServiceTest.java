package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    /**
     * 여기서 문제는 A user는 10000원 B user는 20000원을 넣은 후 A user의 price를 조회했을 때 20000원이 출력된다는 점
     * 왜냐하면 StatefulService는 singleton 이기 때문에 두번째로 넣은 20000원으로 변경된것.
     * 문제 해결 방법 :: 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
     *              :: StatefulService 에서 price를 전역변수로 설정하지말고 price 를 바로 리턴하여 지역변수로 받아주거나 해야한다.
     */
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A user 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB: B user 10000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA: A user 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}