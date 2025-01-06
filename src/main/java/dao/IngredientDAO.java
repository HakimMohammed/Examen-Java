package dao;

import database.SingletonConnexionDB;
import models.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {
    private Connection connection;

    public IngredientDAO() {
        connection = SingletonConnexionDB.getInstance().getConnection();
    }

    public void create(Ingredient ingredient) {
        String SQL = "INSERT INTO Ingredient (name, price) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, ingredient.getNom());
            preparedStatement.setDouble(2, ingredient.getPrix());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ingredient read(int id) {
        String SQL = "SELECT * FROM Ingredient WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Ingredient(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Ingredient> readAll() {
        List<Ingredient> ingredients = new ArrayList<>();
        String SQL = "SELECT * FROM Ingredient";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ingredients.add(new Ingredient(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredients;
    }

    public void update(Ingredient ingredient) {
        String SQL = "UPDATE Ingredient SET name = ?, price = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, ingredient.getNom());
            preparedStatement.setDouble(2, ingredient.getPrix());
            preparedStatement.setInt(3, ingredient.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String SQL = "DELETE FROM Ingredient WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
