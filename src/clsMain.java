import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import COMUN.clsEmailNoValido;
import LD.clsBaseDeDatos;
import LN.clsGestor;
import LN.clsUsuario;
import LN.clsUsuarioRepetido;
import LP.frmPantalla;
import sun.audio.*;

import java.io.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class clsMain {

	
		private static Logger logger = Logger.getLogger( clsMain.class.getName() );
	
	public static void main(String[] args) {

		logger.setLevel( Level.ALL );  
		try {
		
			Handler h = new StreamHandler( System.out, new SimpleFormatter() );
			h.setLevel( Level.FINEST );
			logger.addHandler( h );  // Saca todos los errores a out
			logger.addHandler( new FileHandler( "clsMain.log.xml") ); 
		} catch (Exception e) {
			logger.log( Level.SEVERE, e.toString(), e );
		}
		
		frmPantalla frPantalla = new frmPantalla();
		frPantalla.setVisible(true);
		logger.log( Level.INFO, "Ejecutando programa. ");
		
		frPantalla.cargaProperties();
		
		ArrayList<clsUsuario> listaUsu = new ArrayList<clsUsuario>();
		clsBaseDeDatos.initBD( "Usuarios.bd" );
		clsBaseDeDatos.crearTablaBD();
		clsBaseDeDatos.crearTablaBDPartidas();
		listaUsu = clsBaseDeDatos.cargarVariosDeTabla2(clsBaseDeDatos.getStatement());
		logger.log( Level.INFO, "Usuarios registrados en la base de datos");
		int cont =1;
		for (clsUsuario aux : listaUsu) {
			
			System.out.println(cont+". " +aux.toString());
			logger.log( Level.FINER, cont+". " +aux.toString());
			cont++;
		}
	}
}
