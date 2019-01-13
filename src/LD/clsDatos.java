package LD;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import COMUN.clsConstantes.enFicDatos;



/**
 * La clase clsDatos implementa itfDatos, lo que implica que tendrá que implementar todos los métodos que este posee. De ese modo, esta clase 
 * nos permitirá tanto leer coo escribir en fichero.
 * @author ALUMNO
 *
 */
public class clsDatos implements itfDatos 
{
	ObjectOutputStream oos=null; //Objetos que los convierten a binario.
	ObjectInputStream ois=null; // 
	AppendableObjectOutputStream aos=null; //Sirve para crear objetos 
	
	/**
	 * Estas declaraciones delimitan las rutas de destino de cada fichero
	 */

	private final String fic_usuarios = ".\\data\\usu.dat";
	private final String fic_sesion = ".\\data\\sesion.dat";
	
	/**
	 * Este método hace referencia al enumerado declarado en COMUN.clsConstantes, donde coon un switch case se imitan las opciones de destino de 
	 * fichero. De ese modo, desde el gestor escribiremos lo puesto en el case para que nos devuelva la ruta del fichero deseado.
	 * @param Fichero
	 * @return
	 */
	private String setfichero (enFicDatos Fichero)
	{	
		switch(Fichero)
		{

		case FICHEROUSU:
			return fic_usuarios;
		case FICHEROSESION:
			return fic_sesion;
		}
		return " ";	
	}
	
	/**
	 * Este método, como su propio nombre lo indica, comenzará el proceso de guardado. Le pasaremos por parámetro desde el gestor (normalmente)
	 * el enumerado del fichero en el que deseemos guardar. Luego comprobaremos si existe algún fichero en esa ruta indicada, y si no se da el caso,
	 * se crea un nuevo fichero, para después crearse un nuevo objeto de tipo ObjectOutputStream para escribir en el fichero por prumera vez en el 
	 * método Save. Sin embargo, si ya hay un fichero creado en esa ruta, se creará un objeto de tipo AppendableObjectOutputStream para poder escribir
	 * en un fichero en el que ya habi
	 * ía algo escrito.
	 */
	@Override
	public void ComenzarSave(enFicDatos fichero)
	{		
		String ruta = setfichero(fichero);
		File fic;
		fic = new File(ruta);
		
		if (fic.exists())
		{
			try 
			{
				aos = new AppendableObjectOutputStream(new FileOutputStream(fic,true));
				//Si existe lo único que tengo que hacer es escribir porque el fichero ya está creado.
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				fic.createNewFile();
				oos = new ObjectOutputStream(new FileOutputStream(fic));
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{

				e.printStackTrace();
			}
		}
	}

	/**
	 * El método de TerminarSave sirve para terminar de leer, y hay dos casos en función de si se crea un AppendableObjectOutputStream o un 
	 * ObjectOutputStream, por lo que se cerrará de una u otra manera dependiendo de si el fichero estaba anteriormente creado o no, tal y
	 * como hemos comentado en el método ComenzarSave().
	 */

	@Override
	public void TerminarSave()
	{

				if (oos!= null) 
				{
					try {
						oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (aos!=null) 
				{
					try {
						aos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

//			try
//			{
//				if (oos!=null) oos.close();
//				else if(oos!= null) aos.close();
//			}catch (IOException e)
		
	}

	/**
	 * El método Save() recibe por parámetro lo que se quiere guardar en forma de Serializable, y mediante la funcionalidad writeObject tanto 
	 * del objeto creado ObjectOutputStream como del AppendableObjectOutputStream se escribirñá en el fihcero indicado en ComenzarSave. 
	 */
	@Override
	public void Save(Serializable o) 
	{
		if (oos!= null) 
		{
			try {
				oos.writeObject(o);;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (aos!=null) 
		{
			try {
				aos.writeObject(o);;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * El método de ComenzarRead() recibe por parámetro el enumerado del fichero que queremos leer (si dicho fichero no existe se lanza una excepción) 
	 * y se crea un objeto del tipo ObjectInputStream que luego se utilizará para la respectiva lectura en el método Read() y el cierre dle fichero 
	 * en el método TerminarRead().
	 */
	@Override
	public void ComenzarRead(enFicDatos fichero) throws IOException 
	{
		String ruta=setfichero(fichero);
		File fic;
		
		fic=new File(ruta);
		if (fic.exists())
		{
			try
			{
				ois = new ObjectInputStream(new FileInputStream(fic));
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			throw new IOException();
		}
	}

	/**
	 * El método TerminarRead() simplemente cierra el fichero mediante el objeto ObjectInputStream, siempre y cuando este no sea nulo (ya que si es
	 * nulo, no habrá nada que leer.
	 */
	@Override
	public void TerminarRead() 
	{
		try 
		{
			if(ois!=null)ois.close();
		} 
		catch (IOException e) 
		{
			
		}
		
	}

	/**
	 * En el método de Read() se crea un ArrayList de Serializable en el que mediante un while se van leyendo y añadiendo al ArrayList de
	 * Serializables siempre y cuando se consiga leer algo. Luego se devolverá lo que comentamos a continuación.
	 * @return devuelve un ArrayList de serializables obtenido de lo leído en el fichero.
	 */
	@Override
	public ArrayList<Serializable> Read()
	{
		ArrayList<Serializable> lista = new ArrayList<Serializable>();
		Serializable a = null;
		
		try 
		{
			while ((a=(Serializable)ois.readObject())!= null)
			{
				lista.add(a);
			}
		} 
		
		catch(IOException b)
		{
			
			
		}
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("No hay nadie en la base de datos.");
		} 
		
		
	
			
		return lista;
	}
	
	/**
	 * Este es un método que vamos a usar únicamente para leer los datos del usuario que está
	 * usando la aplicación al momento. Más en concreto, para leer el fichero sesion. Lee el elemento
	 * serializable que está en el fichero (siempre va a ser uno), y lo devuelve con un return. Se lo
	 * hemos añadido a itfDatos.
	 */
	public Serializable ReadSerializable()
	{
		Serializable a = null;
		try
		{
			a = ((Serializable)ois.readObject());
		}
		catch (IOException b)
		{
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return a;
		
	}
	
	/**
	 * Este método resetea un fichero.
	 */
	@Override
	public void ResetFile(enFicDatos fichero) 
	{
		String ruta =setfichero(fichero);
		File f=new File(ruta);
		f.delete();
	}

}
