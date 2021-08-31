package com.cmc.invitaservice.models.response;

import com.cmc.invitaservice.repositories.entities.InvitaTemplate;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Set;

@Data
public class GetAllParentTemplateResponse {
    @JsonIgnore
    Set<InvitaTemplate> listTemplate;
}
