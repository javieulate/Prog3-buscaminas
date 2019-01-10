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
	 ArrayList<clsUsuario> ListaUsuarios;
	 
	@Before
	public void setUp()
	{
		usuario1 = new clsUsuario("a", "a", "a@gmail.com", "a", "a");
		ListaUsuarios = new ArrayList<clsUsuario>();
		clsBaseDeDatos.initBD( "Usuarios.bd" );
		clsBaseDeDatos.crearTablaBD();
	}

	@Test
	public void test() 
	{
		try 
		{
			clsBaseDeDatos.anyadirFilaATabla(clsBaseDeDatos.getStatement(), usuario1);
		} 
		catch (clsEmailNoValido | clsUsuarioRepetido e) 
		{
			e.printStackTrace();
		}
		
		ListaUsuarios = clsBaseDeDatos.cargarVariosDeTabla2(clsBaseDeDatos.getStatement());
		assertEquals(ListaUsuarios.size(), 1);
	}

}
