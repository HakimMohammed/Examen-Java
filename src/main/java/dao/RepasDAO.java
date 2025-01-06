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



}
