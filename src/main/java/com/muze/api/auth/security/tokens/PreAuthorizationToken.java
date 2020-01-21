package com.muze.api.auth.security.tokens;

import com.muze.api.auth.dto.FormLoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    public PreAuthorizationToken(String email, String password) {
        super(email, password);
    }

    public PreAuthorizationToken(FormLoginDto dto) {
        this(dto.getEmail(), dto.getPassword());
    }

    public String getEmail() {
        return (String) super.getPrincipal();
    }

    public String getPassword() {
        return (String) super.getCredentials();
    }
}
