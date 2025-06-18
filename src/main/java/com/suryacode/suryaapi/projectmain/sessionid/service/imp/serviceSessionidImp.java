package com.suryacode.suryaapi.projectmain.sessionid.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suryacode.suryaapi.projectmain.sessionid.dto.SessionidDto;
import com.suryacode.suryaapi.projectmain.sessionid.mapper.SessionMapper;
import com.suryacode.suryaapi.projectmain.sessionid.model.SessionidModel;
import com.suryacode.suryaapi.projectmain.sessionid.respository.Sessionid;
import com.suryacode.suryaapi.projectmain.sessionid.service.serviceSession;
import com.suryacode.suryaapi.projectmain.userdetails.exception.EmailAlreadyExistsException;
import com.suryacode.suryaapi.projectmain.userdetails.model.User;


@Service
public class serviceSessionidImp implements serviceSession {

    @Autowired
    public Sessionid sessionidRepository;
    @Autowired
    public SessionMapper sessionMapper;

@Override
   public SessionidDto getuserIdBySessionId(){
    return null;
   }
    @Override
    public SessionidDto createBySessionId(SessionidDto sessionidDto) {
        Optional<SessionidModel> existingUser = sessionidRepository.findBySessionid(sessionidDto.getSessionid());

        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Session ID already exists: " + sessionidDto.getSessionid());
        }

        SessionidModel sessiondata = sessionMapper.toEntity(sessionidDto);
        SessionidModel saved = sessionidRepository.save(sessiondata);
        return sessionMapper.toDto(saved);
    }
    
}
