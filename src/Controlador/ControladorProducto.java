
package Controlador;

import Modelo.BD;
import Modelo.Categoria;
import Modelo.Producto;
import Vista.VistaProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Vidaurre Julio Fernando
 */
public class ControladorProducto implements ActionListener, ItemListener {
    
    
    public VistaProducto vistaProducto = new VistaProducto();

    public ControladorProducto() {
        
        vistaProducto.btnCancelar.addActionListener(this);
        vistaProducto.btnEliminar.addActionListener(this);
        vistaProducto.btnGuardar.addActionListener(this);
        vistaProducto.btnModificar.addActionListener(this);
        vistaProducto.cBoxCategoriaId.addItemListener(this);
        
        
        
    }
    
    
    
    public void mostrar(int idProducto,String accion){
    
        vistaProducto.setTitle("Producto");
        vistaProducto.setLocationRelativeTo(null);
        vistaProducto.cargarBotones(accion);
        
        vistaProducto.cargarComboBoxProducto(0,listarCategoria());
     
        if (idProducto >0) {
            
           Producto producto = buscarProducto(idProducto);                                                                                      
           vistaProducto.cargarDatos(producto,listarCategoria());
         
       }
        
        
        vistaProducto.setVisible(true);
      
    }
    public void cerrar(){
        ControladorPrincipal.habilitar(); 
        ControladorPrincipal.actualizarListadoProducto();
        vistaProducto.dispose();    
        
    }
    public boolean altaProducto(Producto producto){
        
        
        BD bd = new BD();
        
        boolean salida = bd.altaProducto(producto);
            
        
    return salida;
    }
    
    
    public ArrayList listarProducto(int idProducto, String codigo, String nombre){
    
        ArrayList lista;
        BD bd = new BD();
        lista=bd.listarProducto(idProducto,codigo,nombre);
        return lista;
    
    }
    
 
    public Producto buscarProducto(int idProducto){
        
        Producto producto;
        BD bd = new BD();
        producto = bd.buscarProducto(idProducto);
        
        return producto;
    
    
    }

    public boolean modificarProducto( Producto p) {
           
         BD bd = new BD();
         
         Boolean salida = bd.modificarProducto(p); 
        
         return salida;
    }

    public boolean eliminarProducto(Producto producto) {
       
        
        BD bd = new BD();
        
        boolean salida =bd.eliminarProducto(producto);
        
       return salida;
                
        
    }

    public ArrayList listarCategoria() {
        ArrayList<Categoria> listaCategoria;
        ControladorCategoria ctrlCategoria = new ControladorCategoria();
        listaCategoria=ctrlCategoria.listarCategoria(0,""); //To change body of generated methods, choose Tools | Templates.
        return listaCategoria;
    }

    public ArrayList listarProductoPorCategoria(int idCategoria) {
        
        ArrayList lista;
        BD bd = new BD();
        lista=bd.listarProductoPorCategoria(idCategoria);
        return lista;
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        if(e.getSource()== vistaProducto.btnCancelar){
        
            vistaProducto.limpiar();
            cerrar();
        
        }
        
        if(e.getSource()== vistaProducto.btnGuardar){
    
            String idProducto=vistaProducto.getTxtIdProducto().getText();
            int id;
            
            if( idProducto.equals("")){
                  id=0;
                
            }else{
                 id=Integer.parseInt(idProducto);
                 } 
            
            Producto producto = new Producto(id,vistaProducto.getTxtCodigo().getText(),vistaProducto.getTxtNombre().getText(),
                Integer.parseInt(vistaProducto.getTxtStock().getText()),Double.parseDouble(vistaProducto.getTxtPrecio().getText()),
                vistaProducto.getTxtImagen().getText(), String.valueOf(vistaProducto.getcBoxTipo().getSelectedItem()),Integer.parseInt(vistaProducto.getTxtIdCategoria().getText()));
                        
            boolean salida=altaProducto(producto);
            
            if (salida) {
                
                vistaProducto.limpiar();
                JOptionPane.showMessageDialog(null, "Producto Registrado Exitosamente!");
                    
            }else{
                
                 JOptionPane.showMessageDialog(null, "Error al registra el Producto, intente nuevamente!");
        
            
            
            }
           
        }
        
        if(e.getSource()== vistaProducto.btnEliminar){
          
             Producto producto = new Producto();
             producto.setIdProducto(Integer.parseInt(vistaProducto.getTxtIdProducto().getText()));
            
            if(eliminarProducto(producto)){
            
                JOptionPane.showMessageDialog(null, "Producto Eliminado Exitosamente!");
                vistaProducto.limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar Producto, intente nuevamente!");
        
            }
         
        
        }
        
        if(e.getSource()== vistaProducto.btnModificar){
            
            
                Producto p = new Producto(Integer.parseInt(vistaProducto.getTxtIdProducto().getText()),vistaProducto.getTxtCodigo().getText(),vistaProducto.getTxtNombre().getText(),
                Integer.parseInt(vistaProducto.getTxtStock().getText()),Double.parseDouble(vistaProducto.getTxtPrecio().getText()),
                vistaProducto.getTxtImagen().getText(), String.valueOf(vistaProducto.getcBoxTipo().getSelectedItem()),Integer.parseInt(vistaProducto.getTxtIdCategoria().getText()) );
                                    
             if(modificarProducto(p)){
                 JOptionPane.showMessageDialog(null, "Producto Modificado Exitosamente!");
             }else{
                 JOptionPane.showMessageDialog(null, "Error al modificar el Producto, intente nuevamente!");
             
             }
             
             
        }

        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        
        
       if(e.getSource()== vistaProducto.cBoxCategoriaId){
           
                String categoria=String.valueOf(vistaProducto.cBoxCategoriaId.getSelectedItem());
                
                if (categoria.equals("") || categoria.equals("null")){
                    vistaProducto.txtIdCategoria.setText("0");//setTxtIdCategoria("0");
                }else{
                    int indice = categoria.indexOf(" ");
                    
                    String idCategoria=categoria.substring(0, indice);
                    
                   
                   vistaProducto.txtIdCategoria.setText(idCategoria);
                    } 
       } 
        
    }

      
}
