package com.cmc.invitaservice.service;

import com.cmc.invitaservice.models.request.CreateDocumentRequest;
import com.cmc.invitaservice.models.request.UpdateDocumentRequest;
import com.cmc.invitaservice.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

public interface DocumentService {
    ResponseEntity<GeneralResponse<Object>> getAllDocument();
    ResponseEntity<GeneralResponse<Object>> getDocumentByTemplate(Long id);
    ResponseEntity<GeneralResponse<Object>> deleteDocument(Long id);
    ResponseEntity<GeneralResponse<Object>> getDocumentById(Long Id);
    ResponseEntity<GeneralResponse<Object>> addDocument(CreateDocumentRequest createDocumentRequest);
    ResponseEntity<GeneralResponse<Object>> addSubDocument(Long templateId,Long documentId);
    ResponseEntity<GeneralResponse<Object>> changeDocument(UpdateDocumentRequest updateDocumentRequest, Long Id);
}
