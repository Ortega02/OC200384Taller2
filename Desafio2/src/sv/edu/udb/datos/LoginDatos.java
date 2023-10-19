package sv.edu.udb.datos;

import sv.edu.udb.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDatos {
    private final String SQL_SELECT = "SELECT * FROM clientes WHERE dui = ? AND pin = ?";

    public boolean autenticar(String dui, String pin) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setString(1, dui);
            stmt.setString(2, pin);
            rs = stmt.executeQuery();
            return rs.next(); // Retorna true si existe una coincidencia
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
}
