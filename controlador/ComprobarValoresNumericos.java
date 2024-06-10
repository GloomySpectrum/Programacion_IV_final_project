package controlador;

public class ComprobarValoresNumericos {
    
    public static boolean esNumero(String numero) {
        try {
            Long.parseLong(numero); // Intenta convertir la cadena a un número largo (long)
            return true; // Si tiene éxito, devuelve true
        } catch (NumberFormatException e) {
            return false; // Si hay una excepción, devuelve false
        }
    }
}

