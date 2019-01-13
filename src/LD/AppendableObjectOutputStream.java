package LD;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendableObjectOutputStream extends ObjectOutputStream 
{
	  public AppendableObjectOutputStream(OutputStream out) throws IOException 
	  {
	    super(out);
	  }

	  @Override
	  /**
	   * Sobrescribir el método para que cada vez que se abra un fichero para añadir información, no se escriba la 
	   * cabecera, que genera información heterogénea dentro del fichero, y al intentar leerlo produce error.
	   * http://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream
	   */
	  protected void writeStreamHeader() throws IOException 
	  {
	    // do not write a header
	  }
}