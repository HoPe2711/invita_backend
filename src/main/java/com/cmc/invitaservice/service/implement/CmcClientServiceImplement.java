package com.cmc.invitaservice.service.implement;

import com.cmc.invitaservice.models.DTO.CmcUser;
import com.cmc.invitaservice.models.request.CreateAccountRequest;
import com.cmc.invitaservice.models.response.LoginResponse;
import com.cmc.invitaservice.repositories.ApplicationUserRepository;
import com.cmc.invitaservice.repositories.RoleRepository;
import com.cmc.invitaservice.repositories.entities.ApplicationUser;
import com.cmc.invitaservice.repositories.entities.ERole;
import com.cmc.invitaservice.repositories.entities.Role;
import com.cmc.invitaservice.response.GeneralResponse;
import com.cmc.invitaservice.response.ResponseFactory;
import com.cmc.invitaservice.security.filter.JWT.JwtUtils;
import com.cmc.invitaservice.security.filter.service.UserDetailsImplement;
import com.cmc.invitaservice.security.filter.service.UserDetailsServiceImplement;
import com.cmc.invitaservice.service.CmcClientService;
import com.cmc.invitaservice.service.config.CmcClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CmcClientServiceImplement implements CmcClientService {

    private final CmcClient cmcClient;
    private final UserDetailsServiceImplement userDetailsServiceImplement;
    private final ApplicationUserRepository applicationUserRepository;
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;

    @Autowired
    public CmcClientServiceImplement(CmcClient cmcClient, ApplicationUserRepository applicationUserRepository, UserDetailsServiceImplement userDetailsServiceImplement, JwtUtils jwtUtils, RoleRepository roleRepository) {
        this.cmcClient = cmcClient;
        this.applicationUserRepository = applicationUserRepository;
        this.userDetailsServiceImplement = userDetailsServiceImplement;
        this.jwtUtils = jwtUtils;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> loginUser(String cmcAccessToken){
        CmcUser cmcUser = cmcClient.getUser(cmcAccessToken);
        String username = cmcUser.getEmail()+cmcUser.getId();
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null){
            String password = generatePassword(16);
            CreateAccountRequest createAccountRequest = new CreateAccountRequest(username, password, password, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), "cmcaccount: "+ cmcUser.getEmail());
            Set<Role> roles = new HashSet<>();
            ApplicationUser applicationUser1 = new ApplicationUser();
            applicationUser1.setCreateAccountRequest(createAccountRequest);
            roles.add(roleRepository.findByName(ERole.ROLE_USER));
            applicationUser1.setRoles(roles);
            applicationUser1.setStatus(true);
            applicationUserRepository.save(applicationUser1);
        }
        UserDetails userDetails = userDetailsServiceImplement.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJWT(authentication);
        UserDetailsImplement userDetailsImplement = (UserDetailsImplement) authentication.getPrincipal();
        List<String> roles = userDetailsImplement.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        LoginResponse loginResponse = new LoginResponse( jwt, userDetailsImplement.getId(), userDetailsImplement.getUsername(), userDetailsImplement.getEmail(), roles);
        return ResponseFactory.success(loginResponse, LoginResponse.class);
    }

    private String generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return new String(password);
    }
}
