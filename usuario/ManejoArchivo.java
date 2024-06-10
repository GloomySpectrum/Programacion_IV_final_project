package usuario;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import usuario.habitaciones.*;

public class ManejoArchivo<T> implements IGuardarObjeto<T> {
	
	@Override
	public void guardarObjeto(String path, T object) {
	    List<T> objects = new ArrayList<>();
	    File file = new File(path);
	    if (file.exists()) {
	    	objects = recuperarObjeto(path);
	    }
	    
	    objects.add(object);
	    try (FileOutputStream fileOut = new FileOutputStream(path);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
	        out.writeObject(objects);
	        System.out.println("Serialized data is saved in " + path);
	        
	        out.close();
	        fileOut.close();
	    } catch (IOException i) {
	    	System.out.println("No hay objetos en el archivo.");
	        i.printStackTrace();
	    }
	}
	
	
	/*@Override
	public void guardarObjeto(String path, T object) {
		try{
			FileOutputStream fileOut = new FileOutputStream(path, true);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(object);
			System.out.println("Serialized data is saved in " + path);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}*/


	@Override
	public List<T> recuperarObjeto(String path) {
        List<T> object = new ArrayList<T>();
        try{
        	
        	FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            while (true) {
                try {
                	//object.add((T) in.readObject());
                	object = (List<T>) in.readObject();
                } catch (EOFException e) {
                    System.out.println("Fin del archivo alcanzado.");
                    break;
                } catch (ClassNotFoundException c) {
                    System.out.println("Clase de objeto no encontrada.");
                    c.printStackTrace();
                }
            }
            
            in.close();
            fileIn.close();
            return object;
        } catch (IOException i) {
        	System.out.println("No hay objetos en el archivo.");
            return null;
		}
	}
	
	
	public void eliminarArchivo(String path) {
		File file = new File(path);
		file.delete();
	}

}