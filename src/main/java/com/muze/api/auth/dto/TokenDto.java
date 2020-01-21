package com.muze.api.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDto {

    @JsonProperty("token")
    private String token;

    public TokenDto(String token) {
        this.token = token;
    }
}
