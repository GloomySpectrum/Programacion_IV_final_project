package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import constantes.Constantes;
import interfaz.opcionesAdministrador.*;
import interfaz.opcionesUsuario.HabitacionesDisponibles;

public class VistaAdministrador extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel backGround;
	private JPanel content = new JPanel();
	private JLabel botonSeleciondo;
	private JPanel btnVerificarDisponibilidad;
	private JPanel btnAgregarHabitacion;
	private JPanel btnModificarHabitacion;
	private JLabel lblVerificarDisponibilidad;
	private JLabel lblModificarHabitacion;
	private JLabel lblAgregarHabitacionInventario;
	
	
	public void mostrarPaneles(JPanel panel) {
		panel.setSize(702, 697);
		panel.setLocation(0,0);
		
		content.removeAll();
		content.add(panel, BorderLayout.CENTER);
		content.revalidate();
		content.repaint();
	}
	
	public void colorNormal() {
		btnVerificarDisponibilidad.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnAgregarHabitacion.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnModificarHabitacion.setBackground(Constantes.COLOR_BOTON_NORMAL);
	}

	/**
	 * Create the frame.
	 */
	public VistaAdministrador() {
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
		panelMenu.setBounds(0, 0, 211, 697);
		backGround.add(panelMenu);
		panelMenu.setLayout(null);
		
		btnVerificarDisponibilidad = new JPanel();
		btnVerificarDisponibilidad.setLayout(null);
		btnVerificarDisponibilidad.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnVerificarDisponibilidad.setBounds(1, 319, 211, 39);
		panelMenu.add(btnVerificarDisponibilidad);
		
		lblVerificarDisponibilidad = new JLabel("Verificar Disponibilidad");
		lblVerificarDisponibilidad.setFont(new Font("Arial", Font.ITALIC, 11));
		lblVerificarDisponibilidad.setForeground(Color.WHITE);
		lblVerificarDisponibilidad.setBackground(new Color(18, 91, 173));
		lblVerificarDisponibilidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				botonSeleciondo = lblVerificarDisponibilidad;
				colorNormal();
				btnVerificarDisponibilidad.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
				HabitacionesDisponibles habitacionesDisponibles = new HabitacionesDisponibles(0, false, true);
				mostrarPaneles(habitacionesDisponibles);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(botonSeleciondo != lblVerificarDisponibilidad) {
					btnVerificarDisponibilidad.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(botonSeleciondo != lblVerificarDisponibilidad) {
					btnVerificarDisponibilidad.setBackground(Constantes.COLOR_BOTON_NORMAL);
				}
			}
		});
		lblVerificarDisponibilidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerificarDisponibilidad.setBounds(0, 0, 211, 39);
		btnVerificarDisponibilidad.add(lblVerificarDisponibilidad);
		
		btnAgregarHabitacion = new JPanel();
		btnAgregarHabitacion.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnAgregarHabitacion.setBounds(1, 358, 211, 39);
		panelMenu.add(btnAgregarHabitacion);
		btnAgregarHabitacion.setLayout(null);
		
		lblAgregarHabitacionInventario = new JLabel("Agregar Habitación");
		lblAgregarHabitacionInventario.setFont(new Font("Arial", Font.ITALIC, 11));
		lblAgregarHabitacionInventario.setForeground(Color.WHITE);
		lblAgregarHabitacionInventario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				botonSeleciondo = lblAgregarHabitacionInventario;
				colorNormal();
				btnAgregarHabitacion.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
				AgregarHabitacion agregarHabitacion = new AgregarHabitacion();
				mostrarPaneles(agregarHabitacion);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(botonSeleciondo != lblAgregarHabitacionInventario) {
					btnAgregarHabitacion.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);				
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(botonSeleciondo != lblAgregarHabitacionInventario) {
					btnAgregarHabitacion.setBackground(Constantes.COLOR_BOTON_NORMAL);		
				}
			}
		});
		lblAgregarHabitacionInventario.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarHabitacionInventario.setBounds(0, 0, 211, 39);
		btnAgregarHabitacion.add(lblAgregarHabitacionInventario);
		
		btnModificarHabitacion = new JPanel();
		btnModificarHabitacion.setLayout(null);
		btnModificarHabitacion.setBackground(Constantes.COLOR_BOTON_NORMAL);
		btnModificarHabitacion.setBounds(0, 397, 211, 39);
		panelMenu.add(btnModificarHabitacion);
		
		lblModificarHabitacion = new JLabel("Modificar Habitacion");
		lblModificarHabitacion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblModificarHabitacion.setForeground(Color.WHITE);
		lblModificarHabitacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				botonSeleciondo = lblModificarHabitacion;
				colorNormal();
				btnModificarHabitacion.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
				ModificarHabitacion modificarHabitacion = new ModificarHabitacion();
				mostrarPaneles(modificarHabitacion);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (botonSeleciondo != lblModificarHabitacion) {
					btnModificarHabitacion.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (botonSeleciondo != lblModificarHabitacion) {
					btnModificarHabitacion.setBackground(Constantes.COLOR_BOTON_NORMAL);
				}
			}
		});
		lblModificarHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarHabitacion.setBounds(0, 0, 211, 39);
		btnModificarHabitacion.add(lblModificarHabitacion);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(40, 156, 137, 2);
		panelMenu.add(separator);
		
		JLabel lblTitulo = new JLabel("MyHotel");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.ITALIC, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(2, 124, 210, 21);
		panelMenu.add(lblTitulo);
		
		content.setBounds(211, 0, 702, 697);
		content.setLayout(null);
		HabitacionesDisponibles habitacionesDisponibles = new HabitacionesDisponibles(0, false, true);
		mostrarPaneles(habitacionesDisponibles);
		
		botonSeleciondo = lblVerificarDisponibilidad;
		btnVerificarDisponibilidad.setBackground(Constantes.COLOR_BOTON_SELECCIONADO);
		
		JLabel labelAdministrador = new JLabel("Administrador");
		labelAdministrador.setForeground(Color.WHITE);
		labelAdministrador.setFont(new Font("Arial", Font.ITALIC, 13));
		labelAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		labelAdministrador.setBounds(2, 544, 210, 21);
		panelMenu.add(labelAdministrador);
		
		backGround.add(content);

	}

}
