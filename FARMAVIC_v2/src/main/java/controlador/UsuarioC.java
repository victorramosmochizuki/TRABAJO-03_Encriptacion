package controlador;

import dao.UsuarioD;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.Usuario;

@Data
@Named(value = "usuarioC")
@SessionScoped
public class UsuarioC implements Serializable{

    Usuario usuario;
    UsuarioD dao;
    private int captcha = 0;
    private int intentos = 0;
    private boolean bloquear;

    public UsuarioC() {
        usuario = new Usuario();
        dao = new UsuarioD();

    }

    public void login() throws Exception {
        try {
            dao.login(usuario);
        } catch (Exception e) {
            System.out.println("Error en USUARIOC  login_C " + e.getMessage());
        }
    }

    public void loginNivel() throws Exception {
        try {
            dao.loginNivel(usuario);
        } catch (Exception e) {
            System.out.println("Error en USUARIOC loginNivel_C" + e.getMessage());
        }
    }
    
    
        public void acceso() throws Exception {
        try {
            this.login();
            if (dao.logueo == false) {
                intentos++;
                if (intentos == 1) {
                    setIntentos(1);
                    setCaptcha(0);
                    System.out.println("intentos igual " + intentos);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "1 INTENTO FALLIDO", "Usuario/Contraseña incorrectas"));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "LE QUEDAN 2 INTENTOS", ""));
                }
                if (intentos == 2) {
                    setIntentos(2);
                    setCaptcha(1);
//                    bloquear = true;
                    System.out.println("intentos igual " + intentos);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "2 INTENTO FALLIDO", "Usuario/Contraseña incorrectas"));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "LE QUEDA 1 INTENTO", ""));
                }
                if (intentos == 3) {
                    System.out.println("intentos igual " + intentos);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "3 INTENTO FALLIDO", "Usuario/Contraseña incorrectas"));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "BLOQUEO DE SEGURIDAD", ""));
                    setIntentos(3);
//                    bloquear = true;
                    if (bloquear) {
                        delayTiempo();
                    }
                    if (intentos == 3) {
                        setIntentos(0);
                        setCaptcha(0);
                        bloquear = true;
                    }
                }
            } else {
                this.loginNivel();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetoUsuario", usuario);
                if (dao.nivel == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡BIENVENIDO!", "Ingreso Exitoso"));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/FARMAVIC_v2/faces/vistas/proveedor.xhtml");
                }
                if (dao.nivel == 2) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡BIENVENIDO!", "Ingreso Exitoso"));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/FARMAVIC_v2/faces/vistas/Plantilla.xhtml");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en Acceso_C " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    private static void delayTiempo() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Error en delaySegundo_C " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
        // Obtener el objeto de la sesión activa
    public static Usuario obtenerObjetoSesion() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("objetoUsuario");
    }
    
    // Si la sesión no está iniciada no permitirá entrar a otra vista de la aplicación
    public void seguridadSession() throws IOException {
        if (obtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/FARMAVIC_ODAO");
        }
    }
    
    
    
    

}
