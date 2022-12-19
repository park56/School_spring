package com.example.jwttest.config;

import com.example.jwttest.entity.User;
import com.example.jwttest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findById(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return user.get();
    }
//    private UserDetails createUserDetails(User user) {
//        System.out.println("유저 티테일 서비스 크리에이드 디테일 실행");
//        return User.builder()
//                .id(user.getId())
//                .pw(user.getPw())
//                //.roles(List.of(user.getRoles().toArray(new String[0])))
//                //.role("USER")
//                .build();
//    }

}
