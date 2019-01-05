package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import COMUN.clsConstantes.enFicDatos;
import LD.clsBaseDeDatos;
import LN.clsJuegoPrincipiante;
import LN.clsUsuario;

public class VentanaPrincipiante extends JPanel 
{
	int numminas;
	int casillasbuenas;
	public JLabel JLabelP;
	private JButton botonesCasillaP [][];
	private boolean pulsada [][];
	clsJuegoPrincipiante PartidaPrincipiante;
	public boolean partidaAcabada;
	clsUsuario usu1 = new clsUsuario();
	clsUsuario usu2 = new clsUsuario();
	int punanterior;
	private JLabel labelD;
	private String NumCasillas;
	
	
	private String imagenesbotones[] = {"src/imagenes/0.PNG",
								"src/imagenes/1.PNG",
								"src/imagenes/2.PNG",
								"src/imagenes/3.PNG",
								"src/imagenes/4.PNG",
								"src/imagenes/5.PNG",
								"src/imagenes/6.PNG",
								"src/imagenes/7.PNG",
								"src/imagenes/8.PNG",
								"src/imagenes/9.PNG"};
	private ImageIcon[] imagenes = new ImageIcon[10];
	
	public VentanaPrincipiante(int minas){
		partidaAcabada = false;
		numminas = minas;
		pulsada = new boolean [numminas][numminas];
		PartidaPrincipiante = new clsJuegoPrincipiante(numminas);
		if(minas == 10)
		{
			NumCasillas = " de 90";
			this.setSize(numminas*20, numminas*20+40);
		}
		if(minas == 20)
		{
			NumCasillas = " de 360";
			this.setSize(numminas*20, numminas*20+40);
		}
		if(minas == 30)
		{
			NumCasillas = " de 810";
			this.setSize(numminas*20, numminas*20+40);
		}
		
		this.setLayout(null);
		for (int i = 0; i < numminas; i++){
			for (int j = 0; j < numminas; j++){
				pulsada[i][j] = false;
			}
		}
		casillasbuenas = PartidaPrincipiante.getCasillasbuenas();
		
		botonesCasillaP  = new JButton [numminas][numminas];
		labelD = new JLabel();
		labelD.setBounds(15, 15, 60, 15);
		this.add(labelD);
		AsignarBotonesP();
		GestionEventosP();
	}
	
