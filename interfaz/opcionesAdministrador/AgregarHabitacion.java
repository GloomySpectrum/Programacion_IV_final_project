package interfaz.opcionesAdministrador;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.EtchedBorder;

import constantes.Constantes;
import controlador.ControladorAdministrador;

public class AgregarHabitacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textNumeroHabitacion;
	private JTextField textTipoHabitacion;
	private JTextField textCantidad;
	private JTextField textCantidadBaños;
	private JTextField textPrecio;
	private JTextArea textDescripcion;
	private JPanel btnIngresarHabitacion;
	private JLabel lblIngresarHabitacion;

	
	
	MouseAdapter manejoEventoMouse = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == lblIngresarHabitacion) {
				ControladorAdministrador controlador = new ControladorAdministrador();
				controlador.agregarHabitacion(textNumeroHabitacion.getText(), textTipoHabitacion.getText(), 
						textDescripcion.getText(), textPrecio.getText(), textCantidad.getText(), textCantidadBaños.getText());
			}
		}
	};
	
	public AgregarHabitacion() {

		setLayout(null);
		setSize(Constantes.WIDTH_PANEL_OPCIONES, Constantes.HEIGHT_PANEL_OPCIONES);
		
		JPanel background = new JPanel();
		background.setBackground(Color.WHITE);
		background.setBounds(0, 0, 708, 689);
		add(background);
		background.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(0, 191, 255));
		panelTitulo.setBounds(0, 0, 708, 90);
		background.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Agregar Habitación al Inventario");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.ITALIC, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(227, 29, 319, 30);
		panelTitulo.add(lblTitulo);
		
		JLabel lblNumeroHabitacion = new JLabel("<html> Digite el numero de la habitacion: </html>");
		lblNumeroHabitacion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblNumeroHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroHabitacion.setBounds(102, 252, 94, 48);
		background.add(lblNumeroHabitacion);
		
		textNumeroHabitacion = new JTextField();
		textNumeroHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		textNumeroHabitacion.setFont(new Font("Arial", Font.ITALIC, 11));
		textNumeroHabitacion.setBounds(206, 265, 86, 20);
		background.add(textNumeroHabitacion);
		textNumeroHabitacion.setColumns(10);
		
		JLabel lblTipoHabitacion = new JLabel("<html> Digite el tipo de habitacion: </html>");
		lblTipoHabitacion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblTipoHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoHabitacion.setBounds(331, 252, 94, 48);
		background.add(lblTipoHabitacion);
		
		textTipoHabitacion = new JTextField();
		textTipoHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		textTipoHabitacion.setFont(new Font("Arial", Font.ITALIC, 11));
		textTipoHabitacion.setColumns(10);
		textTipoHabitacion.setBounds(435, 265, 131, 20);
		background.add(textTipoHabitacion);
		
		JLabel lblCapacidad = new JLabel("<html> Digite la capacidad de huspedes : </html>");
		lblCapacidad.setFont(new Font("Arial", Font.ITALIC, 11));
		lblCapacidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapacidad.setBounds(102, 335, 94, 48);
		background.add(lblCapacidad);
		
		textCantidad = new JTextField();
		textCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		textCantidad.setFont(new Font("Arial", Font.ITALIC, 11));
		textCantidad.setColumns(10);
		textCantidad.setBounds(206, 348, 86, 20);
		background.add(textCantidad);
		
		JLabel lblCantidadBaños = new JLabel("<html> Digite la cantidad de baños: </html>");
		lblCantidadBaños.setFont(new Font("Arial", Font.ITALIC, 11));
		lblCantidadBaños.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadBaños.setBounds(331, 335, 94, 48);
		background.add(lblCantidadBaños);
		
		textCantidadBaños = new JTextField();
		textCantidadBaños.setHorizontalAlignment(SwingConstants.CENTER);
		textCantidadBaños.setFont(new Font("Arial", Font.ITALIC, 11));
		textCantidadBaños.setColumns(10);
		textCantidadBaños.setBounds(435, 348, 86, 20);
		background.add(textCantidadBaños);
		
		JLabel lblPrecio = new JLabel("<html> Digite el precio por noche de la habitacion: </html>");
		lblPrecio.setFont(new Font("Arial", Font.ITALIC, 11));
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setBounds(102, 413, 94, 48);
		background.add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		textPrecio.setFont(new Font("Arial", Font.ITALIC, 11));
		textPrecio.setColumns(10);
		textPrecio.setBounds(206, 426, 86, 20);
		background.add(textPrecio);
		
		JLabel lblDescripcion = new JLabel("<html> Digite una descripcion para la habitacion:  </html>");
		lblDescripcion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(331, 413, 94, 59);
		background.add(lblDescripcion);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(435, 432, 145, 40);
		background.add(scrollPane);
		
		textDescripcion = new JTextArea();
		textDescripcion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textDescripcion.setFont(new Font("Arial", Font.ITALIC, 11));
		textDescripcion.setBounds(343, 307, 5, 22);
		scrollPane.add(textDescripcion);
		
		btnIngresarHabitacion = new JPanel();
		btnIngresarHabitacion.setBounds(289, 530, 86, 20);
		background.add(btnIngresarHabitacion);
		btnIngresarHabitacion.setLayout(null);
		
		lblIngresarHabitacion = new JLabel("Registrar");
		lblIngresarHabitacion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblIngresarHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresarHabitacion.addMouseListener(manejoEventoMouse);
		lblIngresarHabitacion.setBounds(0, 0, 86, 20);
		btnIngresarHabitacion.add(lblIngresarHabitacion);
	}
}
