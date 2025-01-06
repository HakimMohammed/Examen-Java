package controllers;

import models.Supplement;
import services.SupplementService;

import java.util.List;

public class SupplementController {
    private SupplementService supplementService = new SupplementService();

    public void creerSupplement(Supplement supplement) {
        supplementService.ajouterSupplement(supplement);
    }

    public Supplement afficherSupplement(int id) {
        return supplementService.trouverSupplement(id);
    }

    public List<Supplement> afficherTousLesSupplements() {
        return supplementService.trouverTousLesSupplements();
    }

    public void mettreAJourSupplement(Supplement supplement) {
        supplementService.modifierSupplement(supplement);
    }

    public void effacerSupplement(int id) {
        supplementService.supprimerSupplement(id);
    }
}
