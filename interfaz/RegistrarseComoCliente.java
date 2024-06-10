package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import constantes.Constantes;
import controlador.ControladorRegistroUsuario;
import usuario.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class RegistrarseComoCliente extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel backGround;
	private JTextField textDireccionResidencia;
	private JTextField textCiudadResidencia;
	private JTextField textTelefonoContacto;
	private JTextField textDocumentoIdentificacion;
	private JTextField textNombres;
	private JTextField textCorreoElectronico;
	private JTextField textApellidos;
	private JPasswordField passwordFieldContraseña;
	private JPasswordField passwordFieldConfirmarContraseña;
	private JComboBox<String> cboTipoIdentificacion;
	
	private JPanel btnRegistrar;
	private JLabel lblRegistrar;
	private JPanel btnIniciarSesion;
	private JLabel lblIniciarSesion;
	
	
	
	private Usuario guardarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setTipoIdentificacion(this.cboTipoIdentificacion.getSelectedItem().toString());
		usuario.setDocumentoIdentificacion(this.textDocumentoIdentificacion.getText());
		usuario.setNombres(this.textNombres.getText());
		usuario.setApellidos(this.textApellidos.getText());
		usuario.setCorreoElectronico(this.textCorreoElectronico.getText());
		usuario.setDireccionResidencia(this.textDireccionResidencia.getText());
		usuario.setCiudadResidencia(this.textCiudadResidencia.getText());
		usuario.setTelofonoContacto(this.textTelefonoContacto.getText());
		usuario.setContraseña(new String(this.passwordFieldContraseña.getPassword()));
		return usuario;
	}
	
	
	MouseAdapter manejoEventoMouse = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == lblIniciarSesion) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}else if(e.getSource() == lblRegistrar) {
				ControladorRegistroUsuario controlador = new ControladorRegistroUsuario();
				String confirmarContraseña = new String(passwordFieldConfirmarContraseña.getPassword());
				if(controlador.registrarCliente(guardarUsuario(), confirmarContraseña)) {
					vaciarInputs();
				}
			}
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			
			if(e.getSource() == lblIniciarSesion) {
				btnIniciarSesion.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
			}else if(e.getSource() == lblRegistrar) {
				btnRegistrar.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
			if(e.getSource() == lblIniciarSesion) {
				btnIniciarSesion.setBackground(Constantes.COLOR_BOTON_NORMAL);
			}else if(e.getSource() == lblRegistrar) {
				btnRegistrar.setBackground(Constantes.COLOR_BOTON_NORMAL);
			}
		}
		
	};
	
	public void vaciarInputs() {
		this.textDocumentoIdentificacion.setText("");
		this.textNombres.setText("");
		this.textApellidos.setText("");
		this.textDireccionResidencia.setText("");
		this.textCiudadResidencia.setText("");
		this.textTelefonoContacto.setText("");
		this.textCorreoElectronico.setText("");
		this.passwordFieldContraseña.setText("");
		this.passwordFieldConfirmarContraseña.setText("");
	}
	
	
	public RegistrarseComoCliente() {
		setType(Type.POPUP);
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 392);
		backGround = new JPanel();
		backGround.setBackground(Color.WHITE);
		backGround.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(backGround);
		backGround.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(18, 91, 162));
		panelTitulo.setBounds(0, 0, 716, 104);
		backGround.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registrarse como Cliente");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(246, 33, 294, 60);
		panelTitulo.add(lblTitulo);
		
		JLabel lblTitulo_2 = new JLabel("MyHotel");
		lblTitulo_2.setForeground(Color.WHITE);
		lblTitulo_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblTitulo_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo_2.setBounds(246, 11, 294, 30);
		panelTitulo.add(lblTitulo_2);
		
		textDireccionResidencia = new JTextField();
		textDireccionResidencia.setFont(new Font("Arial", Font.PLAIN, 12));
		textDireccionResidencia.setColumns(10);
		textDireccionResidencia.setBounds(568, 130, 127, 20);
		backGround.add(textDireccionResidencia);
		
		textCiudadResidencia = new JTextField();
		textCiudadResidencia.setFont(new Font("Arial", Font.PLAIN, 12));
		textCiudadResidencia.setBounds(568, 155, 127, 20);
		backGround.add(textCiudadResidencia);
		textCiudadResidencia.setColumns(10);
		
		textTelefonoContacto = new JTextField();
		textTelefonoContacto.setFont(new Font("Arial", Font.PLAIN, 12));
		textTelefonoContacto.setBounds(568, 180, 127, 20);
		backGround.add(textTelefonoContacto);
		textTelefonoContacto.setColumns(10);
		
		textDocumentoIdentificacion = new JTextField();
		textDocumentoIdentificacion.setFont(new Font("Arial", Font.PLAIN, 12));
		textDocumentoIdentificacion.setBounds(221, 155, 127, 20);
		backGround.add(textDocumentoIdentificacion);
		textDocumentoIdentificacion.setColumns(10);
		
		textNombres = new JTextField();
		textNombres.setFont(new Font("Arial", Font.PLAIN, 12));
		textNombres.setBounds(221, 180, 127, 20);
		backGround.add(textNombres);
		textNombres.setColumns(10);
		
		textCorreoElectronico = new JTextField();
		textCorreoElectronico.setFont(new Font("Arial", Font.PLAIN, 12));
		textCorreoElectronico.setBounds(221, 230, 127, 20);
		backGround.add(textCorreoElectronico);
		textCorreoElectronico.setColumns(10);
		
		textApellidos = new JTextField();
		textApellidos.setFont(new Font("Arial", Font.PLAIN, 12));
		textApellidos.setBounds(221, 205, 127, 20);
		backGround.add(textApellidos);
		textApellidos.setColumns(10);
		
		JLabel lblDireccionResidencia = new JLabel("Direccion de residencia:");
		lblDireccionResidencia.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblDireccionResidencia.setBounds(410, 132, 148, 14);
		backGround.add(lblDireccionResidencia);
		
		JLabel lblCiudadResidencia = new JLabel("Ciudad de residencia:");
		lblCiudadResidencia.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblCiudadResidencia.setBounds(425, 157, 133, 14);
		backGround.add(lblCiudadResidencia);
		
		JLabel lblTipoIdentificacion = new JLabel("Tipo de Identificación:");
		lblTipoIdentificacion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTipoIdentificacion.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblTipoIdentificacion.setBounds(56, 128, 148, 20);
		backGround.add(lblTipoIdentificacion);
		
		JLabel lblDocumentoIdentificacion = new JLabel("Documento de \r\nidentificación:");
		lblDocumentoIdentificacion.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblDocumentoIdentificacion.setBounds(39, 154, 172, 20);
		backGround.add(lblDocumentoIdentificacion);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblNombres.setBounds(147, 182, 57, 14);
		backGround.add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblApellidos.setBounds(147, 207, 57, 14);
		backGround.add(lblApellidos);
		
		JLabel lblCorreoElectronico = new JLabel("Correo electronico:");
		lblCorreoElectronico.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblCorreoElectronico.setBounds(92, 232, 111, 14);
		backGround.add(lblCorreoElectronico);
		
		JLabel lblTelefono = new JLabel("Telefono de contacto:");
		lblTelefono.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblTelefono.setBounds(425, 182, 133, 14);
		backGround.add(lblTelefono);
		
		cboTipoIdentificacion = new JComboBox<String>();
		cboTipoIdentificacion.setFont(new Font("Arial", Font.PLAIN, 11));
		cboTipoIdentificacion.setForeground(new Color(0, 0, 0));
		cboTipoIdentificacion.setBackground(new Color(255, 255, 255));
		cboTipoIdentificacion.setToolTipText("");
		cboTipoIdentificacion.setModel(new DefaultComboBoxModel<String>(new String[] {"Cédula de ciudadanía", "Tarjeta de identidad", "Cédula de extranjería", "Pasaporte"}));
		cboTipoIdentificacion.setBounds(221, 129, 130, 20);
		backGround.add(cboTipoIdentificacion);
		
		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblContraseña.setBounds(475, 208, 83, 14);
		backGround.add(lblContraseña);
		
		passwordFieldContraseña = new JPasswordField();
		passwordFieldContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		passwordFieldContraseña.setBounds(568, 205, 127, 20);
		backGround.add(passwordFieldContraseña);
		
		JLabel lblConfirmarContraseña = new JLabel("Confirmar Contraseña:");
		lblConfirmarContraseña.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblConfirmarContraseña.setBounds(415, 233, 143, 14);
		backGround.add(lblConfirmarContraseña);
		
		passwordFieldConfirmarContraseña = new JPasswordField();
		passwordFieldConfirmarContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		passwordFieldConfirmarContraseña.setBounds(568, 230, 127, 20);
		backGround.add(passwordFieldConfirmarContraseña);
		
		btnRegistrar = new JPanel();
		btnRegistrar.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnRegistrar.setBounds(338, 289, 119, 23);
		backGround.add(btnRegistrar);
		btnRegistrar.setLayout(null);
		
		lblRegistrar = new JLabel("Registrarse");
		lblRegistrar.setForeground(Color.WHITE);
		lblRegistrar.setFont(new Font("Arial", Font.ITALIC, 11));
		lblRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar.setBounds(0, 0, 119, 23);
		btnRegistrar.add(lblRegistrar);
		lblRegistrar.addMouseListener(manejoEventoMouse);
		
		btnIniciarSesion = new JPanel();
		btnIniciarSesion.setLayout(null);
		btnIniciarSesion.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnIniciarSesion.setBounds(576, 289, 119, 23);
		backGround.add(btnIniciarSesion);
		
		lblIniciarSesion = new JLabel("Iniciar Sesion");
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblIniciarSesion.setBounds(0, 0, 119, 23);
		btnIniciarSesion.add(lblIniciarSesion);
		lblIniciarSesion.addMouseListener(manejoEventoMouse);
		
	}

}
