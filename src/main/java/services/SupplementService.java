package services;

import dao.SupplementDAO;
import models.Supplement;

import java.util.List;

public class SupplementService {
    private SupplementDAO supplementDAO = new SupplementDAO();

    public void ajouterSupplement(Supplement supplement) {
        supplementDAO.create(supplement);
    }

    public Supplement trouverSupplement(int id) {
        return supplementDAO.read(id);
    }

    public List<Supplement> trouverTousLesSupplements() {
        return supplementDAO.readAll();
    }

    public void modifierSupplement(Supplement supplement) {
        supplementDAO.update(supplement);
    }

    public void supprimerSupplement(int id) {
        supplementDAO.delete(id);
    }
}
