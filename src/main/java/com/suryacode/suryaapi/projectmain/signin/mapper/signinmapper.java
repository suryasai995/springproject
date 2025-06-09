package com.suryacode.suryaapi.projectmain.signin.mapper;

import org.springframework.stereotype.Component;

import com.suryacode.suryaapi.projectmain.signin.dto.signindto;
import com.suryacode.suryaapi.projectmain.signin.model.siginmodel;

@Component
public class signinmapper {

    public signindto toDto( siginmodel savedUser) {
        return new signindto(savedUser.getId(), savedUser.getEmail(), savedUser.getPassword());
    }

    public siginmodel toEntity( signindto data) {
        return new siginmodel(data.getEmail(), data.getPassword());
    }
}
