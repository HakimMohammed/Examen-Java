package services;

import dao.RepasDAO;
import models.Repas;

import java.util.List;

public class RepasService {

    private RepasDAO repasDAO = new RepasDAO();

    public void ajouterRepas(Repas repas) {
        repasDAO.create(repas);
    }

    public Repas trouverRepas(int id) {
        return repasDAO.read(id);
    }

    public List<Repas> readAll() {
        return repasDAO.readAll();
    }

    public void ajouterSupplement(int idRepas, int idSupplement) {
        repasDAO.addSupplementToRepas(idRepas, idSupplement);
    }

    public void ajouterIngredient(int idRepas, int idIngredient) {
        repasDAO.addIngredientToRepas(idRepas, idIngredient);
    }

}
