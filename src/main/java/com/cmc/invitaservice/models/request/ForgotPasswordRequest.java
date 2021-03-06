package com.cmc.invitaservice.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ForgotPasswordRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String email;
}

