
package Controlador;
import Modelo.*;
import Vista.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author Vidaurre Julio Fernando
 */
public class ControladorPrincipal {
    
    public static Usuario user;
    
    public static VistaMenuPrincipal vistaMenuPrincipal = new VistaMenuPrincipal();
 
    
    // Metodos Propios
    
    public static void mostrar( Usuario usuario){
            
      user = usuario;
      
      vistaMenuPrincipal.setTitle("FondaSoft Resto");
      vistaMenuPrincipal.cargarDatosUsuario();
      vistaMenuPrincipal.setVisible(true);
      
    }
    public static void desabilitar(){
        vistaMenuPrincipal.setEnabled(false);
    }
    public static void habilitar(){
    
        vistaMenuPrincipal.setEnabled(true); 
    }
    public static void ocultar(){
        vistaMenuPrincipal.setVisible(false);
    }
    public static void salir(){
        
        ControladorLogin ctrlL = new ControladorLogin();
        ctrlL.mostrar();
        vistaMenuPrincipal.dispose();
        
    }
   
    
 // Metodos Sobre Cliente
    
    public static DefaultTableModel listarCliente(int id, String dni){
    
         DefaultTableModel modelo = new DefaultTableModel();
         modelo.setNumRows(0);
              
         modelo.addColumn("IdCliente");
         modelo.addColumn("Dni");
         modelo.addColumn("Apellido");
         modelo.addColumn("Nombre");
         modelo.addColumn("Direccion");
         modelo.addColumn("Telefono");
         modelo.addColumn("Email");
        
        
        
         ArrayList lista;
         ControladorCliente ctrlCliente = new ControladorCliente();
         lista=ctrlCliente.listarCliente(id,dni);
     
          Iterator iterador = lista.iterator();
          while(iterador.hasNext()){
             Cliente c = (Cliente) iterador.next();
             Object[] filas = new Object[7];
             filas[0]=c.getIdCliente();
             filas[1]=c.getDni();
             filas[2]=c.getApellido();
             filas[3]=c.getNombre();
             filas[4]=c.getDireccion();
             filas[5]=c.getTelefono();
             filas[6]=c.getEmail();
             
            
             modelo.addRow(filas);
            
             
         }

     return modelo;
         
        
    }
    public static void agregarCliente(int idCliente){
        
            ControladorCliente ctrlCliente = new ControladorCliente();
            ctrlCliente.mostrar(idCliente,"guardar"); 
    }
    public static void modificarCliente(int idCliente) {
        
            ControladorCliente ctrlCliente = new ControladorCliente();
            ctrlCliente.mostrar(idCliente,"modificar");
         
    }
    public static void eliminarCliente(int idCliente) {
            ControladorCliente ctrlCliente = new ControladorCliente();
            ctrlCliente.mostrar(idCliente,"eliminar");
    }
    public static void actualizarListadoCliente(){
        
     
      vistaMenuPrincipal.cargarTablaCliente(0,"");    //mostrar todos los clientes actualizados
    }
    
// Metodos Sobre Usuario
    
