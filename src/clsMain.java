import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import COMUN.clsEmailNoValido;
import LD.clsBaseDeDatos;
import LN.clsGestor;
import LN.clsUsuario;
import LN.clsUsuarioRepetido;
import LP.frmPantalla;


public class clsMain {

	public static void main(String[] args) {

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
	}
}
