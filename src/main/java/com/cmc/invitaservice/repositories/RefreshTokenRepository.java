package com.cmc.invitaservice.repositories;

import com.cmc.invitaservice.repositories.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken findByUsername(String username);
    @Transactional
    void deleteByToken(String token);
    @Transactional
    void deleteByExpiryDateLessThan(Date date);
}
