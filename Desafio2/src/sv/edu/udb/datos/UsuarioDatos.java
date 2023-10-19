package sv.edu.udb.datos;

import sv.edu.udb.beans.UsuarioBeans;
import sv.edu.udb.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UsuarioDatos {
    private final String SQL_INSERT
            = "INSERT INTO clientes (nombre, pin, dui) VALUES (?, ?, ?)";

    public int insert(UsuarioBeans usuarioBeans) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuarioBeans.getNombre());
            stmt.setString(2, usuarioBeans.getPin());
            stmt.setString(3, usuarioBeans.getDui());

            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public boolean existeDUI(String dui) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            String SQL_SELECT = "SELECT COUNT(*) FROM clientes WHERE dui = ?";
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setString(1, dui);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return false;
    }

}
