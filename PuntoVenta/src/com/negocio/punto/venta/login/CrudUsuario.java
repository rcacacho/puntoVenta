package com.negocio.punto.venta.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author Raul
 */
public class CrudUsuario {

    public static int insertUsuario(String nombre, String apellido, String email, String usuario,
            String password, boolean admin) {
        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;
        Calendar calendar = Calendar.getInstance();
        java.sql.Date fechaActual = new java.sql.Date(calendar.getTime().getTime());

        String sql = "INSERT INTO usuarios(nombre,apellido,email,usuario,password,admin,activo,fechaCreacion) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            // set parameters for statement
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, email);
            pstmt.setString(4, usuario);
            pstmt.setString(5, password);
            pstmt.setBoolean(6, admin);
            pstmt.setBoolean(7, true);
            pstmt.setDate(8, fechaActual);

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                // get candidate id
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    candidateId = rs.getInt(1);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return candidateId;
    }

}
