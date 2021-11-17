
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Usuario;


public class UsuarioD extends Conexion {
    
    public static Boolean logueo = false;
    public static int nivel = 0;

    public Usuario login(Usuario usuario) throws Exception {
        Usuario user = new Usuario();
        String sql = "SELECT NOMUSU, PWUSU, NIVUSU FROM USUARIO WHERE NOMUSU=? AND PWUSU=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, usuario.getNOMUSU());
            ps.setString(2, usuario.getPWUSU());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setNOMUSU(rs.getString("NOMUSU"));
                user.setPWUSU(rs.getString("PWUSU"));
                user.setNIVUSU(rs.getInt("NIVUSU"));
                logueo = true;
            } else {
                logueo = false;
            }
            ps.close();
            rs.close();
            return user;
        } catch (Exception e) {
            System.out.println("Errorr en USUARIOD_LOGIN: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public int loginNivel(Usuario usuario) throws Exception {
        String sql = "SELECT NOMUSU, PWUSU, NIVUSU FROM USUARIO WHERE NOMUSU=? AND PWUSU=? ";
        try {
            PreparedStatement ps = (PreparedStatement) this.conectar().prepareStatement(sql);
            ps.setString(1, usuario.getNOMUSU());
            ps.setString(2, usuario.getPWUSU());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nivel = rs.getInt("NIVUSU");
                logueo = true;
            } else {
                logueo = false;
            }
            ps.close();
            rs.close();
            return nivel;
        } catch (Exception e) {
            System.out.println("Errorr en USUARIOD_LOGINNIVEL: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    
    
    
}
