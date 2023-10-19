package sv.edu.udb.datos;

import sv.edu.udb.beans.VerCuentaBeans;
import sv.edu.udb.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VerCuentaDatos {
    public VerCuentaBeans obtenerInformacionCuenta(String numeroCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        VerCuentaBeans cuentaInfo = null;

        try {
            conn = Conexion.getConnection();

            // Obtener el saldo de la cuenta
            String sqlSaldo = "SELECT saldo, cliente_id FROM cuentas WHERE numero_cuenta = ?";
            stmt = conn.prepareStatement(sqlSaldo);
            stmt.setString(1, numeroCuenta);
            rs = stmt.executeQuery();

            if (rs.next()) {
                double saldo = rs.getDouble("saldo");
                int clienteId = rs.getInt("cliente_id");

                // Ahora, obtenemos el nombre del titular
                String nombreTitular = obtenerNombreTitular(clienteId, conn);

                // Agregar lógica para obtener la cantidad de transacciones realizadas
                int transaccionesRealizadas = obtenerTransaccionesRealizadas(numeroCuenta, conn);

                cuentaInfo = new VerCuentaBeans(numeroCuenta, nombreTitular, saldo, transaccionesRealizadas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cuentaInfo;
    }

    // Agregar lógica para obtener el nombre del titular
    private String obtenerNombreTitular(int clienteId, Connection conn) throws SQLException {
        String nombreTitular = "";

        // Realiza una consulta SQL para obtener el nombre del titular a partir del cliente_id
        String sqlNombreTitular = "SELECT nombre FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlNombreTitular)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nombreTitular = rs.getString("nombre");
            }
        }

        return nombreTitular;
    }


    // Agregar lógica para obtener la cantidad de transacciones realizadas
    private int obtenerTransaccionesRealizadas(String numeroCuenta, Connection conn) throws SQLException {
        int transaccionesRealizadas = 0;  // Implementa la lógica para contar las transacciones
        // Realiza una consulta SQL para contar las transacciones
        String sql = "SELECT COUNT(*) AS total FROM transacciones WHERE numero_cuenta = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroCuenta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                transaccionesRealizadas = rs.getInt("total");
            }
        }
        return transaccionesRealizadas;
    }
    public List<String> getCuentasByDUI(String dui) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> cuentas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            String sql = "SELECT numero_cuenta FROM cuentas WHERE cliente_id = (SELECT id FROM clientes WHERE dui = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dui);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cuentas.add(rs.getString("numero_cuenta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return cuentas;
    }
}