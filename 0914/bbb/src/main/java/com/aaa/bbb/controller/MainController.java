package com.aaa.bbb.controller;

import com.aaa.bbb.components.ACompo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {


    @Autowired
    ACompo aCompo; // component에 있는 객체는 이렇게 쓴다

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @GetMapping("") // 주소 설정
    public String index(){
        aCompo.doA();
        return "main";  // main html을 호출
    }

    @GetMapping("freeboard")
    public String freeboard(Model model){
        List list = sqlSessionTemplate.selectList("test.findall");  // userMapper의 findall를 호출
        System.out.println(list);
        model.addAttribute("list",list);
        model.addAttribute("aaa");
        return "freeboard";
    }
}
