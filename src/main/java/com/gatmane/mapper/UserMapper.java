package com.gatmane.mapper;

import com.gatmane.model.User;
import com.gatmane.payload.dto.UserDto;

public class UserMapper {

    public static UserDto toDTO(User saveUser){
        if (saveUser == null) return null;
        UserDto userDto=new UserDto();
        userDto.setId(saveUser.getId());
        userDto.setFullname(saveUser.getFullname());
        userDto.setEmail(saveUser.getEmail());
        userDto.setRole(saveUser.getRole());
        userDto.setCreateAt(saveUser.getCreateAt());
        userDto.setUpdateAt(saveUser.getUpdateAt());
        userDto.setLastLogin(saveUser.getLastLogin());
        userDto.setPhone(saveUser.getPhone());
        userDto.setStoreId(saveUser.getStore()!=null?saveUser.getStore().getId():null);
        userDto.setBranchId(saveUser.getBranch()!=null?saveUser.getBranch().getId():null);
        return userDto;
    }

    public static User toEntity(UserDto userDto){
        User createUser=new User();
       // createUser.setId(userDto.getId());
        createUser.setFullname(userDto.getFullname());
        createUser.setEmail(userDto.getEmail());
        createUser.setRole(userDto.getRole());
        createUser.setCreateAt(userDto.getCreateAt());
        createUser.setUpdateAt(userDto.getUpdateAt());
        createUser.setLastLogin(userDto.getLastLogin());
        createUser.setPhone(userDto.getPhone());
        createUser.setPassword(userDto.getPassword());
        return createUser;
    }
}
