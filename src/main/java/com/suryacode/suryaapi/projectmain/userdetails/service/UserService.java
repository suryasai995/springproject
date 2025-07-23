package com.suryacode.suryaapi.projectmain.userdetails.service;
import java.util.List;

import com.suryacode.suryaapi.projectmain.userdetails.dto.UserDto;

public interface UserService {
    // get data
 
    List<UserDto> getAllUsers();
    // get by id
    UserDto getUserById(Long id);
    //  add new userdetails 
    UserDto createUser(UserDto userDto);
 
    UserDto verifyEmail(String email);
    
}
