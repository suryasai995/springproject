package com.suryacode.suryaapi.projectmain.sessionid.dto;

public class SessionidDto {

    Long id;
    String sessionid;
    String boxid;
    Long userid;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSessionid() {
        return sessionid;
    }
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
    public String getBoxid() {
        return boxid;
    }
    public void setBoxid(String boxid) {
        this.boxid = boxid;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }

     public SessionidDto(){}

    public SessionidDto(Long id, String sessionid, String boxid, Long userid) {
        this.id = id;
        this.sessionid = sessionid;
        this.boxid = boxid;
        this.userid = userid;
    }
    
    public SessionidDto( String sessionid, String boxid, Long userid) {
        this.sessionid = sessionid;
        this.boxid = boxid;
        this.userid = userid;
    }
}
