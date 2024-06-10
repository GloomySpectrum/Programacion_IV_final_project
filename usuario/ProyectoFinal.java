/* 
    Universidad Tecnológica de Perira
    Programa de Ingeniería de Sistemas y Computación
    Programación IV
    Estudiante: Brayan Stiven Valencia Ospina
    Código: 1112298468
    Profesor: Gustavo Adolfo Castañeda Trejos
 */



package usuario;

import java.util.List;

import constantes.Constantes;


public class ProyectoFinal{  
	public static final byte correoAdministrador = 4;
	public static final byte passwordAdministrador = 8;
	
	private static final String[] administrador = {"Cédula de ciudadanía","1111111","admin","admin","admin@gmail.com","calle","pereira","3333333","12345"};
    
	
    public void registrarUsuario(Usuario usuario){
        
    	ManejoArchivo<Usuario> clientes = new ManejoArchivo<Usuario>();
        
        clientes.guardarObjeto(Constantes.RUTA_USUARIOS,usuario);
        
    }
    
	
    public String iniciarSesion(String correoElectronico, String contraseña){
    	ManejoArchivo<Usuario> clientes = new ManejoArchivo<Usuario>();
    	List<Usuario> usuarios = clientes.recuperarObjeto(Constantes.RUTA_USUARIOS);
    	//mostrarClientesRegistrados(usuarios);
    	
    	
    	if(administrador[correoAdministrador].equalsIgnoreCase(correoElectronico) && administrador[passwordAdministrador].equals(contraseña)) {
    		return "administrador";
    	}
    	
    	if (!(usuarios == null)) {
    		for(Usuario cl : usuarios){
				if(cl.iniciarSesion(correoElectronico, contraseña)){
					return "usuario";
				}
			}
    	}
        return "no registrado";
    }
	
	 /* public static void mostrarClientesRegistrados(List<Usuario> usuarios){
	        System.out.println("____________________________________");
	        System.out.println("Clientes registrados: ");
			if (usuarios == null) {
				System.out.println("No hay clientes registrados");
			}else {
		        for (Usuario usuario : usuarios){
		        	 System.out.println("____________________________________");
		        	 System.out.println(usuario.getTipoIdentificacion());
		        	 System.out.println(usuario.getDocumentoIdentificacion());
		        	 System.out.println(usuario.getNombres());
		        	 System.out.println(usuario.getApellidos());
		        	 System.out.println(usuario.getCorreoElectronico());
		        	 System.out.println(usuario.getDireccionResidencia());
		        	 System.out.println(usuario.getCiudadResidencia());
		        	 System.out.println(usuario.getTelofonoContacto());
		        	 System.out.println(usuario.getContraseña());
		             System.out.println("____________________________________");
		         }
		     }
	  }*/
	  
}