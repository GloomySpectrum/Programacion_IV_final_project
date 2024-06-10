package controlador;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constantes.Constantes;
import interfaz.Mensajes;
import interfaz.opcionesUsuario.reserva.ConfirmarReserva;
import usuario.ManejoArchivo;
import usuario.habitaciones.Habitacion;
import usuario.reservas.Reserva;

public class ControladorReserva {
	
	
	public void confirmarReservaHabitacion(boolean validacionReserva, Reserva reserva) {
		
		if(validacionReserva) {
            ManejoArchivo<Reserva> manejoArchivo = new ManejoArchivo<>();
            manejoArchivo.guardarObjeto(Constantes.RUTA_RESERVAS, reserva);
            Mensajes.mensaje("Reservacion exitosa");
		}else {
        	Mensajes.mensaje("Reservacion cancelada");
        }
	}
	
	public Habitacion buscarHabitacion(String tipoHabitacion, Date fechaEntrada, Date fechaSalida) {
		
		ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<>();
		List<Habitacion> habitaciones = manejoArchivo.recuperarObjeto(Constantes.RUTA_HABITACIONES);

		for (Habitacion h : habitaciones) {
			if(h.getDisponibilidad().equals("Disponible")){	
				if (h.getTipoHabitacion().equals(tipoHabitacion)) {
					ControladorUsuario controladorUsuario = new ControladorUsuario();
					if(!controladorUsuario.estaHabitacionReservada(h, fechaEntrada, fechaSalida)) {
						return h;
					}
				}
			}
		}
		return null;
	}
	
	public Habitacion buscarHabitacion(int cantidadHuspedes, Date fechaEntrada, Date fechaSalida) {
		
		ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<>();
		List<Habitacion> habitaciones = manejoArchivo.recuperarObjeto(Constantes.RUTA_HABITACIONES);

		for (Habitacion h : habitaciones) {
			if ( h.getDisponibilidad().equals("Disponible")) {
				if(h.getCantidadHuspedes() == cantidadHuspedes){
					ControladorUsuario controladorUsuario = new ControladorUsuario();
					if(!controladorUsuario.estaHabitacionReservada(h, fechaEntrada, fechaSalida)) {
						return h;
					}					
				}
			}
		}
		return null;
	}
	
	public Boolean validarFechaIngresadas(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FECHA_FORMATO);
		
