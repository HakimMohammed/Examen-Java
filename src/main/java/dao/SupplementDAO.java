package dao;

import database.SingletonConnexionDB;
import models.Supplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplementDAO {

    private Connection connection;

    public SupplementDAO() {
        connection = SingletonConnexionDB.getInstance().getConnection();
    }

    public void create(Supplement supplement) {
        String SQL = "INSERT INTO Supplement (name, price) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, supplement.getNom());
            preparedStatement.setDouble(2, supplement.getPrix());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Supplement read(int id) {
        String SQL = "SELECT * FROM Supplement WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Supplement(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Supplement> readAll() {
        List<Supplement> supplements = new ArrayList<>();
        String SQL = "SELECT * FROM Supplement";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                supplements.add(new Supplement(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplements;
    }

    public void update(Supplement supplement) {
        String SQL = "UPDATE Supplement SET name = ?, price = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, supplement.getNom());
            preparedStatement.setDouble(2, supplement.getPrix());
            preparedStatement.setInt(3, supplement.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String SQL = "DELETE FROM Supplement WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
