package com.negocio.punto.venta.login;

import com.negocio.punto.venta.tablas.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Raul
 */
public class Login {

    private static String usuario;
    private static String contrasena;

    private boolean limpiarTextoLogin(JTextField usr, JPasswordField con) {
        boolean work = false;
        String usuario = usr.getText().trim(), contrasena = con.getText().trim();
        if (!usuario.isEmpty() && !contrasena.isEmpty()) {
            if (!(usuario.length() > 25 && contrasena.length() > 30)) {
                Login.usuario = usuario;
                Login.contrasena = contrasena;
                work = true;
            }
        } else {
            work = false;
        }
        return work;
    }

//    public void login(JTextField jTextFieldUsuario,
//            JPasswordField jPasswordFieldContrasena, JFrame contexto) {
//        try {
//            if (limpiarTextoLogin(jTextFieldUsuario, jPasswordFieldContrasena)) {
//                Conexion.conectar();
//                System.out.println("Acceso!");
//                //contexto.dispose();
//            } else {
//                JOptionPane.showMessageDialog(null, "Complete los campos");
//            }
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Usuario y/o Contrasena incorrecto");
//            jPasswordFieldContrasena.setText("");
//        }
//    }

    public boolean Prueba(String usuario, String password) throws SQLException, ClassNotFoundException {
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
