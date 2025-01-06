package dao;

import database.SingletonConnexionDB;
import models.Client;
import models.PlatPrincipal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatPrincipalDAO   {

    private Connection connection;

    public PlatPrincipalDAO() {
        connection = SingletonConnexionDB.getInstance().getConnection();
    }
    /*
    id
name
base_price

     */

    public void create(PlatPrincipal plat) {
        String SQL = "INSERT INTO PlatPrincipal (name, base_price) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, plat.getNom());
            preparedStatement.setDouble(2, plat.getPrixBase());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public PlatPrincipal read(int id) {
        String SQL = "SELECT * FROM PlatPrincipal WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new PlatPrincipal(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("base_price")
                );
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }

    public List<PlatPrincipal> readAll() {
        List<PlatPrincipal> platPrincipals = new ArrayList<>();
        String SQL = "SELECT * FROM PlatPrincipal";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PlatPrincipal platPrincipal = new PlatPrincipal(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("base_price")
                );
                platPrincipals.add(platPrincipal);
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return platPrincipals;

    }

    public void update(PlatPrincipal plat) {
        String SQL = "UPDATE PlatPrincipal SET name = ?, base_price = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, plat.getNom());
            preparedStatement.setDouble(2, plat.getPrixBase());
            preparedStatement.setInt(3, plat.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void delete(int id) {
        String SQL = "DELETE FROM PlatPrincipal WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
