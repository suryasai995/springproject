package com.suryacode.suryaapi.projectmain.userdetails.mapper;

import org.springframework.stereotype.Component;

import com.suryacode.suryaapi.projectmain.userdetails.dto.UserDto;
import com.suryacode.suryaapi.projectmain.userdetails.model.User;


@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public User toEntity(UserDto dto) {
        return new User(dto.getName(), dto.getEmail());
    }

    public Object toDto(Long id, String name, String email) {
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }
}
