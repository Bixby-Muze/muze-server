package com.muze.api.auth.security;

public class InvaildJwtException extends RuntimeException {

    public InvaildJwtException(String msg) {
        super(msg);
    }
}