		if(fechaSalida.isAfter(fechaEntrada)
					&& !fechaEntrada.isEqual(fechaSalida)) {
				
				LocalDateTime fechaActual = LocalDateTime.now();
				fechaActual = LocalDateTime.parse(fechaActual.format(DateTimeFormatter.ofPattern(Constantes.FECHA_FORMATO)), formatter);
				
				if(fechaEntrada.isAfter(fechaActual) 
						|| fechaEntrada.isEqual(fechaActual)) {
					return true;
					
				}else {
					Mensajes.mensaje("La fecha de entrada no puede ser una fecha que ya paso", "!!Digite una fecha de entrada valida!!");
					return false;
				}
				
		}else {
				Mensajes.mensaje("La fecha de salida no puede ser una fecha igual ó anterior a la fecha de entrada");
				return false;
		}
			
	}
	
	public void reservarHabitacion(Date fechaEntradaReserva, Date fechaSalidaReserva,long idUsuario, String tipoHabitacion, String nombreUsuario) {
		
		if(fechaEntradaReserva != null && fechaSalidaReserva != null) {
			ControladorUsuario controladorUsuario = new ControladorUsuario();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FECHA_FORMATO);
			
			String fechaEntradaStr = controladorUsuario.DateToLocalDateTime(fechaEntradaReserva, 15, 00);
			String fechaSalidaStr = controladorUsuario.DateToLocalDateTime(fechaSalidaReserva, 13, 00);
			
			LocalDateTime fechaSalida = LocalDateTime.parse(fechaSalidaStr, formatter);
			LocalDateTime fechaEntrada = LocalDateTime.parse(fechaEntradaStr, formatter);
			
			if (validarFechaIngresadas(fechaEntrada, fechaSalida)) {
				Habitacion habitacion = buscarHabitacion(tipoHabitacion, fechaEntradaReserva, fechaSalidaReserva);
				
				if (habitacion == null) {
					Mensajes.mensaje("No hay habitaciones disponibles para la fecha seleccionada del tipo " + tipoHabitacion, "!!Error!!");
					return;
				}
				
				List<Reserva> r = new ControladorUsuario().recuperarReservas();
				int id = 100;
				if(r != null) {
					id = r.get(r.size() - 1).getIdReserva() + 1;
				}
				Reserva reserva = new Reserva(id,habitacion.getIdHabitacion(), idUsuario, fechaEntradaStr, fechaSalidaStr, nombreUsuario);
				long precioTotal = calcularPrecioReserva(habitacion, fechaEntrada, fechaSalida);
				
				
				reserva.setPrecio((int) precioTotal);
				reserva.setEstadoReserva("Activa");
				long nochesEstadia = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida) + 1;
				ConfirmarReserva confirmarReserva = new ConfirmarReserva(reserva,habitacion, fechaEntradaStr, 
						fechaSalidaStr, String.valueOf(precioTotal), String.valueOf(nochesEstadia));
				confirmarReserva.setVisible(true);
			}
		}else {
			Mensajes.mensaje("Por favor ingrese las fechas de entrada y salida", "!!Error!!");
		}
	}
	
	
	public long calcularPrecioReserva(Habitacion habitacion, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {

		long diasDiferencia = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida) + 1;

		return habitacion.getPrecio() * diasDiferencia;
	}
	
	public List<Reserva> recuperarReservas() {
		File file = new File(Constantes.RUTA_RESERVAS);
		if (!file.exists()) {
			return null;
        }
		ManejoArchivo<Reserva> manejoArchivo = new ManejoArchivo<>();
		return manejoArchivo.recuperarObjeto(Constantes.RUTA_RESERVAS);
	}
	
	public Reserva recuperarReservaPorId(int idReserva) {
		List<Reserva> reservas = recuperarReservas();
		if (reservas == null) {
			return null;
		}

		for (Reserva reserva : reservas) {
			if (reserva.getIdReserva() == idReserva) {
				return reserva;
			}
		}
		return null;
	}
	
	public List<Reserva> obtenerReservasPorUsuario(long idUsuario) {
		List<Reserva> reservas = recuperarReservas();
		if (reservas == null) {
			return null;
		}
		
		List<Reserva> reservasUsuario = new ArrayList<>();
		for (Reserva reserva : reservas) {
			if (reserva.getIdUsuario() == idUsuario) {
				reservasUsuario.add(reserva);
			}
		}
		return reservasUsuario;
	}
	
	// cambiar el nombre de la funcion
	public String dateToLocalDateTimeString(Date date, int hora, int minuto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");
		
		LocalTime time = LocalTime.of(hora, minuto);
		
		LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		LocalDateTime fechaDT = LocalDateTime.of(fecha, time);

		return fechaDT.format(formatter);
	}
	
	
	
	
	public void actualizarEstadoReserva() {
		
		File file = new File(Constantes.RUTA_RESERVAS);
		if (!file.exists()) {
			return;
		}
		
		ManejoArchivo<Reserva> manejoArchivo = new ManejoArchivo<Reserva>();
		
	    List<Reserva> reservas = recuperarReservas();
	    List<Reserva> reservasActualizadas = new ArrayList<>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FECHA_FORMATO);
		LocalDateTime fechaActual = LocalDateTime.now();
		fechaActual = LocalDateTime.parse(fechaActual.format(DateTimeFormatter.ofPattern(Constantes.FECHA_FORMATO)), formatter);
		
		for (Reserva reserva : reservas) {
			if (!reserva.getEstadoReserva().equals("Cancelada") && !reserva.getEstadoReserva().equals("Finalizada")) {
				LocalDateTime fechaSalida = LocalDateTime.parse(reserva.getFechaSalida(), formatter);
				if (fechaSalida.isBefore(fechaActual)) {
					eliminarReserva(reserva);
					reserva.setEstadoReserva("Finalizada");
				}
			}
			reservasActualizadas.add(reserva);
		}
		
		manejoArchivo.eliminarArchivo(Constantes.RUTA_RESERVAS);
		cargarReservas(reservasActualizadas);
		
		
	}
	
	public void cargarReservas(List<Reserva> reservas) {
        if (reservas == null) {
            return;
        }
        
        ManejoArchivo<Reserva> manejoArchivo = new ManejoArchivo<Reserva>();
		for (Reserva reserva : reservas) {
			manejoArchivo.guardarObjeto(Constantes.RUTA_RESERVAS, reserva);
		}
        
	}
	
	public List<Reserva> eliminarReserva(Reserva reserva) {
		new ManejoArchivo<Reserva>();
		
		List<Reserva> reservas = recuperarReservas();
		
		for (int i = 0; i < reservas.size(); i++) {
			if (reservas.get(i).getIdReserva() == reserva.getIdReserva()) {
				reservas.remove(i);
				break;
			}
		}
		
		return reservas;
	}
	
	public void modificarReserva(Reserva reserva,Habitacion habitacion, Date fechaEntrada, Date FechaSalida) {
		ManejoArchivo<Reserva> manejoArchivo = new ManejoArchivo<Reserva>();
		
		List<Reserva> reservas = eliminarReserva(reserva);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FECHA_FORMATO);
		String fechaEntradaStr = dateToLocalDateTimeString(fechaEntrada, 15, 00);
		String fechaSalidaStr = dateToLocalDateTimeString(FechaSalida, 13, 00);
		
		LocalDateTime fechaEntradaDT = LocalDateTime.parse(fechaEntradaStr, formatter);
		LocalDateTime fechaSalidaDT = LocalDateTime.parse(fechaSalidaStr, formatter);
		
		reserva.setIdHabitacion(habitacion.getIdHabitacion());
		reserva.setFechaEntrada(dateToLocalDateTimeString(fechaEntrada, 15, 00));
		reserva.setFechaSalida(dateToLocalDateTimeString(FechaSalida, 13, 00));
		
		long precioTotal = calcularPrecioReserva(habitacion, fechaEntradaDT, fechaSalidaDT);
		reserva.setPrecio((int) precioTotal);
		
		reservas.add(reserva);
		manejoArchivo.eliminarArchivo(Constantes.RUTA_RESERVAS);
		
		for (Reserva r : reservas) {
			manejoArchivo.guardarObjeto(Constantes.RUTA_RESERVAS, r);
		}
		Mensajes.mensaje("Reserva modificada con exito");
	}
	
	
	public void cancelarReserva(Reserva reserva) {
		actualizarEstadoReserva();
		if (reserva.getEstadoReserva().equals("Finalizada")) {
			Mensajes.mensaje("No se puede cancelar una reserva finalizada");
			return;
		}
		
		if (reserva.getEstadoReserva().equals("Cancelada")) {
			Mensajes.mensaje("No se puede cancelar una reserva ya cancelada");
			return;
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FECHA_FORMATO);
		LocalDateTime fechaActual = LocalDateTime.now();
		fechaActual = LocalDateTime.parse(fechaActual.format(DateTimeFormatter.ofPattern(Constantes.FECHA_FORMATO)), formatter);
		
		LocalDateTime reservaFechaEntradalDT = LocalDateTime.parse(reserva.getFechaEntrada(), formatter);
		long horasDiferencia = ChronoUnit.HOURS.between(fechaActual,reservaFechaEntradalDT);
		
		if(horasDiferencia < Constantes.PLAZO_CANCELACION_RESERVA_HORAS) {
			Mensajes.mensaje("No se puede cancelar la reserva, ya que el plazo de cancelacion ha expirado");
			return;
		}
		
		ManejoArchivo<Reserva> manejoArchivo = new ManejoArchivo<Reserva>();
		List<Reserva> reservas = eliminarReserva(reserva);
		manejoArchivo.eliminarArchivo(Constantes.RUTA_RESERVAS);
		
		reserva.setEstadoReserva("Cancelada");
		reservas.add(reserva);
		
		for (Reserva r : reservas) {
			manejoArchivo.guardarObjeto(Constantes.RUTA_RESERVAS, r);
		}
	}
	
	
	public boolean verificarModificacion(Reserva reservaActual, Date fechaEntrada, Date FechaSalida) {
		
		if (reservaActual.getEstadoReserva().equals("Cancelada")) {
			Mensajes.mensaje("No se puede Realizar modificaciones a una reserva cancelada");
			return false;
		}
		
		if(reservaActual.getEstadoReserva().equals("Finalizada")) {
			new Mensajes();
            Mensajes.mensaje("No se puede Realizar modificaciones a una reserva finalizada");
            return false;
        }
		
		if (fechaEntrada == null || FechaSalida == null) {
			new Mensajes();
			Mensajes.mensaje("Por favor ingrese las fechas de entrada y salida", "!!Error!!");
			return false;
		}
		
		String fechaEntradaStr = dateToLocalDateTimeString(fechaEntrada, 15, 00);
		String fechaSalidaStr = dateToLocalDateTimeString(FechaSalida, 13, 00);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FECHA_FORMATO);
		LocalDateTime fechaActual = LocalDateTime.now();
		fechaActual = LocalDateTime.parse(fechaActual.format(DateTimeFormatter.ofPattern(Constantes.FECHA_FORMATO)), formatter);
		
		LocalDateTime fechaEntradaDT = LocalDateTime.parse(fechaEntradaStr, formatter);
		LocalDateTime fechaSalidaDT = LocalDateTime.parse(fechaSalidaStr, formatter);
		
		if(validarFechaIngresadas(fechaEntradaDT, fechaSalidaDT)) {
			LocalDateTime reservaFechaEntradalDT = LocalDateTime.parse(reservaActual.getFechaEntrada(), formatter);
			long horasDiferencia = ChronoUnit.HOURS.between(fechaActual,reservaFechaEntradalDT);
			System.out.println("Horas de diferencia" + horasDiferencia);
			if(horasDiferencia > Constantes.PLAZO_MODIFICACION_RESERVA_HORAS) {
                return true; // retorna true si si es posile hacer una modificacion
			}else {
				new Mensajes();
				Mensajes.mensaje("No se puede modificar la reserva, ya que el plazo de modificacion ha expirado",
						"!!Error!!");
			}
			return false;
		}
		
			return false;
			
	}
	
	public boolean validarCambios(int idReserva,String numeroHuspedes ,Date fechaEntrada, Date FechaSalida) {
		
		actualizarEstadoReserva();
		Reserva reserva = recuperarReservaPorId(idReserva);
		
		if(!ComprobarValoresNumericos.esNumero(numeroHuspedes) || numeroHuspedes.isEmpty()) {
			Mensajes.mensaje("!!La cantidad de huspedes debe ser un valor numerico");
			return false;
		}
		
		if (verificarModificacion(reserva, fechaEntrada, FechaSalida)) {
			
				int cantidadHuspedes = Integer.parseInt(numeroHuspedes);
				
				Habitacion habitacion = buscarHabitacion(cantidadHuspedes, fechaEntrada, 
						FechaSalida);
				
				if (habitacion != null) {
					System.out.println("Entro Aquiiii");
					return true;
					
				}else {
                    Mensajes.mensaje("No hay habitaciones disponibles con la cantidad de huspedes solicitada");
                }
				
		}	
		return false;
	}

}
