package services;

import dao.CommandeDAO;
import models.Commande;

public class CommandeService {
    private CommandeDAO commandeDAO = new CommandeDAO();

    public void ajouterCommande(Commande commande) {
        commandeDAO.create(commande);
    }

    public Commande trouverCommande(int id) {
        return commandeDAO.read(id);
    }
}
