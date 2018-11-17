package LP;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class frmPartida extends JFrame
{
	public VentanaPrincipiante panelp;
	
	public frmPartida(){
		
		this.setSize(200, 250);
		this.setTitle("Partida Principiante");
		panelp = new VentanaPrincipiante();
		add(panelp);
	}
}
