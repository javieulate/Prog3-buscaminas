package COMUN;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta es una clase en la que vamos a tratar una excepción que puede ser causada en nuestra aplicación.
 * En concreto, se trata de una excepción que salta cuando queremos registrar un elemento (usuario, pelicula,serie)
 * que ya está en fichero.
 * @author Juan Solozabal, Lander Pisón y Javier Alvarez de Eulate
 *
 */
public class clsEmailNoValido extends Exception
{
	/**
	 * Este es el mensaje que va a avisar al usuario que ya existe el elemento que quiere dar de alta.
	 */
	private final String MENSAJE= "La dirección de correo electrónico introducida no es correcta.";
	private static Logger logger = Logger.getLogger(clsEmailNoValido.class.getName() );
	/**
	 * Este es el método que manda el mensaje.
	 */
	@Override
	public String getMessage() 
	{
		logger.log( Level.INFO, MENSAJE);
		return MENSAJE;
	}
	/**
	 * Este es el método que hace que el mensaje sea apropiado para su presentación.
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return MENSAJE;
	}
}



