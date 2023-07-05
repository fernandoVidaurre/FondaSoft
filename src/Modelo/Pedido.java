
package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;


public class Pedido {
    
    private int numero;
    private LocalDate fecha;
    private LocalTime hora;
    private double importe;
    private String estado;
    private int idUsuario;
    private int idMesa;
    private int idCliente;

    public Pedido() {
    }

    public Pedido(int numero, LocalDate fecha, LocalTime hora, double importe, String estado, int idUsuario, int idMesa, int idCliente) {
        this.numero = numero;
        this.fecha = fecha;
        this.hora = hora;
        this.importe = importe;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.idMesa = idMesa;
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    @Override
    public String toString() {
        return "Pedido{" + "numero=" + numero + ", fecha=" + fecha + ", hora=" + hora + ", importe=" + importe + ", estado=" + estado + ", idUsuario=" + idUsuario + ", idMesa=" + idMesa + ", idCliente=" + idCliente + '}';
    }
    
}
