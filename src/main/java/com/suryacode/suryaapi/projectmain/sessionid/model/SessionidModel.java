package com.suryacode.suryaapi.projectmain.sessionid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="sessionid")
public class SessionidModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     

    @Column(unique = true, nullable = false)
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

    public SessionidModel(Long id, String sessionid, String boxid, Long userid) {
        this.id = id;
        this.sessionid = sessionid;
        this.boxid = boxid;
        this.userid = userid;
    }
      public SessionidModel(){}
     public SessionidModel(String sessionid, String boxid, Long userid) {
        this.sessionid = sessionid;
        this.boxid = boxid;
        this.userid = userid;
    }
}
