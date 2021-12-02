package com.apiodkpointage.apiodkpointage.utilisateurs.apprenants;

import com.apiodkpointage.apiodkpointage.Etat;
import com.apiodkpointage.apiodkpointage.promotions.Promotion;
import com.apiodkpointage.apiodkpointage.utilisateurs.Type;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@DiscriminatorValue("APPRENANT")
public class Apprenant extends Utilisateur
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Promotion promotion;

    public Apprenant() {
    }

    public Apprenant(String nom, String prenom, String adresse, int telephone, String email, String login, String motDePass, Etat etat, Long id) {
        super(nom, prenom, adresse, telephone, email, login, motDePass, etat);
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}
