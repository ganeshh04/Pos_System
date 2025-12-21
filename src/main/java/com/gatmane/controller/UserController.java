package com.gatmane.controller;

import com.gatmane.Service.UserService;
import com.gatmane.exceptions.UserException;
import com.gatmane.mapper.UserMapper;
import com.gatmane.model.User;
import com.gatmane.payload.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto>getUserProfile(@RequestHeader ("Authorization")
                                                     String jwt) throws UserException {
       User user= userService.getUserFromJwtToken(jwt);
       return ResponseEntity.ok(UserMapper.toDTO(user));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getUserById(@RequestHeader ("Authorization")
                                                 String jwt,
                                              @PathVariable Long id) throws UserException {
        User user= userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));

    }
}
