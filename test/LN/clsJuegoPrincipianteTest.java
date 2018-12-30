package LN;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class clsJuegoPrincipianteTest 
{

	clsJuegoPrincipiante pPrincipiante, pAmateur, pExperto;
	int contadorPrin, contadorAma, contadorExp;
	
	@Before
	public void setUp()
	{
		// Creación de una partida sencilla
		pPrincipiante = new clsJuegoPrincipiante(10);
		pAmateur = new clsJuegoPrincipiante(20);
		pExperto = new clsJuegoPrincipiante(30);
		contadorPrin = 0;
		contadorAma = 0;
		contadorExp = 0;
	}
	
	@Test
	public void testPrincipiante() 
	{
		for(int i = 0; i < pPrincipiante.getMinas(); i++)
		{
			for(int j = 0; j < pPrincipiante.getMinas(); j++)
			{
				if(pPrincipiante.getSituacioncasillas(i, j) == 9)
				{
					contadorPrin++;
				}
			}
		}
		assertEquals(pPrincipiante.getMinas(), contadorPrin);
	}
	
	/*
	 *  Tengamos en cuenta que en la modalidad amateur, el "tablero" es de 20x20, es decir, de 400 casillas, pero cuenta con 20x2 minas,
	 *  es decir, con 40 minas en total. De ahí que en elassertEquals estemos multiplicando por 2 el getMinas.
	 *  @param Minas Hace referencia no a las minas, sino a la dimensión del tablero del BuscaMinas.
	 */
	@Test
	public void testAmateur()
	{
		for(int i = 0; i < pAmateur.getMinas(); i++)
		{
			for(int j = 0; j < pAmateur.getMinas(); j++)
			{
				if(pAmateur.getSituacioncasillas(i, j) == 9)
				{
					contadorAma++;
				}
			}
		}
		assertEquals(pAmateur.getMinas()*2, contadorAma);
	}
	
	/*
	 *  Tengamos en cuenta que en la modalidad experto, el "tablero" es de 30x30, es decir, de 900 casillas, pero cuenta con 30x3 minas,
	 *  es decir, con 90 minas en total. De ahí que en elassertEquals estemos multiplicando por 3 el getMinas.
	 */
	@Test
	public void testExperto()
	{
		for(int i = 0; i < pExperto.getMinas(); i++)
		{
			for(int j = 0; j < pExperto.getMinas(); j++)
			{
				if(pExperto.getSituacioncasillas(i, j) == 9)
				{
					contadorExp++;
				}
			}
		}
		assertEquals(pExperto.getMinas()*3, contadorExp);
	}
	
	
	@After
	public void tearDown() throws Exception {
	}



}
