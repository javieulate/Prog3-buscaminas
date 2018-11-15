package LN;

import LP.VentanaPrincipiante;

public class clsJuegoPrincipiante 
{
	private int casillasbuenas = 0;
	private int minas = 10;
	// Cada casilla tendrá un valor, dependiendo del número de minas que tenga a su alrededor o si, en su defecto, se trata de una mina.
	
	int situacioncasillas[][] = new int [minas][minas]; 
	
	private boolean pulsada [][] = new boolean [minas][minas];
	
	
	public clsJuegoPrincipiante(){
		for (int i = 0; i < minas; i++){
			for (int j = 0; j < minas; j++){
				pulsada[i][j] = false;
			}
		}
		PartidaPrincipiante();
	}
	
	
	
	public int getCasillasbuenas() {
		return casillasbuenas;
	}



	public void setCasillasbuenas(int casillasbuenas) {
		this.casillasbuenas = casillasbuenas;
	}



	public int getMinas() {
		return minas;
	}



	public void setMinas(int minas) {
		this.minas = minas;
	}



	public int[][] getSituacioncasillas() {
		return situacioncasillas;
	}



	public void setSituacioncasillas(int[][] situacioncasillas) {
		this.situacioncasillas = situacioncasillas;
	}



	public boolean[][] getPulsada() {
		return pulsada;
	}



	public void setPulsada(boolean[][] pulsada) {
		this.pulsada = pulsada;
	}



	public void PartidaPrincipiante(){
		casillasbuenas = 0;
		ColocarMinasP();
		SituacionMinas();
		
	}
	
	/*
	 * El método ColocarMinasP se va a ocupar de colocar 10 minas aleatoriamente a lo largo del "tablero". Conceptualmente se colocará una mina
	 * en aquellas casillas a las que se le asignen el valor 9.
	 * @param 
	 * @param
	 */
	public void ColocarMinasP(){
		for (int i = 0; i < minas; i++){
			for(int j = 0; j < minas; j++){
				situacioncasillas[i][j] = 0;
				// Antes de que se coloquen las minas, las casillas se inicializan con valor 0
			}
		int random1, random2;
		for(int k = 0; k < minas; k++){
			random1 = (int) (Math.random()*minas);
			random2 = (int) (Math.random()*minas);
			situacioncasillas[random1][random2] = 9;
		}
		}
	}
	
	/*
	 * Este método se va a ocupar de sumar el valor de 1 a todas aquellas casillas que se encuentren en las 8 casillas que están alrededor 
	 * de cada mina, en caso de que no se traten de una mina. Pues las casillas que contengan una mina tendrán el valor de 9 que les hemos
	 * asignado en el método ColocarMinasP
	 */
	public void SituacionMinas(){
		for(int i = 0; i < minas; i++){
			for(int j = 0; j < minas; j++){
				if(situacioncasillas[i][j] == 9){
					for(int x = i-1; x < i+2; i++){
						for(int y = j-1; y < j+2; j++){
							if(x>=0 && x<minas && y<=0 && y<minas && situacioncasillas[x][j] != 9){
								situacioncasillas[x][y] ++;
							}
						}
					}
				}
			}
		}
	}
	
	
	
}
