package com.dgsw.jwtst.config;

import antlr.StringUtils;
import io.jsonwebtoken.Jwt;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


// 요청되는 모든 주소에 대해서 필터를 걸어준다.
public class ApiCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("apicheckfilter");

        String auth = request.getHeader("Authorization");
        System.out.println(auth);
        System.out.println(request.getRequestURI());
        // jwt 가지고 있는지 확인
        if (request.getRequestURI().equals("/api/login")) {
            filterChain.doFilter(request,response);
        }
        else if (auth != null && auth.startsWith("Bearer ")){
            JwtUtil jwtUtil = new JwtUtil();
            try {
                String email = jwtUtil.validateAndExtract(auth.substring(7));
                System.out.println("email = "+email);
            } catch (Exception e) {
                e.printStackTrace();
            }
            filterChain.doFilter(request,response);
        }
        else{
            response.setContentType("text/plain");
            response.getOutputStream().write("로그인정보틀림".getBytes(StandardCharsets.UTF_8));
        }
    }
}
