
package Modelo;

/**
 *
 * @author Vidaurre Julio Fernando
 */
public class Mozo extends Persona {
    private int idMozo;
    private String codigo;
    private String tipo;
    private String estado;

    public Mozo() {
    }

    public Mozo(int idMozo, String codigo, String tipo, String estado, int idPersona, String dni, String apellido, String nombre, String direccion, String telefono, String email) {
        super(idPersona, dni, apellido, nombre, direccion, telefono, email);
        this.idMozo = idMozo;
        this.codigo = codigo;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdMozo() {
        return idMozo;
    }

    public void setIdMozo(int idMozo) {
        this.idMozo = idMozo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Mozo{" + "idMozo=" + idMozo + ", codigo=" + codigo + ", tipo=" + tipo + ", estado=" + estado + '}';
    }
        
    
    
}
