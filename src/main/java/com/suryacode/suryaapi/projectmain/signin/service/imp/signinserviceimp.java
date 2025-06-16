package com.suryacode.suryaapi.projectmain.signin.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suryacode.suryaapi.projectmain.signin.dto.signindto;
import com.suryacode.suryaapi.projectmain.signin.mapper.signinmapper;
import com.suryacode.suryaapi.projectmain.signin.model.siginmodel;
import com.suryacode.suryaapi.projectmain.signin.repository.signinrepository;
import com.suryacode.suryaapi.projectmain.signin.service.signinservice;
import com.suryacode.suryaapi.projectmain.userdetails.exception.EmailAlreadyExistsException;

@Service
public class signinserviceimp implements signinservice {
  @Autowired
  private signinrepository signinrepository;

  @Autowired
  private signinmapper signinmapper;

  @Override
  public signindto createAccount(signindto user) {
    Optional<siginmodel> existingUser = signinrepository.findByEmail(user.getEmail());
    if (existingUser.isPresent()) {
      throw new EmailAlreadyExistsException("Email already exists: " + user.getEmail());
    }

    siginmodel savedUser = signinrepository.save(signinmapper.toEntity(user));
    return signinmapper.toDto(savedUser);
  }

  @Override
  public signindto getAccount(signindto user) {

    Optional<siginmodel> existingUser = signinrepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

    if (existingUser.isEmpty()) {
      throw new EmailAlreadyExistsException("Email or password is incorrect ");
    }
    return existingUser.map(signinmapper::toDto).orElse(null);
  }

  @Override
  public List<signindto> getAllAccount() {
    List<siginmodel> users = signinrepository.findAll();
    return users.stream().map(signinmapper::toDto).collect(Collectors.toList());
  }

  @Override
  public signindto getUserById(Long id) {
    siginmodel user = signinrepository.findById(id).orElse(null);
    return signinmapper.toDto(user);
  }
}
