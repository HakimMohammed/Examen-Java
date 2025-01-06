package models;

import java.util.List;
import java.util.stream.Collectors;

public class Repas {
    private int id;
    private PlatPrincipal mainDish;
    private List<Ingredient> ingredients;
    private List<Supplement> supplements;
    private double totalPrice;

    public Repas(int id, PlatPrincipal mainDish, List<Ingredient> ingredients, List<Supplement> supplements) {
        this.id = id;
        this.mainDish = mainDish;
        this.ingredients = ingredients;
        this.supplements = supplements;
        this.totalPrice = calculerTotal();
    }

    public Repas(PlatPrincipal mainDish, List<Ingredient> ingredients, List<Supplement> supplements) {
        this.mainDish = mainDish;
        this.ingredients = ingredients;
        this.supplements = supplements;
        this.totalPrice = calculerTotal();
    }

    public int getId() { return id; }
    public PlatPrincipal getMainDish() { return mainDish; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public List<Supplement> getSupplements() { return supplements; }
    public double getTotalPrice() { return totalPrice; }

    public double calculerTotal() {
        double total = mainDish.getPrixBase();
        for (Ingredient ingredient : ingredients) {
            total += ingredient.getPrix();
        }
        for (Supplement supplement : supplements) {
            total += supplement.getPrix();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Repas {" +
                "id=" + id +
                ", mainDish=" + mainDish.getNom() +
                ", ingredients=" + ingredients.stream().map(Ingredient::getNom).collect(Collectors.joining(", ")) +
                ", supplements=" + supplements.stream().map(Supplement::getNom).collect(Collectors.joining(", ")) +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
