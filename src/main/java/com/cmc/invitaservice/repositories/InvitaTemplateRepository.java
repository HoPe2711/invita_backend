package com.cmc.invitaservice.repositories;

import com.cmc.invitaservice.repositories.entities.InvitaTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface InvitaTemplateRepository extends JpaRepository<InvitaTemplate, Long> {
    InvitaTemplate findInvitaTemplateById(Long id);
    List<InvitaTemplate> findAllByInvitaTemplate_Id(Long id);
    Set<InvitaTemplate> findAllByInvitaTemplateListNotNull();
}
