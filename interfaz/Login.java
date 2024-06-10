package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constantes.Constantes;
import controlador.ControladorLogin;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;

import java.awt.event.FocusAdapter;

public class Login extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel backGround;
	private JTextField txtCorreoElectronico;
	private JPasswordField passContraseña;

	private JPanel btnIrRegistrarCliente;
	private JLabel lblIrRegistrarCliente;
	private JLabel lblIniciarSesion;
	private JPanel btnIniciarSesion;
	
	
	private static final String EMAIL_EMPTY = "Ingrese Correo Electronico";
	private static final String PASSWORD_EMPTY = "IngreseContraseña";
	
	
	private void iniciarSecion() {
		ControladorLogin controlador = new ControladorLogin();
		String contraseña = new String(passContraseña.getPassword());
		String validar = controlador.iniciarSesion(txtCorreoElectronico.getText(), contraseña);
		
		if(validar == "usuario") {
			long idUsuario = controlador.recuperarIdUsuario(txtCorreoElectronico.getText());
			VistaUsuario vistaUsuario = new VistaUsuario(idUsuario);
			vistaUsuario.setVisible(true);
			dispose();
		}else if(validar == "administrador") {
			VistaAdministrador vistaAdministrador = new VistaAdministrador();
			vistaAdministrador.setVisible(true);
			dispose();
		}
	}
	

	MouseAdapter manejoEventoMouse = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == lblIniciarSesion) {
				iniciarSecion();	
			}else if(e.getSource() == lblIrRegistrarCliente) {
				RegistrarseComoCliente registrarse = new RegistrarseComoCliente();
				registrarse.setVisible(true);
				dispose();
			}
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			
			if(e.getSource() == lblIniciarSesion) {
				btnIniciarSesion.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
			}else if(e.getSource() == lblIrRegistrarCliente) {
				btnIrRegistrarCliente.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
			if(e.getSource() == lblIniciarSesion) {
				btnIniciarSesion.setBackground(Constantes.COLOR_BOTON_NORMAL);
			}else if(e.getSource() == lblIrRegistrarCliente) {
				btnIrRegistrarCliente.setBackground(Constantes.COLOR_BOTON_NORMAL);
			}
		}
		
	};

	public Login() {
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 458);
		backGround = new JPanel();
		backGround.setToolTipText("");
		backGround.setBackground(Color.WHITE);
		backGround.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(backGround);
		backGround.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(0, 0, 453, 104);
		panelTitulo.setForeground(Color.WHITE);
		panelTitulo.setBackground(new Color(18, 91, 162));
		backGround.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblBienvenida = new JLabel("Bienvenido a MyHotel...");
		lblBienvenida.setForeground(Color.WHITE);
		lblBienvenida.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblBienvenida.setBackground(Color.WHITE);
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setBounds(88, 11, 267, 56);
		panelTitulo.add(lblBienvenida);
		
		JLabel lblBienvenida2 = new JLabel("Mas que un lugar para descansar.");
		lblBienvenida2.setForeground(Color.WHITE);
		lblBienvenida2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblBienvenida2.setBounds(48, 57, 357, 34);
		panelTitulo.add(lblBienvenida2);
		
		JLabel lblCorreoElectronico = new JLabel("CORREO ELECTRONICO");
		lblCorreoElectronico.setBounds(77, 183, 166, 29);
		lblCorreoElectronico.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		backGround.add(lblCorreoElectronico);
		
		txtCorreoElectronico = new JTextField();
		txtCorreoElectronico.setBounds(91, 215, 181, 14);
		txtCorreoElectronico.setText(EMAIL_EMPTY);
		txtCorreoElectronico.setBorder(null);
		txtCorreoElectronico.setForeground(new Color(128, 128, 128));
		txtCorreoElectronico.setFont(new Font("Arial", Font.PLAIN, 12));
		backGround.add(txtCorreoElectronico);
		txtCorreoElectronico.setColumns(10);
		
		txtCorreoElectronico.addFocusListener(new FocusAdapter() {
	        public void focusGained(FocusEvent e) {
	            if (txtCorreoElectronico.getText().equals(EMAIL_EMPTY)) {
	                txtCorreoElectronico.setText("");
	                txtCorreoElectronico.setForeground(Color.BLACK);
	            }
	        }
	
	        public void focusLost(FocusEvent e) {
	            if (txtCorreoElectronico.getText().isEmpty()) {
	                txtCorreoElectronico.setText(EMAIL_EMPTY);
	                txtCorreoElectronico.setForeground(new Color(128, 128, 128));
	            }
	        }
	    });
		
		JSeparator separatorCorreo = new JSeparator();
		separatorCorreo.setBounds(91, 235, 166, 2);
		backGround.add(separatorCorreo);
		
		JLabel lblContraseña = new JLabel("CONTRASEÑA");
		lblContraseña.setBounds(77, 240, 166, 29);
		lblContraseña.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		backGround.add(lblContraseña);
		
		JSeparator separatorContraseña = new JSeparator();
		separatorContraseña.setBounds(91, 300, 166, 2);
		backGround.add(separatorContraseña);
		
		passContraseña = new JPasswordField();
		passContraseña.setBounds(91, 278, 166, 14);
		passContraseña.setFont(new Font("Arial", Font.PLAIN, 11));
		passContraseña.setForeground(new Color(128, 128, 128));
		passContraseña.setSelectionColor(new Color(192, 192, 192));
		passContraseña.setSelectedTextColor(new Color(0, 0, 0));
		passContraseña.setText(PASSWORD_EMPTY);
		passContraseña.setToolTipText("");
		passContraseña.setName("");
		passContraseña.setBorder(null);
		backGround.add(passContraseña);
		
		passContraseña.addFocusListener(new FocusAdapter() {
	        public void focusGained(FocusEvent e) {
	        	char[] password = passContraseña.getPassword();
				String contraseña = new String(password);
				if(contraseña.equals(PASSWORD_EMPTY)) {
					passContraseña.setText("");
					passContraseña.setForeground(Color.BLACK);
				}
	        }
	
	        public void focusLost(FocusEvent e) {
	        	char[] password = passContraseña.getPassword();
				String contraseña = new String(password);
				if(contraseña.isEmpty()) {
					passContraseña.setText(PASSWORD_EMPTY);
					passContraseña.setForeground(new Color(128, 128, 128));
				}
	        }
	    });
		
		JLabel lblLogin = new JLabel("Inicio De Sesion");
		lblLogin.setBounds(37, 117, 166, 45);
		lblLogin.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 17));
		backGround.add(lblLogin);
		
		btnIrRegistrarCliente = new JPanel();
		btnIrRegistrarCliente.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnIrRegistrarCliente.setBounds(262, 355, 110, 29);
		backGround.add(btnIrRegistrarCliente);
		btnIrRegistrarCliente.setLayout(null);
		
		lblIrRegistrarCliente = new JLabel("Registrarse");
		lblIrRegistrarCliente.setForeground(new Color(255, 255, 255));
		lblIrRegistrarCliente.setFont(new Font("Arial", Font.ITALIC, 11));
		lblIrRegistrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblIrRegistrarCliente.setBounds(0, 0, 110, 29);
		btnIrRegistrarCliente.add(lblIrRegistrarCliente);
		lblIrRegistrarCliente.addMouseListener(manejoEventoMouse);
		
		
		btnIniciarSesion = new JPanel();
		btnIniciarSesion.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnIniciarSesion.setBounds(77, 355, 110, 29);
		backGround.add(btnIniciarSesion);
		btnIniciarSesion.setLayout(null);
		
		lblIniciarSesion = new JLabel("Iniciar Sesion");
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setBounds(0, 0, 110, 29);
		btnIniciarSesion.add(lblIniciarSesion);
		lblIniciarSesion.addMouseListener(manejoEventoMouse);		
		
	}
}
