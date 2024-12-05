package interfaces;

import dao.Transfer;
import java.sql.SQLException;
import java.util.List;

public interface ITransferDAO {
    default void createTransfer(Transfer transfer) throws SQLException {

    }

    Transfer readTransfer(int id) throws SQLException;
    void updateTransfer(Transfer transfer) throws SQLException;
    void deleteTransfer(int id) throws SQLException;
    List<Transfer> getAllTransfers() throws SQLException;
}
