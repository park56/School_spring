package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        // 스프링을 쓰는 이유 - 컨테이너를 이용해 메모리 낭비를 줄이고 개발을 조금 더 편하게 하기 위해

        System.out.println("Hello world!");

        AnnotationConfigApplicationContext acac =
                new AnnotationConfigApplicationContext(AppCtx.class);

        A a1 = acac.getBean(A.class);
        a1.doA();
        acac.close();
    }
}