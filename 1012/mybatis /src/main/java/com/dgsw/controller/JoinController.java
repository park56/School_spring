package com.dgsw.controller;

import com.dgsw.dto.학생수강;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("join")
public class JoinController {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @GetMapping("main")
    public String main(){
        List<학생수강> list =sqlSessionTemplate.selectList("join.student");
        return "join/main";
    }
}
