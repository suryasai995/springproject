package com.suryacode.suryaapi.projectmain.sessionid.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suryacode.suryaapi.projectmain.sessionid.dto.SessionidDto;
import com.suryacode.suryaapi.projectmain.sessionid.model.SessionidModel;

public interface Sessionid extends JpaRepository<SessionidModel ,Long>{
     Optional<SessionidModel> findBySessionid(String sessionid);

}
