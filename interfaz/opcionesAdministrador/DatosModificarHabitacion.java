package interfaz.opcionesAdministrador;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controlador.ControladorAdministrador;
import usuario.habitaciones.Habitacion;

import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class DatosModificarHabitacion extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel background;
	private JTextField textPrecio;
	private JPanel panelContent;
	private JComboBox<String> cboDisponibilidad;
	private JTextArea textArea;
	
	private JPanel btnModificar;
	private JLabel lblModificar;
	
	private JPanel btnCancelar;
	private JLabel lblCancelar;
	private int[] position = {0,0};
	
	private int idHabitacion;
	
	private void addLabelWithBorder(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setBorder(new LineBorder(Color.BLACK, 1));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.ITALIC, 11));
        label.setForeground(Color.WHITE);
        panel.add(label);
	}
	
	public void mostrarHabitacion(int idHabitacion) {
		ControladorAdministrador controladorAdministrador = new ControladorAdministrador();
		Habitacion habitacion = controladorAdministrador.recuperarHabitacionPorId(idHabitacion);
		
		TableroFilaHabitacion panelHabitacion = new TableroFilaHabitacion(7);
		addLabelWithBorder(panelHabitacion,
				"<html><div style='text-align: center;'>" + habitacion.getDisponibilidad() + "</div></html>");
		addLabelWithBorder(panelHabitacion,
				"<html><div style='text-align: center;'>" + habitacion.getIdHabitacion() + "</div></html>");
		addLabelWithBorder(panelHabitacion,
				"<html><div style='text-align: center;'>" + habitacion.getTipoHabitacion() + "</div></html>");
		addLabelWithBorder(panelHabitacion,
				"<html><div style='text-align: center;'>" + habitacion.getCantidadHuspedes() + "</div></html>");
		addLabelWithBorder(panelHabitacion,
				"<html><div style='text-align: center;'>" + habitacion.getCantidadBaños() + "</div></html>");
		addLabelWithBorder(panelHabitacion,
				"<html><div style='text-align: center;'>" + habitacion.getCantidadCamas() + "</div></html>");
		addLabelWithBorder(panelHabitacion,
				"<html><div style='text-align: center;'>" + habitacion.getPrecio() + "</div></html>");
		panelHabitacion.setBounds(position[0], position[1], 710, 50);
		panelHabitacion.setBackground(new Color(54, 154, 186));
		panelContent.add(panelHabitacion);
		position[1] += 51;
	}
	
	MouseAdapter manejoEventoMouse = new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// Las validaciones se deben hacer en el controlador
			if (e.getSource() == lblModificar) {
				ControladorAdministrador controladorAdministrador = new ControladorAdministrador();
				panelContent.removeAll();
				mostrarHabitacion(idHabitacion);
				controladorAdministrador.modificarHabitacion(idHabitacion, cboDisponibilidad.getSelectedItem().toString(),textArea.getText() ,textPrecio.getText());
				mostrarHabitacion(idHabitacion);
				panelContent.repaint();
				panelContent.revalidate();
				
			} else if (e.getSource() == lblCancelar) {
				dispose();
			}
		}
	};
	
	public DatosModificarHabitacion(int idHabitacion) {
		
		this.idHabitacion = idHabitacion;
		
		setType(Type.UTILITY);
		setResizable(false);
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 726, 343);
		background = new JPanel();
		background.setBackground(new Color(255, 255, 255));
		background.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(background);
		background.setLayout(null);
		
		JLabel lblTitulo = new JLabel("<html>Digite los valores a cambiar</html>");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 160, 51);
		background.add(lblTitulo);
		
		JLabel lblDisponibilidad = new JLabel("<html>Disponibilidad :</html>");
		lblDisponibilidad.setFont(new Font("Arial", Font.ITALIC, 11));
		lblDisponibilidad.setBounds(10, 85, 78, 35);
		background.add(lblDisponibilidad);
		
		JLabel lblDescripcion = new JLabel("<html>Digite nueva descripcion:</html>");
		lblDescripcion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblDescripcion.setBounds(238, 85, 78, 35);
		background.add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("<html>Digite nuevo precio de la habitacion:</html>");
		lblPrecio.setFont(new Font("Arial", Font.ITALIC, 11));
		lblPrecio.setBounds(448, 85, 109, 35);
		background.add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setBounds(567, 89, 78, 26);
		background.add(textPrecio);
		textPrecio.setColumns(10);
		
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
		
		btnCancelar = new JPanel();
		btnCancelar.setBounds(517, 263, 78, 26);
		background.add(btnCancelar);
		btnCancelar.setLayout(null);
		
		lblCancelar = new JLabel("Cancelar");
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setBounds(0, 0, 78, 26);
		lblCancelar.addMouseListener(manejoEventoMouse);
		btnCancelar.add(lblCancelar);
		
		JLabel lblNota = new JLabel("<html> Tener en cuanta: Si se cambia la descripcion de la habitacion, se cambiaran todas las descripciones de las habitaciones que sean del mismo tipo</html>");
		lblNota.setForeground(new Color(255, 0, 0));
		lblNota.setFont(new Font("Arial", Font.ITALIC, 11));
		lblNota.setBounds(393, 11, 264, 51);
		background.add(lblNota);
		
		cboDisponibilidad = new JComboBox<String>();
		cboDisponibilidad.setFont(new Font("Arial", Font.PLAIN, 11));
		cboDisponibilidad.setForeground(new Color(0, 0, 0));
		cboDisponibilidad.setBackground(new Color(255, 255, 255));
		cboDisponibilidad.setToolTipText("");
		cboDisponibilidad.setModel(new DefaultComboBoxModel<String>(new String[] {"Disponible", "No Disponible"}));
		cboDisponibilidad.setBounds(92, 89, 130, 27);
		background.add(cboDisponibilidad);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(325, 85, 100, 35);
		background.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(353, 90, 5, 22);
		scrollPane.add(textArea);
		
	}
}
