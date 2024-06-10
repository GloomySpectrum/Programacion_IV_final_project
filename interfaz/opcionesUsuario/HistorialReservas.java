package interfaz.opcionesUsuario;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import constantes.Constantes;
import controlador.ControladorReserva;
import interfaz.opcionesUsuario.reserva.TableroFilaReserva;

import java.awt.ScrollPane;
import java.util.List;

import usuario.reservas.Reserva;

public class HistorialReservas extends JPanel {

	private static final long serialVersionUID = 1L;
	private long idUsuario;
	private JPanel panelContent = new JPanel();
	
	private int[] position = {0, 0};
	
	private void addLabelWithBorder(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setBorder(new LineBorder(Color.BLACK, 1));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.ITALIC, 11));
        label.setForeground(Color.WHITE);
        panel.add(label);
	}
	
	public void mostrarHistorialReservas() {
		panelContent.removeAll();
		position[1] = 0;
		TableroFilaReserva panelReserva = new TableroFilaReserva(9);
		addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>ID Reserva</div></html>");
        addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>ID Habitación</div></html>");
        addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>Fecha Entrada</div></html>");
        addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>Fecha Salida</div></html>");
        addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>Nombre Usuario</div></html>");
        addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>Precio Reserva</div></html>");
        addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>Estado Reserva</div></html>");
        
        panelReserva.setBounds(position[0], position[1], 750, 50);
        panelReserva.setBackground(new Color(54, 154, 186));
        panelContent.add(panelReserva);
        
        ControladorReserva controladorReserva = new ControladorReserva();
        controladorReserva.actualizarEstadoReserva();
        
        List<Reserva> reservas = controladorReserva.obtenerReservasPorUsuario(idUsuario);
        if (reservas == null) {
			return;
		}
        
		
		for (Reserva reserva : reservas) {
			position[1] += 51;
        	panelReserva = new TableroFilaReserva(reserva.getIdReserva(), 9);
            addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>" + reserva.getIdReserva() + "</div></html>");
            addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>" + reserva.getIdHabitacion() + "</div></html>");
            addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>" + reserva.getFechaEntrada() + "</div></html>");
            addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>" + reserva.getFechaSalida() + "</div></html>");
            addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>" + reserva.getNombreUsuario() + "</div></html>");
            addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>" + reserva.getPrecio() + "</div></html>");
            addLabelWithBorder(panelReserva, "<html><div style='text-align: center;'>" + reserva.getEstadoReserva() + "</div></html>");
            panelReserva.colocarBotones();
            panelReserva.setBounds(position[0], position[1], 750, 50);
            panelReserva.setBackground(new Color(84, 184, 216));
			
            panelContent.add(panelReserva);
            
			if (panelContent.getHeight() < position[1]) {
				panelContent.setPreferredSize(new Dimension(750, position[1]));
			}
            
        }
        
        panelContent.revalidate();
        panelContent.repaint();
	}
	
	

	/**
	 * Create the panelContent.
	 */
	public HistorialReservas(long idUsuario) {
		
		this.idUsuario = idUsuario;
		
		setLayout(null);
		setSize(Constantes.WIDTH_PANEL_OPCIONES, Constantes.HEIGHT_PANEL_OPCIONES);
		
		JPanel background = new JPanel();
		background.setBackground(Color.WHITE);
		background.setBounds(0, 0, getWidth(), getHeight());
		add(background);
		background.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(0, 0, 708, 90);
		panelTitulo.setLayout(null);
		panelTitulo.setBackground(new Color(0, 191, 255));
		background.add(panelTitulo);
		
		JLabel lblHistorialDeReservas = new JLabel("Historial de Reservas");
		lblHistorialDeReservas.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialDeReservas.setForeground(Color.WHITE);
		lblHistorialDeReservas.setFont(new Font("Arial", Font.ITALIC, 20));
		lblHistorialDeReservas.setBounds(223, 30, 252, 30);
		panelTitulo.add(lblHistorialDeReservas);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(0, 85, 708, 615);
		background.add(scrollPane);
		scrollPane.add(panelContent);
		
		panelContent.setLayout(null);
		panelContent.setBackground(new Color(54, 154, 186));
		panelContent.setSize(750, 50);
		panelContent.setPreferredSize(new Dimension(750, 50));
		
		mostrarHistorialReservas();
	}
	
	
}
