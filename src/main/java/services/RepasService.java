package services;

import dao.RepasDAO;
import models.Repas;

public class RepasService {

    private RepasDAO repasDAO = new RepasDAO();

    public void ajouterRepas(Repas repas) {
        repasDAO.create(repas);
    }

    public Repas trouverRepas(int id) {
        return repasDAO.read(id);
    }

}
