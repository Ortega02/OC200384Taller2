package sv.edu.udb.datos;

import sv.edu.udb.beans.CuentaBeans;
import sv.edu.udb.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaDatos {
    private final String SQL_INSERT = "INSERT INTO cuentas (saldo, cliente_id) VALUES (?, ?)";

    public int insert(CuentaBeans cuentaBeans) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();

            // Obtén el DUI del objeto cuentaBeans
            String dui = cuentaBeans.getDui();

            // Obtén el ID del cliente a partir del DUI
            int clienteId = obtenerIdClientePorDUI(dui, conn);

            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setDouble(1, cuentaBeans.getSaldo()); // Índice 1 para el saldo
            stmt.setInt(2, clienteId); // Índice 2 para el cliente_id

            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    private int obtenerIdClientePorDUI(String dui, Connection conn) throws SQLException {
        int clienteId = -1; // Valor predeterminado si no se encuentra un cliente

        String sql = "SELECT id FROM clientes WHERE dui = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dui);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                clienteId = rs.getInt("id");
            }
        }

        return clienteId;
    }
}
