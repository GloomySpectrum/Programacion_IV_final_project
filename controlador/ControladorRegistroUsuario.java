package controlador;

import interfaz.Mensajes;
import usuario.ProyectoFinal;
import usuario.Usuario;

public class ControladorRegistroUsuario {
	
	public boolean validarCamposVacios(Usuario usuario) {
		
		if (usuario.getTipoIdentificacion().isEmpty() || usuario.getDocumentoIdentificacion().isEmpty()
				|| usuario.getNombres().isEmpty() || usuario.getApellidos().isEmpty()
				|| usuario.getCorreoElectronico().isEmpty() || usuario.getDireccionResidencia().isEmpty()
				|| usuario.getCiudadResidencia().isEmpty() || usuario.getTelofonoContacto().isEmpty()
				|| usuario.getContraseña().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean registrarCliente(Usuario usuario, String confirmarContraseña) {
		
		boolean validar = false;
		
		boolean validarDocumentoIdentificacion = ComprobarValoresNumericos.esNumero(usuario.getDocumentoIdentificacion());
		
		boolean validarTelefono = ComprobarValoresNumericos.esNumero(usuario.getTelofonoContacto());
		
		boolean validarContraseña = usuario.validarContraseña(confirmarContraseña);
		if(!validarCamposVacios(usuario)){
			if(validarDocumentoIdentificacion) {
				if (validarTelefono) {
					if (validarContraseña) {
						ProyectoFinal cliente = new ProyectoFinal();
						cliente.registrarUsuario(usuario);
						Mensajes.mensaje("Usuario registrado con exito");
						validar = true;
					}else {
						
						Mensajes.mensaje("Las contraseñas no coinciden");
					}
				}else {
					Mensajes.mensaje("Ingrese un telefono de contacto valido");
				}
			}else {
				Mensajes.mensaje("Ingrese un documento de identificacion valido");
			}
		} else {
			Mensajes.mensaje("Por favor llene todos los campos");
		}
		
		return validar;
	}
	

}
