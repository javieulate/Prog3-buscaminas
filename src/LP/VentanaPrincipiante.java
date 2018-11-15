package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LN.clsJuegoPrincipiante;

public class VentanaPrincipiante extends JPanel 
{
	int numminas;
	public JLabel JLabelP;
	private JButton botonesCasillaP [][];
	
	private String imagenesbotones[] = {"imagenes/0.PNG",
								"imagenes/1.PNG",
								"imagenes/2.PNG",
								"imagenes/3.PNG",
								"imagenes/4.PNG",
								"imagenes/5.PNG",
								"imagenes/6.PNG",
								"imagenes/7.PNG",
								"imagenes/8.PNG",
								"imagenes/9.PNG"};
	private ImageIcon[] imagenes = new ImageIcon[10];
	
	public VentanaPrincipiante(){
		clsJuegoPrincipiante PartidaPrincipiante = new clsJuegoPrincipiante();
		this.setSize(200, 240);
		numminas = PartidaPrincipiante.getMinas();
		
		botonesCasillaP  = new JButton [numminas][numminas];
		AsignarBotonesP();
		AsignarImagenesP();
	}
	
	public void AsignarBotonesP(){
		for(int i = 0; i < imagenes.length; i++){
			imagenes[i] = new ImageIcon(getClass().getResource(imagenesbotones[i]));
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
	
	public void AsignarImagenesP(){
		
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
	
	public void PulsarBotonP(int i, int j){
		
	}
}
