package com.cmc.invitaservice.models.response;

import com.cmc.invitaservice.repositories.entities.InvitaTemplate;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Data
public class GetAllChildTemplateResponse {
    @JsonIgnore
    List<InvitaTemplate> listFeedback;
}
