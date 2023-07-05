/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Cliente;
import Modelo.Persona;
import Vista.VistaCliente;
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
public class ControladorCliente implements ActionListener,FocusListener {
    
    
    public  VistaCliente vistaCliente = new VistaCliente();

    public ControladorCliente() {
    
        vistaCliente.btnCancelar.addActionListener(this);
        vistaCliente.btnEliminar.addActionListener(this);
        vistaCliente.btnGuardar.addActionListener(this);
        vistaCliente.btnModificar.addActionListener(this);
        vistaCliente.txtDni.addFocusListener(this);
        
    }
    
    
    
    
    public  void mostrar(int idCliente,String accion){
    
        vistaCliente.setTitle("Clientes");
        vistaCliente.setLocationRelativeTo(null);
        vistaCliente.cargarBotones(accion);
        
        if (idCliente >0) {
            
           Cliente cliente = buscarCliente(idCliente);                                                                                      
           vistaCliente.cargarDatos(cliente);
        }
        
        vistaCliente.setVisible(true);
          
           
    }
    public  void cerrar(){
        ControladorPrincipal.habilitar(); 
        ControladorPrincipal.actualizarListadoCliente();
        vistaCliente.dispose();    
        
    }
    public  void altaCliente(int idPersona){
    
        
        
      Cliente cliente = new Cliente(0, String.valueOf(vistaCliente.getcBoxTipo().getSelectedItem()),
            String.valueOf(vistaCliente.getcBoxEstado().getSelectedItem()), idPersona,
            vistaCliente.getTxtDni().getText(), vistaCliente.getTxtApellido().getText(),vistaCliente.getTxtNombre().getText(),
            vistaCliente.getTxtDireccion().getText(),vistaCliente.getTxtTelefono().getText(),vistaCliente.getTxtEmail().getText());
    
    
         BD bd = new BD();
        if (bd.altaCliente(cliente)){
            vistaCliente.limpiar();
            JOptionPane.showMessageDialog(null, "Cliente Registrado Exitosamente!");
        
        }else{
           
             JOptionPane.showMessageDialog(null, "Error al registra el Cliente, intente nuevamente!");
        
        }
    }
    
    public  ArrayList listarCliente(int id, String dni){
    
        ArrayList lista;
        BD bd = new BD();
        lista=bd.listarCliente(id,dni);
        return lista;
    
    }
    
 
    public  Cliente buscarCliente(int idCliente){
        
        Cliente cliente;
        BD bd = new BD();
        cliente = bd.buscarCliente(idCliente);
        
        return cliente;
    
    
    }

    public  void modificarCliente() {
              
            Cliente cliente = new Cliente(Integer.parseInt(vistaCliente.getTxtIdCliente().getText()) , String.valueOf(vistaCliente.getcBoxTipo().getSelectedItem()),
            String.valueOf(vistaCliente.getcBoxEstado().getSelectedItem()), Integer.parseInt(vistaCliente.getTxtIdPersona().getText()),
            vistaCliente.getTxtDni().getText(), vistaCliente.getTxtApellido().getText(),vistaCliente.getTxtNombre().getText(),
            vistaCliente.getTxtDireccion().getText(),vistaCliente.getTxtTelefono().getText(),vistaCliente.getTxtEmail().getText());
    
    
         BD bd = new BD();
         
          if (bd.modificarCliente(cliente)){
          
            JOptionPane.showMessageDialog(null, "Cliente Modificado Exitosamente!");
        
        }else{
           
             JOptionPane.showMessageDialog(null, "Error al modificar el Cliente, intente nuevamente!");
        
        }
         
    }

    public void eliminarCliente() {
       
        Cliente cliente = new Cliente();
        cliente.setIdCliente(Integer.parseInt(vistaCliente.getTxtIdCliente().getText()));
       
        BD bd = new BD();
        if (bd.eliminarCliente(cliente)){
         JOptionPane.showMessageDialog(null, "Cliente Eliminado Exitosamente!");
          vistaCliente.limpiar();
        }else{
        
             JOptionPane.showMessageDialog(null, "Error al Eliminar el Cliente, intente nuevamente!");
        
        } 
                
        
    }

    public  void verificarDni(String dni) {
  
        System.out.println(dni);
        Persona persona;
        BD bd = new BD();
        
        persona = bd.verificarPersona(dni);
        if (persona.getIdPersona() > 0){
        JOptionPane.showMessageDialog(null, "  La persona ya existe, cargamos los datos");
        vistaCliente.cargarDatosPersona(persona);
        }
        
        
        }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        
        if(e.getSource()== vistaCliente.btnCancelar){
        
            vistaCliente.limpiar();
            cerrar();
        
        }
        
        if(e.getSource()== vistaCliente.btnGuardar){
    
            String idPersona=vistaCliente.getTxtIdPersona().getText();
         
            if( idPersona.equals("")){
            
                altaCliente(0);
            
            }else{
            
                altaCliente(Integer.parseInt(idPersona));
        
                }
        }
        
        if(e.getSource()== vistaCliente.btnEliminar){
        
            eliminarCliente();
        
        }
        
        if(e.getSource()== vistaCliente.btnModificar){
        
            modificarCliente();
        
        
        }
    
    
    }

    @Override
    public void focusGained(FocusEvent e) {
        
        System.out.println("no hacemos nada al ganar el focus");
    }

    @Override
    public void focusLost(FocusEvent e) {
         if(e.getSource()== vistaCliente.txtDni){
        
            verificarDni(vistaCliente.txtDni.getText());
        
        }
        
    }
    
    
    
}
