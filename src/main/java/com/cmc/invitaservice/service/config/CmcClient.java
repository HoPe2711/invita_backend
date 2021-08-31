package com.cmc.invitaservice.service.config;

import com.cmc.invitaservice.models.DTO.CmcUser;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CmcClient {

    private final RestTemplate restTemplate;

    @Autowired
    public CmcClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CmcUser getUser(String accessToken) {
        var path = "/backend/users";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String CMC_API_BASE = "http://localhost:8082";
        return  restTemplate.exchange(CMC_API_BASE + path, HttpMethod.GET, entity,CmcUser.class).getBody();
    }
}