package com.data.session13.service;

import com.data.session13.model.dto.request.UserLogin;
import com.data.session13.model.dto.response.JWTResponse;
import com.data.session13.security.jwt.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailServiceImp implements UserDetailService {
    @Autowired
    private JWTProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        if ("admin".equals(userName)) {
            return new User(
                    "admin",
                    "{noop}1234",
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public JWTResponse login(UserLogin userLogin) {
        if ("admin".equals(userLogin.getUsername()) && "1234".equals(userLogin.getPassword())) {
            String token = jwtProvider.generateToken(userLogin.getUsername());

            return JWTResponse.builder()
                    .username("admin")
                    .fullName("Administrator")
                    .email("admin@example.com")
                    .phone("0123456789")
                    .enabled(true)
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                    .token(token)
                    .build();
        }
        throw new RuntimeException("Invalid username or password");
    }
}
