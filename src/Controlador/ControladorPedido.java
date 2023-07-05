
package Controlador;


import Modelo.BD;
import Modelo.Categoria;
import Modelo.Cliente;
import Modelo.DetallePedido;
import Modelo.Pedido;
import Modelo.Producto;
import Vista.VistaPedido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author Vidaurre Julio Fernando
 */
public class ControladorPedido implements ActionListener,ItemListener {
    
    
    public  VistaPedido vistaPedido = new VistaPedido();

    @SuppressWarnings("LeakingThisInConstructor")
    public ControladorPedido() {
       
         vistaPedido.btnCancelar.addActionListener(this);
         vistaPedido.btnAnular.addActionListener(this);
         vistaPedido.btnGuardar.addActionListener(this);
         vistaPedido.btnModificar.addActionListener(this);
         
         vistaPedido.btnBuscarCliente.addActionListener(this);
         vistaPedido.btnLimpiarDatos.addActionListener(this);
         vistaPedido.btnNuevoCliente.addActionListener(this);
         
         vistaPedido.btnQuitarProducto.addActionListener(this);
         vistaPedido.btnAgregarProducto.addActionListener(this);
         vistaPedido.getcBoxCategoriaId().addItemListener(this);
         
                 
    
    }
   
    public  void mostrar(int numeroPedido,String accion){
    
        vistaPedido.setTitle("Pedido");
        vistaPedido.setLocationRelativeTo(null);
        vistaPedido.cargarBotones(accion); 
        vistaPedido.cargarComboBoxPedido(0,listarCategoria());
        vistaPedido.cargarNumPedido(numeroPedido);
     
        if (numeroPedido >0) {
            
          Pedido pedido = buscarPedido(numeroPedido); 
         
          BD bd = new BD();
          Cliente cliente = bd.buscarCliente(pedido.getIdCliente());
          ArrayList lista=buscarCliente(cliente.getDni());
          vistaPedido.cargarCliente(lista);
          
          
          ArrayList listadoProductosPorPedido;
          ControladorDetallePedido ctrlDetallePedido = new ControladorDetallePedido();
          listadoProductosPorPedido=ctrlDetallePedido.listarDetallePedido(numeroPedido);
          cargarDetallePedido(listadoProductosPorPedido);
                 
       }
        
        
        vistaPedido.setVisible(true);
      
       
    }
    public void cerrar(){
        ControladorPrincipal.habilitar(); 
        ControladorPrincipal.actualizarListadoPedido();
        vistaPedido.dispose();
        
    }
    
    public void cargarDetallePedido(ArrayList listadoProductosPorPedido){
    
         
         Iterator iterador = listadoProductosPorPedido.iterator();
          while(iterador.hasNext()){
             DetallePedido dP = (DetallePedido) iterador.next();
             
             //int idProducto,String producto,Double precio, int stock,int cantidad,String observacion
                     
             vistaPedido.cargarTablaPrePedido(dP.getIdProducto(),dP.getNombre(),dP.getPrecioUnitario(),dP.getCantidad(),dP.getObservacion());
             vistaPedido.actualizarSubtotal(dP.getSubtotal());
           
         }
            
        
    
    }
    
    public  void altaPedido(int numero, DefaultTableModel tablaDetallePedido,int numMesa, int idCliente){
    
       LocalTime hora = LocalTime.now();
       LocalDate fecha = LocalDate.now();
       int idUsuario = ControladorPrincipal.user.getIdUsuario();
       int numPedido; 
       Double total=0.0;
   
         Pedido pedido = new Pedido(0,fecha,hora,total,"tomado",idUsuario,numMesa,idCliente);
         BD bd = new BD();
         numPedido=bd.altaPedido(pedido);
     
         if (numPedido > 0){
            
            ControladorDetallePedido ctrlDetallePedido = new ControladorDetallePedido();
            total = ctrlDetallePedido.altaDetallePedido(numPedido, tablaDetallePedido);
             
            //Actualizamos el importe total del pedido  
            BD bd2 = new BD();  
            pedido.setImporte(total);
             
            if(bd2.modificarPedido(numPedido,pedido)){
                System.out.println("ok");
            }else{
              System.out.println("error");
            }
            
            vistaPedido.limpiarDetalleProducto();
            vistaPedido.limpiarDatosCliente();
            vistaPedido.limpiarTablaPedido();
            vistaPedido.cargarComboBoxPedido(0,listarCategoria());
            JOptionPane.showMessageDialog(null, "Pedido Registrado Exitosamente!");
        
        }else{
           
             JOptionPane.showMessageDialog(null, "Error al registra el Pedido, intente nuevamente!");
        
        }
        
    }
    
