package controllers;

import models.Client;
import services.ClientService;

import java.util.List;

public class ClientController {
    private ClientService clientService = new ClientService();

    public void creerClient(Client client) {
        clientService.ajouterClient(client);
    }

    public Client afficherClient(int id) {
        return clientService.trouverClient(id);
    }

    public List<Client> afficherTousLesClients() {
        return clientService.trouverTousLesClients();
    }

    public void mettreAJourClient(Client client) {
        clientService.modifierClient(client);
    }

    public void effacerClient(int id) {
        clientService.supprimerClient(id);
    }
}
