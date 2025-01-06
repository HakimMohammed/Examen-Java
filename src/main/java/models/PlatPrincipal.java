package models;

public class PlatPrincipal {
    private int id;
    private String nom;
    private double prixBase;

    // Constructeurs, getters et setters
    public PlatPrincipal(int id, String nom, double prixBase) {
        this.id = id;
        this.nom = nom;
        this.prixBase = prixBase;
    }

    public PlatPrincipal(String nom, double prixBase) {
        this.nom = nom;
        this.prixBase = prixBase;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getPrixBase() { return prixBase; }

    public void setNom(String nom) { this.nom = nom; }
    public void setPrixBase(double prixBase) { this.prixBase = prixBase; }

    @Override
    public String toString() {
        return nom;
    }
}
