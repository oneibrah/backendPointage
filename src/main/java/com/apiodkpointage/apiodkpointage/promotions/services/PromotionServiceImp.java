package com.apiodkpointage.apiodkpointage.promotions.services;

import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.administrateurs.repositories.AdministrateurRepository;
import com.apiodkpointage.apiodkpointage.log.Service.LogServiceImp;
import com.apiodkpointage.apiodkpointage.promotions.Promotion;
import com.apiodkpointage.apiodkpointage.promotions.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImp implements PromotionService {
    @Autowired
    PromotionRepository promotionRepository;


    Promotion promotion;
    @Autowired
    LogServiceImp logServiceImp;
    @Autowired
    AdministrateurRepository administrateurRepository;

    @Override
    public Promotion ajouterPromotion(Promotion promotion,Long idAdmin) {
        Administrateur administrateur= administrateurRepository.findById(idAdmin).get();
        logServiceImp.addLogAdmin(administrateur,"L'admin avec l'id: " +idAdmin+" a ajouté la promotion " +promotion.getNom());
        return promotionRepository.save(promotion);

    }

    @Override
    public Promotion modifierPromotion(Promotion promotion, Long id,Long idAdmin) {
        Administrateur administrateur= administrateurRepository.findById(idAdmin).get();
        Promotion modification = promotionRepository.findById(id).get();
        modification.setNom(promotion.getNom());
        modification.setAnnee(promotion.getAnnee());
        modification.setDateDebut(promotion.getDateDebut());
        modification.setDateFin(promotion.getDateFin());
        modification.setApprenants(promotion.getApprenants());
        modification.setTotalApprenants(promotion.getTotalApprenants());
        modification.setNombreFemmes(promotion.getNombreFemmes());
        modification.setNombreHommes(promotion.getNombreHommes());
        modification.setHoraireDebutJournee(promotion.getHoraireDebutJournee());
        modification.setHoraireFinJournee(promotion.getHoraireFinJournee());
        logServiceImp.addLogAdmin(administrateur,"L'admin avec l'id: " + idAdmin + " a modifié la promotion " + promotion.getNom());
        return promotionRepository.save(modification);

    }

    @Override
    public String supprimerPromotion(Long id,Long idAdmin) {
        Administrateur administrateur= administrateurRepository.findById(idAdmin).get();
        promotionRepository.deleteById(id);
        logServiceImp.addLogAdmin(administrateur,"L'admin avec l'id: " + idAdmin + " a supprimé la promotion avec l'id" + id);
        return "Suppression éffectuée avec succès";
    }

    @Override
    public List<Promotion> afficherListePromotion() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion afficherParId(Long id) {
        return promotionRepository.findById(id).get();
    }
}
