package com.suryacode.suryaapi.projectmain.userdetails.service.impl;

// import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suryacode.suryaapi.projectmain.userdetails.dto.UserDto;
import com.suryacode.suryaapi.projectmain.userdetails.exception.EmailAlreadyExistsException;
import com.suryacode.suryaapi.projectmain.userdetails.exception.UserNotFoundException;
import com.suryacode.suryaapi.projectmain.userdetails.mapper.UserMapper;
import com.suryacode.suryaapi.projectmain.userdetails.model.User;
import com.suryacode.suryaapi.projectmain.userdetails.repository.UserRepository;
import com.suryacode.suryaapi.projectmain.userdetails.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
private UserMapper userMapper;

  @Override
public List<UserDto> getAllUsers() {
    return userRepository.findAll()
            .stream()
            .map(userMapper::toDto)
            .collect(Collectors.toList());
}
  

@Override
public UserDto getUserById(Long id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    return userMapper.toDto(user);
}
@Override
public UserDto createUser(UserDto userDto) {
    Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());

    if (existingUser.isPresent()) {
        throw new EmailAlreadyExistsException("Email already exists: " + userDto.getEmail());

    }

    User user = userMapper.toEntity(userDto);
    User savedUser = userRepository.save(user);
    return userMapper.toDto(savedUser);
}


}
