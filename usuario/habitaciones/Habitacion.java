package usuario.habitaciones;

import java.io.Serializable;

public class Habitacion implements Serializable{
	
	private static final long serialVersionUID = 275983576370632048L;
	
	private int idHabitacion;
	private String tipoHabitacion;
	private String descripcion;
	private int precio;
	private int cantidadHuspedes;
	private final int cantidadCamas;
	private final int cantidadBaños;
	private String disponibilidad = "Disponible";
	
	
	public Habitacion(int idHabitacion, String tipoHabitacion, String descripcion, int precio, int cantidadCamas, int cantidadBaños) {
		this.idHabitacion = idHabitacion;
		this.tipoHabitacion = tipoHabitacion;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidadHuspedes = cantidadCamas;
		this.cantidadCamas = cantidadCamas;
		this.cantidadBaños = cantidadBaños;
	}
	
	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	public String getDisponibilidad() {
		return disponibilidad;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getIdHabitacion() {
		return idHabitacion;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getCantidadHuspedes() {
		return cantidadHuspedes;
	}

	public int getCantidadCamas() {
		return cantidadCamas;
	}

	public int getCantidadBaños() {
		return cantidadBaños;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
