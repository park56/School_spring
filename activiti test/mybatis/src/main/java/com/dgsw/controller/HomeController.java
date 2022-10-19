package com.dgsw.controller;

import com.dgsw.dto.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("login")
    public String signup(){
        return "signup";
    }

    @PostMapping("signup")
    public String postSignup(@Valid User user, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            System.out.println(user);
            return "signup";
        }

        sqlSessionTemplate.update("user.signup", user);
        return "redirect:/index";
    }

}