
package Modelo;

/**
 *
 * @author Vidaurre Julio Fernando
 */
public class Usuario extends Persona {
    
    private int idUsuario;
    private String usuario;
    private String password;
    private String estado;
   // private int personaId;
   

    public Usuario() {
    }

    public Usuario(int idUsuario, String usuario, String password, String estado,int idPersona, String dni, String apellido, String nombre, String direccion, String telefono, String email) {
        super(idPersona, dni, apellido, nombre, direccion, telefono, email);
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.estado = estado;
    
       
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", usuario=" + usuario + ", password=" + password + ", estado=" + estado +'}';
    }

   
 
}
