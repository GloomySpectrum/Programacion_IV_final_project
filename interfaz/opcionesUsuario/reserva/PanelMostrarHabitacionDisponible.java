package interfaz.opcionesUsuario.reserva;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;

import constantes.Constantes;
import interfaz.Mensajes;
import usuario.habitaciones.Habitacion;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;


public class PanelMostrarHabitacionDisponible extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JLabel lblTipoHabitacion;
	public JLabel lblDescripcion;
	public JLabel lblPrecio;
	public JLabel lblCantidadHuspedes;
	public JLabel lblCantidadBaños;
	public JLabel lblCantidadCamas;
	public JLabel lblIdHabitacion;
	
	private JLabel lblReservar = new JLabel("Reservar");
	private JPanel btnReservar = new JPanel();
	private JPanel background;
	
	
	private Habitacion habitacion;
	private long idUsuario;
	private Date fechaEntrada;
	private Date fechaSalida;

	MouseAdapter manejoEventoMouse = new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			lblReservar.setForeground(Color.WHITE);
			btnReservar.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			lblReservar.setForeground(Color.WHITE);
			btnReservar.setBackground(Constantes.COLOR_BOTON_NORMAL);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(fechaEntrada != null && fechaSalida != null) {
				PedirNombreReserva pedirNombreReserva = new PedirNombreReserva(habitacion, idUsuario, fechaEntrada, fechaSalida);
				pedirNombreReserva.setVisible(true);
			}else {
				Mensajes.mensaje("Todos los campos de fecha deben ser llenados");
			}
		}
		
	};
	
	
	public void mostrarBotonReservar() {
		btnReservar.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnReservar.setBounds(570, 184, 138, 44);
		btnReservar.setLayout(null);
		
		lblReservar.setForeground(new Color(255, 255, 255));
		lblReservar.addMouseListener(manejoEventoMouse);
		lblReservar.setFont(new Font("Arial", Font.ITALIC, 11));
		lblReservar.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservar.setBounds(0, 0, 138, 44);
		btnReservar.add(lblReservar);
		
		background.add(btnReservar);
	}
	
	public void mostrarIdHabitacion() {
		lblIdHabitacion = new JLabel("New label");
		lblIdHabitacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblIdHabitacion.setForeground(new Color(255, 255, 255));
		lblIdHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdHabitacion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblIdHabitacion.setBounds(555, 0, 106, 41);
		background.add(lblIdHabitacion);
	}
	
	
	public PanelMostrarHabitacionDisponible(Habitacion habitacion, long idUsuario, Date fechaEntrada, Date fechaSalida) {
		
		this.habitacion = habitacion;
		this.idUsuario = idUsuario;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		
		setLayout(null);
		
		
		setSize(Constantes.WIDTH_PANEL_CONTENT_HABITACIONES, Constantes.HEIGHT_PANEL_CONTENT_HABITACIONES);
		
		background = new JPanel();
		background.setBorder(new LineBorder(new Color(0, 0, 0)));
		background.setBackground(new Color(54, 154, 186));
		background.setBounds(0, 0, 708, 229);
		add(background);
		background.setLayout(null);
		
		lblTipoHabitacion = new JLabel("New label");
		lblTipoHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoHabitacion.setForeground(new Color(255, 255, 255));
		lblTipoHabitacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTipoHabitacion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblTipoHabitacion.setBounds(0, 0, 487, 41);
		background.add(lblTipoHabitacion);
		
		lblDescripcion = new JLabel("New label");
		lblDescripcion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDescripcion.setBounds(0, 108, 708, 65);
		background.add(lblDescripcion);
		
		lblPrecio = new JLabel("New label");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setForeground(new Color(255, 255, 255));
		lblPrecio.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPrecio.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		lblPrecio.setBounds(361, 184, 139, 44);
		background.add(lblPrecio);
		
		lblCantidadHuspedes = new JLabel("New label");
		lblCantidadHuspedes.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadHuspedes.setForeground(new Color(255, 255, 255));
		lblCantidadHuspedes.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCantidadHuspedes.setFont(new Font("Arial", Font.ITALIC, 11));
		lblCantidadHuspedes.setBounds(0, 56, 179, 41);
		background.add(lblCantidadHuspedes);
		
		lblCantidadBaños = new JLabel("New label");
		lblCantidadBaños.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadBaños.setForeground(new Color(255, 255, 255));
		lblCantidadBaños.setFont(new Font("Arial", Font.ITALIC, 11));
		lblCantidadBaños.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCantidadBaños.setBounds(263, 56, 179, 41);
		background.add(lblCantidadBaños);
		
		lblCantidadCamas = new JLabel("New label");
		lblCantidadCamas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadCamas.setForeground(new Color(255, 255, 255));
		lblCantidadCamas.setFont(new Font("Arial", Font.ITALIC, 11));
		lblCantidadCamas.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCantidadCamas.setBounds(529, 52, 179, 41);
		background.add(lblCantidadCamas);
		

	}
}
