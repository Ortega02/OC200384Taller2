package sv.edu.udb.datos;

import sv.edu.udb.beans.TransaccionesBeans;
import sv.edu.udb.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaccionesDatos {
    public List<TransaccionesBeans> getTransaccionesByCuenta(String numeroCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TransaccionesBeans> transacciones = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            String sql = "SELECT tipo, cantidad, numero_cuenta, fecha FROM transacciones WHERE numero_cuenta = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, numeroCuenta);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                double cantidad = rs.getDouble("cantidad");
                String fecha = rs.getString("fecha");

                transacciones.add(new TransaccionesBeans(tipo, cantidad, numeroCuenta, fecha));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return transacciones;
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
