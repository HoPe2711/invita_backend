package com.cmc.invitaservice.service;

import com.cmc.invitaservice.models.request.*;
import com.cmc.invitaservice.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    ResponseEntity<GeneralResponse<Object>> loginAccount(LoginRequest loginRequest);
    ResponseEntity<GeneralResponse<Object>> logoutAccount(LogoutRequest logoutRequest);
    ResponseEntity<GeneralResponse<Object>> verifySignUp(Map<String, String> requestParam);
    ResponseEntity<GeneralResponse<Object>> changePassword(ChangePasswordRequest changePasswordRequest);
    ResponseEntity<GeneralResponse<Object>> signupAccount(CreateAccountRequest createAccountRequest, HttpServletRequest request);
    ResponseEntity<GeneralResponse<Object>> forgotPassword(ForgotPasswordRequest forgotPasswordRequest, HttpServletRequest httpServletRequest);
    ResponseEntity<GeneralResponse<Object>> resetPassword(ResetPasswordRequest resetPasswordRequest, Map<String, String> requestParam);
}
