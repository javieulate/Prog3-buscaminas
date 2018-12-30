import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

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

	public static void main(String[] args) {
//
//		try{
//			
//				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		}
//			catch (InstantiationException | IllegalAccessException
//					| UnsupportedLookAndFeelException | ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		frmPantalla frPantalla = new frmPantalla();
		frPantalla.setVisible(true);
		try {
			frPantalla.cargaProperties();
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		ArrayList<clsUsuario> listaUsu = new ArrayList<clsUsuario>();
		clsBaseDeDatos.initBD( "Usuarios.bd" );
		clsBaseDeDatos.crearTablaBD();
		listaUsu = clsBaseDeDatos.cargarVariosDeTabla2(clsBaseDeDatos.getStatement());
		for (clsUsuario aux : listaUsu) {
			
			System.out.println(aux.toString());
		}
//		play();
		
	}
	
//	public static void play() {
//		try { File file = new File("song.wav");
//		Clip clip = AudioSystem.getClip(); 
//		clip.open(AudioSystem.getAudioInputStream(file));
//		clip.start(); 
//		Thread.sleep(clip.getMicrosecondLength()); }
//		catch (Exception e) { 
//			System.err.println(e.getMessage());
//			}
//		}
}
