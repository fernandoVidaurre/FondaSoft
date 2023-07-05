
package Controlador;

import Modelo.BD;
import Modelo.Mozo;
import Modelo.Persona;
import Vista.VistaMozo;
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
public class ControladorMozo implements ActionListener,FocusListener {
    
    
    public  VistaMozo vistaMozo = new VistaMozo();

    public ControladorMozo() {
        
        vistaMozo.btnCancelar.addActionListener(this);
        vistaMozo.btnEliminar.addActionListener(this);
        vistaMozo.btnGuardar.addActionListener(this);
        vistaMozo.btnModificar.addActionListener(this);
        vistaMozo.txtDni.addFocusListener(this);
    }
    
    
    
    
    public void mostrar(int idMozo,String accion){
    
        vistaMozo.setTitle("Mozos/as");
        vistaMozo.setLocationRelativeTo(null);
        vistaMozo.cargarBotones(accion);
        
        if (idMozo >0) {
            
           Mozo mozo = buscarMozo(idMozo);                                                                                      
           vistaMozo.cargarDatos(mozo);
        }
        
        vistaMozo.setVisible(true);
          
           
    }
    public  void cerrar(){
        ControladorPrincipal.habilitar(); 
        ControladorPrincipal.actualizarListadoMozo();
        vistaMozo.dispose();    
        
    }
    public void altaMozo(int idPersona){
    
   
        
    Mozo mozo = new Mozo(0,vistaMozo.getTxtCodigo().getText(),String.valueOf(vistaMozo.getcBoxTipo().getSelectedItem()),
            String.valueOf(vistaMozo.getcBoxEstado().getSelectedItem()), idPersona,
            vistaMozo.getTxtDni().getText(), vistaMozo.getTxtApellido().getText(),vistaMozo.getTxtNombre().getText(),
            vistaMozo.getTxtDireccion().getText(),vistaMozo.getTxtTelefono().getText(),vistaMozo.getTxtEmail().getText());
    
    
         BD bd = new BD();
        if (bd.altaMozo(mozo)){
            vistaMozo.limpiar();
            JOptionPane.showMessageDialog(null, "Mozo Registrado Exitosamente!");
        
        }else{
           
             JOptionPane.showMessageDialog(null, "Error al registra el Mozo, intente nuevamente!");
        
        }
    }
    
    public  ArrayList listarMozo(int id, String dni, String codigo){
    
        ArrayList lista;
        BD bd = new BD();
        lista=bd.listarMozo(id,dni,codigo);
        return lista;
    
    }
    
 
    public  Mozo buscarMozo(int idMozo){
        
        Mozo mozo;
        BD bd = new BD();
        mozo = bd.buscarMozo(idMozo);
        
        return mozo;
    
    
    }

    public void modificarMozo() {
              
            Mozo mozo = new Mozo(Integer.parseInt(vistaMozo.getTxtIdMozo().getText()),vistaMozo.getTxtCodigo().getText(),String.valueOf(vistaMozo.getcBoxTipo().getSelectedItem()),
            String.valueOf(vistaMozo.getcBoxEstado().getSelectedItem()), Integer.parseInt(vistaMozo.getTxtIdPersona().getText()),
            vistaMozo.getTxtDni().getText(), vistaMozo.getTxtApellido().getText(),vistaMozo.getTxtNombre().getText(),
            vistaMozo.getTxtDireccion().getText(),vistaMozo.getTxtTelefono().getText(),vistaMozo.getTxtEmail().getText());  
    
         
            BD bd = new BD();
         
            if (bd.modificarMozo(mozo)){
          
            JOptionPane.showMessageDialog(null, "Mozo Modificado Exitosamente!");
        
            }else{
           
             JOptionPane.showMessageDialog(null, "Error al modificar el Mozo, intente nuevamente!");
        
        }
         
    }

    public  void eliminarMozo() {
       
        Mozo mozo = new Mozo();
        mozo.setIdMozo(Integer.parseInt(vistaMozo.getTxtIdMozo().getText()));
       
        BD bd = new BD();
        if (bd.eliminarMozo(mozo)){
         JOptionPane.showMessageDialog(null, "Mozo Eliminado Exitosamente!");
          vistaMozo.limpiar();
        }else{
        
             JOptionPane.showMessageDialog(null, "Error al Eliminar el Mozo, intente nuevamente!");
        
        } 
                
        
    }

    public  void verificarDni(String dni) {
  
        System.out.println(dni);
        Persona persona;
        BD bd = new BD();
        
        persona = bd.verificarPersona(dni);
        if (persona.getIdPersona() > 0){
        JOptionPane.showMessageDialog(null, "  La persona ya existe, cargamos los datos");
        vistaMozo.cargarDatosPersona(persona);
        }
        
        
        }

    @Override
    public void actionPerformed(ActionEvent e) {
       
              if(e.getSource()== vistaMozo.btnCancelar){
        
                    vistaMozo.limpiar();
                    cerrar();
        
        }
        
        if(e.getSource()== vistaMozo.btnGuardar){
    
            String idPersona=vistaMozo.getTxtIdPersona().getText();
         
            if( idPersona.equals("")){
            
                altaMozo(0);
            
            }else{
            
                altaMozo(Integer.parseInt(idPersona));
        
                }
        }
        
        if(e.getSource()== vistaMozo.btnEliminar){
        
            eliminarMozo();
        
        }
        
        if(e.getSource()== vistaMozo.btnModificar){
        
            modificarMozo();
        
        
        }
    
        
        
    }

    @Override
    public void focusGained(FocusEvent e) {
                
         System.out.println("no hacemos nada al ganar el focus");
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource()== vistaMozo.txtDni){
        
            verificarDni(vistaMozo.txtDni.getText());
        
        }
    }
    
}
