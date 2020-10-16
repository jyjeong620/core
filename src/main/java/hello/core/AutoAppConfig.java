package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core",        //시작하는 package 위치 지정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class) //AppConfig.class 를 제외하기위해
)
public class AutoAppConfig {

}
