package com.data.session13.service;

import com.data.session13.model.dto.request.UserLogin;
import com.data.session13.model.dto.response.JWTResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailService {
    UserDetails loadUserByUsername(String userName);

    JWTResponse login(UserLogin userLogin);
}
