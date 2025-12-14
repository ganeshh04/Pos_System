package com.gatmane.Service.impl;

import com.gatmane.Service.AuthService;
import com.gatmane.configuration.JwtProvider;
import com.gatmane.exceptions.UserException;
import com.gatmane.mapper.UserMapper;
import com.gatmane.model.User;
import com.gatmane.model.UserRole;
import com.gatmane.payload.dto.UserDto;
import com.gatmane.payload.response.AuthResponse;
import com.gatmane.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

import static com.gatmane.mapper.UserMapper.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomeUserImplementation customeUserImplementation;

    @Override
    public AuthResponse signup(UserDto userDto) throws UserException {
        User user =userRepository.findByEmail(userDto.getEmail());
        if(user!=null){
            throw new UserException("email id already registered");
        }
        if(userDto.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new UserException("role admin is not allowed");
        }

        User newUser=new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());
        newUser.setFullname(userDto.getPhone());
        newUser.setPhone(userDto.getPhone());
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setCreateAt(LocalDateTime.now());
        newUser.setUpdateAt((LocalDateTime.now()));

      User saveUser=  userRepository.save(newUser);
        Authentication authentication=
                new UsernamePasswordAuthenticationToken(userDto.getEmail(),userDto.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully");

        authResponse.setUser(UserMapper.toDTO(saveUser));
        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) throws UserException {

        String email=userDto.getEmail();
        String password=userDto.getPassword();
        Authentication authentication=authenticate(email,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();



        String role=authorities.iterator().next().getAuthority();

        String jwt=jwtProvider.generateToken(authentication);
        User user=userRepository.findByEmail(email);

        user.setLastLogin(LocalDateTime.now());

        userRepository.save(user);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successfully");

        authResponse.setUser(UserMapper.toDTO(user));

        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException{
        UserDetails userDetails=customeUserImplementation.loadUserByUsername(email);

        if(userDetails==null){
            throw new UserException("email id doesn't exist"+email);
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new UserException("password doesn't exist");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
}
