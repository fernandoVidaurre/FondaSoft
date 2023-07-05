package Modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class BD {

    private Connection con;
    private int ultimoRegistro;
    
    // Datos de acceso a la Base de Datos
    private final String base = "fonda_soft";
    //private final String user = "pipo";
    //private final String password = "Valen.2020";
    private final String user = "root";
    private final String password = "root";
    private final String url = "jdbc:mysql://localhost:3306/" + base + "?useSSL=false";

    // Constructor
    public BD() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("se conecto");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println("no se pudo conectar");
            JOptionPane.showMessageDialog(null, "Error en conexion con BD");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void cerrarConexionBD() {

        try {
            con.close();
            System.out.println("Conexion Cerrada");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    
    // Operaciones sobre Persona
    public boolean altaPersona(Persona p) {
        boolean salida;
        PreparedStatement ps;

        String sql = " INSERT INTO persona(dni,apellido,nombre,direccion,telefono,email) VALUES (?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getDni());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getDireccion());
            ps.setString(5, p.getTelefono());
            ps.setString(6, p.getEmail());
            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                salida = true;
                // este codigo obtiene el personaId insertado
                ResultSet generatedKeys;
                generatedKeys = ps.getGeneratedKeys();

                if (generatedKeys.next()) {
                    setUltimoRegistro((int) generatedKeys.getLong(1)); // setter de esta clase BD
                    System.out.println(getUltimoRegistro());
                }
            } else {

                salida = false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {

            //cerrarConexionBD(); no cerramos la conexion por que se continua el uso desde la clase usuario
        }
        return salida;
    }

    public boolean editarPersona(Persona p) {
        boolean salida;
        PreparedStatement ps;
        String sql = " UPDATE persona SET dni=?,apellido=?,nombre=?,direccion=?,telefono=?,email=? "
                + "WHERE idPersona=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getDni());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getDireccion());
            ps.setString(5, p.getTelefono());
            ps.setString(6, p.getEmail());
            ps.setInt(7, p.getIdPersona());
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; // if else abreviado para almacenar true o false
            this.setUltimoRegistro(p.getIdPersona());

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {
            
            //no cerramos la conexion por que se continua el uso desde la clase usuario
            //cerrarConexionBD();

        }
        return salida;
    }

    public boolean bajaPersona(int idPersona) {
        boolean salida;
        PreparedStatement ps;
        String sql = "DELETE FROM persona WHERE idPersona=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPersona);
            int resultado = ps.executeUpdate();
            
            salida = resultado > 0;
        
        
        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {

            cerrarConexionBD();

        }
        return salida;
    }

    public ArrayList listarPersona() {

        ArrayList lista = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM persona ORDER BY idPersona ASC";

        try {

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = new Persona(rs.getInt("idPersona"), rs.getString("dni"), rs.getString("apellido"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("email"));
                lista.add(p);
            }

            return lista;
        } catch (SQLException e) {

            System.err.println(e);
            return null;

        } finally {

            cerrarConexionBD();

        }

    }

    public Persona verificarPersona(String dni) {
       
        Persona persona=null;
        
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM persona WHERE dni=?";

         try {

            ps = con.prepareStatement(sql);
            ps.setString(1,dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                 persona = new Persona(rs.getInt("idPersona"), rs.getString("dni"), rs.getString("apellido"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("email"));
             
            }else{
            
            persona = new Persona();
            persona.setIdPersona(-1);
            
            }

            
           } catch (SQLException e) {

            System.err.println(e);
            

        } finally {

            cerrarConexionBD();

        }
       
      return persona;
      
    }
    
    // metodo de uso general
    public int getUltimoRegistro() {
        return ultimoRegistro;
    }

    public void setUltimoRegistro(int ultimoRegistro) {
        this.ultimoRegistro = ultimoRegistro;
    }

   
    // Operaciones sobre Iniciar Sesion
    public Usuario loginUsuario(Usuario u){
        
       
        Usuario salida = new Usuario();
        PreparedStatement ps;
        String sql = "SELECT * FROM persona "
                +" INNER JOIN usuario "
                +" ON persona.idPersona = usuario.personaId "
                +" WHERE usuario = ?  AND password = ?";
                
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getPassword());
            ResultSet rs = ps.executeQuery();
            
            
                       
            if(rs.next())
            {
                 
               salida = new Usuario(Integer.parseInt(rs.getString("idUsuario")),
                       rs.getString("usuario"),rs.getString("password"),
                       rs.getString("estado"),Integer.parseInt(rs.getString("idPersona")),
                       rs.getString("dni"), rs.getString("apellido"), rs.getString("nombre"),
                       rs.getString("direccion"), rs.getString("telefono"), rs.getString("email") );
                    
            }
            
            
            //**
        } catch (SQLException e) {
            System.err.println(e);
            // salida =new Usuario();
             salida.setIdUsuario(0);
            
        } finally {

            cerrarConexionBD();
             
        }
    return salida; 
    }
   

    // Operaciones Sobre Cliente
    public boolean altaCliente(Cliente c){
     boolean salida;
     boolean PersonaSalida;
        Persona p = new Persona(c.getIdPersona(), c.getDni(), c.getApellido(), c.getNombre(), c.getDireccion(), c.getTelefono(), c.getEmail());
        
        // preguntamos si esta dando de alta a un cliente cuya persona no existe
        if (p.getIdPersona() == 0) {
            
            PersonaSalida=altaPersona(p);
        }else{
            // aqui entra por que esta dando de alta como cliente a una persona que existe por lo cual se procede como modificar
        
            PersonaSalida=editarPersona(p);
        
        }
        
        
        
        if (PersonaSalida) {
            System.out.println("entro a dar alta usuario");
    
            PreparedStatement ps;

            String sql = " INSERT INTO cliente(tipo,estado,personaId) VALUES (?,?,?)";

            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, c.getTipo());
                ps.setString(2, c.getEstado());
                ps.setInt(3, getUltimoRegistro());

                int resultado = ps.executeUpdate();

                salida = resultado > 0; // esto es if else abreviado para almacenar true o false

            } catch (SQLException e) {
                System.err.println(e);
                salida = false;
            } finally {

                cerrarConexionBD();

            }

        } else {

            salida = false;

        }
        return salida;
    
    
    }
    
    public ArrayList listarCliente(int id, String dni){
    
        ArrayList lista = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql;
        
        if (dni.equals("") && id==0) {
            
            System.out.println("busca todo");
            
             sql = "SELECT * FROM persona "
                +" INNER JOIN cliente " 
                +" ON persona.idPersona = cliente.personaId "
                +" ORDER BY cliente.idCliente ASC";
              
        }else{
            if(id > 0){
            
             
              sql = "SELECT * FROM persona "
                +" INNER JOIN cliente " 
                +" ON persona.idPersona = cliente.personaId "
                +" WHERE cliente.idCliente= "+id         
                +" ORDER BY cliente.idCliente ASC";
            
            }else{
             
              sql = "SELECT * FROM persona "
                +" INNER JOIN cliente " 
                +" ON persona.idPersona = cliente.personaId "
                +" WHERE persona.dni= "+dni         
                +" ORDER BY cliente.idCliente ASC";
            
            }
        
        
        }

        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Cliente c = new Cliente(Integer.parseInt(rs.getString("idCliente")),
                       rs.getString("tipo"),rs.getString("estado"),Integer.parseInt(rs.getString("idPersona")),
                       rs.getString("dni"), rs.getString("apellido"), rs.getString("nombre"),
                       rs.getString("direccion"), rs.getString("telefono"), rs.getString("email") );
                
                
                lista.add(c);
            }

            return lista;
        } catch (SQLException e) {

            System.err.println(e);
            return null;

        } finally {

            cerrarConexionBD();

        }
        
    
    }

    public Cliente buscarCliente(int idCliente) {
    
        Cliente cliente = null;
        PreparedStatement ps;
        ResultSet rs;
        String sql ="SELECT * FROM persona "
                +" INNER JOIN cliente " 
                +" ON persona.idPersona = cliente.personaId "
                +" WHERE cliente.idCliente = ?";
        
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,Integer.toString(idCliente));
            rs =ps.executeQuery();
            
            if(rs.next())
            {
                 cliente = new Cliente(Integer.parseInt(rs.getString("idCliente")),
                       rs.getString("tipo"),rs.getString("estado"),Integer.parseInt(rs.getString("idPersona")),
                       rs.getString("dni"), rs.getString("apellido"), rs.getString("nombre"),
                       rs.getString("direccion"), rs.getString("telefono"), rs.getString("email") );
                
              
            }
            
            
        } catch (SQLException e) {
            System.err.println(e);
            cliente = new Cliente();
            cliente.setIdCliente(0);
            
        } finally {

             cerrarConexionBD();

        }
        
        
        
        return cliente;
    }

    public boolean modificarCliente(Cliente c) {
        boolean salida;
        Persona p = new Persona(c.getIdPersona(), c.getDni(), c.getApellido(), c.getNombre(), c.getDireccion(), c.getTelefono(), c.getEmail());
        if (editarPersona(p)) {
                
            PreparedStatement ps;
            
           

           String sql = " UPDATE cliente SET tipo=?,estado=?,personaId=? WHERE idCliente=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getTipo());
            ps.setString(2, c.getEstado());
            ps.setInt(3, c.getIdPersona());
            ps.setInt(4, c.getIdCliente());
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; // if else abreviado para almacenar true o false
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {

                cerrarConexionBD();

            }

        } else {

            salida = false;

        }
        return salida;
     
    }

    public boolean eliminarCliente(Cliente c) {
      boolean salida;
      PreparedStatement ps;
      String sql = "DELETE FROM cliente WHERE idCliente=?";
       try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, c.getIdCliente());
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; 
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {

                cerrarConexionBD();

            }
      
      
      
      return salida;  
    }

    

    // Operaciones Sobre Usuario
    public boolean altaUsuario(Usuario u) {
        boolean salida;
        boolean PersonaSalida;
        
        Persona p = new Persona(u.getIdPersona(), u.getDni(), u.getApellido(), u.getNombre(), u.getDireccion(), u.getTelefono(), u.getEmail());
        
         if (p.getIdPersona() == 0) {
            
            PersonaSalida=altaPersona(p);
        }else{
            // aqui entra por que esta dando de alta como cliente a una persona que existe por lo cual se procede como modificar
        
            PersonaSalida=editarPersona(p);
        
        }
        
        
        if (PersonaSalida) {
            System.out.println("entro a dar alta usuario");
            PreparedStatement ps;

            String sql = " INSERT INTO usuario(usuario,password,estado,personaId) VALUES (?,?,?,?)";

            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, u.getUsuario());
                ps.setString(2, u.getPassword());
                ps.setString(3, u.getEstado());
                ps.setInt(4, getUltimoRegistro());

                int resultado = ps.executeUpdate();

                salida = resultado > 0; // esto es if else abreviado para almacenar true o false

            } catch (SQLException e) {
                System.err.println(e);
                salida = false;
            } finally {

                cerrarConexionBD();

            }

        } else {

            salida = false;

        }
        return salida;
    }
    
    public ArrayList listarUsuario(int idUsuario, String dni, String nombreUsuario) {
     
        ArrayList lista = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql;
        
        if (dni.equals("") && idUsuario==0 && nombreUsuario.equals("")) {
            
          //  System.out.println("busca todo");
            
             sql = "SELECT * FROM persona "
                +" INNER JOIN usuario " 
                +" ON persona.idPersona = usuario.personaId "
                +" ORDER BY usuario.idUsuario ASC";
              
        }else{
            if(idUsuario > 0){
            
             
              sql = "SELECT * FROM persona "
                +" INNER JOIN usuario " 
                +" ON persona.idPersona = usuario.personaId "
                +" WHERE usuario.idUsuario= "+idUsuario         
                +" ORDER BY usuario.idUsuario ASC";
            
            }else{
             
                if(!dni.equals("")){
                
                sql = "SELECT * FROM persona "
                +" INNER JOIN usuario " 
                +" ON persona.idPersona = usuario.personaId "
                +" WHERE persona.dni= "+dni         
                +" ORDER BY usuario.idUsuario ASC";
                            
                }else{
                
                 sql = "SELECT * FROM persona "
                +" INNER JOIN usuario " 
                +" ON persona.idPersona = usuario.personaId "
                +" WHERE usuario.usuario= '"+nombreUsuario+"'"      // aqui tuve que usar comillas simples por que no me tomaba sin comillas   
                +" ORDER BY usuario.idUsuario ASC";
   
                }
            }
            
        }


        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Usuario u = new Usuario(Integer.parseInt(rs.getString("idUsuario")),
                       rs.getString("usuario"),rs.getString("password"),rs.getString("estado"),Integer.parseInt(rs.getString("idPersona")),
                       rs.getString("dni"), rs.getString("apellido"), rs.getString("nombre"),
                       rs.getString("direccion"), rs.getString("telefono"), rs.getString("email") );
                
                
                lista.add(u);
            }

            return lista;
        } catch (SQLException e) {

            System.err.println(e);
            return null;

        } finally {

            cerrarConexionBD();

        }
        
        
        
    }

    public Usuario buscarUsuario(int idUsuario) {
      
        
        Usuario usuario = null;
        PreparedStatement ps;
        ResultSet rs;
        String sql ="SELECT * FROM persona "
                +" INNER JOIN usuario " 
                +" ON persona.idPersona = usuario.personaId "
                +" WHERE usuario.idUsuario = ?";
        
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,Integer.toString(idUsuario));
            rs =ps.executeQuery();
            
            if(rs.next())
            {
                 usuario = new Usuario(Integer.parseInt(rs.getString("idUsuario")),
                       rs.getString("usuario"),rs.getString("password"),rs.getString("estado"),Integer.parseInt(rs.getString("idPersona")),
                       rs.getString("dni"), rs.getString("apellido"), rs.getString("nombre"),
                       rs.getString("direccion"), rs.getString("telefono"), rs.getString("email") );
                
              
            }
            
            
        } catch (SQLException e) {
            System.err.println(e);
            usuario = new Usuario();
            usuario.setIdUsuario(0);
            
        } finally {

             cerrarConexionBD();

        }
        
        
        
        return usuario;
    
    
    
    
    }

    public boolean modificarUsuario(Usuario u) {
       
         boolean salida;
        Persona p = new Persona(u.getIdPersona(), u.getDni(), u.getApellido(), u.getNombre(), u.getDireccion(), u.getTelefono(), u.getEmail());
        if (editarPersona(p)) {
                
            PreparedStatement ps;
            
           

           String sql = " UPDATE usuario SET usuario=?,password=?,estado=?,personaId=? WHERE idUsuario=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEstado());
            ps.setInt(4, u.getIdPersona());
            ps.setInt(5, u.getIdUsuario());
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; // if else abreviado para almacenar true o false
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {

                cerrarConexionBD();

            }

        } else {

            salida = false;

        }
        return salida;
     
        
        
        
    }

    public boolean eliminarUsuario(Usuario u) {
    
      boolean salida;
      PreparedStatement ps;
      String sql = "DELETE FROM usuario WHERE idUsuario=?";
       try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, u.getIdUsuario());
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; 
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {

                cerrarConexionBD();

            }
      
      
      
      return salida; 
        
        
    }

   
    //Operaciones Sobre Mozo
    public boolean altaMozo(Mozo m) {
     
     boolean salida;
     boolean PersonaSalida;
        Persona p = new Persona(m.getIdPersona(), m.getDni(), m.getApellido(), m.getNombre(), m.getDireccion(), m.getTelefono(), m.getEmail());
        
        // preguntamos si esta dando de alta a un cliente cuya persona no existe
        if (p.getIdPersona() == 0) {
            
            PersonaSalida=altaPersona(p);
        }else{
            // aqui entra por que esta dando de alta como mozo a una persona que existe por lo cual se procede como modificar
        
            PersonaSalida=editarPersona(p);
        
        }
        
        
        
        if (PersonaSalida) {
            System.out.println("entro a dar alta ");
    
            PreparedStatement ps;

            String sql = " INSERT INTO mozo(codigo,tipo,estado,personaId) VALUES (?,?,?,?)";

            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, m.getCodigo());
                ps.setString(2, m.getTipo());
                ps.setString(3, m.getEstado());
                ps.setInt(4, getUltimoRegistro()); 

                int resultado = ps.executeUpdate();

                salida = resultado > 0; // esto es if else abreviado para almacenar true o false

            } catch (SQLException e) {
                System.err.println(e);
                salida = false;
            } finally {

                cerrarConexionBD();

            }

        } else {

            salida = false;

        }
        return salida;
    
    
        
        
    }

    public ArrayList listarMozo(int idMozo, String dni, String codigo) {
   
        ArrayList lista = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql;
        
        if (dni.equals("") && idMozo==0 && codigo.equals("")) {
            
          //  System.out.println("busca todo");
            
             sql = "SELECT * FROM persona "
                +" INNER JOIN mozo " 
                +" ON persona.idPersona = mozo.personaId "
                +" ORDER BY mozo.idMozo ASC";
              
        }else{
            if(idMozo > 0){
            
             
              sql = "SELECT * FROM persona "
                +" INNER JOIN mozo " 
                +" ON persona.idPersona = mozo.personaId "
                +" WHERE mozo.idMozo= "+idMozo         
                +" ORDER BY mozo.idMozo ASC";
            
            }else{
             
                if(!dni.equals("")){
                
                sql = "SELECT * FROM persona "
                +" INNER JOIN mozo " 
                +" ON persona.idPersona = mozo.personaId "
                +" WHERE persona.dni= "+dni         
                +" ORDER BY mozo.idMozo ASC";
                            
                }else{
                
                 sql = "SELECT * FROM persona "
                +" INNER JOIN mozo " 
                +" ON persona.idPersona = mozo.personaId "
                +" WHERE mozo.codigo= '"+codigo+"'"
                +" ORDER BY mozo.idMozo ASC";
   
                }
            }
            
        }


        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
              Mozo m = new Mozo(Integer.parseInt(rs.getString("idMozo")),rs.getString("codigo"),
                       rs.getString("tipo"),rs.getString("estado"),Integer.parseInt(rs.getString("idPersona")),
                       rs.getString("dni"), rs.getString("apellido"), rs.getString("nombre"),
                       rs.getString("direccion"), rs.getString("telefono"), rs.getString("email") );
                
              
                
                lista.add(m);
            }

            return lista;
        } catch (SQLException e) {

            System.err.println(e);
            return null;

        } finally {

            cerrarConexionBD();

        }
        
        
        
        
        
    }

    public Mozo buscarMozo(int idMozo) {
 
        Mozo mozo = null;
        PreparedStatement ps;
        ResultSet rs;
        String sql ="SELECT * FROM persona "
                +" INNER JOIN mozo " 
                +" ON persona.idPersona = mozo.personaId "
                +" WHERE mozo.idMozo = ?";
        
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,Integer.toString(idMozo));
            rs =ps.executeQuery();
            
            if(rs.next())
            {
                 mozo = new Mozo(Integer.parseInt(rs.getString("idMozo")),rs.getString("codigo"),
                       rs.getString("tipo"),rs.getString("estado"),Integer.parseInt(rs.getString("idPersona")),
                       rs.getString("dni"), rs.getString("apellido"), rs.getString("nombre"),
                       rs.getString("direccion"), rs.getString("telefono"), rs.getString("email") );
                
              
            }
            
            
        } catch (SQLException e) {
            System.err.println(e);
            mozo = new Mozo();
            mozo.setIdMozo(0);
            
        } finally {

             cerrarConexionBD();

        }
        
        
        
        return mozo;
        
    }

    public boolean modificarMozo(Mozo m) {
     
        boolean salida;
        Persona p = new Persona(m.getIdPersona(),m.getDni(), m.getApellido(), m.getNombre(), m.getDireccion(), m.getTelefono(), m.getEmail());
        if (editarPersona(p)) {
                
            PreparedStatement ps;
            
           

           String sql = " UPDATE mozo SET codigo=?,tipo=?,estado=?,personaId=? WHERE idMozo=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getCodigo());
            ps.setString(2, m.getTipo());
            ps.setString(3, m.getEstado());
            ps.setInt(4, m.getIdPersona());
            ps.setInt(5, m.getIdMozo());
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; // if else abreviado para almacenar true o false
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {

                cerrarConexionBD();

            }

        } else {

            salida = false;

        }
        return salida;
     
    }

    public boolean eliminarMozo(Mozo m) {
     
      boolean salida;
      PreparedStatement ps;
      String sql = "DELETE FROM mozo WHERE idMozo=?";
       try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, m.getIdMozo());
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; 
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {

                cerrarConexionBD();

            }
      
      
      
      return salida; 
        
    }

   
    //Operaciones Sobre Categoria
    public boolean altaCategoria(Categoria c) {

     boolean salida;
        
            PreparedStatement ps;

            String sql = " INSERT INTO categoria(nombre,descripcion) VALUES (?,?)";

            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, c.getNombre());
                ps.setString(2, c.getDescripcion());
                
                int resultado = ps.executeUpdate();

                salida = resultado > 0; // esto es if else abreviado para almacenar true o false

            } catch (SQLException e) {
                System.err.println(e);
                salida = false;
            } finally {

                cerrarConexionBD();

            }
        return salida;
        
    }

    public ArrayList listarCategoria(int idCategoria, String nombre) {

        ArrayList lista = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql;
        
        if (nombre.equals("") && idCategoria==0) {
            
             
             sql = "SELECT * FROM categoria ORDER BY categoria.idCategoria ASC";
               
                 
              
        }else{
            if(idCategoria > 0){
            
             
              sql = "SELECT * FROM categoria WHERE categoria.idCategoria= "+idCategoria
                   +" ORDER BY categoria.idCategoria ASC";
            
            }else{
             
              sql = "SELECT * FROM categoria "
                +" WHERE categoria.nombre= '"+nombre+"'"         
                +" ORDER BY categoria.idCategoria ASC";
            
            }
        
        
        }

        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Categoria c = new Categoria(Integer.parseInt(rs.getString("idCategoria")),rs.getString("nombre"),rs.getString("descripcion"));
                
                
                
                lista.add(c);
            }

            return lista;
        } catch (SQLException e) {

            System.err.println(e);
            return null;

        } finally {

            cerrarConexionBD();

        }
        
        
    }

    public Categoria buscarCategoria(int idCategoria) {
   
        Categoria categoria = null;
        PreparedStatement ps;
        ResultSet rs;
        String sql ="SELECT * FROM categoria "
                   +" WHERE categoria.idCategoria = ?";
        
       
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,Integer.toString(idCategoria));
            rs =ps.executeQuery();
            
            if(rs.next())
            {
               
                  categoria = new Categoria(Integer.parseInt(rs.getString("idCategoria")),rs.getString("nombre"),rs.getString("descripcion"));
                  
             
            }
            
            
        } catch (SQLException e) {
            System.err.println(e);
            categoria = new Categoria();
            categoria.setIdCategoria(0);
            
        } finally {

             cerrarConexionBD();

        }
        
        
        
        return categoria; 
        
    }

    public boolean modificarCategoria(Categoria c) {

        boolean salida;
       
                
            PreparedStatement ps;
            
           

           String sql = " UPDATE categoria SET nombre=?,descripcion=? WHERE idCategoria=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getIdCategoria());
            
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; // if else abreviado para almacenar true o false
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
       
        } finally {

                cerrarConexionBD();

            }

        
        return salida;
        
    }

    public boolean eliminarCategoria(Categoria c) {
      
      boolean salida;
      PreparedStatement ps;
      String sql = "DELETE FROM categoria WHERE idCategoria=?";
       try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, c.getIdCategoria());
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; 
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {

                cerrarConexionBD();

            }
      
      
      
      return salida; 
        
        
    }

    
    
    //Operaciones Sobre Producto
    public boolean altaProducto(Producto p) {
             
      boolean salida;
        
            PreparedStatement ps;

            String sql = " INSERT INTO producto(codigo,nombre,stock,precio,imagen,tipo,categoriaId) VALUES (?,?,?,?,?,?,?)";

            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, p.getCodigo());
                ps.setString(2,p.getNombre());
                ps.setInt(3,p.getStock());
                ps.setDouble(4,p.getPrecio());
                ps.setString(5,p.getImagen());
                ps.setString(6,p.getTipo());
                ps.setInt(7,p.getIdCategoria());
                                
                int resultado = ps.executeUpdate();

                salida = resultado > 0; // esto es if else abreviado para almacenar true o false

            } catch (SQLException e) {
                System.err.println(e);
                salida = false;
            } finally {

                cerrarConexionBD();

            }
        return salida;
        
    }

    public ArrayList listarProducto(int idProducto, String codigo, String nombre) {
   
        ArrayList lista = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql;
        
        if (nombre.equals("") && idProducto==0 && codigo.equals("")) {
            
          //  System.out.println("busca todo");
            
             sql = "SELECT * FROM producto "
                +" INNER JOIN categoria " 
                +" ON producto.categoriaId = categoria.idCategoria "
                +" ORDER BY producto.idProducto ASC";
              
        }else{
            if(idProducto > 0){
            
             
              sql = "SELECT * FROM producto "
                +" INNER JOIN categoria " 
                +" ON producto.categoriaId = categoria.idCategoria "
                +" WHERE producto.idProducto= "+idProducto         
                +" ORDER BY produtcto.idProducto ASC";
            
            }else{
             
                if(!nombre.equals("")){
                
                sql = "SELECT * FROM producto "
                +" INNER JOIN categoria " 
                +" ON producto.categoriaId = categoria.idCategoria "
                +" WHERE producto.nombre like '"+nombre+"%'"         
                +" ORDER BY producto.idProducto ASC";
                            
                }else{
                
                 sql = "SELECT * FROM producto "
                +" INNER JOIN categoria " 
                +" ON producto.categoriaId = categoria.idCategoria "
                +" WHERE producto.codigo= '"+codigo+"'"
                +" ORDER BY producto.idProducto ASC";
   
                }
            }
            
        }


        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
             
                Producto p = new Producto(Integer.parseInt(rs.getString("idProducto")),rs.getString("codigo"),rs.getString("nombre"),rs.getInt("stock"),rs.getDouble("precio"),rs.getString("imagen"),rs.getString("tipo"),rs.getInt("categoriaId"));
                
               
              
                
                lista.add(p);
            }

            return lista;
        } catch (SQLException e) {

            System.err.println(e);
            return null;

        } finally {

            cerrarConexionBD();

        }
        
        
        
        
        
    }
    
     public ArrayList listarProductoPorCategoria(int idCategoria) {
    
        ArrayList lista = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql;
        
               
        
            
             sql = "SELECT * FROM producto "
                +" INNER JOIN categoria " 
                +" ON producto.categoriaId = categoria.idCategoria "
                +" WHERE producto.categoriaId= "+idCategoria      
                +" ORDER BY producto.idProducto ASC";
              
        


        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
             
                Producto p = new Producto(Integer.parseInt(rs.getString("idProducto")),rs.getString("codigo"),rs.getString("nombre"),rs.getInt("stock"),rs.getDouble("precio"),rs.getString("imagen"),rs.getString("tipo"),rs.getInt("categoriaId"));
                
               
              
                
                lista.add(p);
            }

            return lista;
        } catch (SQLException e) {

            System.err.println(e);
            return null;

        } finally {

            cerrarConexionBD();

        }
        
         
         
         
    }
    

    public Producto buscarProducto(int idProducto) {
    
        
        Producto producto = null;
        PreparedStatement ps;
        ResultSet rs;
        String sql ="SELECT * FROM producto "
                   +" WHERE producto.idProducto = ?";
        
       
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,Integer.toString(idProducto));
            rs =ps.executeQuery();
            
            if(rs.next())
            {
                  producto = new Producto(Integer.parseInt(rs.getString("idProducto")),rs.getString("codigo"),rs.getString("nombre"),rs.getInt("stock"),rs.getDouble("precio"),rs.getString("imagen"),rs.getString("tipo"),rs.getInt("categoriaId"));
               
        
            }
            
            
        } catch (SQLException e) {
            System.err.println(e);
            producto = new Producto();
            producto.setIdProducto(0);
            
        } finally {

             cerrarConexionBD();

        }
        
        
        
        return producto; 
        
        
    }

    public boolean modificarProducto(Producto p) {

         boolean salida;
       
                
            PreparedStatement ps;
            
           

           String sql = " UPDATE producto SET codigo=?,nombre=?,stock=?,precio=?,imagen=?,tipo=?,categoriaId=? WHERE idProducto=?";
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, p.getCodigo());
                ps.setString(2,p.getNombre());
                ps.setInt(3,p.getStock());
                ps.setDouble(4,p.getPrecio());
                ps.setString(5,p.getImagen());
                ps.setString(6,p.getTipo());
                ps.setInt(7,p.getIdCategoria());
                ps.setInt(8,p.getIdProducto());
            
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; // if else abreviado para almacenar true o false
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
       
        } finally {

                cerrarConexionBD();

            }

        
        return salida;
        
    }

    public boolean eliminarProducto(Producto p) {
        
     boolean salida;
      PreparedStatement ps;
      String sql = "DELETE FROM producto WHERE idProducto=?";
       try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getIdProducto());
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; 
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {

                cerrarConexionBD();

            }
      
      
      
      return salida; 
        
        
    
    }
    

    //Operaciones Sobre Pedido
    
    public int altaPedido(Pedido p) {
             
      int salida=0;
        
            PreparedStatement ps;

            String sql = " INSERT INTO pedido(fecha,hora,importe,estado,idUsuario,idMesa,idCliente) VALUES (?,?,?,?,?,?,?)";

            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, p.getFecha());
                ps.setObject(2,p.getHora());
                ps.setDouble(3,p.getImporte());
                ps.setString(4,p.getEstado());
                ps.setInt(5,p.getIdUsuario());
                ps.setInt(6,p.getIdMesa());
                ps.setInt(7,p.getIdCliente());
                                
                int resultado = ps.executeUpdate();
                
                            
                if (resultado > 0) {

               
                // este codigo obtiene el personaId insertado
                ResultSet generatedKeys;
                generatedKeys = ps.getGeneratedKeys();

                if (generatedKeys.next()) {
                    setUltimoRegistro((int) generatedKeys.getLong(1)); // setter de esta clase BD
                    salida=getUltimoRegistro();
                }
                
            }
                
          

            } catch (SQLException e) {
                System.err.println(e);
                
            } finally {

                cerrarConexionBD();

            }
        return salida;
        
    }
    
    public boolean modificarPedido(int numPedido, Pedido pedido ) {

         boolean salida;
       
                
            PreparedStatement ps;
            
                                

           String sql = " UPDATE pedido SET fecha=?,hora=?,importe=?,estado=?,idUsuario=?,idMesa=?,idCliente=? WHERE numero=?";
        try {
             ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, pedido.getFecha());
                ps.setObject(2,pedido.getHora());
                ps.setDouble(3,pedido.getImporte());
                ps.setString(4,pedido.getEstado());
                ps.setInt(5,pedido.getIdUsuario());
                ps.setInt(6,pedido.getIdMesa());
                ps.setInt(7,pedido.getIdCliente());
                ps.setInt(8,numPedido);
                                
                int resultado = ps.executeUpdate();
            
               salida = resultado>0; // if else abreviado para almacenar true o false
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
       
        } finally {

                cerrarConexionBD();

            }

        
        return salida;
        
    }

    public ArrayList listarPedido(int numero, String dni) {
        
        ArrayList lista = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql;
        
        if (dni.equals("") && numero==0) {
            
             
             sql = "SELECT * FROM pedido ORDER BY pedido.numero DESC";
               
                 
              
        }else{
            if(numero > 0){
            
             
              sql = "SELECT * FROM pedido WHERE pedido.numero= "+numero
                   +" ORDER BY pedido.numero DESC";
            
            }else{
             
              sql = "SELECT * FROM pedido "
                +" INNER JOIN cliente"
                +" INNER JOIN persona"
                +" ON ((pedido.idCliente = cliente.idCliente)"
                +" AND (cliente.personaId = persona.idPersona))"      
                +" WHERE persona.dni= "+dni         
                +" ORDER BY pedido.numero DESC";
            
            }
        
        
        }

        //System.out.println(sql);
        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Pedido p = new Pedido(Integer.parseInt(rs.getString("numero")),LocalDate.parse(rs.getString("fecha")),LocalTime.parse(rs.getString("hora")),Double.parseDouble(rs.getString("importe")),rs.getString("estado"),Integer.parseInt(rs.getString("idUsuario")),Integer.parseInt(rs.getString("idMesa")),Integer.parseInt(rs.getString("idCliente")));
               // Pedido p = new Pedido(1,LocalDate.now() ,LocalTime.now(),50.22,"pedido",11,11,11);   
                      
                
                lista.add(p);
            }
            
           // System.out.print(lista);
            return lista;
        } catch (SQLException e) {

            System.err.println(e);
            return null;

        } finally {

            cerrarConexionBD();

        }
        
        
    }
    
     public ArrayList listarPedidoMesa(int numeroMesa) {
        
        ArrayList lista = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql;
        
       
                   
             
              sql = "SELECT * FROM pedido "
                +" INNER JOIN mesa"
                +" ON (pedido.idMesa = mesa.idMesa)"
                +" WHERE mesa.estado = 'abierta' AND mesa.numero = "+numeroMesa         
                +" ORDER BY pedido.numero DESC";
            
            
        
        
        

        //System.out.println(sql);
        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Pedido p = new Pedido(Integer.parseInt(rs.getString("numero")),LocalDate.parse(rs.getString("fecha")),LocalTime.parse(rs.getString("hora")),Double.parseDouble(rs.getString("importe")),rs.getString("estado"),Integer.parseInt(rs.getString("idUsuario")),Integer.parseInt(rs.getString("idMesa")),Integer.parseInt(rs.getString("idCliente")));
               // Pedido p = new Pedido(1,LocalDate.now() ,LocalTime.now(),50.22,"pedido",11,11,11);   
                      
                
                lista.add(p);
            }
            
           // System.out.print(lista);
            return lista;
        } catch (SQLException e) {

            System.err.println(e);
            return null;

        } finally {

            cerrarConexionBD();

        }
        
        
    }

    
    public Pedido buscarPedido(int numeroPedido) {
    
        Pedido pedido = null;
        PreparedStatement ps;
        ResultSet rs;
        String sql ="SELECT * FROM pedido "
                   +" WHERE pedido.numero = ?";
        
       
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,Integer.toString(numeroPedido));
            rs =ps.executeQuery();
            
            if(rs.next())
            {
               
                
                //   pedido = new Producto(Integer.parseInt(rs.getString("idProducto")),rs.getString("codigo"),rs.getString("nombre"),rs.getInt("stock"),rs.getDouble("precio"),rs.getString("imagen"),rs.getString("tipo"),rs.getInt("categoriaId"));
                pedido = new Pedido(Integer.parseInt(rs.getString("numero")),LocalDate.parse(rs.getString("fecha")),LocalTime.parse(rs.getString("hora")),Double.parseDouble(rs.getString("importe")),rs.getString("estado"),Integer.parseInt(rs.getString("idUsuario")),Integer.parseInt(rs.getString("idMesa")),Integer.parseInt(rs.getString("idCliente")));
        
            }
          return pedido;  
            
        } catch (SQLException e) {
            System.err.println(e);
            pedido = new Pedido();
            pedido.setNumero(0);
            return pedido; 
            
        } finally {

             cerrarConexionBD();

        }
     
    
    }
   
    
    //Operaciones Sobre DetallePedido
    
    public boolean altaDetallePedido(DetallePedido dP){
    
       boolean salida;
        
            PreparedStatement ps;

            String sql = " INSERT INTO detallepedido(numPedido,idProducto,nombre,cantidad,precioUnitario,subtotal,observacion) VALUES (?,?,?,?,?,?,?)";

            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,dP.getNumPedido());
                ps.setInt(2,dP.getIdProducto());
                ps.setString(3,dP.getNombre());
                ps.setInt(4,dP.getCantidad());
                ps.setDouble(5,dP.getPrecioUnitario());
                ps.setDouble(6,dP.getSubtotal());
                ps.setString(7,dP.getObservacion());
                                
                int resultado = ps.executeUpdate();

                salida = resultado > 0; // esto es if else abreviado para almacenar true o false

            } catch (SQLException e) {
                System.err.println(e);
                salida = false;
            } finally {

                cerrarConexionBD();

            }
        return salida;
    
    }

    public ArrayList listarDetallePedido(int numeroPedido) {
      
   
        ArrayList lista = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql;
        
        
              sql = "SELECT * FROM detallePedido "
                             
                +" WHERE detallePedido.numPedido= "+numeroPedido         
                +" ORDER BY detallePedido.idDetallePedido ASC";
            
       
        try {
            
           
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
             
                DetallePedido dP = new DetallePedido(Integer.parseInt(rs.getString("idDetallePedido")),Integer.parseInt(rs.getString("numPedido")),Integer.parseInt(rs.getString("idProducto")),rs.getString("nombre"),Integer.parseInt(rs.getString("cantidad")),Double.parseDouble(rs.getString("precioUnitario")),Double.parseDouble(rs.getString("subtotal")),rs.getString("observacion"));
                
                lista.add(dP);
            }
           
            return lista;
        } catch (SQLException e) {

            System.err.println(e);
            return null;

        } finally {

            cerrarConexionBD();

        }
   
    }

    public boolean eliminarDetallePedido(int idPedido) {
        
    
     boolean salida;
      PreparedStatement ps;
      String sql = "DELETE FROM detallepedido WHERE numPedido=?";
       try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPedido);
            int resultado = ps.executeUpdate();
            
            salida = resultado>0; 
            

        } catch (SQLException e) {
            System.err.println(e);
            salida = false;
        } finally {

                cerrarConexionBD();

            }
      
      
        
        return salida;
        
    }
}
