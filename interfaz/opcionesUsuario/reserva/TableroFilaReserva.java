package interfaz.opcionesUsuario.reserva;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import constantes.Constantes;
import controlador.ControladorReserva;
import interfaz.Mensajes;
import usuario.reservas.Reserva;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;

public class TableroFilaReserva extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel btnEditar = new JPanel();
	private JLabel lblEditar = new JLabel("Editar");
	
	private JPanel btnCancelar = new JPanel();
	private JLabel lblCancelar = new JLabel("Cancelar");
	private int idReserva;
	
	MouseAdapter manejoEventoMouse = new MouseAdapter() {
		
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
            if (e.getSource() == lblEditar) {
            	ControladorReserva controladorReserva = new ControladorReserva();
            	controladorReserva.actualizarEstadoReserva();
            	String estadoReserva = controladorReserva.recuperarReservaPorId(idReserva).getEstadoReserva();
            	
            	if(!estadoReserva.equals("Cancelada")  && !estadoReserva.equals("Finalizada")) {
            		DigitarCambioReserva digitarCambioReserva = new DigitarCambioReserva(idReserva);
            		digitarCambioReserva.setVisible(true);            		
            	}else {
            		Mensajes.mensaje("No se puede editar la reserva");
            	}
            }else if(e.getSource() == lblCancelar) {
            	
            	ControladorReserva controladorReserva = new ControladorReserva();
            	controladorReserva.actualizarEstadoReserva();
            	Reserva reserva = controladorReserva.recuperarReservaPorId(idReserva);
            	controladorReserva.cancelarReserva(reserva);
            	
            }
		}
		
		
		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			if (e.getSource() == lblEditar) {
				btnEditar.setBackground(Constantes.COLOR_BOTON_EDITAR_SELECCIONADO);
			}
			if (e.getSource() == lblCancelar) {
				btnCancelar.setBackground(Constantes.COLOR_BOTON_CANCELAR_SELECCIONADO);
			}
		}
		
		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			colorNormal();
		}
	};
	
	
	public void colorNormal() {
		btnEditar.setBackground(Constantes.COLOR_BOTON_EDITAR_NORMAL);
		btnCancelar.setBackground(Constantes.COLOR_BOTON_CANCELAR_NORMAL);
	}
	
	public void colocarBotones() {
        btnEditar.setBackground(Constantes.COLOR_BOTON_EDITAR_NORMAL);
        btnEditar.setBorder(new LineBorder(Color.BLACK, 1));
        btnEditar.setLayout(new GridLayout(1, 1));
        lblEditar.addMouseListener(manejoEventoMouse);
        lblEditar.setHorizontalAlignment(JLabel.CENTER);
        btnEditar.add(lblEditar);
        
        btnCancelar.setBackground(Constantes.COLOR_BOTON_CANCELAR_NORMAL);
        btnCancelar.setBorder(new LineBorder(Color.BLACK, 1));
        lblCancelar.addMouseListener(manejoEventoMouse);
        lblCancelar.setHorizontalAlignment(JLabel.CENTER);
        btnCancelar.setLayout(new GridLayout(1, 1));
        btnCancelar.add(lblCancelar);
        add(btnEditar);
        add(btnCancelar);
    }
	
	public TableroFilaReserva(int idReserva, int columnas) {
		setLayout(new GridLayout(0, columnas));
		this.idReserva = idReserva;
	}
	
	public TableroFilaReserva(int columnas) {
		setLayout(new GridLayout(0, columnas));
	}
}
