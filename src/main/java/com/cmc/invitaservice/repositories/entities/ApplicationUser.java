package com.cmc.invitaservice.repositories.entities;

import com.cmc.invitaservice.models.request.CreateAccountRequest;
import com.cmc.invitaservice.models.request.UpdateAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name =  "status", nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    @JsonIgnore//Properties(value = "applicationUser", allowSetters = true)
    private List<InvitaDocument> invitaDocumentList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public void setCreateAccountRequest(CreateAccountRequest createAccountRequest){
        this.username = createAccountRequest.getUsername();
        this.password = createAccountRequest.getPassword();
        this.firstName = createAccountRequest.getFirstName();
        this.lastName = createAccountRequest.getLastName();
        this.email = createAccountRequest.getEmail();
        this.status = false;
    }

    public void setUpdateAccountRequest(UpdateAccountRequest updateAccountRequest){
        this.username = updateAccountRequest.getUsername();
        this.firstName = updateAccountRequest.getFirstName();
        this.lastName = updateAccountRequest.getLastName();
        this.email = updateAccountRequest.getEmail();
    }
}
