package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// 이렇게 하면  MyLogger 에 가짜 객체를 만들어 넣어주고 실제로 호출될 때 진짜를 넣어준다
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" +  uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" +  uuid + "] request scope bean create : "+ this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" +  uuid + "] request scope bean close : "+ this);
    }
}
