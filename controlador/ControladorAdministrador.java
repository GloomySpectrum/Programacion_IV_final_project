package controlador;

import java.util.List;

import constantes.Constantes;
import interfaz.Mensajes;
import usuario.ManejoArchivo;
import usuario.habitaciones.Habitacion;

public class ControladorAdministrador {
	
	
	public void mostrarHabitaciones() {
		ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<Habitacion>();
		List<Habitacion> habitaciones = manejoArchivo.recuperarObjeto(Constantes.RUTA_HABITACIONES);

		if (habitaciones.isEmpty()) {
			Mensajes.mensaje("No hay habitaciones registradas");
			return;
		}

		for (Habitacion habitacion : habitaciones) {
			System.out.println(habitacion.getIdHabitacion());
		}
	}
	
	public void eliminarHabitacion(int idHabitacion){
		// Hacer esto mismo para eliminar reserva--------------------------------------------
		ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<Habitacion>();
		List<Habitacion> habitaciones = manejoArchivo.recuperarObjeto(Constantes.RUTA_HABITACIONES);
		manejoArchivo.eliminarArchivo(Constantes.RUTA_HABITACIONES);

		for (int i = 0; i < habitaciones.size(); i++) {
			if (habitaciones.get(i).getIdHabitacion() == idHabitacion) {
				habitaciones.remove(i);;
				break;
			}
		}
		
		for (Habitacion habitacion : habitaciones) {
			manejoArchivo.guardarObjeto(Constantes.RUTA_HABITACIONES, habitacion);
		}
		
	}
	
	public void eliminarHabitacion(String idHabitacion) {
		if (!idHabitacion.isEmpty()) {

			if (!ComprobarValoresNumericos.esNumero(idHabitacion)) {
				Mensajes.mensaje("El id de la habitacion debe ser un valor numerico");
				return;
			}

			ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<Habitacion>();
			List<Habitacion> habitaciones = manejoArchivo.recuperarObjeto(Constantes.RUTA_HABITACIONES);
			
			if (habitaciones.isEmpty()) {
				Mensajes.mensaje("No hay habitaciones registradas");
				return;
			}
			
			if(!existeHabitacion(Integer.parseInt(idHabitacion))){
				Mensajes.mensaje("La habitacion con el id " + idHabitacion + " no existe");
				return;
			}
			
			eliminarHabitacion(Integer.parseInt(idHabitacion));

		} else {
			Mensajes.mensaje("Por favor llene todos los campos");
		}
	}
	
	public boolean existeHabitacion(int idHabitacion) {
		ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<Habitacion>();
		List<Habitacion> habitaciones = manejoArchivo.recuperarObjeto(Constantes.RUTA_HABITACIONES);
		
		for (Habitacion habitacion : habitaciones) {
			if (habitacion.getIdHabitacion() == idHabitacion) {
				return true;
			}
		}
		return false;
	}
	
	public void agregarHabitacion(String numeroHabitacion,String tipoHabitacion, 
			String descripcion, String precio, String cantidadCamas, String cantidadBaños) {
		if (!numeroHabitacion.isEmpty() && !tipoHabitacion.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()
				&& !cantidadCamas.isEmpty() && !cantidadBaños.isEmpty()) {
			
			if(!ComprobarValoresNumericos.esNumero(numeroHabitacion) || numeroHabitacion.equals("0")) {
				Mensajes.mensaje("El numero de habitacion debe ser un valor numerico diferente de 0");
				return;
			}

			if (!ComprobarValoresNumericos.esNumero(precio)) {
				Mensajes.mensaje("El precio debe ser un valor numerico");
				return;
			}
			
			if (!ComprobarValoresNumericos.esNumero(cantidadCamas) || cantidadCamas.equals("0")) {
				Mensajes.mensaje("La cantidad de huspedes debe ser un valor numerico diferente de 0");
				return;
			}
			
			if (!ComprobarValoresNumericos.esNumero(cantidadBaños)) {
				Mensajes.mensaje("La cantidad de baños debe ser un valor numerico");
				return;
			}
			
			if (existeHabitacion(Integer.parseInt(numeroHabitacion))) {
				Mensajes.mensaje("La habitacion con el numero " + numeroHabitacion + " ya existe");
				return;
			}
			
			Habitacion habitacion = new Habitacion(Integer.parseInt(numeroHabitacion), tipoHabitacion, 
					descripcion, Integer.parseInt(precio), Integer.parseInt(cantidadCamas), Integer.parseInt(cantidadBaños));
			
			ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<Habitacion>();
			manejoArchivo.guardarObjeto(Constantes.RUTA_HABITACIONES ,habitacion);
			
			Mensajes.mensaje("Habitacion agregada correctamente");
			
		}else {
			Mensajes.mensaje("Por favor llene todos los campos");
		}
		
	}
	
	public Habitacion recuperarHabitacionPorId(int idHabitacion) {
		ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<Habitacion>();
		List<Habitacion> habitaciones = manejoArchivo.recuperarObjeto(Constantes.RUTA_HABITACIONES);
		for (Habitacion habitacion : habitaciones) {
			
			if (habitacion.getIdHabitacion() == idHabitacion) {
				return habitacion;
			}
		}
		return null;
	}
	
	public void cambiarDescripcionesHabitaciones(String tipoHabitacion, String descripcion) {
		ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<Habitacion>();
		List<Habitacion> habitaciones = manejoArchivo.recuperarObjeto(Constantes.RUTA_HABITACIONES);
		
		for (Habitacion habitacion : habitaciones) {
			if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
				if(habitacion.getDescripcion() != descripcion) {
					habitacion.setDescripcion(descripcion);					
				}else {
					return;
				}
			}
			manejoArchivo.guardarObjeto(Constantes.RUTA_HABITACIONES, habitacion);
		}
		manejoArchivo.eliminarArchivo(Constantes.RUTA_HABITACIONES);
		
		for (Habitacion habitacion : habitaciones) {
			manejoArchivo.guardarObjeto(Constantes.RUTA_HABITACIONES, habitacion);
		}
	}
	
	public void modificarHabitacion(int idHabitacion, String disponibilidad, String descripcion, String precio) {
		ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<Habitacion>();
		
		Habitacion nuevaHabitacion = recuperarHabitacionPorId(idHabitacion);
		
		if (disponibilidad.isEmpty() && descripcion.isEmpty() && precio.isEmpty()) {
			Mensajes.mensaje("Por favor llene al menos un campo");
			return;
		}
		
		if(!disponibilidad.isEmpty()) {
			nuevaHabitacion.setDisponibilidad(disponibilidad);
		}
		
		if (!descripcion.isEmpty()) {
			nuevaHabitacion.setDescripcion(descripcion);
			cambiarDescripcionesHabitaciones(nuevaHabitacion.getTipoHabitacion(), descripcion);
		}
		
		if (!precio.isEmpty()) {
			if (ComprobarValoresNumericos.esNumero(precio)) {
				nuevaHabitacion.setPrecio(Integer.parseInt(precio));
			}else {
				Mensajes.mensaje("El precio debe ser un valor numerico");
				return;
			}
			
		}
		
		eliminarHabitacion(idHabitacion);
		manejoArchivo.guardarObjeto(Constantes.RUTA_HABITACIONES, nuevaHabitacion);
		Mensajes.mensaje("Habitacion modificada correctamente");
	}
	
}
