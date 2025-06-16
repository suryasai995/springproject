package com.suryacode.suryaapi.projectmain.userdetails.mapper;

import org.springframework.stereotype.Component;

import com.suryacode.suryaapi.projectmain.userdetails.dto.UserDto;
import com.suryacode.suryaapi.projectmain.userdetails.model.User;


@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),user.getEmail(), user.getName(), user.getUserid(), user.getGender(), user.getPhone(), user.getAddress(), user.getLang());
    }

    public User toEntity(UserDto user) {
        return new User(user.getEmail(), user.getName(), user.getUserid(), user.getGender(), user.getPhone(), user.getAddress(), user.getLang());
    }

    public Object toDto(Long id, String name, String email) {
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }
}
