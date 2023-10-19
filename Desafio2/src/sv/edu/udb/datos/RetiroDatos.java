package sv.edu.udb.datos;

import sv.edu.udb.beans.RetiroBeans;
import sv.edu.udb.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetiroDatos {
    private final String SQL_UPDATE_SALDO = "UPDATE cuentas SET saldo = saldo - ? WHERE numero_cuenta = ?";
    private final String SQL_INSERT_TRANSACCION = "INSERT INTO transacciones (tipo, cantidad, numero_cuenta) VALUES (?, ?, ?)";

    public boolean realizarRetiro(RetiroBeans retiro) {
        Connection conn = null;
        PreparedStatement updateSaldoStmt = null;
        PreparedStatement insertTransaccionStmt = null;

        try {
            conn = Conexion.getConnection();
            conn.setAutoCommit(false); // Iniciar transacción

            updateSaldoStmt = conn.prepareStatement(SQL_UPDATE_SALDO);
            updateSaldoStmt.setDouble(1, retiro.getCantidad());
            updateSaldoStmt.setLong(2, retiro.getNumeroCuenta());
            int saldoActualizado = updateSaldoStmt.executeUpdate();

            insertTransaccionStmt = conn.prepareStatement(SQL_INSERT_TRANSACCION);
            insertTransaccionStmt.setString(1, retiro.getTipoTransaccion());
            insertTransaccionStmt.setDouble(2, retiro.getCantidad());
            insertTransaccionStmt.setLong(3, retiro.getNumeroCuenta());
            int transaccionRegistrada = insertTransaccionStmt.executeUpdate();

            if (saldoActualizado > 0 && transaccionRegistrada > 0) {
                conn.commit(); // Confirmar la transacción
                return true; // Retiro exitoso
            } else {
                conn.rollback(); // Revertir la transacción en caso de error
                return false; // Error en el retiro
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // Revertir la transacción en caso de excepción
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false; // Error en el retiro
        } finally {
            Conexion.close(updateSaldoStmt);
            Conexion.close(insertTransaccionStmt);
            Conexion.close(conn);
        }
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

    public double obtenerSaldoCuenta(String numeroCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double saldo = 0.0;

        try {
            conn = Conexion.getConnection();
            String sql = "SELECT saldo FROM cuentas WHERE numero_cuenta = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, numeroCuenta);
            rs = stmt.executeQuery();

            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return saldo;
    }
}
