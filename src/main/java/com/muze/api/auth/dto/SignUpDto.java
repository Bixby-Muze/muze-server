package com.muze.api.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SignUpDto {

    private String email;
    private String username;
    private String password;

    public SignUpDto(Map<String, Object> json) {
        this.email = (String) json.get("email");
        this.username = (String) json.get("username");
        this.password = (String) json.get("password");
    }
}
