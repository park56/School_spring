package com.dgsw.jwtst.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {


    // api/login 이라는 주소로 들어올떄...
    protected ApiLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    // 로그인 할지 시도하는 메서드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        System.out.println("테스트");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("email = "+email);
        System.out.println("password = "+password);

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(email, password);

        System.out.println("\n\n==========");
        System.out.println("auth Token = "+authToken.getName());
        System.out.println("auth Token = "+authToken.getPrincipal());
        System.out.println("==========\n\n");

        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.setContentType("text/plain");
        String email = ((User)authResult.getPrincipal()).getUsername();

        JwtUtil jwtUtil = new JwtUtil();

        try {
            response.getOutputStream().write(jwtUtil.generateToken(email).getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
