package COMUN;

import java.util.ArrayList;

//Cosas comunes a diferentes clases.

public class clsConstantes 
{
	public enum enFicDatos
	{
		FICHEROUSU,
		FICHEROSESION,
	}
	
/**
 * Todos los siguientes string se usan para que desde el men� se pueda acceder a los atributos de 
 * cada objeto. Tambi�n est�n definidos las referencias de los botones usados en el apartado gr�fico.
 */
	
	public static final String PROP_USUARIO_NOMBRE = "PROP_USUARIO_NOMBRE";
	public static final String PROP_USUARIO_APELLIDO = "PROP_USUARIO_APELLIDO";
	public static final String PROP_USUARIO_CORREOELEC = "PROP_USUARIO_CORREOELEC";
	public static final String PROP_USUARIO_NOMUSUARIO = "PROP_USUARIO_NOMUSUARIO";
	public static final String PROP_USUARIO_CONTRASENA = "PROP_USUARIO_CONTRASENA";
	public static final String PROP_USUARIO_NUMEROVIDAS = "PROP_USUARIO_NUMEROVIDAS";
	public static final String PROP_USUARIO_PUNTUACION = "PROP_USUARIO_PUNTUACION";
	
	public static final String CMD_BTN_INICIAR_SESION = "CMD_BTN_INICIAR_SESION";
	public static final String CMD_BTN_REGISTRARSE = "CMD_BTN_REGISTRARSE";
	public static final String CMD_BTN_INICIAR_SESION2 = "CMD_BTN_INICIAR_SESION2";
	public static final String CMD_BTN_REGISTRARSE2 = "CMD_BTN_REGISTRARSE2";
	public static final String CMD_BTN_PRINCIPIANTE = "CMD_BTN_PRINCIPIANTE";
	public static final String CMD_BTN_AMATEUR = "CMD_BTN_AMATEUR";
	public static final String CMD_BTN_EXPERTO = "CMD_BTN_EXPERTO";

	public static final String CMD_BTN_RANKINGPERSONAL = "CMD_BTN_RANKINGPERSONAL";
	public static final String CMD_BTN_RANKINGPRINCIPAL = "CMD_BTN_RANKINGPRINCIPAL";
	
	public static final String CMD_BTN_METAL = "CMD_BTN_METAL";
	public static final String CMD_BTN_WINDOWS = "CMD_BTN_WINDOWS";
	
	public static final String CMD_BTN_CERRARSESION = "CMD_BTN_CERRARSESION";
	public static final String CMD_BTN_BORRARUSUARIO = "CMD_BTN_BORRARUSUARIO";

	public static final String CMD_BTN_ACTUALIZARLISTAS = "CMD_BTN_ACTUALIZARLISTAS";
	public static final String CMD_BTN_ATRAS = "CMD_BTN_ATRAS";
	public static final String CMD_BTN_ATRAS2 = "CMD_BTN_ATRAS2";

	/**
	 * Este m�todo sirve principalmente para agregar un patr�n denominado EMAIL_ADDRESS.
	 */
	 public static final String EMAIL_ADDRESS = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";	 
}