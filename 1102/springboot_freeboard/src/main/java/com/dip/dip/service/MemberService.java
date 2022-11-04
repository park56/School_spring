package com.dip.dip.service;

import com.dip.dip.entity.Member;
import com.dip.dip.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("UserDetailsService")
public class MemberService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println(email);
        System.out.println(passwordEncoder.encode("1234"));

        Member member = memberRepository.findByEmail(email);

        return User.builder()
                .username(email)
                .password(member.getPassword())
                .roles("ADMIN")
                .build();
    }
}
