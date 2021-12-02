package com.apiodkpointage.apiodkpointage.log;

import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Administrateur administrateur;


    private String typeActeur;
    private String action;

    private LocalDate date = LocalDate.now();
    private  LocalTime heure = LocalTime.now();

    public Log() {
    }

    public Log(Utilisateur utilisateur, Administrateur administrateur, String typeActeur, String action, LocalDate date, LocalTime heure) {
        this.utilisateur = utilisateur;
        this.administrateur = administrateur;
        this.typeActeur = typeActeur;
        this.action = action;
        this.date = date;
        this.heure = heure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public String getTypeActeur() {
        return typeActeur;
    }

    public void setTypeActeur(String typeActeur) {
        this.typeActeur = typeActeur;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }
}
