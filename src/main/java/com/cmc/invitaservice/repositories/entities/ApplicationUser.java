package com.cmc.invitaservice.repositories.entities;

import com.cmc.invitaservice.models.external.request.CreateAccountRequest;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class ApplicationUser extends BaseEntity {
    private static final long serialVersionUID = 1823759985171944176L;
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private  String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name =  "email", nullable = false, unique = true)
    private  String email;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    private Collection<InvitaDocument> invitaDocuments;

    public void setCreateAccountRequest(CreateAccountRequest createAccountRequest){
        this.username = createAccountRequest.getUsername();
        this.password = createAccountRequest.getPassword();
        this.firstName = createAccountRequest.getFirstName();
        this.lastName = createAccountRequest.getLastName();
        this.email = createAccountRequest.getEmail();
    }
}