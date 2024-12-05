package main;

import dao.DatabaseConnection;
import view.TransferView;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Conectar a la base de datos
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Conexión a la base de datos exitosa.");

            // Inicializar la vista de transferencia
            TransferView transferView = new TransferView();
            transferView.show();

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
