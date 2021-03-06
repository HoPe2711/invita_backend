package com.cmc.invitaservice.models.response;

import com.cmc.invitaservice.repositories.entities.InvitaDocument;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Data
public class GetDocumentByTemplate {
    @JsonIgnore
    List<InvitaDocument> listDocument;
}