package com.cmc.invitaservice.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginCmcRequest {
    @NotBlank
    private String accessToken;
}
