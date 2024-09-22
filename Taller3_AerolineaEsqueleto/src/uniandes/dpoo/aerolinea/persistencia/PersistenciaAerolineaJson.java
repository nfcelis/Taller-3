package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {
	
	public void cargarAerolinea( String archivo, Aerolinea aerolinea )
    {try {
		
	        String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
	        JSONObject raiz = new JSONObject( jsonCompleto );}

	 catch (IOException e) {
	            e.printStackTrace(); 
	        }
	       
    }

	    /**
	     * Salva en un archivo toda la información sobre los clientes y los tiquetes vendidos por la aerolínea
	     * @param archivo La ruta al archivo donde debe quedar almacenada la información
	     * @param aerolinea La aerolínea que tiene la información que se quiere almacenar
	     * @throws IOException Se lanza esta excepción si hay problemas escribiendo el archivo
	     */
	    @Override
public void salvarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException {
	    JSONObject jobject = new JSONObject();
	    
	    try (PrintWriter pw = new PrintWriter(archivo)) {
	        pw.println(jobject.toString(2));
	    }
	    catch (IOException e) {
	        e.printStackTrace(); }
	}

}

