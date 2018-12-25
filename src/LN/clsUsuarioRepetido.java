package LN;

import java.util.logging.Level;
import java.util.logging.Logger;



public class clsUsuarioRepetido extends Exception{

	
	private final String MENSAJE= "El usuario que quiere dar de alta ya ha sido dado de alta previamente.";
	private static Logger logger = Logger.getLogger(clsUsuarioRepetido.class.getName() );

	@Override
	public String getMessage() {
		logger.log( Level.INFO, MENSAJE);
		return MENSAJE;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return MENSAJE;
	}
}
