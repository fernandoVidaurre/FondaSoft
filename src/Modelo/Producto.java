
package Modelo;

/**
 *
 * @author Vidaurre Julio Fernando
 */
public class Producto {
    
    private int idProducto;
    private String codigo;
    private String nombre;
    private int stock;
    private double precio;
    private String imagen;
    private String tipo;
    private int idCategoria;

    public Producto() {
    }

    public Producto(int idProducto, String codigo, String nombre, int stock, double precio, String imagen, String tipo, int idCategoria) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.imagen = imagen;
        this.tipo = tipo;
        this.idCategoria = idCategoria;
    }

   

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", codigo=" + codigo + ", nombre=" + nombre + ", stock=" + stock + ", precio=" + precio + ", imagen=" + imagen + ", tipo=" + tipo + ", idCategoria=" + idCategoria + '}';
    }

    
    
    
    
}
