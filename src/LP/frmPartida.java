package LP;


import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class frmPartida extends JInternalFrame implements ActionListener
{
	public VentanaPrincipiante panelp;
	public frmAnuncio anuncio;
	public JLabel cronometro;
	// Valores para guardar los minutos y segundos de cada partida.
	public int imin=0, iseg=0;
	public enum Dificultad {PRINCIPIANTE, AMATEUR, EXPERTO};
	public Dificultad dificultad;
	Thread hilo;
	
	
	
	
	public frmPartida(int tipo, int width, int height)
	{
		this.setLayout(new BorderLayout());
		cronometro = new JLabel("00:00");
		cronometro.setFont( new Font( Font.DIALOG, Font.BOLD, 25 ) );
	    cronometro.setHorizontalAlignment( JLabel.CENTER );
	    cronometro.setForeground( Color.RED );
	    cronometro.setBackground( Color.BLACK );
	    cronometro.setOpaque( true );
	    cronometro.setSize(75, 50);
	    cronometro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	    this.add(cronometro, BorderLayout.NORTH);
	    
		if(tipo == 1)
		{
			dificultad = Dificultad.PRINCIPIANTE;
			panelp = new VentanaPrincipiante(10);
			this.setSize(panelp.getWidth(), panelp.getHeight()+20);
			this.setTitle("Partida Principiante");
			this.add(panelp, BorderLayout.CENTER);
			this.pack();
			this.setVisible(true);
			this.setResizable(false);
			this.setClosable(true);
			 
			
		}
		if(tipo == 2)
		{
			dificultad = Dificultad.AMATEUR;
			panelp = new VentanaPrincipiante(20);
			this.setSize(panelp.getWidth(), panelp.getHeight()+20);
			this.setTitle("Partida Amateur");
			this.add(panelp, BorderLayout.CENTER);
			this.setVisible(true);
			this.pack();
			this.setResizable(false);
			this.setClosable(true);
			
		}
		if(tipo == 3)
		{
			dificultad = Dificultad.EXPERTO;
			panelp = new VentanaPrincipiante(30);
			this.setSize(panelp.getWidth(), panelp.getHeight()+20);
			this.setTitle("Partida Experto");
			this.add(panelp, BorderLayout.CENTER);
			this.setVisible(true);
			this.setResizable(false);
			this.setClosable(true);
		}
		
		hilo = new Thread (new Runnable(){

			@Override
			public void run() 
			{
				Integer minutos = 0, segundos = 0;
				String min = "", seg = "";
				try
				{
					while(!(panelp.partidaAcabada))
					{
						Thread.sleep(1000);
						segundos ++;
						if(segundos == 60)
						{
							minutos ++;
							segundos = 0;
						}
						
						if( minutos < 10 ) min = "0" + minutos;
			            else min = minutos.toString();
			            if( segundos < 10 ) seg = "0" + segundos;
			            else seg = segundos.toString();
			            
			            cronometro.setText(min + ":" + seg);
					}
				}
				catch(Exception e){}
				cronometro.setText(min+":"+seg);
				imin = Integer.parseInt(min);
				iseg = Integer.parseInt(seg);
			}
			
		});
		hilo.start();
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
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
//// Es importante darle tama�o -pack()- al JInternalFrame,
//// porque si no, tendr� tama�o 0,0 y no lo veremos.
//internal.pack();
//
//// Por defecto el JInternalFrame no es redimensionable ni
//// tiene el bot�n de cerrar, as� que se lo ponemos.
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