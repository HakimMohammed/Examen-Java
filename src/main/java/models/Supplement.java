package models;

public class Supplement {
    private int id;
    private String nom;
    private double prix;

    // Constructeurs, getters et setters
    public Supplement(int id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public Supplement(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
}
