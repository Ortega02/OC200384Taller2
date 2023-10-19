package sv.edu.udb.datos;

import sv.edu.udb.beans.VerCuentasBeans;
import sv.edu.udb.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VerCuentasDatos {
    public List<VerCuentasBeans> getCuentasYTransacciones(String dui) {
        List<VerCuentasBeans> cuentas = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();

            String sql = "SELECT c.numero_cuenta, cl.nombre, c.saldo, COUNT(t.id) AS transacciones_realizadas " +
                    "FROM cuentas c " +
                    "INNER JOIN clientes cl ON c.cliente_id = cl.id " +
                    "LEFT JOIN transacciones t ON c.cliente_id = t.numero_cuenta " +
                    "WHERE cl.dui = ? " +
                    "GROUP BY c.numero_cuenta, cl.nombre, c.saldo";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dui);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String numeroCuenta = rs.getString("numero_cuenta");
                String nombreTitular = rs.getString("nombre");
                double saldo = rs.getDouble("saldo");
                int transaccionesRealizadas = rs.getInt("transacciones_realizadas");

                VerCuentasBeans cuenta = new VerCuentasBeans(numeroCuenta, nombreTitular, saldo, transaccionesRealizadas);
                cuentas.add(cuenta);
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