    public static TableModel listarUsuario(int idUsuario, String dni, String nombreUsuario) {
     
         DefaultTableModel modelo = new DefaultTableModel();
         modelo.setNumRows(0);
              
         modelo.addColumn("IdUsuario");
         modelo.addColumn("Usuario");
         modelo.addColumn("Password");
         modelo.addColumn("Apellido");
         modelo.addColumn("Nombre");
         modelo.addColumn("Estado");
        
               
         ArrayList lista;
        
         ControladorUsuario ctrlUsuario = new ControladorUsuario();
         
         lista=ctrlUsuario.listarUsuario(idUsuario,dni,nombreUsuario);
     
          Iterator iterador = lista.iterator();
          while(iterador.hasNext()){
             Usuario u = (Usuario) iterador.next();
             Object[] filas = new Object[6];
             filas[0]=u.getIdUsuario();
             filas[1]=u.getUsuario();           
             filas[2]="********"; //u.getPassword();
             filas[3]=u.getApellido();
             filas[4]=u.getNombre();
             filas[5]=u.getEstado();
             
            
             modelo.addRow(filas);
            
             
         }

     return modelo;
         
        
    }
    public static void agregarUsuario(int idUsuario) {
    
         ControladorUsuario ctrlUsuario = new ControladorUsuario();
         ctrlUsuario.mostrar(idUsuario,"guardar");
        
        }
    public static void actualizarListadoUsuario(){
    
        vistaMenuPrincipal.cargarTablaUsuario(0,"","");    //mostrar todos los usuarios actualizados
    
    }
    public static void modificarUsuario(int idUsuario) {
       
         ControladorUsuario ctrlUsuario = new ControladorUsuario();
         ctrlUsuario.mostrar(idUsuario,"modificar");
        
    }
    public static void eliminarUsuario(int idUsuario) {
        
         ControladorUsuario ctrlUsuario = new ControladorUsuario();
         ctrlUsuario.mostrar(idUsuario,"eliminar");
    }

// Metodos Sobre Mozo

    public static void actualizarListadoMozo() {
  
        vistaMenuPrincipal.cargarTablaMozo(0,"","");  // mostrar todos los mozos actualizados 
    }

    public static TableModel listarMozo(int idMozo, String dni, String codigo) {
       
         DefaultTableModel modelo = new DefaultTableModel();
         modelo.setNumRows(0);
              
         modelo.addColumn("IdMozo");
         modelo.addColumn("Codigo");
         modelo.addColumn("Apellido");
         modelo.addColumn("Nombre");
         modelo.addColumn("Telefono");
         modelo.addColumn("Email");
         modelo.addColumn("Estado");
        
        
        
         ArrayList lista;
         ControladorMozo ctrlMozo = new ControladorMozo();
         lista=ctrlMozo.listarMozo(idMozo,dni,codigo);
     
          Iterator iterador = lista.iterator();
          while(iterador.hasNext()){
             Mozo m = (Mozo) iterador.next();
             Object[] filas = new Object[7];
             filas[0]=m.getIdMozo();
             filas[1]=m.getCodigo();           
             filas[2]=m.getApellido();
             filas[3]=m.getNombre();
             filas[4]=m.getTelefono();
             filas[5]=m.getEmail();
             filas[6]=m.getEstado();
            
             modelo.addRow(filas);
            
             
         }

     return modelo;
         
        
        
    }

    public static void agregarMozo(int idMozo) {
       
        ControladorMozo ctrlMozo = new ControladorMozo();
        ctrlMozo.mostrar(idMozo, "guardar");
        
    }

    public static void modificarMozo(int idMozo) {
        
        ControladorMozo ctrlMozo = new ControladorMozo();
        ctrlMozo.mostrar(idMozo, "modificar");
    }

    public static void eliminarMozo(int idMozo) {
        
        ControladorMozo ctrlMozo = new ControladorMozo();
        ctrlMozo.mostrar(idMozo,"eliminar");
    
    }
    
// Metodos Sobre Categoria   

    public static void actualizarListadoCategoria() {
       
         vistaMenuPrincipal.cargarTablaCategoria(0,"");  // mostrar todas las categorias actualizadas 
    }

    public static void agregarCategoria(int idCategoria) {
       
        ControladorCategoria ctrlCategoria = new ControladorCategoria();
        
        ctrlCategoria.mostrar(idCategoria,"guardar");
        
    }

    public static void modificarCategoria(int idCategoria) {
            
         ControladorCategoria ctrlCategoria = new ControladorCategoria();
         ctrlCategoria.mostrar(idCategoria,"modificar");
        
    }

