package controlador;

import java.util.List;

import constantes.Constantes;
import interfaz.*;
import usuario.*;

public class ControladorLogin {
	
	public String iniciarSesion(String correoElectronico, String contraseña) {
		
		if(correoElectronico.isEmpty() && !contraseña.isEmpty()) {
			Mensajes.mensaje("Todos los campos deben estar llenados correctamente", "\n¡Vuelve a Intentarlo!");
			return "";
		}
		
		ProyectoFinal usuario = new ProyectoFinal();
		String validar = usuario.iniciarSesion(correoElectronico, contraseña); 
		
		if(!validar.equals("no registrado")) {
			return validar;
		}else {
			Mensajes.mensaje("Correo Electrónico o Contraseña incorrecto", "\n¡Vuelve a Intentarlo!");	
		}
		return "";

	}
	
	public long recuperarIdUsuario(String correoElectronico) {
		ManejoArchivo<Usuario> clientes = new ManejoArchivo<Usuario>();
		List<Usuario> usuarios = clientes.recuperarObjeto(Constantes.RUTA_USUARIOS);

		if (!(usuarios == null)) {
			for (Usuario cl : usuarios) {
				if (cl.getCorreoElectronico().equals(correoElectronico)) {
					return Long.parseLong(cl.getDocumentoIdentificacion());
				}
			}
		}
		return 0;
	}

}