    public void modificarPedido(int numeroPedido, DefaultTableModel tablaDetallePedido,int numMesa, int idCliente){
    
   
        Pedido pedido;
        BD bd = new BD();
        pedido= bd.buscarPedido(numeroPedido);
        
        pedido.setIdMesa(numMesa);
        pedido.setIdCliente(idCliente);
        pedido.setEstado("modificado");
        
        ControladorDetallePedido ctrlDP = new ControladorDetallePedido();
        
        if(ctrlDP.eliminarDetallePedido(numeroPedido)){
        
            pedido.setImporte(0.0);
            double total = ctrlDP.altaDetallePedido(numeroPedido, tablaDetallePedido);
            pedido.setImporte(total);
            
            BD bd2 = new BD();
            if(bd2.modificarPedido(numeroPedido,pedido)){
                System.out.println("ok");
            }else{
              System.out.println("error");
            }
            
            vistaPedido.limpiarDetalleProducto();
            vistaPedido.limpiarDatosCliente();
            vistaPedido.limpiarTablaPedido();
            vistaPedido.cargarComboBoxPedido(0,listarCategoria());
            JOptionPane.showMessageDialog(null, "Pedido modificado Exitosamente!");
       
        }else{
        
        
              JOptionPane.showMessageDialog(null, "Error al modificar el Pedido, intente nuevamente!");
        

        
        }
  
    
    }
    
    
    public void anularPedido(int numeroPedido, int numMesa){
    
        Pedido pedido;
        BD bd = new BD();
        pedido= bd.buscarPedido(numeroPedido);
        pedido.setEstado("cancelado");
        
        ControladorDetallePedido ctrlDP = new ControladorDetallePedido();
        
        if(ctrlDP.anularDetallePedido(numeroPedido)){
                               
            BD bd2 = new BD();
            if(bd2.modificarPedido(numeroPedido,pedido)){
                System.out.println("ok");
            }else{
              System.out.println("error");
            }
            
            vistaPedido.limpiarDetalleProducto();
            vistaPedido.limpiarDatosCliente();
            vistaPedido.limpiarTablaPedido();
            vistaPedido.cargarComboBoxPedido(0,listarCategoria());
            JOptionPane.showMessageDialog(null, "Pedido Anulado Exitosamente!");
       
        }else{
        
        
              JOptionPane.showMessageDialog(null, "Error al Anular el Pedido, intente nuevamente!");
        

        
        }
        
        
        
    }
    
    public ArrayList listarPedido(int numero, String dni){
    
        ArrayList listado;

        BD bd = new BD();
        listado=bd.listarPedido(numero,dni);
       

    
    
       return listado;
    }
    public ArrayList listarPedidoMesa(int numeroMesa){
    
        ArrayList listado;

        BD bd = new BD();
        listado=bd.listarPedidoMesa(numeroMesa);
       

    
    
       return listado;
    }
    public Pedido buscarPedido(int numeroPedido){
        
        Pedido pedido;
        BD bd = new BD();
        pedido = bd.buscarPedido(numeroPedido);
        
        return pedido;
    
    
    }
    
    
    
    
    public  TableModel listarProducto(int idCategoria){
    
       
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.setNumRows(0);
              
         modelo.addColumn("IdProducto");
         modelo.addColumn("Categoria");
         modelo.addColumn("Nombre");
         modelo.addColumn("Precio");
         modelo.addColumn("Stock");
        
        
         
         ArrayList lista;
         ControladorProducto ctrlProducto = new ControladorProducto();
         lista=ctrlProducto.listarProductoPorCategoria(idCategoria);
     
          Iterator iterador = lista.iterator();
          while(iterador.hasNext()){
             Producto p = (Producto) iterador.next();
             Object[] filas = new Object[5];
             filas[0]=p.getIdProducto();
             // el sgte codigo es para poder mostrar en el listado el nombre de la categoria y no el idCategoria
             BD bd = new BD();
             Categoria c =bd.buscarCategoria(p.getIdCategoria());
             filas[1]=c.getNombre(); 
             // *******************
             filas[2]=p.getNombre();
             filas[3]=p.getPrecio();
             filas[4]=p.getStock();
            
             modelo.addRow(filas);
             
             
         }

     return modelo;
         
    }
    
     public  ArrayList buscarCliente(String dni) {

         
         ArrayList lista;
         ControladorCliente ctrlCliente = new ControladorCliente();
         lista=ctrlCliente.listarCliente(0,dni); // parametro 0 busca todos los clientes cuyo dni coincidan
     
         return lista;
             
         
     }

   
   
    public  ArrayList listarCategoria() {
    
        ArrayList<Categoria> listaCategoria;
        ControladorCategoria ctrlCategoria = new ControladorCategoria();
        listaCategoria=ctrlCategoria.listarCategoria(0,""); //To change body of generated methods, choose Tools | Templates.
        return listaCategoria;
    
    }

  