    public static TableModel listarCategoria(int idCategoria, String nombre) {
 
         DefaultTableModel modelo = new DefaultTableModel();
         modelo.setNumRows(0);
              
         modelo.addColumn("IdCategoria");
         modelo.addColumn("Nombre");
         modelo.addColumn("Descripcion");
         
        
        
         ArrayList lista;
         ControladorCategoria ctrlCategoria = new ControladorCategoria();
         lista=ctrlCategoria.listarCategoria(idCategoria,nombre);
     
          Iterator iterador = lista.iterator();
          while(iterador.hasNext()){
             
              Categoria c = (Categoria) iterador.next();
             
             Object[] filas = new Object[3];
             
             filas[0]=c.getIdCategoria();
             filas[1]=c.getNombre();
             filas[2]=c.getDescripcion();
                         
             modelo.addRow(filas);
            
             
         }

     return modelo;
         
        
    }

    public static void eliminarCategoria(int idCategoria) {
        
         ControladorCategoria ctrlCategoria = new ControladorCategoria();
         ctrlCategoria.mostrar(idCategoria,"eliminar");
    }
    
    
// Metodos Sobre Producto    

    public static void actualizarListadoProducto() {

        vistaMenuPrincipal.cargarTablaProducto(0,"","");  // mostrar todas las categorias actualizadas 
   
    }
    public static void agregarProducto(int idProducto){
    
        ControladorProducto ctrlProducto = new ControladorProducto();
        ctrlProducto.mostrar(idProducto,"guardar");
    
    }
    public static void modificarProducto(int idProducto){
        ControladorProducto ctrlProducto = new ControladorProducto();
        ctrlProducto.mostrar(idProducto,"modificar");
    
    }
    public static void eliminarProducto(int idProducto){
        ControladorProducto ctrlProducto = new ControladorProducto();
        ctrlProducto.mostrar(idProducto,"eliminar");
    
    }
    public static TableModel listarProducto(int idProducto, String codigo, String nombre){
  
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.setNumRows(0);
              
         modelo.addColumn("IdProducto");
         modelo.addColumn("Codigo");
         modelo.addColumn("Categoria");
         modelo.addColumn("Nombre");
         modelo.addColumn("Tipo");
         modelo.addColumn("Precio");
         modelo.addColumn("Stock");
        
        
         
         ArrayList lista;
         ControladorProducto ctrlProducto = new ControladorProducto();
         lista=ctrlProducto.listarProducto(idProducto,codigo,nombre);
     
          Iterator iterador = lista.iterator();
          while(iterador.hasNext()){
             Producto p = (Producto) iterador.next();
             Object[] filas = new Object[7];
             filas[0]=p.getIdProducto();
             filas[1]=p.getCodigo();   
             // el sgte codigo es para poder mostrar en el listado el nombre de la categoria y no el idCategoria
             BD bd = new BD();
             Categoria c =bd.buscarCategoria(p.getIdCategoria());
             filas[2]=c.getNombre(); 
             // *******************
             filas[3]=p.getNombre();
             filas[4]=p.getTipo();
             filas[5]=p.getPrecio();
             filas[6]=p.getStock();
            
             modelo.addRow(filas);
            
             
         }

     return modelo;
     
  
  }
  
// Metodos Sobre Pedido
    
    public static void actualizarListadoPedido(){
    
        vistaMenuPrincipal.cargarTablaPedido(0,""); 
    }
    public static void agregarPedido(int numeroPedido){
        
        ControladorPedido ctrlPedido = new ControladorPedido();
        ctrlPedido.mostrar(numeroPedido,"guardar");
        
    }
    public static TableModel listarPedido(int numeroPedido, String dni){
    
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.setNumRows(0);
              
         modelo.addColumn("Numero");
         modelo.addColumn("Cliente");
         modelo.addColumn("Fecha");
         modelo.addColumn("Hora");
         modelo.addColumn("Estado");
         modelo.addColumn("Importe");
      
         ArrayList lista;
         ControladorPedido ctrlPedido = new ControladorPedido();
         lista=ctrlPedido.listarPedido(numeroPedido,dni);
     
          Iterator iterador = lista.iterator();
          while(iterador.hasNext()){
             Pedido p = (Pedido) iterador.next();
             Object[] filas = new Object[8];
             filas[0]=p.getNumero();
             
             // el sgte codigo es para poder mostrar en el listado el nombre de la categoria y no el idCategoria
             BD bd = new BD();
             Cliente c =bd.buscarCliente(p.getIdCliente());
             filas[1]=c.getApellido()+" "+c.getNombre();
            //filas[2]=c.getNombre(); 
             // *******************
             filas[2]=p.getFecha();
             filas[3]=p.getHora();
             filas[4]=p.getEstado();
             filas[5]=p.getImporte();
           
             modelo.addRow(filas);
            
             
         } 
        
       return modelo;
  
    }
    
