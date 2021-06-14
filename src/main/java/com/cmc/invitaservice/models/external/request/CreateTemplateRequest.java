package com.cmc.invitaservice.models.external.request;

import lombok.Data;

@Data
public class CreateTemplateRequest {
    private String templateName;
    private String templateContent;
    private Long parentId;
    private String note;
    private String previewImg;
}
