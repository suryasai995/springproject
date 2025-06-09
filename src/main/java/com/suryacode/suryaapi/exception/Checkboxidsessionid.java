package com.suryacode.suryaapi.exception;

public class Checkboxidsessionid extends RuntimeException {

    // Default constructor
    public Checkboxidsessionid() {
        super("Missing required headers: boxid or sessionid");
    }

    // Constructor with custom message
    public Checkboxidsessionid(String message) {
        super(message);
    }
}
