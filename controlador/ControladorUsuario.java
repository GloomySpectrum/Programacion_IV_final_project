package controlador;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constantes.Constantes;
import interfaz.Mensajes;
import usuario.ManejoArchivo;
import usuario.habitaciones.Habitacion;
import usuario.reservas.Reserva;

public class ControladorUsuario {
	
	public List<Habitacion> validarCamposIngresados(String numeroHuspedes, Date fechaEntrada, Date fechaSalida, boolean mostrarIdHabitacion) {
		
		if(!ComprobarValoresNumericos.esNumero(numeroHuspedes)) {
			if(!numeroHuspedes.isEmpty()) {
				Mensajes.mensaje("!!La cantidad de huspedes debe ser un valor numerico");
				return null;
            }
		}
		
		if (fechaEntrada == null || fechaSalida == null) {
			Mensajes.mensaje("!!Por favor seleccione las fechas de entrada y salida!!");
			return null;
		}

		if (fechaEntrada.after(fechaSalida)) {
			Mensajes.mensaje("!!La fecha de entrada no puede ser mayor a la fecha de salida!!");
			return null;
		}

		if (fechaSalida.before(fechaEntrada)) {
			Mensajes.mensaje("!!La fecha de salida no puede ser menor a la fecha de entrada!!");
			return null;
		}
		
		List<Habitacion> habitaciones = recuperarHabitaciones();
		if (habitaciones == null) {
			Mensajes.mensaje("!!No hay habitaciones disponibles!!");
			return null;
		}
		List<Habitacion> habitacionesMostrar= new  ArrayList<Habitacion>();
		List<String> tiposHabitacionMostrados = new ArrayList<String>();
		tiposHabitacionMostrados.add(" ");
		
		if(!numeroHuspedes.isEmpty()) {
			if(Integer.parseInt(numeroHuspedes) >= 1) {
				for(Habitacion h : habitaciones) {
					if (!tiposHabitacionMostrados.contains(h.getTipoHabitacion()) || mostrarIdHabitacion ) {
						if(Integer.parseInt(numeroHuspedes) == h.getCantidadHuspedes()) {
							if (!estaHabitacionReservada(h, fechaEntrada, fechaSalida)) {
								habitacionesMostrar.add(h);
								tiposHabitacionMostrados.add(h.getTipoHabitacion());
							}												
						}
					}
				}
			}else {
				Mensajes.mensaje("!!La cantidad de huspedes debe ser mayor a 0!!");
				return null;
			}
			
		}else {
			for (Habitacion h : habitaciones) {
				if (!tiposHabitacionMostrados.contains(h.getTipoHabitacion()) || mostrarIdHabitacion) {
					if (!estaHabitacionReservada(h, fechaEntrada, fechaSalida)) {
						habitacionesMostrar.add(h);
						tiposHabitacionMostrados.add(h.getTipoHabitacion());
					}
				}
			}
			
		}
			
		return habitacionesMostrar;
	}
	
	public List<Habitacion> recuperarHabitaciones() {
		
		File file = new File(Constantes.RUTA_HABITACIONES);
		if (!file.exists()) {
			return null;	
        }
		
		ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<>();
        List<Habitacion> habitaciones = manejoArchivo.recuperarObjeto(Constantes.RUTA_HABITACIONES);
		
		return habitaciones;
    }
	
	public List<Reserva> recuperarReservas() {
		ManejoArchivo<Reserva> manejoArchivo = new ManejoArchivo<>();
		return manejoArchivo.recuperarObjeto(Constantes.RUTA_RESERVAS);
	}
	
	
	public String DateToLocalDateTime(Date date, int hora, int minuto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");
		
		LocalTime time = LocalTime.of(hora, minuto);
		
		LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		LocalDateTime fechaDT = LocalDateTime.of(fecha, time);

		return fechaDT.format(formatter);
	}
	
	
	public boolean estaHabitacionReservada(Habitacion habitacion, Date fechaEntrada, Date fechaSalida) {
		
		
		File file = new File(Constantes.RUTA_RESERVAS);
		if (!file.exists()) {
			return false;
        }
		
		if (fechaEntrada == null || fechaSalida == null) {
			return false;
		}
		
		List<Reserva> reservas = recuperarReservas();
		
		String fechaEntradaStr = DateToLocalDateTime(fechaEntrada, 15, 00);
		String fechaSalidaStr = DateToLocalDateTime(fechaSalida, 13, 00);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FECHA_FORMATO);
		
		LocalDateTime fechaEntradaInterfaz = LocalDateTime.parse(fechaEntradaStr, formatter);
		LocalDateTime fechaSalidaInterfaz= LocalDateTime.parse(fechaSalidaStr,formatter);

		for(Reserva reserva : reservas) {
			if (!reserva.getEstadoReserva().equals("Cancelada") && !reserva.getEstadoReserva().equals("Finalizada")) {
				LocalDateTime fechaSalidaReserva = LocalDateTime.parse(reserva.getFechaSalida(), formatter);
				LocalDateTime fechaEntradaReserva = LocalDateTime.parse(reserva.getFechaEntrada(), formatter);
				
				if(habitacion.getIdHabitacion() == reserva.getIdHabitacion()) {
					
					if(fechaEntradaInterfaz.isAfter(fechaEntradaReserva) && fechaSalidaInterfaz.isBefore(fechaSalidaReserva)
							|| fechaEntradaInterfaz.isAfter(fechaEntradaReserva) && fechaEntradaInterfaz.isBefore(fechaSalidaReserva)
							|| fechaSalidaInterfaz.isAfter(fechaEntradaReserva) && fechaSalidaInterfaz.isBefore(fechaSalidaReserva)
							|| fechaEntradaInterfaz.isBefore(fechaEntradaReserva) && fechaSalidaInterfaz.isAfter(fechaSalidaReserva)
							|| fechaEntradaInterfaz.isEqual(fechaEntradaReserva) || fechaSalidaInterfaz.isEqual(fechaSalidaReserva)) {
						return true; //esta reservada para las fechas ingresadas
					}
					
				}
			}
		}
		
		return false;
	}
	
}
