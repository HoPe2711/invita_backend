package com.cmc.invitaservice.service;

import com.cmc.invitaservice.models.external.request.ChangePasswordRequest;
import com.cmc.invitaservice.models.external.request.CreateAccountRequest;
import com.cmc.invitaservice.models.external.request.LoginRequest;
import com.cmc.invitaservice.repositories.entities.ApplicationUser;

public interface UserService {
    ApplicationUser addAccount(CreateAccountRequest createAccountRequest);
    boolean findUsername(String username);
    boolean findEmail(String email);
    boolean checkAccount(LoginRequest loginRequest);
    boolean changePassword(String username, ChangePasswordRequest changePasswordRequest);
}
