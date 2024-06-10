package interfaz.opcionesUsuario;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import controlador.ControladorUsuario;
import interfaz.opcionesUsuario.reserva.PanelMostrarHabitacionDisponible;

import java.awt.ScrollPane;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import usuario.habitaciones.*;
import javax.swing.JTextField;

import constantes.Constantes;
import javax.swing.border.LineBorder;
import java.awt.Cursor;

public class HabitacionesDisponibles extends JPanel {

	private static final long serialVersionUID = -1887072540622739454L;
	private JPanel panelContentHabitaciones;
	private ScrollPane scrollPane;
	
	private int[] position = {0, 0};
	
	private JTextField textNumeroHuspedes;
	private JDateChooser obtenerFechaEntrada;
	private JDateChooser obtenerFechaSalida;
	private JLabel lblBuscar;
	private JPanel btnBuscar;
	private long idUsuario;
	
	private boolean mostrarBotonReservar = false;
	boolean mostrarIdHabitacion = false;
	
	
	public void cargarHabitacion(Habitacion h) {
		
		PanelMostrarHabitacionDisponible panelHabitacion = new PanelMostrarHabitacionDisponible(h, idUsuario, obtenerFechaEntrada.getDate(), obtenerFechaSalida.getDate());
		if(mostrarBotonReservar) {
            panelHabitacion.mostrarBotonReservar();
		}
		if (mostrarIdHabitacion) {
			panelHabitacion.mostrarIdHabitacion();
			panelHabitacion.lblIdHabitacion.setText("Id Habitacion:  " + h.getIdHabitacion());
		}
		panelHabitacion.lblTipoHabitacion.setText("Tipo Habitacion: " + h.getTipoHabitacion());
		panelHabitacion.lblDescripcion.setText("Descripcion: " + h.getDescripcion());
		panelHabitacion.lblCantidadHuspedes.setText("Cantidad de Huspedes: " + h.getCantidadHuspedes());
		panelHabitacion.lblPrecio.setText("Precio: " + h.getPrecio());
		panelHabitacion.lblCantidadBaños.setText("Cantidad de Baños: " + h.getCantidadBaños());
		panelHabitacion.lblCantidadCamas.setText("Cantidad de Camas: " + h.getCantidadCamas());
		panelHabitacion.setBackground(Color.WHITE);
		panelHabitacion.setBounds(position[0], position[1], Constantes.WIDTH_PANEL_CONTENT_HABITACIONES, Constantes.HEIGHT_PANEL_CONTENT_HABITACIONES);
		
		position[1] += Constantes.HEIGHT_PANEL_CONTENT_HABITACIONES + 5;
		
		panelContentHabitaciones.setPreferredSize(new Dimension(484, position[1]));
		panelContentHabitaciones.add(panelHabitacion);
		panelContentHabitaciones.revalidate();
		panelContentHabitaciones.repaint();
	}
	
	public void cargarHabitacion() {
		
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		List<Habitacion> habitacionesMostrar = controladorUsuario.validarCamposIngresados(textNumeroHuspedes.getText(), 
				obtenerFechaEntrada.getDate(), obtenerFechaSalida.getDate(), mostrarIdHabitacion);
		
		if (habitacionesMostrar == null) {
			return;
		}
		
		panelContentHabitaciones.removeAll();
		position[1] = 0;	
		
		for (Habitacion h : habitacionesMostrar) {
			cargarHabitacion(h);
		}
	}
	
