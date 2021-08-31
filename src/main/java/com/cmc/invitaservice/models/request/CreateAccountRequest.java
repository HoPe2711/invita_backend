package com.cmc.invitaservice.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CreateAccountRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String retypePassword;
    @NotBlank
    private  String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private  String email;

}
