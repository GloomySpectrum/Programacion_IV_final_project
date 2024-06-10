package interfaz.opcionesAdministrador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import constantes.Constantes;
import interfaz.opcionesUsuario.reserva.TableroFilaReserva;
import usuario.ManejoArchivo;
import usuario.habitaciones.Habitacion;

import javax.swing.JTextField;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Panel;

public class ModificarHabitacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFiltrar;
	private JLabel lblFiltrar;
	private JPanel btnFiltrar;
	private JPanel panelContent;
	private int[] position = {0, 0};
	
	private void addLabelWithBorder(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setBorder(new LineBorder(Color.BLACK, 1));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.ITALIC, 11));
        label.setForeground(Color.WHITE);
        panel.add(label);
	}
	
	public void cargarHabitacion(String filtrar) {
		panelContent.removeAll();
		position[1] = 0;
		TableroFilaHabitacion panelHabitacion = new TableroFilaHabitacion(9);
		addLabelWithBorder(panelHabitacion, "<html><div style='text-align: center;'>Disponibilidad</div></html>");
		addLabelWithBorder(panelHabitacion, "<html><div style='text-align: center;'>ID Habitacion</div></html>");
        addLabelWithBorder(panelHabitacion, "<html><div style='text-align: center;'>Tipo Habitacion</div></html>");
        addLabelWithBorder(panelHabitacion, "<html><div style='text-align: center;'>Cantidad de Huspedes</div></html>");
        addLabelWithBorder(panelHabitacion, "<html><div style='text-align: center;'>Cantidad de Baños</div></html>");
        addLabelWithBorder(panelHabitacion, "<html><div style='text-align: center;'>Cantidad de Camas</div></html>");
        addLabelWithBorder(panelHabitacion, "<html><div style='text-align: center;'>Precio</div></html>");
        
        panelHabitacion.setBounds(position[0], position[1], 750, 50);
        panelHabitacion.setBackground(new Color(54, 154, 186));
        panelContent.add(panelHabitacion);
        
        ManejoArchivo<Habitacion> manejoArchivo = new ManejoArchivo<Habitacion>();
        List<Habitacion> habitaciones = manejoArchivo.recuperarObjeto(Constantes.RUTA_HABITACIONES);
        if(filtrar == null) {
        	filtrar = "0";
        }
        
        
        for (Habitacion habitacion : habitaciones) {
        	if(habitacion.getIdHabitacion() == Integer.parseInt(filtrar) || filtrar.equals("0")){    		
        		position[1] += 51;
        		panelHabitacion = new TableroFilaHabitacion(habitacion.getIdHabitacion(),9);
        		panelHabitacion.setSize(484, 50);
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
        		panelHabitacion.colocarBotones();
        		
        		panelHabitacion.setBounds(position[0], position[1], 750, 50);
        		panelHabitacion.setBackground(new Color(54, 154, 186));
        		
        		if (panelContent.getHeight() < position[1]) {
        			panelContent.setPreferredSize(new Dimension(750, position[1]+51));
        		}
        		
        		panelContent.add(panelHabitacion);
        	}
        }
		
		panelContent.revalidate();
        panelContent.repaint();
	}
	
	MouseAdapter manejoEventoMouse = new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == lblFiltrar) {
				if(textFiltrar.getText().isEmpty()) {
					cargarHabitacion(null);
				}else {
					cargarHabitacion(textFiltrar.getText());					
				}
			}
		}
	};
	
	public ModificarHabitacion() {

		setLayout(null);
		setSize(Constantes.WIDTH_PANEL_OPCIONES, Constantes.HEIGHT_PANEL_OPCIONES);
		
		JPanel background = new JPanel();
		background.setBackground(Color.WHITE);
		background.setBounds(0, 0, getWidth(), getHeight());
		add(background);
		background.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(0, 191, 255));
		panelTitulo.setBounds(0, 0, 708, 90);
		background.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Modificar Habitación del Inventario");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.ITALIC, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(190, 30, 327, 30);
		panelTitulo.add(lblTitulo);
		
		JLabel lblFiltar = new JLabel("<html>Filtrar por numero de habitacion:</html>");
		lblFiltar.setFont(new Font("Arial", Font.ITALIC, 11));
		lblFiltar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltar.setBounds(150, 101, 125, 25);
		background.add(lblFiltar);
		
		textFiltrar = new JTextField();
		textFiltrar.setBounds(285, 104, 86, 20);
		background.add(textFiltrar);
		textFiltrar.setColumns(10);
		
		btnFiltrar = new JPanel();
		btnFiltrar.setBounds(446, 101, 86, 22);
		background.add(btnFiltrar);
		btnFiltrar.setLayout(null);
		
		lblFiltrar = new JLabel("Filtrar");
		lblFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltrar.setFont(new Font("Arial", Font.ITALIC, 11));
		lblFiltrar.setBounds(0, 0, 86, 22);
		lblFiltrar.addMouseListener(manejoEventoMouse);
		btnFiltrar.add(lblFiltrar);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(0, 142, 708, 558);
		background.add(scrollPane);
		
		panelContent = new JPanel();
		panelContent.setSize(750, 381);
		panelContent.setPreferredSize(new Dimension(750, 381));
		panelContent.setLayout(null);
		scrollPane.add(panelContent);
		
		cargarHabitacion("0");
	}
}
