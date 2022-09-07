package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;    // 객체 담는 통

@Configuration // 컨테이너
public class AppCtx {

    @Bean
    public AA aa() {
        return new AA();
    }

    @Bean
    public BB bb(){
        return new BB();
    }
}
