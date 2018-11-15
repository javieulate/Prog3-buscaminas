import java.util.ArrayList;

import COMUN.clsEmailNoValido;
import LN.clsGestor;
import LN.clsUsuario;
import LN.clsUsuarioRepetido;
import LP.frmPantalla;


public class clsMain {

	public static void main(String[] args) {

		frmPantalla frame = new frmPantalla();
		frame.setVisible(true);
		ArrayList<clsUsuario> listaUsu = new ArrayList<clsUsuario>();
//		clsUsuario a = new clsUsuario("aa", "aa@aa.es", "aa", 0, 0);
//		clsUsuario b = new clsUsuario("aa", "bb@aa.es", "aa", 0, 0);
//		
//		try {
//			if(clsGestor.validarEmail(a.getMail())){
//				try {
//					
//					clsGestor.altaUsuario( a, listaUsu);
//					listaUsu.add(a);
//				} catch (clsUsuarioRepetido e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}					
//		} catch (clsEmailNoValido e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		try {
//			if(clsGestor.validarEmail(b.getMail())){
//				try {
//					clsGestor.altaUsuario(b, listaUsu);
//					listaUsu.add(b);
//				} catch (clsUsuarioRepetido e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}					
//		} catch (clsEmailNoValido e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	}
}
