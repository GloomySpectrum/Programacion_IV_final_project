package constantes;

import java.awt.Color;

public final class Constantes {
	
	public static final String RUTA_HABITACIONES = "habitaciones.dat";
	public static final String RUTA_USUARIOS = "clientes.dat";
	public static final String RUTA_RESERVAS = "reservas.dat";
	
	public static final int WIDTH_PANEL_OPCIONES = 708;
	public static final int HEIGHT_PANEL_OPCIONES = 700;
	
	
	public static final int WIDTH_PANEL_CONTENT_HABITACIONES = 708;
	public static final int HEIGHT_PANEL_CONTENT_HABITACIONES = 229;
	
	public static final Color COLOR_BOTON_SELECCIONADO = new Color(20,102,192);
	public static final Color COLOR_BOTON_NORMAL = new Color(18, 91, 173);
	
	public static final Color COLOR_BOTON_EDITAR_SELECCIONADO = new Color(72, 118, 206);
	public static final Color COLOR_BOTON_EDITAR_NORMAL = new Color(100, 149, 237);
	public static final Color COLOR_BOTON_CANCELAR_SELECCIONADO = new Color(192, 192, 192);
	public static final Color COLOR_BOTON_CANCELAR_NORMAL = new Color(169, 169, 169);
	public static final Color COLOR_BOTON_ELIMINAR_SELECCIONADO = new Color(255, 0, 0);
	public static final Color COLOR_BOTON_ELIMINAR_NORMAL = new Color(255, 100, 100);
	
	public static final String HORA_ENTRADA = "03:00 p. m.";
	public static final String HORA_SALIDA = "01:00 p. m.";
	public static final String FECHA_FORMATO = "yyyy/MM/dd hh:mm a";
	
	public static final int PLAZO_MODIFICACION_RESERVA_HORAS = 48; // tiempo en horas para modificar reserva antes de la fecha de entrada de la reserva actual
    public static final int PLAZO_CANCELACION_RESERVA_HORAS = 24; // tiempo en horas para cancelar reserva antes de la fecha de entrada de la reserva actual
}
