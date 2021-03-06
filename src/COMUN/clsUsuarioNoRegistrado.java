package COMUN;

import java.util.logging.Level;
import java.util.logging.Logger;

import LD.clsBaseDeDatos;

/**
 * Esta es una clase en la que vamos a tratar una excepci�n que puede ser causada en nuestra aplicaci�n.
 * En concreto, se trata de una excepci�n que salta cuando queremos acceder a la aplicaci�n con datos de 
 * un usuario no registrado.
 * @author ALUMNO
 *
 */

public class clsUsuarioNoRegistrado extends Exception
{
		/**
		 * Este es el mensaje que va a avisar al usuario que ya existe el elemento que quiere dar de alta.
		 */
		private final String MENSAJE= "El nombre de usuario y el correo electr�nico introducido no coincide con ning�n usuario registrado.";
		private static Logger logger = Logger.getLogger(clsUsuarioNoRegistrado.class.getName() );
		/**
		 * Este es el m�todo que manda el mensaje.
		 */
		@Override
		public String getMessage() 
		{
			logger.log( Level.INFO, MENSAJE);
			return MENSAJE;
		}
		
		/**
		 * Este es el m�todo que hace que el mensaje sea apropiado para su presentaci�n.
		 */
		@Override
		public String toString() {
			return MENSAJE;
		}
}