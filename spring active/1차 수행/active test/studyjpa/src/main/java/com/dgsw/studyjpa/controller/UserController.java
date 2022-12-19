package com.dgsw.studyjpa.controller;

import com.dgsw.studyjpa.entity.User;
import com.dgsw.studyjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("signup")
    public String showSignup() {
        return "signup";
    }

    @PostMapping("signup")
    public Model signup(User user, Model model) {

        if (user.getId().equals("") || user.getPw().equals("")){
            model.addAttribute("error","id나 pw가 비어있음");
            return model;
        }

        boolean bool = userRepository.existsById(user.getId());

        if(!bool){
            model.addAttribute("error","id가 이미 존재함");
            return model;
        }

        userRepository.save(user);
        model.addAttribute("result","회원가입 성공");

        return model;
    }

    @GetMapping("login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("login")
    public Model login(User user, Model model) {

        if(user.getId().equals("") || user.getPw().equals("")){
            model.addAttribute("error", "id나 pw가 비어있음");
            return model;
        }

        User newUser = userRepository.getById(user.getId());

        if(!(user.getPw().equals(newUser.getPw()))) {
            model.addAttribute("error", "비밀번호가 틀림");
            return model;
        }

        model.addAttribute("result","로그인 성공");

        return model;
    }

}