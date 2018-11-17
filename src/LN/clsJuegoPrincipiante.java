package LN;

import LP.VentanaPrincipiante;

public class clsJuegoPrincipiante 
{
	private int casillasbuenas;
	private int minas = 10;
	// Cada casilla tendrá un valor, dependiendo del número de minas que tenga a su alrededor o si, en su defecto, se trata de una mina.
	
	int situacioncasillas[][];
	
	
	
	
	public clsJuegoPrincipiante(){
		casillasbuenas = 0;
		situacioncasillas = new int [minas][minas]; 
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



	public int getSituacioncasillas(int i, int j) 
	{
		return situacioncasillas[i][j];
	}



	public void setSituacioncasillas(int[][] situacioncasillas) {
		this.situacioncasillas = situacioncasillas;
	}


	public void PartidaPrincipiante(){
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
		
        for ( int k=0;k<minas;k++)
        	{
            	do{
            		random1=(int)(Math.random()*minas);
            		random2=(int)(Math.random()*minas);
            	}while(situacioncasillas[random1][random2]!=0);
            	situacioncasillas[random1][random2]=9;
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
					for(int x = i-1; x < i+2; x++){
						for(int y = j-1; y < j+2; y++){
							if(x>=0 && x<minas && y>=0 && y<minas && situacioncasillas[x][y] != 9){
								situacioncasillas[x][y] ++;
							}
						}
					}
				}
			}
		}
	}
	
	
	
}
