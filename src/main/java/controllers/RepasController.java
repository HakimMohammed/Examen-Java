package controllers;

import models.Repas;
import services.RepasService;

public class RepasController {
    private RepasService repasService = new RepasService();

    public void creerRepas(Repas repas) {
        repasService.ajouterRepas(repas);
    }

    public Repas afficherRepas(int id) {
        return repasService.trouverRepas(id);
    }
}
