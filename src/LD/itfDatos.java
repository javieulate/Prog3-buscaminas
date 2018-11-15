package LD;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import COMUN.clsConstantes.enFicDatos;


public interface itfDatos 
{
	
	/**
	 * 
	 * @param fichero: 	enumerado de la clase clsConstantes que indica el fichero del que se
	 * 					va a escribir.
	 * Método que debe crear un objectOutputStream o un AppendableObjectOutputStream para proceder
	 * a la escritura del fichero. Si el fichero existe,AppendableObjectOutputStream; de lo 
	 * contrario objectOutputStream
	 */
	public void ComenzarSave(enFicDatos fichero);
	
	/**
	 * Método que debe cerrar el fichero en el que se ha escrito.
	 */
	public void TerminarSave();
	/**
	 * 
	 * @param o: Objeto a guardar, que debe implementar la interfaz serializable.
	 * Método que guarda en el fichero indicado previamente el objeto recibido.
	 */
	public void Save(Serializable o);
	
	/**
	 * 
	 * @param fichero: enumerado de la clase clsConstantes que indica el fichero del que se
	 * 					va a leer. 
	 * @throws IOException: excepción lanzada en caso de que se dé un error de lectura/escritura en fichero.
	 * Método que crea un objectInputStream para la lectura del fichero indicado previamente.
	 */
	public void ComenzarRead(enFicDatos fichero) throws IOException;
	/**
	 * Método que cierra el fichero del que se ha leído.
	 */
	public void TerminarRead();
	
	/**
	 * 
	 * @return ArrayList<Serializable>: Devuelve un arraylist con los datos leídos, en el tipo
	 * Serializable.
	 */
	public ArrayList<Serializable> Read();	
	
	/**
	 * 
	 * @param fichero enumerado de la clase clsConstantes que indica el fichero que se
	 * 					va a borrar.
	 * Se borra el fichero (porque vamos a escribir datos modificados).
	 */
	
	public Serializable ReadSerializable();
	
	public void ResetFile (enFicDatos fichero);
}
