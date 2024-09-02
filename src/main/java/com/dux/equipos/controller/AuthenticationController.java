package com.dux.equipos.controller;

import com.dux.equipos.controller.dto.AuthLoginRequest;
import com.dux.equipos.controller.dto.AuthResponse;
import com.dux.equipos.service.impl.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserDetailService userDetailService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest userRequest) {
        return new ResponseEntity<>(this.userDetailService.login(userRequest), HttpStatus.OK);
    }

}
