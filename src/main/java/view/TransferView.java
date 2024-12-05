package view;

import dao.TransferDAO;
import dao.Transfer;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class TransferView {
    private TransferDAO transferDAO;
    private Scanner scanner;

    public TransferView() {
        try {
            transferDAO = new TransferDAO();
            scanner = new Scanner(System.in);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        System.out.println("Bienvenido a la plataforma de transferencias de dinero.");
        boolean running = true;
        while (running) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Crear transferencia");
            System.out.println("2. Ver transferencia");
            System.out.println("3. Actualizar transferencia");
            System.out.println("4. Borrar transferencia");
            System.out.println("5. Salir");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createTransfer();
                    break;
                case 2:
                    viewTransfer();
                    break;
                case 3:
                    updateTransfer();
                    break;
                case 4:
                    deleteTransfer();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void createTransfer() {
        System.out.println("Ingrese el monto:");
        BigDecimal amount = scanner.nextBigDecimal();
        System.out.println("Ingrese la moneda:");
        String currency = scanner.next();

        Transfer transfer = new Transfer(0, amount, currency);
        try {
            transferDAO.createTransfer(transfer);
            System.out.println("Transferencia creada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewTransfer() {
        System.out.println("Ingrese el ID de la transferencia:");
        int id = scanner.nextInt();

        try {
            Transfer transfer = transferDAO.readTransfer(id);
            if (transfer != null) {
                System.out.println("ID: " + transfer.getId());
                System.out.println("Monto: " + transfer.getAmount());
                System.out.println("Moneda: " + transfer.getCurrency());
            } else {
                System.out.println("Transferencia no encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTransfer() {
        System.out.println("Ingrese el ID de la transferencia:");
        int id = scanner.nextInt();
        System.out.println("Ingrese el nuevo monto:");
        BigDecimal amount = scanner.nextBigDecimal();
        System.out.println("Ingrese la nueva moneda:");
        String currency = scanner.next();

        Transfer transfer = new Transfer(id, amount, currency);
        try {
            transferDAO.updateTransfer(transfer);
            System.out.println("Transferencia actualizada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteTransfer() {
        System.out.println("Ingrese el ID de la transferencia:");
        int id = scanner.nextInt();

        try {
            transferDAO.deleteTransfer(id);
            System.out.println("Transferencia borrada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
