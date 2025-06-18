package com.suryacode.suryaapi.projectmain.sessionid.service;

import com.suryacode.suryaapi.projectmain.sessionid.dto.SessionidDto;

public interface serviceSession {

    SessionidDto getuserIdBySessionId();
    
    SessionidDto createBySessionId(SessionidDto sessionidDto);
}
