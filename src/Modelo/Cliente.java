
package Modelo;

/**
 *
 * @author Vidaurre Julio Fernando
 */
public class Cliente extends Persona {
    private int idCliente;
    private String tipo;
    private String estado;

    public Cliente() {
    }

    public Cliente(int idCliente, String tipo, String estado,int idPersona, String dni, String apellido, String nombre, String direccion, String telefono, String email) {
        super(idPersona, dni, apellido, nombre, direccion, telefono, email);
        this.idCliente = idCliente;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", tipo=" + tipo + ", estado=" + estado + '}';
    }
    
    
}
