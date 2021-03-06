package com.cmc.invitaservice.controller.internal;

import com.cmc.invitaservice.models.request.CreateDocumentRequest;
import com.cmc.invitaservice.models.request.UpdateDocumentRequest;
import com.cmc.invitaservice.response.GeneralResponse;
import com.cmc.invitaservice.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping(path = "/external", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/document")
    public ResponseEntity<GeneralResponse<Object>> getAllDocument() {
        return documentService.getAllDocument();
    }

    @DeleteMapping("/document/{documentId}")
    public ResponseEntity<GeneralResponse<Object>> deleteDocument(@PathVariable(name="documentId") Long documentId){
        return documentService.deleteDocument(documentId);
    }

    @GetMapping("/document_temp/{templateId}")
    public ResponseEntity<GeneralResponse<Object>> getDocumentByTemplate(@PathVariable(name="templateId") Long templateId) {
        return documentService.getDocumentByTemplate(templateId);
    }

    @GetMapping("/document/{documentId}")
    public  ResponseEntity<GeneralResponse<Object>> getDocumentByName(@PathVariable(name="documentId") Long documentId){
        return documentService.getDocumentById(documentId);
    }

    @GetMapping("/document_temp/{templateId}/{documentId}")
    public ResponseEntity<GeneralResponse<Object>> addSubDocment(@PathVariable(name="templateId") Long templateId,
                                                                 @PathVariable(name="documentId") Long documentId) {
        return documentService.addSubDocument(templateId,documentId);
    }

    @PostMapping("document")
    public ResponseEntity<GeneralResponse<Object>> addDocument(@RequestBody CreateDocumentRequest createDocumentRequest){
        return documentService.addDocument(createDocumentRequest);
    }

    @PutMapping("/document/{documentId}")
    public  ResponseEntity<GeneralResponse<Object>> editDocument(@PathVariable(name="documentId") Long documentId,
                                        @RequestBody UpdateDocumentRequest updateDocumentRequest) {
            return documentService.changeDocument(updateDocumentRequest, documentId);
    }
}
