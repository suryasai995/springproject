package com.suryacode.suryaapi.projectmain.sessionid.mapper;

import org.springframework.stereotype.Component;

import com.suryacode.suryaapi.projectmain.sessionid.dto.SessionidDto;
import com.suryacode.suryaapi.projectmain.sessionid.model.SessionidModel;

@Component
public class SessionMapper {

     public SessionidDto toDto(SessionidModel session) {
        return new SessionidDto(session.getId(),session.getSessionid(),session.getBoxid(),session.getUserid());
    }

    public SessionidModel toEntity(SessionidDto session) {
        return new SessionidModel(session.getSessionid(),session.getBoxid(),session.getUserid());
    }
}
