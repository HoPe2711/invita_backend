package com.cmc.invitaservice.service;

import com.cmc.invitaservice.models.request.CreateTemplateRequest;
import com.cmc.invitaservice.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

public interface TemplateService {
    ResponseEntity<GeneralResponse<Object>> getAllTemplate();
    ResponseEntity<GeneralResponse<Object>> getChildTemplateByTemplateId(Long id);
    ResponseEntity<GeneralResponse<Object>> getParentTemplate();
    ResponseEntity<GeneralResponse<Object>> deleteTemplate(Long id);
    ResponseEntity<GeneralResponse<Object>> getTemplateByTemplateId(Long id);
    ResponseEntity<GeneralResponse<Object>> addTemplate(CreateTemplateRequest createTemplateRequest);
    ResponseEntity<GeneralResponse<Object>> changeTemplate(CreateTemplateRequest createTemplateRequest, Long id);
}
