package controllers;

import models.Commande;
import services.CommandeService;

public class CommandeController {
    private CommandeService commandeService = new CommandeService();

    public void creerCommande(Commande commande) {
        commandeService.ajouterCommande(commande);
    }

    public Commande afficherCommande(int id) {
        return commandeService.trouverCommande(id);
    }
}
