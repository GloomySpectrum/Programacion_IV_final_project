package interfaz;

import javax.swing.JOptionPane;
import javax.swing.JDialog;

public class Mensajes extends JDialog {


	private static final long serialVersionUID = -6840144890531452721L;

	public static void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
	}
	
	public static void mensaje(String mensaje_1, String mensaje_2) {
        JOptionPane.showMessageDialog(null, mensaje_1 + mensaje_2);
	}

}
