package com.cmc.invitaservice.service;

import com.cmc.invitaservice.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

public interface CmcClientService {
    ResponseEntity<GeneralResponse<Object>> loginUser(String cmcAccessToken);
}
