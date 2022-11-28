package com.dgsw.jwtst.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class MySecuConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

        http.formLogin();
        http.csrf().disable();
        http.logout();


//        Memer memer = new Memer();
//        memer.setName("nasdf");
//        memer.setPassword("123");

//        Memer.builder()
//            .name("asdf")
//            .password("asdf").build();

        //freebaord/list
        //notes/list
        //notes/post
        //notes/update
        //notes/article/1
        http.addFilterBefore(apiCheckFilter(),
                UsernamePasswordAuthenticationFilter.class);
        // /api/login
        http.addFilterBefore( apiLoginFilter() ,
                UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public ApiCheckFilter apiCheckFilter(){
        return new ApiCheckFilter();
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        ApiLoginFilter apiLoginFilter =
                new ApiLoginFilter("/api/login");
        apiLoginFilter.setAuthenticationManager(authenticationManager());
        return apiLoginFilter;
    }
}
