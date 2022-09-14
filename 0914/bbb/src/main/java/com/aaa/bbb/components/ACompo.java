package com.aaa.bbb.components;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ACompo {

    public void doA(){
        System.out.println("ACompo doA");
    }
}
