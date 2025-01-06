package models;

public class Client {
    private int id;
    private String nom;
    private String email;

    public Client(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public Client(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }

    public void setNom(String nom) { this.nom = nom; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
