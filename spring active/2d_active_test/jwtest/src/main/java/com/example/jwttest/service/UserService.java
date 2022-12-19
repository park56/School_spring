package com.example.jwttest.service;

import com.example.jwttest.config.JwtUtil;
import com.example.jwttest.dto.TokenInfo;
import com.example.jwttest.entity.User;
import com.example.jwttest.repository.UserRepository;
import com.example.jwttest.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // 회원가입 로직
    @Transactional(rollbackFor = Exception.class)
    public CommonResponse userServiceRegister(User user) {

        CommonResponse commonResponse = new CommonResponse();

        if(user.getId().equals("") || user.getPw().equals("") || user.getName().equals("") || user.getEmail().equals("")){
            commonResponse.setAllCommonResponse(400, false, "비번, 이름, 이메일이 비어있음");
            return commonResponse;
        }

        if(!userRepository.findById(user.getId()).isEmpty()){
            commonResponse.setAllCommonResponse(400, false, "이미 있는 아이디");
            return commonResponse;
        }
        System.out.println(user.getName());

        user.setPw(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        commonResponse.setAllCommonResponse(200, true,"회원가입 성공");
        return  commonResponse;
    }

    // 이메일 인증 로직
    public CommonResponse userServiceEmailAuth(String email) {
        CommonResponse commonResponse = new CommonResponse();

        if(email.equals("")){
            commonResponse.setAllCommonResponse(400, false,"이메일이 비어있음");
            return  commonResponse;
        }

        // 받는 이메일
        String userEmail = email;
        String code = "";   // 인증 코드
        code = getRandnum();

        // 보내는 이메일
        String myEmail = "sh-ym3384@naver.com";
        String password = "TAKIYOU^^123";

        Properties prop = new Properties();
        prop.put("mail.smtp.host","smtp.naver.com");
        prop.put("mail.smtp.port",465);
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.ssl.enable","true");
        prop.put("mail.ssl.trust","smtp.naver.com");

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myEmail, password);
            }
        });

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(myEmail));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

            message.setSubject("인증 코드");

            message.setText(code);

            Transport.send(message);

        } catch (AddressException e) {
            commonResponse.setAllCommonResponse(400, false, "AddressException");
            return commonResponse;
            //throw new RuntimeException(e);
        } catch (MessagingException e) {
            commonResponse.setAllCommonResponse(400, false, "MessagingExcoption");
            return commonResponse;
            //throw new RuntimeException(e);
        }

        commonResponse.setAllCommonResponse(200, true, code);

        return commonResponse;
    }

    // 로그인 로직
    @Transactional(rollbackFor = Exception.class)   // 모든 예외에 대해 롤백
    public TokenInfo userServiceLogin(String id, String pw) {
        // id, pw로 authentication 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, pw);

        // authentication로 검증
       try {
           Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
           TokenInfo tokenInfo = new JwtUtil().generateToken(authentication);
           tokenInfo.setAllCommonResponse(200, true, id);
           return tokenInfo;
       }catch (AuthenticationException e) {
           if (e.toString().equals(new UsernameNotFoundException(id).toString())) {         // 아이디가 존재하는지 체크
               TokenInfo tokenInfo = new TokenInfo("null" );
               tokenInfo.setAllCommonResponse(400, false, "아이디가 맞지 않습니다");
               return tokenInfo;
           } else if (e.toString().equals(new BadCredentialsException("비밀번호가 일치하지 않습니다.").toString())) {    // 비밀번호가 맞는지 체크
               TokenInfo tokenInfo = new TokenInfo("null" );
               tokenInfo.setAllCommonResponse(400, false, "비밀번호가 맞지 않습니다");
               return tokenInfo;
           } else {                                                                         // 그 외 문제가 발생할 경우
               System.out.println(e.toString());
               TokenInfo tokenInfo = new TokenInfo("null" );
               tokenInfo.setAllCommonResponse(400, false, "뭔지 모를 오류");
               return tokenInfo;
           }
       }
    }

    // 6자리 난수 생성함수
    String getRandnum() {
        Random random = new Random();
        int createNum  = 0;
        String randNum = "";
        String resultNum = "";

        for(int i = 0; i< 6; i++) {
            createNum = random.nextInt(9);
            randNum = Integer.toString(createNum);
            resultNum += randNum;
        }

        return resultNum;
    }
}
