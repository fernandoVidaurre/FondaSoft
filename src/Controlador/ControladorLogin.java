
package Controlador;

import Modelo.BD;
import Modelo.Usuario;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Vidaurre Julio Fernando
 */
public class ControladorLogin implements ActionListener{
    
    public  VistaLogin vistaLogin = new VistaLogin();

    public ControladorLogin() {
        vistaLogin.setTitle("Inicio de Sesion");
        vistaLogin.btnIngresar.addActionListener(this);
        vistaLogin.btnSalir.addActionListener(this);
    
    }
    
    
    
    public  void mostrar(){
     
        
        vistaLogin.setVisible(true);
        vistaLogin.setLocationRelativeTo(null);
        
    }
    public  void ocultar(){
        vistaLogin.dispose();
    }
    public void salir(){
        
        
        vistaLogin.dispose();
        System.exit(0);
        
    }
    public Usuario loginUsuario(String user, String pass){
        
        Usuario usuario=new Usuario();
        usuario.setUsuario(user);
        usuario.setPassword(pass);
       
        BD bd = new BD();
        
        Usuario usr= bd.loginUsuario(usuario);
        return usr;
    
    }
    public void acceder(){
       
        String usuario,password;
        usuario =vistaLogin.getTxtUsuario().getText();
        password = new String(vistaLogin.getTxtPassword().getPassword());
        
        Usuario user =loginUsuario(usuario,password);
       
        if (user.getIdUsuario() > 0) {
            ocultar();
            ControladorPrincipal.mostrar(user);
                      
        }else{
        
            JOptionPane.showMessageDialog(null, "Datos Incorrectos!");
            limpiar();
           
        
        }
        System.out.println("Usuario: "+usuario);
        System.out.println("Password: "+password);
        
    
    
 }
    public void limpiar(){
        
        vistaLogin.setTxtUsuario("");
        vistaLogin.setTxtPassword("");
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
    
       
        
        if(e.getSource()== vistaLogin.btnIngresar){
        
            acceder();
        
        }
        if(e.getSource()== vistaLogin.btnSalir){
        
            salir();
        }
        
    
    
    }
    
    
}