package services;

import dao.PlatPrincipalDAO;
import models.PlatPrincipal;

import java.util.List;

public class PlatPrincipalService {
    private PlatPrincipalDAO platPrincipalDAO = new PlatPrincipalDAO();

    public void ajouterPlat(PlatPrincipal platPrincipal) {
        platPrincipalDAO.create(platPrincipal);
    }

    public PlatPrincipal trouverPlat(int id) {
        return platPrincipalDAO.read(id);
    }

    public List<PlatPrincipal> trouverTousLesPlatsPrincipaux() {
        return platPrincipalDAO.readAll();
    }

    public void modifierPlat(PlatPrincipal platPrincipal) {
        platPrincipalDAO.update(platPrincipal);
    }

    public void supprimerPlat(int id) {
        platPrincipalDAO.delete(id);
    }

}
