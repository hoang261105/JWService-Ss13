package com.data.session13.controller;

import com.data.session13.model.dto.request.UserLogin;
import com.data.session13.model.dto.response.APIResponse;
import com.data.session13.model.dto.response.JWTResponse;
import com.data.session13.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<JWTResponse>> login(@RequestBody UserLogin userLogin) {
        JWTResponse jwtResponse = userDetailService.login(userLogin);
        return new ResponseEntity<>(new APIResponse<>(true, "Đăng nhập thành công!", jwtResponse, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Xin chào, bạn đã xác thực JWT thành công!");
    }
}
