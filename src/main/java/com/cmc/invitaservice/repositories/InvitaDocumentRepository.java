package com.cmc.invitaservice.repositories;

import com.cmc.invitaservice.repositories.entities.InvitaDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitaDocumentRepository extends JpaRepository<InvitaDocument,Long> {
    InvitaDocument findInvitaDocumentById(Long id);
    List<InvitaDocument> findInvitaDocumentByApplicationUserUsername(String username);
    List<InvitaDocument> findInvitaDocumentsByInvitaTemplate_Id(Long id);
    List<InvitaDocument> findInvitaDocumentsByApplicationUser_UsernameAndInvitaTemplate_Id(String username, Long id);
}
