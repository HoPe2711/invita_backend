package com.cmc.invitaservice.models.external.request;

import lombok.Data;

@Data
public class UpdateDocumentRequest {
    private String documentName;
    private String filledInformation;
    private String note;
}
