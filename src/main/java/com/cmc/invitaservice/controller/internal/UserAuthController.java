package com.cmc.invitaservice.controller.internal;

import com.cmc.invitaservice.models.external.request.ChangePasswordRequest;
import com.cmc.invitaservice.models.external.request.LogoutRequest;
import com.cmc.invitaservice.models.external.request.RefreshTokenRequest;
import com.cmc.invitaservice.models.external.response.RefreshTokenResponse;
import com.cmc.invitaservice.response.GeneralResponse;
import com.cmc.invitaservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*")
@Slf4j
@RestController
public class UserAuthController {

    private final UserService userService;

    public UserAuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/auth/changepassword")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<GeneralResponse<Object>> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return userService.changePassword(changePasswordRequest);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<GeneralResponse<Object>> logout(@RequestBody LogoutRequest logoutRequest){
        return userService.logoutAccount(logoutRequest);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<GeneralResponse<RefreshTokenResponse>> refresh(@RequestBody RefreshTokenRequest request){
        return userService.refreshToken(request);
    }
}
