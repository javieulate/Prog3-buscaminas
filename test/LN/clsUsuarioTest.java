package LN;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import COMUN.clsEmailNoValido;
import LD.clsBaseDeDatos;

public class clsUsuarioTest {
	
	 clsUsuario usuario1;
	 ArrayList ListaUsuarios;
	 
	@Before
	public void setUp()
	{
		usuario1 = new clsUsuario("a", "a", "a@gmail.com", "a", "a");
		try 
		{
			clsBaseDeDatos.anyadirFilaATabla(clsBaseDeDatos.getStatement(), usuario1);
		} 
		catch (clsEmailNoValido | clsUsuarioRepetido e) 
		{
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void test() 
	{
		
	}

}
