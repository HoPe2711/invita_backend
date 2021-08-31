package com.cmc.invitaservice.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@NotBlank
public class UpdateDocumentRequest {
    private String documentName;
    private String filledInformation;
    private String note;
}
