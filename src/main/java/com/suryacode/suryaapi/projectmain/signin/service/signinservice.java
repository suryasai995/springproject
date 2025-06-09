package com.suryacode.suryaapi.projectmain.signin.service;

import java.util.List;

import com.suryacode.suryaapi.projectmain.signin.dto.signindto;

public interface signinservice {
 List<signindto> getAllAccount();

 signindto getUserById(Long id);

  signindto createAccount(signindto user);
}
