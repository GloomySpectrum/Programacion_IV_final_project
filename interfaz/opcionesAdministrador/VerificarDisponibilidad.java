package interfaz.opcionesAdministrador;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VerificarDisponibilidad extends JPanel {

	private static final long serialVersionUID = 1L;

	
	public VerificarDisponibilidad() {
		
		setLayout(null);
		setSize(484, 523);
		
		JPanel background = new JPanel();
		background.setBackground(Color.WHITE);
		background.setBounds(0, 0, getWidth(), getHeight());
		add(background);
		background.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(0, 191, 255));
		panelTitulo.setBounds(0, 0, 484, 90);
		background.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Verificar Disponibilidad");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.ITALIC, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(106, 31, 252, 30);
		panelTitulo.add(lblTitulo);
	}

}
