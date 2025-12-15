package com.gatmane.mapper;

import com.gatmane.model.User;
import com.gatmane.payload.dto.UserDto;

public class UserMapper {

    public static UserDto toDTO(User saveUser){
        UserDto userDto=new UserDto();
        userDto.setId(saveUser.getId());
        userDto.setFullname(saveUser.getFullname());
        userDto.setEmail(saveUser.getEmail());
        userDto.setRole(saveUser.getRole());
        userDto.setCreateAt(saveUser.getCreateAt());
        userDto.setUpdateAt(saveUser.getUpdateAt());
        userDto.setLastLogin(saveUser.getLastLogin());
        userDto.setPhone(saveUser.getPhone());
        return userDto;
    }
}
