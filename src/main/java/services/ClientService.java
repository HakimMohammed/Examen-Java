package services;

import dao.ClientDAO;
import models.Client;

import java.util.List;

public class ClientService {
    private ClientDAO clientDAO = new ClientDAO();

    public void ajouterClient(Client client) {
        clientDAO.create(client);
    }

    public Client trouverClient(int id) {
        return clientDAO.read(id);
    }

    public List<Client> trouverTousLesClients() {
        return clientDAO.readAll();
    }

    public void modifierClient(Client client) {
        clientDAO.update(client);
    }

    public void supprimerClient(int id) {
        clientDAO.delete(id);
    }
}
