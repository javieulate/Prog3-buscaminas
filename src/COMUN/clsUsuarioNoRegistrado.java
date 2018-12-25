package COMUN;

import java.util.logging.Level;
import java.util.logging.Logger;

import LD.clsBaseDeDatos;


/**
 * Esta es una clase en la que vamos a tratar una excepción que puede ser causada en nuestra aplicación.
 * En concreto, se trata de una excepción que salta cuando queremos acceder a la aplicación con datos de 
 * un usuario no registrado.
 * @author ALUMNO
 *
 */

public class clsUsuarioNoRegistrado extends Exception
{
		/**
		 * Este es el mensaje que va a avisar al usuario que ya existe el elemento que quiere dar de alta.
		 */
		private final String MENSAJE= "El nombre de usuario y el correo electrónico introducido no coincide con ningún usuario registrado.";
		private static Logger logger = Logger.getLogger(clsUsuarioNoRegistrado.class.getName() );
		/**
		 * Este es el método que manda el mensaje.
		 */
		@Override
		public String getMessage() 
		{
			// TODO Auto-generated method stub
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
