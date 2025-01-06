package models;

import java.util.List;

public class Commande {
    private int id;
    private Client client;
    private double totalPrice;

    public Commande(int id, Client client, double totalPrice) {
        this.id = id;
        this.client = client;
        this.totalPrice = totalPrice;
    }

    public Commande(Client client, double totalPrice) {
        this.client = client;
        this.totalPrice = totalPrice;
    }

    public int getId() { return id; }
    public Client getClient() { return client; }
    public double getTotalPrice() { return totalPrice; }
}
