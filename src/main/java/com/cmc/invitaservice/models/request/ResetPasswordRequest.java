package com.cmc.invitaservice.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordRequest {
    @NotBlank
    private String password;
    @NotBlank
    private String retypePassword;
}
