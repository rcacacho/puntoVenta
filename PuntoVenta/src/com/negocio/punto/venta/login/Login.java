package com.negocio.punto.venta.login;

import com.negocio.punto.venta.tablas.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Raul
 */
public class Login {

    public boolean Consulta(String usuario, String password) throws SQLException, ClassNotFoundException {
        try {
            Conexion.conectar();
            Conexion conexion = new Conexion();

            ResultSet resultado = conexion.consultar("SELECT idUsuario, admin, usuario FROM usuario WHERE usuario = '" + usuario + "' and password = '" + password + "'");
            resultado.last();
            if (resultado.getRow() > 0) {
                Usuario usuarioactual = Usuario.getInstance();
                usuarioactual.setIdUsuario(resultado.getInt("idUsuario"));
                usuarioactual.setAdmin(resultado.getBoolean("admin"));
                usuarioactual.setUsuario(resultado.getString("usuario"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
