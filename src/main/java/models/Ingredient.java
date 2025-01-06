package models;

public class Ingredient {
    private int id;
    private String nom;
    private double prix;

    // Constructeurs, getters et setters
    public Ingredient(int id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public Ingredient(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}