	MouseAdapter manejoEventoMouse = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == lblBuscar) {
				cargarHabitacion();				
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == lblBuscar) {
				btnBuscar.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == lblBuscar) {
				btnBuscar.setBackground(Constantes.COLOR_BOTON_NORMAL);
			}
		}
	};
	
	
	
	public HabitacionesDisponibles(long idUsuario, boolean mostrarBotonReservar, boolean mostrarIdHabitacion) {
		this.idUsuario = idUsuario;
		this.mostrarBotonReservar = mostrarBotonReservar;
		this.mostrarIdHabitacion = mostrarIdHabitacion;
		
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(Constantes.WIDTH_PANEL_OPCIONES, Constantes.HEIGHT_PANEL_OPCIONES);
		
		JPanel background = new JPanel();
		background.setBorder(new LineBorder(new Color(0, 0, 0)));
		background.setBackground(Color.WHITE);
		background.setBounds(0, 0, 708, 700);
		add(background);
		background.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(0, 0, 708, 90);
		panelTitulo.setLayout(null);
		panelTitulo.setBackground(new Color(54, 154, 186));
		background.add(panelTitulo);
		
		JLabel lblHabitacionesDisponibles = new JLabel("Habitaciones Disponibles");
		lblHabitacionesDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		lblHabitacionesDisponibles.setForeground(Color.WHITE);
		lblHabitacionesDisponibles.setFont(new Font("Arial", Font.ITALIC, 20));
		lblHabitacionesDisponibles.setBounds(206, 29, 252, 30);
		panelTitulo.add(lblHabitacionesDisponibles);
		
		
		panelContentHabitaciones = new JPanel();
		panelContentHabitaciones.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panelContentHabitaciones.setBackground(Color.WHITE);
		panelContentHabitaciones.setBounds(0, 88, 484, 424);
		
		scrollPane = new ScrollPane();
		scrollPane.setBounds(0, 182, 708, 518);
		scrollPane.add(panelContentHabitaciones);
		panelContentHabitaciones.setLayout(null);
		background.add(scrollPane);
		
		JPanel BackgroundFiltroBuscarHabitacion = new JPanel();
		BackgroundFiltroBuscarHabitacion.setBackground(Color.WHITE);
		BackgroundFiltroBuscarHabitacion.setBounds(0, 90, 708, 90);
		background.add(BackgroundFiltroBuscarHabitacion);
		BackgroundFiltroBuscarHabitacion.setLayout(null);
		
		JLabel lblFechaEntrada = new JLabel("<Html> Digite fecha de ingreso </Html>");
		lblFechaEntrada.setBounds(10, 11, 73, 31);
		BackgroundFiltroBuscarHabitacion.add(lblFechaEntrada);
		
		JLabel lblFechaSalida = new JLabel("<Html> Digite fecha de salida</Html>");
		lblFechaSalida.setBounds(273, 11, 73, 31);
		BackgroundFiltroBuscarHabitacion.add(lblFechaSalida);
		
		obtenerFechaEntrada = new JDateChooser();
		obtenerFechaEntrada.setBounds(89, 11, 161, 31);
		//obtenerFechaEntrada.setDateFormatString("dd/MM/yyyy hh:mm a");
		obtenerFechaEntrada.setDateFormatString("yyyy/MM/dd");
		BackgroundFiltroBuscarHabitacion.add(obtenerFechaEntrada);
		
		obtenerFechaSalida = new JDateChooser();
		obtenerFechaSalida.setBounds(345, 11, 161, 31);
		//obtenerFechaSalida.setDateFormatString("dd/MM/yyyy hh:mm a");
		obtenerFechaSalida.setDateFormatString("yyyy/MM/dd");
		BackgroundFiltroBuscarHabitacion.add(obtenerFechaSalida);
		
		JLabel lblNumeroHuspedes = new JLabel("<html>Numero de huspedes</html>");
		lblNumeroHuspedes.setBounds(529, 11, 67, 31);
		BackgroundFiltroBuscarHabitacion.add(lblNumeroHuspedes);
		
		textNumeroHuspedes = new JTextField();
		textNumeroHuspedes.setBounds(606, 11, 67, 31);
		BackgroundFiltroBuscarHabitacion.add(textNumeroHuspedes);
		textNumeroHuspedes.setColumns(10);
		
		btnBuscar = new JPanel();
		btnBuscar.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnBuscar.setForeground(new Color(0, 0, 0));
		btnBuscar.setBounds(283, 53, 67, 28);
		BackgroundFiltroBuscarHabitacion.add(btnBuscar);
		btnBuscar.setLayout(null);
		
		lblBuscar = new JLabel("Buscar");
		lblBuscar.setForeground(new Color(255, 255, 255));
		lblBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBuscar.setFont(new Font("Arial", Font.ITALIC, 11));
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.addMouseListener(manejoEventoMouse);
		lblBuscar.setBounds(0, 0, 67, 28);
		btnBuscar.add(lblBuscar);
		
	}
}
