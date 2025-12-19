package com.gatmane.controller;

import com.gatmane.Service.UserService;
import com.gatmane.exceptions.UserException;
import com.gatmane.mapper.UserMapper;
import com.gatmane.model.User;
import com.gatmane.payload.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto>getUSerProfile(@RequestHeader ("Authorization")
                                                     String jwt) throws UserException {
       User user= userService.getUserFromJwtToken(jwt);
       return ResponseEntity.ok(UserMapper.toDTO(user));

    }
}
