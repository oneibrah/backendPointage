package com.apiodkpointage.apiodkpointage.utilisateurs.repository;

import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateursRepository extends JpaRepository <Utilisateur, Long > {
    Optional<Utilisateur> findByLoginAndMotDePass(String login, String password);
    Utilisateur findUtilisateurByLogin(String login);
}
