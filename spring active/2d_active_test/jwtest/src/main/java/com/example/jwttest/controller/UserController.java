package com.example.jwttest.controller;

import com.example.jwttest.service.UserService;
import com.example.jwttest.dto.TokenInfo;
import com.example.jwttest.entity.User;
import com.example.jwttest.repository.UserRepository;
import com.example.jwttest.request.RequestLogin;
import com.example.jwttest.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserService userService;

    // 회원가입
    @PostMapping("register")
    public CommonResponse register(@RequestBody User user) {
        CommonResponse commonResponse = userService.userServiceRegister(user);
        return  commonResponse;
    }

    // 이메일 인증
    @GetMapping("emailauth")
    public CommonResponse emailauth(@RequestParam("email") String email) {
        CommonResponse commonResponse = userService.userServiceEmailAuth(email);
        return  commonResponse;
    }

    // 로그인
    @PostMapping("login")
    public TokenInfo login(@RequestBody RequestLogin requestLogin) {
        String id = requestLogin.getId();
        String pw = requestLogin.getPw();

        //TokenInfo tokenInfo = new TokenInfo("id", "pw");
        TokenInfo tokenInfo = userService.userServiceLogin(id, pw);
        userService.userServiceLogin(id, pw);
        return tokenInfo;
    }
}
