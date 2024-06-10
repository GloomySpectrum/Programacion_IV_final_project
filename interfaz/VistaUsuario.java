package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constantes.Constantes;
import interfaz.opcionesUsuario.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import javax.swing.JSeparator;

public class VistaUsuario extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel backGround;
	private JPanel content = new JPanel();
	
	private JLabel botonSeleccionado;
	private JPanel btnHabitacionesDisponibles;
	private JPanel btnHistorialReservas;
	
	private JLabel lblHabitacionesDisponibles;
	private JLabel lblHistorialReservas;
	
	private long idUsuario;
	
	MouseAdapter manejoEventoMouse = new MouseAdapter() {
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == lblHabitacionesDisponibles) {
				botonSeleccionado = lblHabitacionesDisponibles;
				colorNormal();
				btnHabitacionesDisponibles.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
				HabitacionesDisponibles habitacionesDisponible = new HabitacionesDisponibles(idUsuario, true, false);
				mostrarPaneles(habitacionesDisponible);
			} else if (e.getSource() == lblHistorialReservas) {
				botonSeleccionado = lblHistorialReservas;
				colorNormal();
				btnHistorialReservas.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
				HistorialReservas historialReservas = new HistorialReservas(idUsuario);
				mostrarPaneles(historialReservas);
			}
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == lblHabitacionesDisponibles) {
				if(botonSeleccionado != lblHabitacionesDisponibles) {
					btnHabitacionesDisponibles.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
				}				
			} else if (e.getSource() == lblHistorialReservas) {
				if (botonSeleccionado != lblHistorialReservas) {
					btnHistorialReservas.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
				}
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == lblHabitacionesDisponibles) {
				if(botonSeleccionado != lblHabitacionesDisponibles) {
					btnHabitacionesDisponibles.setBackground(Constantes.COLOR_BOTON_NORMAL);
				}				
			} else if (e.getSource() == lblHistorialReservas) {
				if (botonSeleccionado != lblHistorialReservas) {
					btnHistorialReservas.setBackground(Constantes.COLOR_BOTON_NORMAL);
				}
			}
		}
	};
	
	public void mostrarPaneles(JPanel panel) {
		panel.setSize(Constantes.WIDTH_PANEL_OPCIONES, Constantes.HEIGHT_PANEL_OPCIONES);
		panel.setLocation(0,0);
		
		content.removeAll();
		content.add(panel, BorderLayout.CENTER);
		content.revalidate();
		content.repaint();
	}
	
	public void colorNormal() {
		btnHabitacionesDisponibles.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnHistorialReservas.setBackground(Constantes.COLOR_BOTON_NORMAL);
	}


	public VistaUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
		
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 929, 736);
		
		backGround = new JPanel();
		backGround.setBackground(Color.WHITE);
		backGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(backGround);
		backGround.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(13, 72, 162));
		panelMenu.setBounds(0, 0, 211, 700);
		backGround.add(panelMenu);
		panelMenu.setLayout(null);
		
		// Boton Habitaciones Disponibles
		btnHabitacionesDisponibles = new JPanel();
		btnHabitacionesDisponibles.setBounds(0, 347, 211, 39);
		btnHabitacionesDisponibles.setLayout(null);
		btnHabitacionesDisponibles.setBackground(Constantes.COLOR_BOTON_NORMAL);
		panelMenu.add(btnHabitacionesDisponibles);
		
		lblHabitacionesDisponibles = new JLabel("Habitaciones Disponibles");
		lblHabitacionesDisponibles.setFont(new Font("Arial", Font.ITALIC, 11));
		lblHabitacionesDisponibles.setForeground(Color.WHITE);
		lblHabitacionesDisponibles.setBackground(new Color(18, 91, 173));
		lblHabitacionesDisponibles.addMouseListener(manejoEventoMouse);
		lblHabitacionesDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		lblHabitacionesDisponibles.setBounds(0, 0, 211, 39);
		btnHabitacionesDisponibles.add(lblHabitacionesDisponibles);
		
		// Boton Historial de Reservas
		btnHistorialReservas = new JPanel();
		btnHistorialReservas.setBounds(0, 387, 211, 39);
		btnHistorialReservas.setLayout(null);
		btnHistorialReservas.setBackground(Constantes.COLOR_BOTON_NORMAL);
		panelMenu.add(btnHistorialReservas);
		
		lblHistorialReservas = new JLabel("Historial de Reservas");
		lblHistorialReservas.setFont(new Font("Arial", Font.ITALIC, 11));
		lblHistorialReservas.setForeground(Color.WHITE);
		
		lblHistorialReservas.addMouseListener(manejoEventoMouse);
		lblHistorialReservas.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialReservas.setBounds(0, 0, 211, 39);
		btnHistorialReservas.add(lblHistorialReservas);
		//Fin botones
		
		JSeparator separator = new JSeparator();
		separator.setBounds(43, 147, 137, 2);
		panelMenu.add(separator);
		
		JLabel lblTitulo = new JLabel("MyHotel");
		lblTitulo.setBounds(0, 115, 211, 21);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.ITALIC, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelMenu.add(lblTitulo);
		
		content.setBounds(211, 0, Constantes.WIDTH_PANEL_OPCIONES, Constantes.HEIGHT_PANEL_OPCIONES);
		content.setLayout(null);
		backGround.add(content);

		
		HabitacionesDisponibles habitacionesDisponibles = new HabitacionesDisponibles(idUsuario, true, false);

		mostrarPaneles(habitacionesDisponibles);
		
		
		botonSeleccionado = lblHabitacionesDisponibles;
		btnHabitacionesDisponibles.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
		
		JLabel lblCliente= new JLabel("Cliente");
		lblCliente.setBounds(0, 559, 211, 21);
		lblCliente.setForeground(Color.WHITE);
		lblCliente.setFont(new Font("Arial", Font.ITALIC, 13));
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		panelMenu.add(lblCliente);
		
		

	}
}
