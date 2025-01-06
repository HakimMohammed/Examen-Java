package dao;

import controllers.PlatPrincipalController;
import database.SingletonConnexionDB;
import models.Ingredient;
import models.PlatPrincipal;
import models.Repas;
import models.Supplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepasDAO {
    private Connection connection;

    public RepasDAO() {
        connection = SingletonConnexionDB.getInstance().getConnection();
    }

    public void create(Repas repas) {
        String SQL = "INSERT INTO Repas (id, main_dish_id, total_price) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, repas.getId());
            preparedStatement.setInt(2, repas.getMainDish().getId());
            preparedStatement.setDouble(3, repas.getTotalPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Repas read(int id) {
        String SQL = "SELECT r.id AS repas_id, r.total_price, p.id AS plat_id, p.name AS plat_name, p.base_price AS plat_price " +
                "FROM Repas r " +
                "JOIN PlatPrincipal p ON r.main_dish_id = p.id " +
                "WHERE r.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                PlatPrincipal mainDish = new PlatPrincipal(
                        resultSet.getInt("plat_id"),
                        resultSet.getString("plat_name"),
                        resultSet.getDouble("plat_price")
                );

                // Récupérer les ingrédients liés
                List<Ingredient> ingredients = getIngredientsForRepas(id);

                // Récupérer les suppléments liés
                List<Supplement> supplements = getSupplementsForRepas(id);

                return new Repas(
                        resultSet.getInt("repas_id"),
                        mainDish,
                        ingredients,
                        supplements
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Repas> readAll() {
        String SQL = "SELECT r.id AS repas_id, r.total_price, p.id AS plat_id, p.name AS plat_name, p.base_price AS plat_price " +
                "FROM Repas r " +
                "JOIN PlatPrincipal p ON r.main_dish_id = p.id";

        List<Repas> repasList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PlatPrincipal mainDish = new PlatPrincipal(
                        resultSet.getInt("plat_id"),
                        resultSet.getString("plat_name"),
                        resultSet.getDouble("plat_price")
                );

                // Get ingredients for the current meal using JOIN
                String ingredientSQL = "SELECT i.id, i.name, i.price, ri.quantity " +
                        "FROM RepasIngredient ri " +
                        "JOIN Ingredient i ON ri.ingredient_id = i.id " +
                        "WHERE ri.meal_id = ?";
                PreparedStatement ingredientStmt = connection.prepareStatement(ingredientSQL);
                ingredientStmt.setInt(1, resultSet.getInt("repas_id"));
                ResultSet ingredientResult = ingredientStmt.executeQuery();
                List<Ingredient> ingredients = new ArrayList<>();
                while (ingredientResult.next()) {
                    Ingredient ingredient = new Ingredient(
                            ingredientResult.getInt("id"),
                            ingredientResult.getString("name"),
                            ingredientResult.getDouble("price")
                    );
                    ingredients.add(ingredient); // Optionally handle quantity here
                }

                // Get supplements for the current meal using JOIN
                String supplementSQL = "SELECT s.id, s.name, s.price " +
                        "FROM RepasSupplement rs " +
                        "JOIN Supplement s ON rs.supplement_id = s.id " +
                        "WHERE rs.meal_id = ?";
                PreparedStatement supplementStmt = connection.prepareStatement(supplementSQL);
                supplementStmt.setInt(1, resultSet.getInt("repas_id"));
                ResultSet supplementResult = supplementStmt.executeQuery();
                List<Supplement> supplements = new ArrayList<>();
                while (supplementResult.next()) {
                    Supplement supplement = new Supplement(
                            supplementResult.getInt("id"),
                            supplementResult.getString("name"),
                            supplementResult.getDouble("price")
                    );
                    supplements.add(supplement);
                }

                repasList.add(new Repas(
                        resultSet.getInt("repas_id"),
                        mainDish,
                        ingredients,
                        supplements
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repasList;
    }


    private List<Ingredient> getIngredientsForRepas(int repasId) {
        String SQL = "SELECT i.id, i.name, i.price " +
                "FROM RepasIngredient ri " +
                "JOIN Ingredient i ON ri.ingredient_id = i.id " +
                "WHERE ri.meal_id = ?";
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, repasId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ingredients.add(new Ingredient(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    // Méthode pour récupérer les suppléments liés à un repas
    private List<Supplement> getSupplementsForRepas(int repasId) {
        String SQL = "SELECT s.id, s.name, s.price " +
                "FROM RepasSupplement rs " +
                "JOIN Supplement s ON rs.supplement_id = s.id " +
                "WHERE rs.meal_id = ?";
        List<Supplement> supplements = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, repasId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                supplements.add(new Supplement(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplements;
    }

    // add an ingredient to a meal
    public void addIngredientToRepas(int repasId, int ingredientId) {
        String SQL = "INSERT INTO RepasIngredient (meal_id, ingredient_id, quantity) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, repasId);
            preparedStatement.setInt(2, ingredientId);
            preparedStatement.setInt(3, 1); // default quantity
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // add a supplement to a meal
    public void addSupplementToRepas(int repasId, int supplementId) {
        String SQL = "INSERT INTO RepasSupplement (meal_id, supplement_id) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, repasId);
            preparedStatement.setInt(2, supplementId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
