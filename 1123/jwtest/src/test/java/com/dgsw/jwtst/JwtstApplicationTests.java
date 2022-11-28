package com.dgsw.jwtst;

import com.dgsw.jwtst.config.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtstApplicationTests {

    @Autowired
    JwtUtil jwtUtil;

    @Test
    void contextLoads() throws Exception {

        String jtoken = jwtUtil.generateToken("aa@naver.com");
        System.out.println(jtoken);

        String madeemail = jwtUtil.validateAndExtract(jtoken);
        System.out.println(madeemail);
    }

    /*
    eyJhbGciOiJIUzI1NiJ9.
    eyJpYXQiOjE2NjkxNzg1MzQsImV4cCI6MTY3MTc3MDUzNCwic3ViIjoiYWFAbmF2ZXIuY29tIn0.
    Wbsh_Ai3CjoxbAZlqI9jDgJdK_4Lf24y_kK7npWWz7c
     */
}
