package com.gatmane.controller;

import com.gatmane.Service.AuthService;
import com.gatmane.exceptions.UserException;
import com.gatmane.payload.dto.UserDto;
import com.gatmane.payload.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    //auth/signup
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(@RequestBody UserDto userDto) throws UserException {
        return
                ResponseEntity.ok(
                        authService.signup(userDto)
                );

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody UserDto userDto) throws UserException {
        return
                ResponseEntity.ok(
                        authService.login(userDto)
                );

    }







}
