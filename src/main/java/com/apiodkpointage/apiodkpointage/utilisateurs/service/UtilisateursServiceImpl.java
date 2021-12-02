package com.apiodkpointage.apiodkpointage.utilisateurs.service;

import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.administrateurs.repositories.AdministrateurRepository;
import com.apiodkpointage.apiodkpointage.log.Service.LogServiceImp;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;
import com.apiodkpointage.apiodkpointage.utilisateurs.repository.UtilisateursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateursServiceImpl implements UtilisateursServices{
    @Autowired
    UtilisateursRepository utilisateursRepository;

    @Autowired
    AdministrateurRepository administrateurRepository;

    @Autowired
    LogServiceImp logServiceImp;


    @Override
    public String ajout_utilisateurs(Utilisateur utilisateur, Long idAdmin) {
        Administrateur administrateur = administrateurRepository.findById(idAdmin).get();
        utilisateursRepository.save(utilisateur);
        logServiceImp.addLogAdmin(administrateur, "ajout de l'utilisateur "+ utilisateur.getPrenom()+" "+ utilisateur.getNom());

        return "utilisateur "+utilisateur.getPrenom()+" "+utilisateur.getNom()+" ajouté avec succès";
    }

    @Override
    public Utilisateur modifier_utilisateur(Utilisateur utilisateur, Long id, Long idAdmin) {
        Administrateur administrateur=administrateurRepository.findById(idAdmin).get();
       Utilisateur modifier = utilisateursRepository.findById(id).get();
       modifier.setNom(utilisateur.getNom());
       modifier.setPrenom(utilisateur.getPrenom());
       modifier.setAdresse(utilisateur.getAdresse());
       modifier.setEmail(utilisateur.getEmail());
       modifier.setEtat(utilisateur.getEtat());
       modifier.setLogin(utilisateur.getLogin());
       modifier.setMotDePass(utilisateur.getMotDePass());
       modifier.setTelephone(utilisateur.getTelephone());
       modifier.setDateModification();
       logServiceImp.addLogAdmin(administrateur, "Modfication de l'utilisateur "+ utilisateur.getPrenom()+" "+ utilisateur.getNom());

        return utilisateursRepository.save(modifier);
    }

    @Override
    public void supprimer_utilisateur(Long id, Long idAdmin) {
        Administrateur administrateur= administrateurRepository.findById(idAdmin).get();
        utilisateursRepository.deleteById(id);
        logServiceImp.addLogAdmin(administrateur, "Suppression de l'utlisateur avec id: "+id+" par Admin avec id: "+ idAdmin);
    }

    @Override
    public List<Utilisateur> afficher_utilisateur() {
        return utilisateursRepository.findAll();
    }

    @Override
    public Utilisateur afficher_par_id(Long id) {
        return utilisateursRepository.findById(id).get();
    }

    @Override
    public String modifierPassword(Long id, String nouveauPassword) {
        Utilisateur utilisateurExistant = utilisateursRepository.findById(id).get();
        utilisateurExistant.setMotDePass(nouveauPassword);
        logServiceImp.addLog(utilisateurExistant, "Modification du mot de passe pour l'admin "+utilisateurExistant.getPrenom()+" "+utilisateurExistant.getNom());

        return "Mot de passe modifié avec succès !";
    }

    @Override
    public Utilisateur login(String login, String password) {
        Optional <Utilisateur> connexion= utilisateursRepository.findByLoginAndMotDePass(login, password);

        if(connexion.isEmpty())
        {
            return null;
        }

        logServiceImp.addLog(connexion.get(), "Connexion de l'utilisateur "+connexion.get().getPrenom()+" "+connexion.get().getNom());
        return connexion.get();
    }

    @Override
    public Utilisateur findByUtilisateurAndLogin(String login) {
        return utilisateursRepository.findUtilisateurByLogin(login);
    }


}
