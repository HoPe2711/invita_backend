package com.cmc.invitaservice.models.external.response;

import lombok.Data;

import static com.cmc.invitaservice.security.SecurityConstants.TOKEN_PREFIX;

@Data
public class RefreshTokenResponse {
    private String token;
    private String type = TOKEN_PREFIX;

    public RefreshTokenResponse(String token) {
        this.token = token;
    }
}
