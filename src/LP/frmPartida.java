package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class frmPartida extends JInternalFrame implements ActionListener
{
	public VentanaPrincipiante panelp;
	public frmAnuncio anuncio;
	
	public frmPartida(int tipo)
	{
		if(tipo == 1)
		{
			panelp = new VentanaPrincipiante(10);
			this.setSize(panelp.getWidth(), panelp.getHeight()+20);
			this.setTitle("Partida Principiante");
//			anuncio = new frmAnuncio();
//			this.add(anuncio);
//			this.setVisible(true);
//			this.setResizable(false);
//			this.setClosable(true);			
			this.add(panelp);
			this.pack();
			this.setVisible(true);
			this.setResizable(false);
			this.setClosable(true);
		}
		if(tipo == 2)
		{
			panelp = new VentanaPrincipiante(20);
			this.setSize(panelp.getWidth(), panelp.getHeight()+20);
			this.setTitle("Partida Amateur");
			this.add(panelp);
			this.setVisible(true);
			this.pack();
			this.setResizable(false);
			this.setClosable(true);
		}
		if(tipo == 3)
		{
			panelp = new VentanaPrincipiante(30);
			this.setSize(panelp.getWidth(), panelp.getHeight()+20);
			this.setTitle("Partida Experto");
			this.add(panelp);
			this.setVisible(true);
			this.setResizable(false);
			this.setClosable(true);
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

//JFrame v = new JFrame("Prueba JInternalFrame");
//JDesktopPane dp = new JDesktopPane();
//v.getContentPane().add(dp);
//
//// Se construye el panel que ira dentro del JInternalFrame
//JPanel p = new JPanel();
//p.setLayout(new FlowLayout());
//p.add (new JLabel("Una etiqueta"));
//p.add (new JTextField(10));
//
//// Se construye el JInternalFrame
//JInternalFrame internal = new JInternalFrame("Un Internal Frame");
//internal.add(p);
//
//// Es importante darle tamaño -pack()- al JInternalFrame,
//// porque si no, tendrá tamaño 0,0 y no lo veremos.
//internal.pack();
//
//// Por defecto el JInternalFrame no es redimensionable ni
//// tiene el botón de cerrar, así que se lo ponemos.
//internal.setResizable(true);
//internal.setClosable(true);
//
//// Se mete el internal en el JDesktopPane
//dp.add(internal);
//
//// Se visualiza todo.
//v.setSize(500,500);
//v.setVisible(true);
//v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//// Se visualiza el JInternalFrame 
//internal.setVisible(true);