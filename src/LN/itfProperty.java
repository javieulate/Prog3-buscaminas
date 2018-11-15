/**
 * 
 */
package LN;

/**
 * @author javier.cerro
 * Interfaz para la independencia entre la LN y la LP.
 */
public interface itfProperty 
{
	
	
	/**
	 * Devuelve la propiedad solicitada por nombre de tipo String.
	 * @para propiedad nombre de la propiedad a devolver del objeto. 
	 */
	String 	getStringProperty	(String propiedad);
	
	/**
	 * Devuelve la propiedad solicitada por nombre de tipo Integer.
	 * @para propiedad nombre de la propiedad a devolver del objeto. 
	 */
	Integer getIntegerProperty	(String propiedad);
	
	/**
	 * Devuelve la propiedad solicitada por nombre de tipo Float.
	 * @para propiedad nombre de la propiedad a devolver del objeto. 
	 */
	Float 	getFloatProperty	(String propiedad);
	
	/**
	 * Devuelve la propiedad solicitada por nombre de tipo Double.
	 * @para propiedad nombre de la propiedad a devolver del objeto. 
	 */
	Double	getDoubleProperty	(String propiedad);
	
	/**
	 * Devuelve la propiedad solicitada por nombre de tipo char.
	 * @para propiedad nombre de la propiedad a devolver del objeto. 
	 */
	char	getCharProperty		(String propiedad);

}