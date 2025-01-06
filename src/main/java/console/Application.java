package console;

import controllers.*;
import models.*;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ClientController clientController = new ClientController();
        PlatPrincipalController platController = new PlatPrincipalController();
        IngredientController ingredientController = new IngredientController();
        SupplementController supplementController = new SupplementController();
        RepasController repasController = new RepasController();
        CommandeController commandeController = new CommandeController();

        // Insertion des données
        /*Client client = new Client( "Ali baba", "ali.baba@example.com");
        clientController.creerClient(client);

        PlatPrincipal plat1 = new PlatPrincipal( "Tajine de viande & Pruneaux", 60);
        PlatPrincipal plat2 = new PlatPrincipal( "Tajine de poulet & légumes", 50);
        platController.creerPlat(plat1);
        platController.creerPlat(plat2);

        Ingredient ingr1 = new Ingredient("Viande", 20);
        Ingredient ingr2 = new Ingredient( "Pruneaux", 10);
        Ingredient ingr3 = new Ingredient( "Poisson", 25);
        Ingredient ingr4 = new Ingredient( "Carotte", 5);
        ingredientController.creerIngredient(ingr1);
        ingredientController.creerIngredient(ingr2);
        ingredientController.creerIngredient(ingr3);
        ingredientController.creerIngredient(ingr4);

        Supplement supp1 = new Supplement("Frites", 11);
        Supplement supp2 = new Supplement( "Boisson", 12);
        Supplement supp3 = new Supplement( "Jus d'orange", 13);
        Supplement supp4 = new Supplement( "Salade marocaine", 14);
        supplementController.creerSupplement(supp1);
        supplementController.creerSupplement(supp2);
        supplementController.creerSupplement(supp3);
        supplementController.creerSupplement(supp4);*/

        // Récupération des données
        Client client = clientController.afficherClient(1);
        PlatPrincipal plat1 = platController.afficherPlat(1);
        PlatPrincipal plat2 = platController.afficherPlat(2);
        Ingredient ingr1 = ingredientController.afficherIngredient(1);
        Ingredient ingr2 = ingredientController.afficherIngredient(2);
        Ingredient ingr3 = ingredientController.afficherIngredient(3);
        Ingredient ingr4 = ingredientController.afficherIngredient(4);
        Supplement supp1 = supplementController.afficherSupplement(1);
        Supplement supp2 = supplementController.afficherSupplement(2);
        Supplement supp3 = supplementController.afficherSupplement(3);
        Supplement supp4 = supplementController.afficherSupplement(4);

        // Création des repas
        Repas repas1 = new Repas( plat1, List.of(ingr1, ingr2), List.of(supp1, supp2));
        Repas repas2 = new Repas( plat2, List.of(ingr3, ingr4), List.of(supp3, supp4));
        repasController.creerRepas(repas1);
        repasController.ajouterIngredient(1, 1);
        repasController.ajouterIngredient(1, 2);
        repasController.ajouterSupplement(1, 1);
        repasController.ajouterSupplement(1, 2);

        repasController.creerRepas(repas2);
        repasController.ajouterIngredient(2, 3);
        repasController.ajouterIngredient(2, 4);
        repasController.ajouterSupplement(2, 3);
        repasController.ajouterSupplement(2, 4);

        System.out.println("Repas 1: " + repas1);

        // Création de la commande
        Commande commande = new Commande( client, repas1.getTotalPrice() + repas2.getTotalPrice());
        commandeController.creerCommande(commande);

        // Récupération et affichage des données
        Commande retrievedCommande = commandeController.afficherCommande(1);

        System.out.println("Bienvenue " + retrievedCommande.getClient().getNom());
        System.out.println("---------TICKET---------");
        System.out.println("Nom: " + retrievedCommande.getClient().getNom());
        System.out.println("Nombre de repas: 2");

        Repas retrievedRepas1 = repasController.afficherRepas(1);
        System.out.println("Repas N°1: " + retrievedRepas1.getMainDish().getNom());
        System.out.println("Ingrédients:");
        for (Ingredient ingredient : retrievedRepas1.getIngredients()) {
            System.out.println("- " + ingredient.getNom() + ": " + ingredient.getPrix());
        }
        System.out.println("Suppléments:");
        for (Supplement supplement : retrievedRepas1.getSupplements()) {
            System.out.println("- " + supplement.getNom() + ": " + supplement.getPrix());
        }

        Repas retrievedRepas2 = repasController.afficherRepas(2);
        System.out.println("Repas N°2: " + retrievedRepas2.getMainDish().getNom());
        System.out.println("Ingrédients:");
        for (Ingredient ingredient : retrievedRepas2.getIngredients()) {
            System.out.println("- " + ingredient.getNom() + ": " + ingredient.getPrix());
        }
        System.out.println("Suppléments:");
        for (Supplement supplement : retrievedRepas2.getSupplements()) {
            System.out.println("- " + supplement.getNom() + ": " + supplement.getPrix());
        }

        System.out.println("*****");
        System.out.println("Total: " + retrievedCommande.getTotalPrice());
    }
}

