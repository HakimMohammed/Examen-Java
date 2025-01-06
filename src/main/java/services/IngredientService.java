package services;

import dao.IngredientDAO;
import models.Ingredient;

import java.util.List;

public class IngredientService {
    private IngredientDAO ingredientDAO = new IngredientDAO();

    public void ajouterIngredient(Ingredient ingredient) {
        ingredientDAO.create(ingredient);
    }

    public Ingredient trouverIngredient(int id) {
        return ingredientDAO.read(id);
    }

    public List<Ingredient> trouverTousLesIngredients() {
        return ingredientDAO.readAll();
    }

    public void modifierIngredient(Ingredient ingredient) {
        ingredientDAO.update(ingredient);
    }

    public void supprimerIngredient(int id) {
        ingredientDAO.delete(id);
    }
}
