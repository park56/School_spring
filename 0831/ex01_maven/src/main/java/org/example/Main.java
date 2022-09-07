package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext acac =
                new AnnotationConfigApplicationContext(AppCtx.class);

        AA a1 = acac.getBean(AA.class);
        AA a2 = acac.getBean(AA.class); // 만들어진 객체를 재사용해 메모리 낭비를 줄임
        AA a3 = new AA();   // 객체를 새로 만듦

        System.out.println("a1 = "+ a1);
        System.out.println("a2 = "+ a2);
        System.out.println("a3 = "+ a3);

        acac.close();
    }
}
