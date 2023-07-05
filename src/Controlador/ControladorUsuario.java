
package Controlador;

import Modelo.BD;
import Modelo.Persona;
import Modelo.Usuario;
import Vista.VistaUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Vidaurre Julio Fernando
 */
public class ControladorUsuario implements ActionListener,FocusListener{
    
    
    public  VistaUsuario vistaUsuario = new VistaUsuario();

    public ControladorUsuario() {
        
        vistaUsuario.btnCancelar.addActionListener(this);
        vistaUsuario.btnEliminar.addActionListener(this);
        vistaUsuario.btnGuardar.addActionListener(this);
        vistaUsuario.btnModificar.addActionListener(this);
        vistaUsuario.txtDni.addFocusListener(this);
       
    
    }
    
    
    
    public  void mostrar(int idUsuario,String accion){
    
        vistaUsuario.setTitle("Usuario");
        vistaUsuario.setLocationRelativeTo(null);
        vistaUsuario.cargarBotones(accion);
        
        if (idUsuario >0) {
            
           Usuario usuario = buscarUsuario(idUsuario);                                                                                      
           vistaUsuario.cargarDatos(usuario);
        }
        
        vistaUsuario.setVisible(true);
          
           
    }
    public void cerrar(){
        ControladorPrincipal.habilitar(); 
        ControladorPrincipal.actualizarListadoUsuario();
        vistaUsuario.dispose();
    }
    public void altaUsuario(int idPersona){
    
       String password = new String(vistaUsuario.getTxtPassword().getPassword());
      
   
      Usuario usuario = new Usuario(0,vistaUsuario.getTxtUsuario().getText(),password,
            String.valueOf(vistaUsuario.getcBoxEstado().getSelectedItem()), idPersona,
            vistaUsuario.getTxtDni().getText(), vistaUsuario.getTxtApellido().getText(),vistaUsuario.getTxtNombre().getText(),
            vistaUsuario.getTxtDireccion().getText(),vistaUsuario.getTxtTelefono().getText(),vistaUsuario.getTxtEmail().getText());
    
    
         BD bd = new BD();
        if (bd.altaUsuario(usuario)){
            vistaUsuario.limpiar();
            JOptionPane.showMessageDialog(null, "Usuario Registrado Exitosamente!");
        
        }else{
           
             JOptionPane.showMessageDialog(null, "Error al registra el Usuario, intente nuevamente!");
        
        }
    }
    
    public ArrayList listarUsuario(int idUsuario, String dni, String nombreUsuario){
    
        ArrayList lista;
        BD bd = new BD();
        lista=bd.listarUsuario(idUsuario,dni,nombreUsuario);
        return lista;
    
    }
    
 
    public Usuario buscarUsuario(int idUsuario){
        
        Usuario usuario;
        BD bd = new BD();
        usuario = bd.buscarUsuario(idUsuario);
        
        return usuario;
    
    
    }

    public  void modificarUsuario() {
             
            String password = new String(vistaUsuario.getTxtPassword().getPassword());
            
            Usuario usuario = new Usuario(Integer.parseInt(vistaUsuario.getTxtIdUsuario().getText()),vistaUsuario.getTxtUsuario().getText(),password,
            String.valueOf(vistaUsuario.getcBoxEstado().getSelectedItem()),Integer.parseInt(vistaUsuario.getTxtIdPersona().getText()),
            vistaUsuario.getTxtDni().getText(), vistaUsuario.getTxtApellido().getText(),vistaUsuario.getTxtNombre().getText(),
            vistaUsuario.getTxtDireccion().getText(),vistaUsuario.getTxtTelefono().getText(),vistaUsuario.getTxtEmail().getText());
    
    
         BD bd = new BD();
         
          if (bd.modificarUsuario(usuario)){
          
            JOptionPane.showMessageDialog(null, "Usuario Modificado Exitosamente!");
        
        }else{
           
             JOptionPane.showMessageDialog(null, "Error al modificar el Usuario, intente nuevamente!");
        
        }
         
    }

    public void eliminarUsuario() {
       
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(Integer.parseInt(vistaUsuario.getTxtIdCliente().getText()));
       
        BD bd = new BD();
        if (bd.eliminarUsuario(usuario)){
         JOptionPane.showMessageDialog(null, "Usuario Eliminado Exitosamente!");
          vistaUsuario.limpiar();
        }else{
        
             JOptionPane.showMessageDialog(null, "Error al Eliminar el Usuario, intente nuevamente!");
        
        } 
                
        
    }

    public  void verificarDni(String dni) {
  
        System.out.println(dni);
        Persona persona;
        BD bd = new BD();
        
        persona = bd.verificarPersona(dni);
        if (persona.getIdPersona() > 0){
        JOptionPane.showMessageDialog(null, "  La persona ya existe, cargamos los datos");
        vistaUsuario.cargarDatosPersona(persona);
        }
        
        
        }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource()== vistaUsuario.btnCancelar){
        
            vistaUsuario.limpiar();
            cerrar();
        
        }
        
        if(e.getSource()== vistaUsuario.btnGuardar){
    
            String idPersona=vistaUsuario.getTxtIdPersona().getText();
         
            if( idPersona.equals("")){
            
                altaUsuario(0);
            
            }else{
            
                altaUsuario(Integer.parseInt(idPersona));
        
                }
        }
        
        if(e.getSource()== vistaUsuario.btnEliminar){
        
            eliminarUsuario();
        
        }
        
        if(e.getSource()== vistaUsuario.btnModificar){
        
            modificarUsuario();
        
        
        }
    
    }
    
    


    @Override
    public void focusGained(FocusEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       System.out.println("no hacemos nada al ganar el focus");
    }

    @Override
    public void focusLost(FocusEvent e) {
       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource()== vistaUsuario.txtDni){
        
            verificarDni(vistaUsuario.txtDni.getText());
        
        }
    }

   
}