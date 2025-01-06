package dao;

import database.SingletonConnexionDB;
import models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private Connection connection;

    public ClientDAO() {
        connection = SingletonConnexionDB.getInstance().getConnection();
    }

    public void create(Client client) {
        String SQL = "INSERT INTO Client (name, email) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public Client read(int id) {
        String SQL = "SELECT * FROM Client WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }

    public List<Client> readAll() {
        List<Client> clients = new ArrayList<>();
        String SQL = "SELECT * FROM Client";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return clients;
    }

    public void update(Client client) {
        String SQL = "UPDATE Client SET nom = ?, email = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setInt(3, client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void delete(int id) {
        String SQL = "DELETE FROM Client WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