     public static TableModel listarPedidoMesa(int numeroMesa){
    
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.setNumRows(0);
              
         modelo.addColumn("Numero");
         modelo.addColumn("Fecha");
         modelo.addColumn("Hora");
         modelo.addColumn("Estado");
         modelo.addColumn("Importe");
      
         ArrayList lista;
         ControladorPedido ctrlPedido = new ControladorPedido();
         lista=ctrlPedido.listarPedidoMesa(numeroMesa);
     
          Iterator iterador = lista.iterator();
          while(iterador.hasNext()){
             Pedido p = (Pedido) iterador.next();
             Object[] filas = new Object[8];
             filas[0]=p.getNumero();
             filas[1]=p.getFecha();
             filas[2]=p.getHora();
             filas[3]=p.getEstado();
             filas[4]=p.getImporte();
           
             modelo.addRow(filas);
            
             
         } 
        
       return modelo;
  
    }
    
    public static TableModel listarDetallePedido(int numeroPedido){
         DefaultTableModel modelo = new DefaultTableModel();
         modelo.setNumRows(0);
              
         modelo.addColumn("Codigo");
         modelo.addColumn("Producto");
         modelo.addColumn("Cantidad");
         modelo.addColumn("Observ.");
         modelo.addColumn("P.U.");
         modelo.addColumn("Subtotal");
        
        
        
         ArrayList lista;
         ControladorDetallePedido ctrlDetallePedido = new ControladorDetallePedido();
         
         lista=ctrlDetallePedido.listarDetallePedido(numeroPedido);
         Iterator iterador = lista.iterator();
         
         while(iterador.hasNext()){
             DetallePedido dp = (DetallePedido) iterador.next();
             Object[] filas = new Object[6];
             filas[0]=dp.getIdProducto();
             filas[1]=dp.getNombre();
             filas[2]=dp.getCantidad();
             filas[3]=dp.getObservacion();
             filas[4]=dp.getPrecioUnitario();
             filas[5]=dp.getSubtotal();
           
             modelo.addRow(filas);
            
         }
        
       
         return modelo; 
    }
    
    public static void modificarPedido(int numeroPedido){
    
        ControladorPedido ctrlPedido = new ControladorPedido();
        ctrlPedido.mostrar(numeroPedido,"modificar");
    
    }
    
    public static void cancelarPedido(int numeroPedido){
    
    ControladorPedido ctrlPedido = new ControladorPedido();
    ctrlPedido.mostrar(numeroPedido,"eliminar");
        
    }
    public static double obtenerTotalPedido(int numeroPedido){
    
    ControladorPedido ctrlPedido = new ControladorPedido();
    Pedido p = ctrlPedido.buscarPedido(numeroPedido);
    return p.getImporte();
    }
    
    public static double obtenerTotalPedidoMesa(int numeroMesa){
     
       ArrayList lista;
       double total=0.0;
       ControladorPedido ctrlPedido = new ControladorPedido();
       lista=ctrlPedido.listarPedidoMesa(numeroMesa);
     
       Iterator iterador = lista.iterator();
       while(iterador.hasNext()){
             Pedido p = (Pedido) iterador.next();
                if(p.getImporte() >= 0){
                total=total + p.getImporte();
                }
            
         } 
     
        return total;
    
    }
    
    
}
