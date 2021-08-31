package com.cmc.invitaservice.service.implement;

import com.cmc.invitaservice.models.request.CreateTemplateRequest;
import com.cmc.invitaservice.models.response.GetAllChildTemplateResponse;
import com.cmc.invitaservice.models.response.GetAllParentTemplateResponse;
import com.cmc.invitaservice.models.response.GetAllTemplateResponse;
import com.cmc.invitaservice.repositories.InvitaTemplateRepository;
import com.cmc.invitaservice.repositories.entities.InvitaTemplate;
import com.cmc.invitaservice.response.GeneralResponse;
import com.cmc.invitaservice.response.ResponseFactory;
import com.cmc.invitaservice.response.ResponseStatusEnum;
import com.cmc.invitaservice.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class TemplateServiceImplement implements TemplateService {

    private final InvitaTemplateRepository invitaTemplateRepository;

    @Autowired
    public TemplateServiceImplement(InvitaTemplateRepository invitaTemplateRepository) {
        this.invitaTemplateRepository = invitaTemplateRepository;
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getAllTemplate() {
        List<InvitaTemplate> invitaTemplateList = invitaTemplateRepository.findAll();
        GetAllTemplateResponse getAllTemplateResponse = new GetAllTemplateResponse();
        getAllTemplateResponse.setListTemplate(invitaTemplateList);
        return ResponseFactory.success(getAllTemplateResponse);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> deleteTemplate(Long id){
        InvitaTemplate invitaTemplate = invitaTemplateRepository.findInvitaTemplateById(id);
        if (invitaTemplate == null)
            return ResponseFactory.error(HttpStatus.valueOf(400), ResponseStatusEnum.TEMPLATE_EXIST);
        invitaTemplateRepository.deleteById(id);
        return ResponseFactory.success();
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getParentTemplate(){
        Set<InvitaTemplate> invitaTemplateList = invitaTemplateRepository.findAllByInvitaTemplateListNotNull();
        GetAllParentTemplateResponse getAllParentTemplateResponse = new GetAllParentTemplateResponse();
        getAllParentTemplateResponse.setListTemplate(invitaTemplateList);
        return ResponseFactory.success(getAllParentTemplateResponse);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getChildTemplateByTemplateId(Long templateId){
        List<InvitaTemplate> invitaTemplateList = invitaTemplateRepository.findAllByInvitaTemplate_Id(templateId);
        GetAllChildTemplateResponse getAllChildTemplateResponse = new GetAllChildTemplateResponse();
        getAllChildTemplateResponse.setListFeedback(invitaTemplateList);
        return ResponseFactory.success(getAllChildTemplateResponse);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getTemplateByTemplateId(Long templateId){
        InvitaTemplate invitaTemplate = invitaTemplateRepository.findInvitaTemplateById(templateId);
        return ResponseFactory.success(invitaTemplate);
    }

    private InvitaTemplate getParent(Long id){
        return invitaTemplateRepository.findInvitaTemplateById(id);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> addTemplate(CreateTemplateRequest createTemplateRequest){
        Long parentId = createTemplateRequest.getParentId();
        InvitaTemplate parentTemplate = getParent(parentId);
        if (parentId != null && parentTemplate == null)
            return ResponseFactory.error(HttpStatus.valueOf(400), ResponseStatusEnum.TEMPLATE_EXIST);
        InvitaTemplate invitaTemplate = new InvitaTemplate();
        invitaTemplate.setInvitaTemplate(parentTemplate);
        invitaTemplate.setCreateTemplateRequest(createTemplateRequest);
        invitaTemplateRepository.save(invitaTemplate);
        return ResponseFactory.success(invitaTemplate);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> changeTemplate(CreateTemplateRequest createTemplateRequest, Long templateId){
        InvitaTemplate invitaTemplate = invitaTemplateRepository.findInvitaTemplateById(templateId);
        if (invitaTemplate == null)
            return ResponseFactory.error(HttpStatus.valueOf(400), ResponseStatusEnum.UNKNOWN_ERROR);
        Long parentId = createTemplateRequest.getParentId();
        InvitaTemplate parentTemplate = getParent(parentId);
        if (parentId != null && (parentTemplate == null || parentId.equals(templateId)))
            return ResponseFactory.error(HttpStatus.valueOf(400), ResponseStatusEnum.TEMPLATE_EXIST);
        invitaTemplate.setCreateTemplateRequest(createTemplateRequest);
        invitaTemplate.setInvitaTemplate(parentTemplate);
        invitaTemplateRepository.save(invitaTemplate);
        return ResponseFactory.success(invitaTemplate);
    }
}
