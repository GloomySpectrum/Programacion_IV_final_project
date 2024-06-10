package interfaz.opcionesUsuario.reserva;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;

import constantes.Constantes;
import controlador.ControladorReserva;
import usuario.habitaciones.Habitacion;
import usuario.reservas.Reserva;

import java.awt.event.MouseEvent;

public class ConfirmarReserva extends JFrame{

	private static final long serialVersionUID = 4971751015262098482L;

	private JPanel background;
	
	private JPanel btnAceptar;
	private JLabel lblAceptar;
	
	private JPanel btnCancelar;
	private JLabel lblCancelar;
	
	private Reserva reserva;
	
	
	
	MouseAdapter manejoEventoMouse = new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == lblCancelar) {
				btnCancelar.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
			} else if (e.getSource() == lblAceptar) {
				btnAceptar.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
			}
		}
		
		
		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == lblCancelar) {
                btnCancelar.setBackground(Constantes.COLOR_BOTON_NORMAL);
            } else if (e.getSource() == lblAceptar) {
                btnAceptar.setBackground(Constantes.COLOR_BOTON_NORMAL);
            }
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			ControladorReserva controladorReserva = new ControladorReserva();
            if (e.getSource() == lblCancelar) {
                controladorReserva.confirmarReservaHabitacion(false, reserva);
            }else if (e.getSource() == lblAceptar) {
            	controladorReserva.confirmarReservaHabitacion(true, reserva);

            }
            
            dispose();
		}
		
	};


	public void colorNormal() {
		btnCancelar.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnAceptar.setBackground(Constantes.COLOR_BOTON_NORMAL);
	}

	
	public ConfirmarReserva(Reserva reserva, Habitacion habitacion, String fechaEntrada, 
			String fechaSalida, String valorReserva, String nochesEstadia) {
		
		this.reserva = reserva;
		
		setTitle("Historia Reserva");
		
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 603, 409);
		
		background = new JPanel();
		background.setBackground(new Color(255, 255, 255));
		background.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(background);
		background.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(54, 154, 186));
		panelTitulo.setBounds(0, 0, 587, 54);
		background.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Historia Reserva");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Arial", Font.ITALIC, 15));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 11, 587, 32);
		panelTitulo.add(lblTitulo);
		
		JLabel lblIdReserva = new JLabel("Id Reserva: " + reserva.getIdReserva() );
		lblIdReserva.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblIdReserva.setFont(new Font("Arial", Font.ITALIC, 11));
		lblIdReserva.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdReserva.setBounds(182, 65, 244, 28);
		background.add(lblIdReserva);
		
		JLabel lblTipoHabitacion = new JLabel("Tipo Habitacion: " + habitacion.getTipoHabitacion());
		lblTipoHabitacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTipoHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoHabitacion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblTipoHabitacion.setBounds(305, 117, 244, 28);
		background.add(lblTipoHabitacion);
		
		JLabel lblIdHabitacion = new JLabel("Id Habitacion: " + habitacion.getIdHabitacion());
		lblIdHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdHabitacion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblIdHabitacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblIdHabitacion.setBounds(30, 117, 244, 28);
		background.add(lblIdHabitacion);
		
		JLabel lblValorReserva = new JLabel("Valor Reserva: " + valorReserva);
		lblValorReserva.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorReserva.setFont(new Font("Arial", Font.ITALIC, 11));
		lblValorReserva.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblValorReserva.setBounds(305, 195, 244, 28);
		background.add(lblValorReserva);
		
		JLabel lblFechaEntrada = new JLabel("Fecha Entrada: " + fechaEntrada);
		lblFechaEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaEntrada.setFont(new Font("Arial", Font.ITALIC, 11));
		lblFechaEntrada.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFechaEntrada.setBounds(30, 156, 244, 28);
		background.add(lblFechaEntrada);
		
		JLabel lblFechaSalida = new JLabel("Fecha Salida: " + fechaSalida);
		lblFechaSalida.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSalida.setFont(new Font("Arial", Font.ITALIC, 11));
		lblFechaSalida.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFechaSalida.setBounds(305, 156, 244, 28);
		background.add(lblFechaSalida);
		
		btnAceptar = new JPanel();
		btnAceptar.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnAceptar.setBounds(479, 316, 70, 28);
		background.add(btnAceptar);
		btnAceptar.setLayout(null);
		
		lblAceptar = new JLabel("Aceptar");
		lblAceptar.setForeground(new Color(255, 255, 255));
		lblAceptar.setFont(new Font("Arial", Font.ITALIC, 11));
		lblAceptar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAceptar.setBounds(0, 0, 70, 28);
		lblAceptar.addMouseListener(manejoEventoMouse);
		btnAceptar.add(lblAceptar);
		
		btnCancelar = new JPanel();
		btnCancelar.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnCancelar.setBounds(397, 316, 70, 28);
		background.add(btnCancelar);
		btnCancelar.setLayout(null);
		
		lblCancelar = new JLabel("Cancelar");
		lblCancelar.setForeground(new Color(255, 255, 255));
		lblCancelar.setFont(new Font("Arial", Font.ITALIC, 11));
		lblCancelar.addMouseListener(manejoEventoMouse);
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setBounds(0, 0, 70, 28);
		btnCancelar.add(lblCancelar);
		
		JLabel lblNochesEstadia = new JLabel("Noches Estadia: " + nochesEstadia + " noches");
		lblNochesEstadia.setHorizontalAlignment(SwingConstants.CENTER);
		lblNochesEstadia.setFont(new Font("Arial", Font.ITALIC, 11));
		lblNochesEstadia.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNochesEstadia.setBounds(30, 195, 244, 28);
		background.add(lblNochesEstadia);
		
		JLabel lblDescripcion = new JLabel("Descripcion: " + habitacion.getDescripcion());
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblDescripcion.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDescripcion.setBounds(30, 239, 519, 57);
		background.add(lblDescripcion);
		
	}
}