    public static void altaCliente(int idPersona, String dni, String apellido, String nombre, String telefono, String direccion, String email, String tipo, String estado) {
        
         Cliente cliente = new Cliente(0,tipo,estado, idPersona,dni,apellido,nombre,direccion,telefono,email);
    
    
         BD bd = new BD();
        if (bd.altaCliente(cliente)){
           
            JOptionPane.showMessageDialog(null, "Cliente Registrado Exitosamente!");
        
        }else{
           
             JOptionPane.showMessageDialog(null, "Error al registra el Cliente, intente nuevamente!");
        
        }

        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
       
        if(e.getSource()== vistaPedido.btnCancelar){
        
             vistaPedido.limpiarDetalleProducto();
             vistaPedido.limpiarTablaPedido();
             vistaPedido.limpiarDatosCliente();
             cerrar();
        
        }
        
        if(e.getSource()== vistaPedido.btnGuardar){
    
            int numMesa= 0;
            int idCliente;
            
            String idC = vistaPedido.getTxtIdCliente().getText();
            if (idC.isEmpty()) {
                idCliente=1;
            }else{
             idCliente = Integer.parseInt(idC);
            }
            altaPedido(0,vistaPedido.modelo,numMesa,idCliente);
        
        }
        
        if(e.getSource()== vistaPedido.btnModificar){
        
            int numMesa=0;
            modificarPedido(Integer.parseInt(vistaPedido.getTxtNumPedido().getText()),vistaPedido.modelo,numMesa,Integer.parseInt(vistaPedido.getTxtIdCliente().getText()));
            cerrar();
        }
        
        if(e.getSource() == vistaPedido.btnAnular){
        
            int numMesa=0;
            anularPedido(Integer.parseInt(vistaPedido.getTxtNumPedido().getText()),numMesa);
            cerrar();
        
        }
        
        
        if(e.getSource()== vistaPedido.btnLimpiarDatos){
                vistaPedido.limpiarDatosCliente();
            
        
        }
        
        if(e.getSource()== vistaPedido.btnNuevoCliente){
             
            
            if ( vistaPedido.getTxtIdCliente().getText().equals("")){     
            int idPersona=0;
            String dni = vistaPedido.getTxtDniCliente().getText();
            String apellido = vistaPedido.getTxtApellidoCliente().getText();
            String nombre = vistaPedido.getTxtNombreCliente().getText();
            String telefono = vistaPedido.getTxtTelefonoCliente().getText();
            String direccion = vistaPedido.getTxtDireccionCliente().getText();
            String email = vistaPedido.getTxtEmailCliente().getText();
            String tipo = String.valueOf(vistaPedido.getcBoxTipo().getSelectedItem());
            String estado = String.valueOf(vistaPedido.getcBoxEstado().getSelectedItem());

            altaCliente(idPersona,dni,apellido,nombre,telefono,direccion,email,tipo,estado);
            ArrayList lista=buscarCliente(dni);
            vistaPedido.cargarCliente(lista);
            
        } else {
            JOptionPane.showMessageDialog(null, "Error! El cliente ya existe. Ingrese dni y luego presione buscar");

        }
            
        
        }
        
          if(e.getSource()== vistaPedido.btnBuscarCliente){
                
              
               String dni= vistaPedido.getTxtDniCliente().getText();
                buscarCliente(dni);//
                ArrayList lista=buscarCliente(dni);
                vistaPedido.cargarCliente(lista);
        
        }
        
        if (e.getSource()== vistaPedido.btnQuitarProducto) {
             
        //vistaPedido.getjTablePedido().getSelectedRow();
         int fila = vistaPedido.getjTablePedido().getSelectedRow();
         if (fila >= 0){
            
            double subTotal = Double.parseDouble(vistaPedido.getjTablePedido().getValueAt(fila, 4).toString());
            subTotal *= -1;
            vistaPedido.modelo.removeRow(fila);
            vistaPedido.actualizarSubtotal(subTotal);

         }else{

            JOptionPane.showMessageDialog(null, "Seleccione un Producto a Quitar ");

            }
            
        }
        
        if(e.getSource()== vistaPedido.btnAgregarProducto){
        
            
              int fila = vistaPedido.getjTableProducto().getSelectedRow();

        if (fila >=0) {

            int idProducto = Integer.parseInt(vistaPedido.getjTableProducto().getValueAt(fila,0).toString());
            String producto = vistaPedido.getjTableProducto().getValueAt(fila,2).toString();
            double precio = Double.parseDouble(vistaPedido.getjTableProducto().getValueAt(fila,3).toString());
            int stock = Integer.parseInt(vistaPedido.getjTableProducto().getValueAt(fila,4).toString());
            int cantidad = Integer.parseInt(vistaPedido.getTxtCantidad().getText());
            String observacion = vistaPedido.getTxtAreaObserv().getText();
            double subTotal= precio*cantidad;

            vistaPedido.cargarTablaPrePedido(idProducto,producto,precio,cantidad,observacion);
            vistaPedido.limpiarDetalleProducto();
            vistaPedido.actualizarSubtotal(subTotal);

        }else{

            JOptionPane.showMessageDialog(null, "Seleccione un Producto");
        }
        
        
        
        
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    
         if(e.getSource()== vistaPedido.getcBoxCategoriaId()){
           
             String categoria=String.valueOf(vistaPedido.getcBoxCategoriaId().getSelectedItem());

        int idCategoria;

        if (categoria.equals("null")){
            idCategoria=0;
        }else{
            
           int indice = categoria.indexOf(" ");
           String iCategoria=categoria.substring(0, indice);
            idCategoria= Integer.parseInt(String.valueOf(iCategoria));
        }

        vistaPedido.cargarTablaProducto( listarProducto(idCategoria));  
             
             
       } 
    
    }

 
    
}
