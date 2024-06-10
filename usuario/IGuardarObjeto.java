package usuario;

import java.util.List;

public interface IGuardarObjeto<T> {
	
    void guardarObjeto(String path, T objeto);
    List<T> recuperarObjeto(String path);
    
}
