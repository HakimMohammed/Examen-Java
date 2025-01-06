package dao;

import controllers.ClientController;
import database.SingletonConnexionDB;
import models.Client;
import models.Commande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeDAO {
    private Connection connection;

    public CommandeDAO() {
        connection = SingletonConnexionDB.getInstance().getConnection();
    }

    public void create(Commande commande) {
        String SQL = "INSERT INTO Commande (id, client_id, total_price) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, commande.getId());
            preparedStatement.setInt(2, commande.getClient().getId());
            preparedStatement.setDouble(3, commande.getTotalPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Commande read(int id) {
        String SQL = "SELECT c.id AS commande_id, c.total_price, cl.id AS client_id, cl.name AS client_name, cl.email AS client_email " +
                "FROM Commande c " +
                "JOIN Client cl ON c.client_id = cl.id " +
                "WHERE c.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("client_id"),
                        resultSet.getString("client_name"),
                        resultSet.getString("client_email")
                );
                return new Commande(
                        resultSet.getInt("commande_id"),
                        client,
                        resultSet.getDouble("total_price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

