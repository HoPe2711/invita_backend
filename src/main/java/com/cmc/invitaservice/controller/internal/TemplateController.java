package com.cmc.invitaservice.controller.internal;

import com.cmc.invitaservice.models.request.CreateTemplateRequest;
import com.cmc.invitaservice.response.GeneralResponse;
import com.cmc.invitaservice.service.TemplateService;
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

public class TemplateController {

    private final TemplateService templateService;

    @Autowired
    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("/template")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Object>> getAllTemplate() {
        return templateService.getAllTemplate();
    }

    @GetMapping("/template_child/{templateId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Object>> getChildTemplateById(@PathVariable(name="templateId") Long templateId){
        return templateService.getChildTemplateByTemplateId(templateId);
    }

    @GetMapping("/template_parent")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Object>> getParentTemplate(){
        return templateService.getParentTemplate();
    }

    @DeleteMapping("/template/{templateId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Object>> deleteTemplate(@PathVariable (name = "templateId") Long templateId){
        return templateService.deleteTemplate(templateId);
    }

    @GetMapping("/template/{templateId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Object>> getTemplateById(@PathVariable(name="templateId") Long templateId){
        return templateService.getTemplateByTemplateId(templateId);
    }

    @PostMapping("/template")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Object>> addTemplate(@RequestBody CreateTemplateRequest createTemplateRequest){
        return templateService.addTemplate(createTemplateRequest);
    }

    @PutMapping("/template/{templateId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Object>> editTemplate(@PathVariable(name="templateId") Long templateId,
                                        @RequestBody CreateTemplateRequest createTemplateRequest) {
        return templateService.changeTemplate(createTemplateRequest, templateId);
    }
}