package controllers;

import models.PlatPrincipal;
import services.PlatPrincipalService;

import java.util.List;

public class PlatPrincipalController {
    private PlatPrincipalService platPrincipalService = new PlatPrincipalService();

    public void creerPlat(PlatPrincipal plat) {
        platPrincipalService.ajouterPlat(plat);
    }

    public PlatPrincipal afficherPlat(int id) {
        return platPrincipalService.trouverPlat(id);
    }

    public List<PlatPrincipal> afficherTousLesPlats() {
        return platPrincipalService.trouverTousLesPlatsPrincipaux();
    }

    public void mettreAJourPlat(PlatPrincipal plat) {
        platPrincipalService.modifierPlat(plat);
    }

    public void effacerPlat(int id) {
        platPrincipalService.supprimerPlat(id);
    }
}
