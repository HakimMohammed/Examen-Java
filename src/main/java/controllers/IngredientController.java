package controllers;

import models.Ingredient;
import services.IngredientService;

import java.util.List;

public class IngredientController {
    private IngredientService ingredientService = new IngredientService();

    public void creerIngredient(Ingredient ingredient) {
        ingredientService.ajouterIngredient(ingredient);
    }

    public Ingredient afficherIngredient(int id) {
        return ingredientService.trouverIngredient(id);
    }

    public List<Ingredient> afficherTousLesIngredients() {
        return ingredientService.trouverTousLesIngredients();
    }

    public void mettreAJourIngredient(Ingredient ingredient) {
        ingredientService.modifierIngredient(ingredient);
    }

    public void effacerIngredient(int id) {
        ingredientService.supprimerIngredient(id);
    }
}
