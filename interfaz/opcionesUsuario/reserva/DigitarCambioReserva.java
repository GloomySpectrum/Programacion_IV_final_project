package interfaz.opcionesUsuario.reserva;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import controlador.ComprobarValoresNumericos;
import controlador.ControladorReserva;
import controlador.ControladorUsuario;
import interfaz.Mensajes;
import usuario.habitaciones.Habitacion;
import usuario.reservas.Reserva;

import javax.swing.JTextField;
import java.awt.Window.Type;

public class DigitarCambioReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel background;
	private JTextField textNumeroHuspedes;
	
	private int idReserva;
	
	private JPanel panelContent;
	private JDateChooser dateChooserFechaSalida;
	private JDateChooser dateChooserFechaEntrada;
	
	private JPanel btnModificar;
	private JLabel lblModificar;
	
	private JPanel btnSalir;
	private JLabel lblSalir;
	
	private void addLabelWithBorder(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setBorder(new LineBorder(Color.BLACK, 1));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.ITALIC, 11));
        label.setForeground(Color.WHITE);
        panel.add(label);
	}
	
	public void mostrarReserva(Reserva reserva) {
		
		TableroFilaReserva panelReserva = new TableroFilaReserva(6);
		addLabelWithBorder(panelReserva, 
				"<html><div style='text-align: center;'>" + reserva.getIdReserva() + "</div></html>");
        addLabelWithBorder(panelReserva, 
        		"<html><div style='text-align: center;'>" + reserva.getIdHabitacion() + "</div></html>");
        addLabelWithBorder(panelReserva, 
        		"<html><div style='text-align: center;'>" + reserva.getFechaEntrada() + "</div></html>");
        addLabelWithBorder(panelReserva, 
        		"<html><div style='text-align: center;'>" + reserva.getFechaSalida() + "</div></html>");
        addLabelWithBorder(panelReserva, 
        		"<html><div style='text-align: center;'>" + reserva.getNombreUsuario() + "</div></html>");
        addLabelWithBorder(panelReserva, 
        		"<html><div style='text-align: center;'>" + reserva.getPrecio() + "</div></html>");
        panelReserva.setBackground(new Color(54, 154, 186));
        panelContent.add(panelReserva);
        
	}
	
	
	MouseAdapter manejoEventoMouse = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == lblModificar) {
				ControladorReserva controladorReserva = new ControladorReserva();
				controladorReserva.actualizarEstadoReserva();
				
				if(controladorReserva.validarCambios(idReserva, textNumeroHuspedes.getText(), dateChooserFechaEntrada.getDate(), dateChooserFechaSalida.getDate())){
					Reserva reserva = controladorReserva.recuperarReservaPorId(idReserva);
					Habitacion habitacion = controladorReserva.buscarHabitacion(Integer.parseInt(textNumeroHuspedes.getText()), dateChooserFechaEntrada.getDate(), dateChooserFechaSalida.getDate());
					panelContent.removeAll();
					mostrarReserva(reserva);
					controladorReserva.modificarReserva(reserva, habitacion, dateChooserFechaEntrada.getDate(),
							dateChooserFechaSalida.getDate());
					reserva = controladorReserva.recuperarReservaPorId(idReserva);
					mostrarReserva(reserva);
					
					panelContent.revalidate();
					panelContent.repaint();
					textNumeroHuspedes.setText("");
					dateChooserFechaEntrada.setDate(null);
					dateChooserFechaSalida.setDate(null);
					
				}
			} else if (e.getSource() == lblSalir) {
				dispose();
			}
		}

		/*@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			if (e.getSource() == lblEditar) {
				btnEditar.setBackground(Constantes.COLOR_BOTON_EDITAR_SELECCIONADO);
			}
			if (e.getSource() == lblSalir) {
				btnSalir.setBackground(Constantes.COLOR_BOTON_CANCELAR_SELECCIONADO);
			}
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			colorNormal();
		}*/
	};
	
	
	
	public DigitarCambioReserva(int idReserva) {
		setType(Type.UTILITY);
		setResizable(false);
		
		this.idReserva = idReserva;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 726, 343);
		background = new JPanel();
		background.setBackground(new Color(255, 255, 255));
		background.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(background);
		background.setLayout(null);
		
		JLabel lblTitulo = new JLabel("<html>Digite fecha de entrada, fecha de salida y numero de huspedes</html>");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 160, 51);
		background.add(lblTitulo);
		
		dateChooserFechaEntrada = new JDateChooser();
		dateChooserFechaEntrada.setDateFormatString("yyyy/MM/dd");
		dateChooserFechaEntrada.setBounds(119, 94, 122, 26);
		background.add(dateChooserFechaEntrada);
		
		JLabel lblFechaEntrada = new JLabel("<html>Digite nueva fecha de entrada:</html>");
		lblFechaEntrada.setBounds(10, 85, 99, 35);
		background.add(lblFechaEntrada);
		
		JLabel lblFechaSalida = new JLabel("<html>Digite nueva fecha de entrada:</html>");
		lblFechaSalida.setBounds(284, 85, 99, 35);
		background.add(lblFechaSalida);
		
		dateChooserFechaSalida = new JDateChooser();
		dateChooserFechaSalida.setDateFormatString("yyyy/MM/dd");
		dateChooserFechaSalida.setBounds(393, 94, 122, 26);
		background.add(dateChooserFechaSalida);
		
		JLabel lblNumeroHuspedes = new JLabel("<html>Digite numeros de huspedes:</html>");
		lblNumeroHuspedes.setBounds(548, 85, 99, 35);
		background.add(lblNumeroHuspedes);
		
		textNumeroHuspedes = new JTextField();
		textNumeroHuspedes.setBounds(659, 94, 39, 26);
		background.add(textNumeroHuspedes);
		textNumeroHuspedes.setColumns(10);
		
		panelContent = new JPanel();
		panelContent.setBackground(new Color(54, 154, 186));
		panelContent.setBounds(0, 150, 710, 102);
		background.add(panelContent);
		panelContent.setLayout(new GridLayout(2, 0));
		
		btnModificar = new JPanel();
		btnModificar.setBounds(620, 263, 78, 26);
		background.add(btnModificar);
		btnModificar.setLayout(null);
		
		lblModificar = new JLabel("Modificar");
		lblModificar.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificar.setBounds(0, 0, 78, 25);
		lblModificar.addMouseListener(manejoEventoMouse);
		btnModificar.add(lblModificar);
		
		btnSalir = new JPanel();
		btnSalir.setBounds(517, 263, 78, 26);
		background.add(btnSalir);
		btnSalir.setLayout(null);
		
		lblSalir = new JLabel("Salir");
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalir.setBounds(0, 0, 78, 26);
		lblSalir.addMouseListener(manejoEventoMouse);
		btnSalir.add(lblSalir);
	}
}
