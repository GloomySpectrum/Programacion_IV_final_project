package interfaz.opcionesUsuario.reserva;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constantes.Constantes;
import controlador.ControladorReserva;
import usuario.habitaciones.Habitacion;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JSeparator;

public class PedirNombreReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel background;
	private JTextField textNombreUsuario;
	private JLabel lblContinuar;
	private JLabel lblError;
	private JPanel btnContinuar;
	
	private Habitacion habitacion;
	private long idUsuario;
	private Date fechaEntrada;
	private Date fechaSalida;
	

	
	MouseAdapter manejoEventoMouse = new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (e.getSource() == lblContinuar) {
				if (textNombreUsuario.getText().isEmpty()) {
					lblError.setText("Digite un nombre de usuario");
				}else {
					setVisible(false);
					ControladorReserva controladorReserva = new ControladorReserva();
					controladorReserva.reservarHabitacion(fechaEntrada, fechaSalida, idUsuario, habitacion.getTipoHabitacion(), textNombreUsuario.getText());
					dispose();
				}
			}
		}
			
		@Override
		public void mouseEntered(MouseEvent e) {
            if (e.getSource() == lblContinuar) {
                btnContinuar.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
            }
        }
		
		@Override
		public void mouseExited(MouseEvent e) {
            if (e.getSource() == lblContinuar) {
                btnContinuar.setBackground(Constantes.COLOR_BOTON_NORMAL);
            }
		}
	};
	
	public PedirNombreReserva(Habitacion habitacion, long idUsuario, Date fechaEntrada, Date fechaSalida) {
		this.habitacion = habitacion;
		this.idUsuario = idUsuario;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 375, 206);
		background = new JPanel();
		background.setBackground(new Color(255, 255, 255));
		background.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(background);
		background.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("<html>Digite nombre de usuario: </html>");
		lblNombreUsuario.setFont(new Font("Arial", Font.ITALIC, 11));
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreUsuario.setBounds(27, 46, 94, 38);
		background.add(lblNombreUsuario);
		
		textNombreUsuario = new JTextField();
		textNombreUsuario.setBounds(131, 56, 218, 28);
		background.add(textNombreUsuario);
		textNombreUsuario.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(131, 82, 218, 2);
		background.add(separator);
		
		btnContinuar = new JPanel();
		btnContinuar.setBounds(138, 115, 85, 28);
		btnContinuar.setBackground(Constantes.COLOR_BOTON_NORMAL);
		background.add(btnContinuar);
		btnContinuar.setLayout(null);
		
		lblContinuar = new JLabel("Continuar");
		lblContinuar.setForeground(new Color(255, 255, 255));
		lblContinuar.setHorizontalAlignment(SwingConstants.CENTER);
		lblContinuar.setBounds(0, 0, 85, 28);
		lblContinuar.setFont(new Font("Arial", Font.ITALIC, 11));
		lblContinuar.addMouseListener(manejoEventoMouse);
		btnContinuar.add(lblContinuar);
		
		lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Arial", Font.ITALIC, 11));
		lblError.setHorizontalAlignment(SwingConstants.LEFT);
		lblError.setBounds(138, 27, 198, 14);
		background.add(lblError);
	}
}
