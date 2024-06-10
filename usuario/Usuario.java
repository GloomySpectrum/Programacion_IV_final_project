package usuario;

import java.io.Serializable;

public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoIdentificacion;
	private String documentoIdentificacion;
	private String nombres;
	private String apellidos;
	private String correoElectronico;
	private String direccionResidencia;
	private String ciudadResidencia;
	private String telofonoContacto;
	private String contraseña;
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombre) {
		this.nombres = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellido) {
		this.apellidos = apellido;
	}

    public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	public String getDocumentoIdentificacion() {
		return documentoIdentificacion;
	}
	public void setDocumentoIdentificacion(String documentoIdentificacion) {
		this.documentoIdentificacion = documentoIdentificacion;
	}
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public String getDireccionResidencia() {
		return direccionResidencia;
	}
	public void setDireccionResidencia(String direccionResidencia) {
		this.direccionResidencia = direccionResidencia;
	}
	
	public String getCiudadResidencia() {
		return ciudadResidencia;
	}
	public void setCiudadResidencia(String ciudadResidencia) {
		this.ciudadResidencia = ciudadResidencia;
	}
	
	public String getTelofonoContacto() {
		return telofonoContacto;
	}
	public void setTelofonoContacto(String telofonoContacto) {
		this.telofonoContacto = telofonoContacto;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
    
	public String getContraseña() {
		return contraseña;
	}
	
	public boolean iniciarSesion(String correoElectronico, String contraseña) {
		return this.correoElectronico.equals(correoElectronico) && this.contraseña.equals(contraseña);
	}
	
	public boolean validarContraseña(String confirmarContraseña) {
		return this.contraseña.equals(confirmarContraseña);
	}
	
}
