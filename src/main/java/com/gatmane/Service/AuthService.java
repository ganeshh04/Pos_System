package com.gatmane.Service;

import com.gatmane.exceptions.UserException;
import com.gatmane.payload.dto.UserDto;
import com.gatmane.payload.response.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login (UserDto userDto) throws UserException;

}
