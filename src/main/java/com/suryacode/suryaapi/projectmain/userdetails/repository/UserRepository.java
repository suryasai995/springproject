package com.suryacode.suryaapi.projectmain.userdetails.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suryacode.suryaapi.projectmain.userdetails.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserid(Long userid);


}


