package com.gatmane.Service.impl;

import com.gatmane.Service.UserService;
import com.gatmane.configuration.JwtProvider;
import com.gatmane.exceptions.UserException;
import com.gatmane.model.User;
import com.gatmane.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;
    @Override
    public User getUserFromJwtToken(String token) throws UserException {

        String email=jwtProvider.getEmailFromToken(token);
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new UserException("Invalid token");
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {

        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new UserException("user not found");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {

        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new UserException("user not found");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).orElseThrow(null);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}
