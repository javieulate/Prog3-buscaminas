package LP;


import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LD.clsBaseDeDatos;
import LN.clsUsuario;

public class frmPartida extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VentanaPrincipiante panelp;
	public frmAnuncio anuncio;
	public JLabel cronometro;
	// Valores para guardar los minutos y segundos de cada partida.
	public int ihora=0, imin=0, iseg=0;
	public enum Dificultad {PRINCIPIANTE, AMATEUR, EXPERTO};
	public Dificultad dificultad;
	public int casillasAcertadas;
	clsUsuario usu1 = new clsUsuario();
	clsUsuario usu2 = new clsUsuario();
	int punanterior;
	int numvidas;
	
	Thread hilo;

	
	
	
	
	public frmPartida(int tipo)
	{
		
		this.setLayout(new BorderLayout());
		cronometro = new JLabel("00:00:00");
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
			this.setSize(panelp.getWidth()+13, panelp.getHeight()+cronometro.getHeight()+47);
			this.add(panelp, BorderLayout.CENTER);
		}
		if(tipo == 2)
		{
			dificultad = Dificultad.AMATEUR;
			panelp = new VentanaPrincipiante(20);
			this.setSize(panelp.getWidth()+13, panelp.getHeight()+cronometro.getHeight()+47);
			this.add(panelp, BorderLayout.CENTER);
			
		}
		if(tipo == 3)
		{
			dificultad = Dificultad.EXPERTO;
			panelp = new VentanaPrincipiante(30);
			this.setSize(panelp.getWidth()+13, panelp.getHeight()+cronometro.getHeight()+47);
			this.add(panelp, BorderLayout.CENTER);
			this.setVisible(true);
		}
		
		hilo = new Thread (new Runnable(){

			@Override
			public void run() 
			{
				Integer horas = 0, minutos = 0, segundos = 0;
				String hora = "", min = "", seg = "";
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
							if(minutos == 60)
							{
								horas++;
								minutos = 0;
							}
						}
						
						if(horas < 10) hora = "0" + horas;
						else hora = horas.toString();
						if( minutos < 10 ) min = "0" + minutos;
			            else min = minutos.toString();
			            if( segundos < 10 ) seg = "0" + segundos;
			            else seg = segundos.toString();
			            
			            cronometro.setText(hora + ":" + min + ":" + seg);
					}
				}
				catch(Exception e){}
				
				cronometro.setText(hora + ":" + min + ":" + seg);
				
				ihora = horas;
				imin = minutos;
				iseg = segundos;
				casillasAcertadas = panelp.casillasbuenas;
				usuario();
				actualizarPuntos();
				vidas(casillasAcertadas);
				clsBaseDeDatos.modificarFilaEnTabla(clsBaseDeDatos.getStatement(), usu1);
			}
			
		});
		hilo.start();
		
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		
	}
	
	public void vidas(int aciertos)
	{
		if(casillasAcertadas == 90)
		{
			// Se suman 5 vidas si se gana la partida en principiante
			numvidas = usu1.getNumeroVidas();
			usu1.setNumeroVidas(numvidas + 5);
		}
		
		if(casillasAcertadas == 360)
		{
			// Se suman 10 vidas si se gana la partida en amateur
			numvidas = usu1.getNumeroVidas();
			usu1.setNumeroVidas(numvidas + 10);
		}
		
		if(casillasAcertadas == 810)
		{
			// Se suman 15 vidas si se gana la partida en experto
			numvidas = usu1.getNumeroVidas();
			usu1.setNumeroVidas(numvidas + 15);
		}
		else
		{
			numvidas = usu1.getNumeroVidas();
			if(numvidas > 0)
			{
				usu1.setNumeroVidas(numvidas - 1);
			}
			else{
				
			}
		}
		
	}
	
	public void usuario(){
		
		usu2 = clsBaseDeDatos.leerDeFicheroSerializado2(".\\data\\sesion.dat");
		usu1 = clsBaseDeDatos.cargarDeTabla2(clsBaseDeDatos.getStatement(), usu2.getNomUsuario(), usu2.getMail(), usu2.getContrasena());
	}
	public void actualizarPuntos(){
		punanterior = usu1.getPuntuacion();
		usu1.setPuntuacion(casillasAcertadas+punanterior);
		
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