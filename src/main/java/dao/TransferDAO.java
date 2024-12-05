package dao;

import interfaces.ITransferDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferDAO implements ITransferDAO {
    private Connection connection;

    public TransferDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void createTransfer(Transfer transfer) throws SQLException {
        String query = "INSERT INTO transfers (amount, currency) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBigDecimal(1, transfer.getAmount());
            statement.setString(2, transfer.getCurrency());
            statement.executeUpdate();
        }
    }

    @Override
    public Transfer readTransfer(int id) throws SQLException {
        String query = "SELECT * FROM transfers WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Transfer(
                            resultSet.getInt("id"),
                            resultSet.getBigDecimal("amount"),
                            resultSet.getString("currency")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public void updateTransfer(Transfer transfer) throws SQLException {
        String query = "UPDATE transfers SET amount = ?, currency = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBigDecimal(1, transfer.getAmount());
            statement.setString(2, transfer.getCurrency());
            statement.setInt(3, transfer.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteTransfer(int id) throws SQLException {
        String query = "DELETE FROM transfers WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Transfer> getAllTransfers() throws SQLException {
        List<Transfer> transfers = new ArrayList<>();
        String query = "SELECT * FROM transfers";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                transfers.add(new Transfer(
                        resultSet.getInt("id"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getString("currency")
                ));
            }
        }
        return transfers;
    }
}
