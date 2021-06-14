package com.cmc.invitaservice.controller.external;

import com.cmc.invitaservice.models.external.request.CreateAccountRequest;
import com.cmc.invitaservice.models.external.request.ForgotPasswordRequest;
import com.cmc.invitaservice.models.external.request.LoginRequest;
import com.cmc.invitaservice.models.external.request.ResetPasswordRequest;
import com.cmc.invitaservice.response.GeneralResponse;
import com.cmc.invitaservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<GeneralResponse<Object>> signUp(@RequestBody CreateAccountRequest createAccountRequest, HttpServletRequest request){
        return userService.signupAccount(createAccountRequest,request);
    }

    @GetMapping("/verify")
    public ResponseEntity<GeneralResponse<Object>> verify(@RequestParam Map<String, String> requestParam){
        return userService.verifySignUp(requestParam);
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<Object>> login(@Valid @RequestBody LoginRequest loginRequest){
        return userService.loginAccount(loginRequest);
    }

    @PostMapping("/forgot")
    public ResponseEntity<GeneralResponse<Object>> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest, HttpServletRequest httpServletRequest){
        return userService.forgotPassword(forgotPasswordRequest,httpServletRequest);
    }

    @PostMapping("/reset")
    public ResponseEntity<GeneralResponse<Object>> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest,
                                                                 @RequestParam Map<String, String> requestParam){
        return userService.resetPassword(resetPasswordRequest,requestParam);
    }
}
