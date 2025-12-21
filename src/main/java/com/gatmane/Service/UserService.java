package com.gatmane.Service;

import com.gatmane.exceptions.UserException;
import com.gatmane.model.User;

import java.util.List;

public interface UserService {
    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id);
    List<User> getAllUsers();


}