	public void AsignarBotonesP(){
		for(int i = 0; i < imagenes.length; i++){
			imagenes[i] = new ImageIcon(imagenesbotones[i]);
		}
		for(int f = 0; f < numminas; f++){
			for (int j = 0;j < numminas; j++){
				botonesCasillaP[f][j] = new JButton();
				botonesCasillaP[f][j].setBounds(20*f, 20*j+40, 20, 20);
				botonesCasillaP[f][j].setBackground(java.awt.Color.gray);
				this.add(botonesCasillaP[f][j]);
			}
		
		}
	}
	
	
	public void GestionEventosP(){
		for(int i = 0; i < numminas; i++){
			for(int j = 0; j < numminas; j++){
				botonesCasillaP[i][j].addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						for(int i = 0; i < numminas; i++){
							for(int j = 0; j < numminas; j++){
								if(e.getSource() == botonesCasillaP[i][j]){
									PulsarBotonP(i, j);
								}
							}
						}
					}
					
				});
			}
		}
	}
	
	// RECURSIVIDAD al llamar el método PulsarBotonP al método PulsarBotonP2 y este, a su vez, volver a llamar a PulsarBotonP intentando llegar
	// caso base
	public void PulsarBotonP(int i, int j){
		PulsarBotonP2(i, j);
		VisualizarCasillasP();
	}
	
	public void PulsarBotonP2(int i, int j){
		if(i>=0 && i<numminas && j>=0 && j<numminas && pulsada[i][j] == false){
			pulsada[i][j] = true;
			// Si en la casilla pulsada se encuentra una bomba
			if(PartidaPrincipiante.getSituacioncasillas(i, j) == 9){
				DestaparBotonP();
				usuario();				
				actualizarPuntos();
				JOptionPane.showMessageDialog(null, "Lo siento, has perdido");
				partidaAcabada = true;
				
			}
			else{
				casillasbuenas++;
				PartidaPrincipiante.setCasillasbuenas(casillasbuenas);
				if (casillasbuenas==(numminas*numminas - numminas)){
					DestaparBotonP();
					usuario();
					actualizarPuntos();					
					JOptionPane.showMessageDialog(null, "Has ganado!");
					partidaAcabada = true;
				}
				 labelD.setText(casillasbuenas+NumCasillas);
			}
			//Si no es bomba y no hay bombas cerca, pone las casillas visibles.
			if(PartidaPrincipiante.getSituacioncasillas(i, j) == 0){
				PulsarBotonP(i, j - 1);
				PulsarBotonP(i, j + 1);
				PulsarBotonP(i - 1, j);
				PulsarBotonP(i + 1, j);
			}
		}
	}
	
	public void DestaparBotonP(){
		for(int i = 0; i < numminas; i++){
			for(int j = 0; j < numminas; j++){
				pulsada[i][j] = true;
			}
		}
	}
	
	public void VisualizarCasillasP(){
		for(int i = 0; i < numminas; i++){
			for(int j = 0; j < numminas; j++){
				if(pulsada[i][j] == true){
					if(PartidaPrincipiante.getSituacioncasillas(i, j) == 0){
						botonesCasillaP[i][j].setIcon(imagenes[0]);
					}
					else if(PartidaPrincipiante.getSituacioncasillas(i, j) == 1){
						botonesCasillaP[i][j].setIcon(imagenes[1]);
					}
					else if(PartidaPrincipiante.getSituacioncasillas(i, j) == 2){
						botonesCasillaP[i][j].setIcon(imagenes[2]);
					}
					else if(PartidaPrincipiante.getSituacioncasillas(i, j) == 3){
						botonesCasillaP[i][j].setIcon(imagenes[3]);
					}
					else if(PartidaPrincipiante.getSituacioncasillas(i, j) == 4){
						botonesCasillaP[i][j].setIcon(imagenes[4]);
					}
					else if(PartidaPrincipiante.getSituacioncasillas(i, j) == 5){
						botonesCasillaP[i][j].setIcon(imagenes[5]);
					}
					else if(PartidaPrincipiante.getSituacioncasillas(i, j) == 6){
						botonesCasillaP[i][j].setIcon(imagenes[6]);
					}
					else if(PartidaPrincipiante.getSituacioncasillas(i, j) == 7){
						botonesCasillaP[i][j].setIcon(imagenes[7]);
					}
					else if(PartidaPrincipiante.getSituacioncasillas(i, j) == 8){
						botonesCasillaP[i][j].setIcon(imagenes[8]);
					}
					else if(PartidaPrincipiante.getSituacioncasillas(i, j) == 9){
						botonesCasillaP[i][j].setIcon(imagenes[9]);
					}
				}
			}
		}
	}
	
	public void QuitarBotonesP(){
		for(int i = 0; i < numminas; i++){
			for(int j = 0; j < numminas; j++){
				this.remove(botonesCasillaP[i][j]);
			}
		}
	}
	
	public void usuario(){
	
		usu2=clsBaseDeDatos.leerDeFicheroSerializado2(".\\data\\sesion.dat");
		usu1=clsBaseDeDatos.cargarDeTabla2(clsBaseDeDatos.getStatement(), usu2.getNomUsuario(), usu2.getMail(), usu2.getContrasena());
	}
	public void actualizarPuntos(){
		punanterior=usu1.getPuntuacion();
		usu1.setPuntuacion(casillasbuenas+punanterior);
		clsBaseDeDatos.modificarFilaEnTabla(clsBaseDeDatos.getStatement(), usu1);
	}

}
