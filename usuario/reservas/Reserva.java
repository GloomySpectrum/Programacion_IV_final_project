package usuario.reservas;

import java.io.Serializable;

public class Reserva implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private final int  idReserva;
	private int idHabitacion;
	private final long idUsuario;
	private String fechaEntrada;
	private String fechaSalida;
	private int precio;
	private String nombreUsuario;
	private String estadoReserva;
	
	public Reserva(int idReserva,int idHabitacion, long idUsuario, String fechaEntrada, String fechaSalida, String nombreUsuario) {
		this.idReserva = idReserva;
		this.idHabitacion = idHabitacion;
		this.idUsuario = idUsuario;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getEstadoReserva() {
		return estadoReserva;
	}
	
	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	
	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getIdReserva() {
		return idReserva;
	}
	
	
	public int getIdHabitacion() {
		return idHabitacion;
	}
	
	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	
	

}
