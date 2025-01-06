package controllers;

import models.Repas;
import services.RepasService;

import java.util.List;

public class RepasController {
    private RepasService repasService = new RepasService();

    public void creerRepas(Repas repas) {
        repasService.ajouterRepas(repas);
    }

    public Repas afficherRepas(int id) {
        return repasService.trouverRepas(id);
    }

    public List<Repas> afficherTousLesRepas() {
        return repasService.readAll();
    }

    public void ajouterSupplement(int idRepas, int idSupplement) {
        repasService.ajouterSupplement(idRepas, idSupplement);
    }

    public void ajouterIngredient(int idRepas, int idIngredient) {
        repasService.ajouterIngredient(idRepas, idIngredient);
    }
}
