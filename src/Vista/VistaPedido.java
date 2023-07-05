
package Vista;



import Modelo.Categoria;
import Modelo.Cliente;
import Modelo.Pedido;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class VistaPedido extends javax.swing.JFrame {

   
   
   public DefaultTableModel modelo;  
     
    public VistaPedido() {
        initComponents();
        modelo = new DefaultTableModel();
        modelo.addColumn("Cantidad");
        modelo.addColumn("idProducto");
        modelo.addColumn("Producto");
        modelo.addColumn("P.Unitario");
        modelo.addColumn("Subtotal");
        modelo.addColumn("Observacion");
        this.jTablePedido.setModel(modelo);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cBoxCategoriaId = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProducto = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaObserv = new javax.swing.JTextArea();
        btnAgregarProducto = new javax.swing.JButton();
        btnQuitarProducto = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePedido = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txtDniCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtApellidoCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtEmailCliente = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        btnLimpiarDatos = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cBoxTipo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cBoxEstado = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtDireccionCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNumPedido = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnCancelar.setText("Cancelar");

        btnGuardar.setText("Guardar");

        btnModificar.setText("Modificar");

        btnAnular.setText("Anular Pedido");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnular)
                .addContainerGap(357, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(btnAnular))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel2.setText("Categoria:");

        jTableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"012", "Empanadas", "Empanada de carne", "50", "15"},
                {"013", "Empanadas", "Empanada de pollo", "55", "16"},
                {"014", "Empanadas", "Empanada de queso", "60", "21"}
            },
            new String [] {
                "idProducto", "Categoria", "Producto", "Precio", "Stock"
            }
        ));
        jScrollPane1.setViewportView(jTableProducto);

        jLabel3.setText("Cantidad: ");

        txtCantidad.setText("1");

        jLabel4.setText("Observ: ");

        txtAreaObserv.setColumns(20);
        txtAreaObserv.setRows(5);
        jScrollPane2.setViewportView(txtAreaObserv);

        btnAgregarProducto.setText("Agregar >>");

        btnQuitarProducto.setText("<< Quitar");

        jTablePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "idProducto", "Producto", "P.Unitario", "Subtotal", "Observaciones"
            }
        ));
        jScrollPane3.setViewportView(jTablePedido);

        jLabel5.setText("Subtotal:");

        txtSubtotal.setText("0");

        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jSeparator2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("DNI Cliente: ");

        btnBuscarCliente.setText("Buscar");

        btnNuevoCliente.setText("Nuevo");

        jLabel6.setText("Apellido:");

        jLabel7.setText("Nombre:");

        jLabel8.setText("Telefono:");

        jLabel9.setText("Direccion:");

        btnLimpiarDatos.setText("Limpiar");

        jLabel11.setText("Tipo:");

        cBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "individual", "empresarial" }));

        jLabel12.setText("Estado:");

        cBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "activo", "inactivo" }));

        jLabel13.setText("Email :");

        jLabel10.setText("Pedido:");

        jLabel14.setText("Cliente");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(btnAgregarProducto)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 21, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnQuitarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cBoxCategoriaId, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(2, 2, 2)
                        .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefonoCliente)
                                    .addComponent(txtNombreCliente)
                                    .addComponent(txtDireccionCliente)
                                    .addComponent(txtEmailCliente)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtApellidoCliente))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDniCliente))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnLimpiarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(cBoxCategoriaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(7, 7, 7)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarProducto)
                        .addGap(20, 20, 20)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnQuitarProducto)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSubtotal)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarCliente)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(txtEmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(cBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(cBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLimpiarDatos)
                                    .addComponent(btnNuevoCliente))
                                .addGap(53, 53, 53))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
     public void cargarBotones(String accion) {

        if (accion.equals("guardar")) {
            btnGuardar.setVisible(true);
            btnModificar.setVisible(false);
            btnAnular.setVisible(false);
        } else {

            if (accion.equals("modificar")) {
                btnGuardar.setVisible(false);
                btnModificar.setVisible(true);
                btnAnular.setVisible(false);

            } else {

                if (accion.equals("eliminar")) {

                    btnGuardar.setVisible(false);
                    btnModificar.setVisible(false);
                    btnAnular.setVisible(true);
                }
            }

        }

    }
    
      public void limpiarDetalleProducto() {

       
        this.cBoxCategoriaId.setSelectedIndex(0);
        this.txtAreaObserv.setText("");
        this.txtCantidad.setText("1");
        
    }
      public void limpiarDatosCliente(){
          
        this.txtIdCliente.setText("");  
        this.txtDniCliente.setText("");
        this.txtApellidoCliente.setText("");
        this.txtNombreCliente.setText("");
        this.txtEmailCliente.setText("");
        this.txtTelefonoCliente.setText("");
        this.txtDireccionCliente.setText("");
        this.cBoxTipo.setSelectedIndex(0);
        this.cBoxEstado.setSelectedIndex(0);
     
      }
      
      public void limpiarTablaPedido(){
      
            modelo.setNumRows(0);
            this.txtSubtotal.setText("0");
      }
     
     public void cargarComboBoxPedido(int idCategoria, ArrayList lista) {
           
            this.cBoxCategoriaId.removeAllItems(); 
            Iterator iterador = lista.iterator();
            while(iterador.hasNext()){
                 Categoria c = (Categoria) iterador.next();        
                 this.cBoxCategoriaId.addItem(c);
                 if (c.getIdCategoria()== idCategoria){
                 this.cBoxCategoriaId.setSelectedItem(c);
                 }
            } 
       
       }  
     
      public void cargarTablaProducto(TableModel listadoProducto){
    
            jTableProducto.setModel(listadoProducto);
     
      }
     
      public void cargarTablaPrePedido(int idProducto,String producto,Double precio,int cantidad,String observacion){
      
             Object[] filas = new Object[6];
             filas[0]=cantidad;
             filas[1]=idProducto; 
             filas[2]=producto;
             filas[3]=precio;
             filas[4]=cantidad*precio;
             filas[5]=observacion;
            
             modelo.addRow(filas);
       
      }
      public void actualizarSubtotal(double importe){

            Double subTotal = Double.parseDouble(this.txtSubtotal.getText())+ importe;
            this.txtSubtotal.setText(String.valueOf(subTotal));
      }
     
      
      public void cargarNumPedido(int numPedido) {

          this.txtNumPedido.setText(String.valueOf(numPedido));

    }
      
      
      
      public void cargarCliente(ArrayList<Cliente> lista){
       
        for (Cliente c : lista) {
            this.txtIdCliente.setText(String.valueOf(c.getIdCliente()));
            this.txtDniCliente.setText(c.getDni());
            this.txtApellidoCliente.setText(c.getApellido());
            this.txtNombreCliente.setText(c.getNombre());
            this.txtDireccionCliente.setText(c.getDireccion());
            this.txtTelefonoCliente.setText(c.getTelefono());
            this.txtEmailCliente.setText(c.getEmail());
            this.cBoxEstado.setSelectedItem(c.getEstado());
            this.cBoxTipo.setSelectedItem(c.getTipo());
        }
      }
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarProducto;
    public javax.swing.JButton btnAnular;
    public javax.swing.JButton btnBuscarCliente;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnLimpiarDatos;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnNuevoCliente;
    public javax.swing.JButton btnQuitarProducto;
    public javax.swing.JComboBox<Categoria> cBoxCategoriaId;
    private javax.swing.JComboBox<String> cBoxEstado;
    private javax.swing.JComboBox<String> cBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTablePedido;
    private javax.swing.JTable jTableProducto;
    private javax.swing.JTextField txtApellidoCliente;
    private javax.swing.JTextArea txtAreaObserv;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtDniCliente;
    private javax.swing.JTextField txtEmailCliente;
    public javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNumPedido;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTelefonoCliente;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTextField getTxtApellidoCliente() {
        return txtApellidoCliente;
    }

    public void setTxtApellidoCliente(javax.swing.JTextField txtApellidoCliente) {
        this.txtApellidoCliente = txtApellidoCliente;
    }

    public javax.swing.JTextArea getTxtAreaObserv() {
        return txtAreaObserv;
    }

    public void setTxtAreaObserv(javax.swing.JTextArea txtAreaObserv) {
        this.txtAreaObserv = txtAreaObserv;
    }

    public javax.swing.JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(javax.swing.JTextField txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public javax.swing.JTextField getTxtDireccionCliente() {
        return txtDireccionCliente;
    }

    public void setTxtDireccionCliente(javax.swing.JTextField txtDireccionCliente) {
        this.txtDireccionCliente = txtDireccionCliente;
    }

    public javax.swing.JTextField getTxtDniCliente() {
        return txtDniCliente;
    }

    public void setTxtDniCliente(javax.swing.JTextField txtDniCliente) {
        this.txtDniCliente = txtDniCliente;
    }

    public javax.swing.JTextField getTxtEmailCliente() {
        return txtEmailCliente;
    }

    public void setTxtEmailCliente(javax.swing.JTextField txtEmailCliente) {
        this.txtEmailCliente = txtEmailCliente;
    }

    public javax.swing.JTextField getTxtIdCliente() {
        return txtIdCliente;
    }

    public void setTxtIdCliente(javax.swing.JTextField txtIdCliente) {
        this.txtIdCliente = txtIdCliente;
    }

    public javax.swing.JTextField getTxtNombreCliente() {
        return txtNombreCliente;
    }

    public void setTxtNombreCliente(javax.swing.JTextField txtNombreCliente) {
        this.txtNombreCliente = txtNombreCliente;
    }

    public javax.swing.JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public void setTxtSubtotal(javax.swing.JTextField txtSubtotal) {
        this.txtSubtotal = txtSubtotal;
    }

    public javax.swing.JTextField getTxtTelefonoCliente() {
        return txtTelefonoCliente;
    }

    public void setTxtTelefonoCliente(javax.swing.JTextField txtTelefonoCliente) {
        this.txtTelefonoCliente = txtTelefonoCliente;
    }

    public javax.swing.JComboBox<String> getcBoxEstado() {
        return cBoxEstado;
    }

    public void setcBoxEstado(javax.swing.JComboBox<String> cBoxEstado) {
        this.cBoxEstado = cBoxEstado;
    }

    public javax.swing.JComboBox<String> getcBoxTipo() {
        return cBoxTipo;
    }

    public void setcBoxTipo(javax.swing.JComboBox<String> cBoxTipo) {
        this.cBoxTipo = cBoxTipo;
    }

    public javax.swing.JTable getjTablePedido() {
        return jTablePedido;
    }

    public void setjTablePedido(javax.swing.JTable jTablePedido) {
        this.jTablePedido = jTablePedido;
    }

    public javax.swing.JTable getjTableProducto() {
        return jTableProducto;
    }

    public void setjTableProducto(javax.swing.JTable jTableProducto) {
        this.jTableProducto = jTableProducto;
    }

    public javax.swing.JComboBox<Categoria> getcBoxCategoriaId() {
        return cBoxCategoriaId;
    }

    public void setcBoxCategoriaId(javax.swing.JComboBox<Categoria> cBoxCategoriaId) {
        this.cBoxCategoriaId = cBoxCategoriaId;
    }

    public javax.swing.JTextField getTxtNumPedido() {
        return txtNumPedido;
    }

    public void setTxtNumPedido(javax.swing.JTextField txtNumPedido) {
        this.txtNumPedido = txtNumPedido;
    }



    
}
