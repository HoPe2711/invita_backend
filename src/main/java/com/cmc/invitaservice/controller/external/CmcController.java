package com.cmc.invitaservice.controller.external;

import com.cmc.invitaservice.models.request.LoginCmcRequest;
import com.cmc.invitaservice.response.GeneralResponse;
import com.cmc.invitaservice.service.CmcClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/auth")
public class CmcController {

    private final CmcClientService cmcClientService;

    @Autowired
    public CmcController(CmcClientService cmcClientService) {
        this.cmcClientService = cmcClientService;
    }

    @PostMapping("/cmc/login")
    public ResponseEntity<GeneralResponse<Object>> cmcLogin(@RequestBody LoginCmcRequest loginCmcRequest){
        return cmcClientService.loginUser(loginCmcRequest.getAccessToken());
    }
}
