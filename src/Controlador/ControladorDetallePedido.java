
package Controlador;

import Modelo.BD;
import Modelo.DetallePedido;
import Modelo.Producto;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Vidaurre Julio Fernando
 */
public class ControladorDetallePedido {

    public ControladorDetallePedido() {
       
        
    }
            
    public  double altaDetallePedido(int numPedido, DefaultTableModel tablaDetallePedido){
    
               double total=0.0;
               int cantidad = tablaDetallePedido.getRowCount();
               for (int i = 0; i < cantidad; i++) {
                
                DetallePedido dP = new DetallePedido();
                dP.setIdDetallePedido(0);
                dP.setNumPedido(numPedido);
                dP.setCantidad(Integer.parseInt(tablaDetallePedido.getValueAt(i, 0).toString()));
                dP.setIdProducto(Integer.parseInt(tablaDetallePedido.getValueAt(i, 1).toString()));
                dP.setNombre(tablaDetallePedido.getValueAt(i, 2).toString());
                dP.setPrecioUnitario(Double.parseDouble(tablaDetallePedido.getValueAt(i, 3).toString()));
                dP.setSubtotal(Double.parseDouble(tablaDetallePedido.getValueAt(i, 4).toString()));
                dP.setObservacion(tablaDetallePedido.getValueAt(i, 5).toString()); 
             
                BD bdAgregar = new BD();
                if (bdAgregar.altaDetallePedido(dP)){
                
                    total +=dP.getSubtotal();
                    
                    // actualizamos el stock del producto
                    BD bdP = new BD();
                    Producto p = bdP.buscarProducto(dP.getIdProducto());
                    p.setStock( p.getStock() - dP.getCantidad());
                    BD bdP2 = new BD();
                    bdP2.modificarProducto(p);
                    
                }
             
             }
        
        
        
        return total;
    }
    
    public ArrayList listarDetallePedido(int numeroPedido) {
    
        ArrayList listado;
       
        BD bd = new BD();
        listado=bd.listarDetallePedido(numeroPedido);
               
        return listado;
        
    }
    
    
    /*
    public static ArrayList listarProducto(int idProducto, String codigo, String nombre){
    
        ArrayList lista;
        BD bd = new BD();
        lista=bd.listarProducto(idProducto,codigo,nombre);
        return lista;
    
    }
    
 
    public static Producto buscarProducto(int idProducto){
        
        Producto producto;
        BD bd = new BD();
        producto = bd.buscarProducto(idProducto);
        
        return producto;
    
    
    }

    public static void modificarProducto() {
              
             Producto producto = new Producto(Integer.parseInt(vistaProducto.getTxtIdProducto().getText()),vistaProducto.getTxtCodigo().getText(),vistaProducto.getTxtNombre().getText(),
                Integer.parseInt(vistaProducto.getTxtStock().getText()),Double.parseDouble(vistaProducto.getTxtPrecio().getText()),
                vistaProducto.getTxtImagen().getText(), String.valueOf(vistaProducto.getcBoxTipo().getSelectedItem()),Integer.parseInt(vistaProducto.getTxtIdCategoria().getText()) );
             
    
         BD bd = new BD();
         
          if (bd.modificarProducto(producto)){
          
            JOptionPane.showMessageDialog(null, "Producto Modificado Exitosamente!");
        
        }else{
           
             JOptionPane.showMessageDialog(null, "Error al modificar el Producto, intente nuevamente!");
        
          }        
    }
*/
    public boolean eliminarDetallePedido(int idPedido) {
       
        boolean salida;
        ArrayList listado;
        BD bd = new BD();
        listado = bd.listarDetallePedido(idPedido);
        System.out.print(listado);
        if(!listado.isEmpty()){
        
            Iterator iterador = listado.iterator();
            while(iterador.hasNext()){
             
              DetallePedido dP = (DetallePedido) iterador.next();
              
              ControladorProducto ctrlP = new ControladorProducto();
              Producto p = ctrlP.buscarProducto(dP.getIdProducto());
              int stock = p.getStock() + dP.getCantidad();
              p.setStock(stock);
              ctrlP.modificarProducto(p);
              
              
         }
        BD bd2 = new BD();
        
        salida = bd2.eliminarDetallePedido(idPedido);
        
        }else{
        
        salida=true;
        
        }
        
         
          
      return salida;
                
        
    }
    
     public boolean anularDetallePedido(int idPedido) {
       
        boolean salida;
        
        ArrayList listado;
        BD bd = new BD();
        listado = bd.listarDetallePedido(idPedido);
        
        if(!listado.isEmpty()){
        
            Iterator iterador = listado.iterator();
            while(iterador.hasNext()){
             
              DetallePedido dP = (DetallePedido) iterador.next();
              
              ControladorProducto ctrlP = new ControladorProducto();
              Producto p = ctrlP.buscarProducto(dP.getIdProducto());
              int stock = p.getStock() + dP.getCantidad();
              p.setStock(stock);
              ctrlP.modificarProducto(p);
              
              
         }
        BD bd2 = new BD();
        
        salida = true;
        
        }else{
        
        salida=true;
        
        }
        
         
          
      return salida;
                
        
    }
    
    
/*
    public static ArrayList listarCategoria() {
        ArrayList<Categoria> listaCategoria;
        listaCategoria=ControladorCategoria.listarCategoria(0,""); //To change body of generated methods, choose Tools | Templates.
        return listaCategoria;
    }

    static ArrayList listarProductoPorCategoria(int idCategoria) {
        
         ArrayList lista;
        BD bd = new BD();
        lista=bd.listarProductoPorCategoria(idCategoria);
        return lista;
        
        
    }

  */

    
    
}
