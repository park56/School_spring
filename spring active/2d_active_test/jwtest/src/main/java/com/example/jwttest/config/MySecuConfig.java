package com.example.jwttest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // 이미 있는 시큐리티를 내걸로 바꾸겠다
@RequiredArgsConstructor
public class MySecuConfig {

//    @Autowired
//    JwtUtil jwtUtil;

    private  final JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions()
                .disable()
                .and()
                .httpBasic().disable() // http basic 사용하지 않음
                .csrf().disable()   // csrf보안을 사용하지 않음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session 사용 안함
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/user/register").permitAll()  // 해당 API에서는 모든 요청 허용
                .antMatchers("/user/emailauth").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/list").permitAll()
                .antMatchers("view").permitAll()
                .antMatchers("/view/**").permitAll()
                //.antMatchers("/wirte").permitAll()
                //.antMatchers("/write").hasRole("USER")   // 해당 API에서 요청은 USER권한이 있어야 한다
                .anyRequest().authenticated()   // 이밖의 모든 요청은 인증이 필요하다
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);    // 내가만든 jwt필터를 사용하겠다
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

}
