package interfaz.opcionesAdministrador;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import constantes.Constantes;
import controlador.ControladorAdministrador;
import interfaz.Mensajes;

public class TableroFilaHabitacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private int idHabitacion;
	
	private JPanel btnEditar = new JPanel();
	private JLabel lblEditar = new JLabel("Editar");
	
	private JPanel btnEliminar = new JPanel();
	private JLabel lblEliminar = new JLabel("Eliminar");
	
	
	MouseAdapter manejoEventoMouse = new MouseAdapter() {

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			// Las validaciones se deben hacer en el controlador
			if (e.getSource() == lblEditar) {
				DatosModificarHabitacion datosModificarHabitacion = new DatosModificarHabitacion(idHabitacion);
				datosModificarHabitacion.setVisible(true);
				
			} else if (e.getSource() == lblEliminar) {
				ControladorAdministrador controlador = new ControladorAdministrador();
				controlador.eliminarHabitacion(idHabitacion);
				Mensajes.mensaje("Habitacion eliminada con exito");
				
			}
		}
		
		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			if (e.getSource() == lblEditar) {
				btnEditar.setBackground(Constantes.COLOR_BOTON_EDITAR_SELECCIONADO);
			} else if (e.getSource() == lblEliminar) {
				btnEliminar.setBackground(Constantes.COLOR_BOTON_ELIMINAR_SELECCIONADO);
			}
		}
		
		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			if (e.getSource() == lblEditar) {
				btnEditar.setBackground(Constantes.COLOR_BOTON_EDITAR_NORMAL);
			} else if (e.getSource() == lblEliminar) {
				btnEliminar.setBackground(Constantes.COLOR_BOTON_ELIMINAR_NORMAL);
			}
		}
	};
	
	public void colocarBotones() {
        btnEditar.setBackground(Constantes.COLOR_BOTON_EDITAR_NORMAL);
        btnEditar.setBorder(new LineBorder(Color.BLACK, 1));
        btnEditar.setLayout(new GridLayout(1, 1));
        lblEditar.addMouseListener(manejoEventoMouse);
        lblEditar.setHorizontalAlignment(JLabel.CENTER);
        btnEditar.add(lblEditar);
        
        btnEliminar.setBackground(Constantes.COLOR_BOTON_ELIMINAR_NORMAL);
        btnEliminar.setBorder(new LineBorder(Color.BLACK, 1));
        lblEliminar.addMouseListener(manejoEventoMouse);
        btnEliminar.setLayout(new GridLayout(1, 1));
        lblEliminar.setHorizontalAlignment(JLabel.CENTER);
        btnEliminar.add(lblEliminar);
        add(btnEditar);
        add(btnEliminar);
    }
	
	
	public TableroFilaHabitacion(int idHabitacion, int columnas) {
		setLayout(new GridLayout(0, columnas));
        this.idHabitacion = idHabitacion;
    }
	
	public TableroFilaHabitacion(int columnas) {
		setLayout(new GridLayout(0, columnas));
	}
		

}
