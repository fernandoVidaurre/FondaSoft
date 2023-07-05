/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Categoria;
import Vista.VistaCategoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Vidaurre Julio Fernando
 */
public class ControladorCategoria implements ActionListener {
    
    
    public  VistaCategoria vistaCategoria = new VistaCategoria();

    public ControladorCategoria() {
        
        vistaCategoria.btnCancelar.addActionListener(this);
        vistaCategoria.btnEliminar.addActionListener(this);
        vistaCategoria.btnGuardar.addActionListener(this);
        vistaCategoria.btnModificar.addActionListener(this);
        
    }
    
    
    
    public void mostrar(int idCategoria,String accion){
    
        vistaCategoria.setTitle("Categoria");
        vistaCategoria.setLocationRelativeTo(null);
        vistaCategoria.cargarBotones(accion);
        
        if (idCategoria >0) {
           
           Categoria categoria = buscarCategoria(idCategoria);                                                                                      
           
           vistaCategoria.cargarDatos(categoria);
           
        }
        
        vistaCategoria.setVisible(true);
          
           
    }
    public  void cerrar(){
        ControladorPrincipal.habilitar(); 
        ControladorPrincipal.actualizarListadoCategoria();
        vistaCategoria.dispose();    
        
    }
    public void altaCategoria(int idCategoria){
    
        
    Categoria categoria = new Categoria(0,vistaCategoria.getTxtNombre().getText(),vistaCategoria.getTxtAreaDescripcion().getText());

    
    
         BD bd = new BD();
        if (bd.altaCategoria(categoria)){
            vistaCategoria.limpiar();
            JOptionPane.showMessageDialog(null, "Categoria Registrada Exitosamente!");
        
        }else{
           
             JOptionPane.showMessageDialog(null, "Error al registra la Categoria, intente nuevamente!");
        
        }
    }
    
    public ArrayList listarCategoria(int idCategoria, String nombre){
    
        ArrayList lista;
        BD bd = new BD();
        lista=bd.listarCategoria(idCategoria,nombre);
        return lista;
    
    }
    
 
    public Categoria buscarCategoria(int idCategoria){
         
        Categoria categoria;
        BD bd = new BD();
        categoria = bd.buscarCategoria(idCategoria);
       
        return categoria;
    
    
    }

    public void modificarCategoria() {
              
            Categoria categoria = new Categoria(Integer.parseInt(vistaCategoria.getTxtIdCategoria().getText()),vistaCategoria.getTxtNombre().getText(),vistaCategoria.getTxtAreaDescripcion().getText());
          
    
    
         BD bd = new BD();
         
          if (bd.modificarCategoria(categoria)){
          
            JOptionPane.showMessageDialog(null, "Categoria Modificada Exitosamente!");
        
        }else{
           
             JOptionPane.showMessageDialog(null, "Error al modificar la Categoria, intente nuevamente!");
        
        }
         
    }

    public void eliminarCategoria() {
       
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(Integer.parseInt(vistaCategoria.getTxtIdCategoria().getText()));
       
        BD bd = new BD();
        if (bd.eliminarCategoria(categoria)){
         JOptionPane.showMessageDialog(null, "Categoria Eliminada Exitosamente!");
          vistaCategoria.limpiar();
        }else{
        
             JOptionPane.showMessageDialog(null, "Error al Eliminar la Categoria, intente nuevamente!");
        
        } 
                
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
         if(e.getSource()== vistaCategoria.btnCancelar){
        
            vistaCategoria.limpiar();
            cerrar();
        
        }
        
        if(e.getSource()== vistaCategoria.btnGuardar){
    
            String idCategoria=vistaCategoria.getTxtIdCategoria().getText();
         
            if( idCategoria.equals("")){
            
                altaCategoria(0);
            
            }else{
            
                altaCategoria(Integer.parseInt(idCategoria));
        
                }
        }
        
        if(e.getSource()== vistaCategoria.btnEliminar){
        
            eliminarCategoria();
        
        }
        
        if(e.getSource()== vistaCategoria.btnModificar){
        
            modificarCategoria();
        
        
        }
        
    }

    
    
}
