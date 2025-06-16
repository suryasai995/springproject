package com.suryacode.suryaapi.projectmain.signin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suryacode.suryaapi.projectmain.signin.model.siginmodel;

public interface signinrepository extends JpaRepository<siginmodel, Long> {
    Optional<siginmodel> findByEmail(String email);

    // signinrepository.java
    Optional<siginmodel> findByEmailAndPassword(String email, String password);

}
