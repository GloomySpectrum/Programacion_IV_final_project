package controlador;

public class ComprobarValoresNumericos {
	
	public static boolean esNumero(String numero){
        
        try{
        	Long.parseLong(numero);
        	
            return true;
        }catch(NumberFormatException e) {
        	return false;
        }
    }

}
