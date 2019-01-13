package LP;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import LD.clsBaseDeDatos;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class frmAnuncio extends JInternalFrame implements ActionListener{

	/**
	 * 
	 */
	private JPanel panelBotones;
	private JButton btPlay;
	public JButton b;
	private JInternalFrame internalFrame;
	public JLabel cuentatras;
	private static Logger logger = Logger.getLogger(frmAnuncio.class.getName() );
	// Componente que permite gestionar los ficheros de video
	private EmbeddedMediaPlayerComponent mediaPlayer;
	
	// Ubicación de las librerías de VLC
	private static final String LIB_VLC = "C:\\Program Files\\libsVLC-64bits\\";
	private enum Estado {PLAY, PAUSE, STOP};
	private Estado estado;
	private File ficheroVideo;
	Thread hilo;

	/**
	 * Create the application.
	 */
	public frmAnuncio() 
	{
		this.setResizable(false);
    	this.setClosable(false);
    	this.setMaximizable(false);
    	this.setIconifiable(false); 
        this.setOpaque(false);
        this.toFront();
        cuentatras = new JLabel("5");
		cuentatras.setFont( new Font( Font.DIALOG, Font.BOLD, 25 ) );
	    cuentatras.setHorizontalAlignment( JLabel.CENTER );
	    cuentatras.setForeground( Color.RED );
	    cuentatras.setBackground(Color.BLACK);
	    cuentatras.setOpaque(true);
	    cuentatras.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), LIB_VLC);
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		mediaPlayer = new EmbeddedMediaPlayerComponent();
		
		initialize();
		iniciarVideo();
		this.setVisible(true);
		
		hilo = new Thread (new Runnable(){
			
			Integer segundos = 5;
			String seg = "5";
			int count=0; //Sirve para hacerlo más real. Solo hay 0,1 segundos de desviación entre la cuenta atrás y al parar el vídeo.
			//Si no implementamos este contador, si quedan 3 segundos para la cuenta atrás y lo paramos en el segundo 2,95;
			//la cuenta atrás bajaría hasta los dos segundos. Con count esto no pasa, bajaría hasta el 2,9 (redondeado, 3).
			boolean ejecutar=true;
			@Override
			public void run() 
			{
				while(ejecutar)
				{
					if(estado ==Estado.PLAY)
					{
						try {
							Thread.sleep(100);
							count++;
								if(count==10)
								{
								segundos --;
								count=0;
								}
								b.setText(segundos + " segundos");
								if(segundos == 0)
								{
									ejecutar=false;
									b.setEnabled(true);
									b.setText("Omitir anuncio");
								}
								seg= segundos.toString();
								cuentatras.setText(seg);
								cuentatras.setOpaque(true);
							} catch (InterruptedException e) {
								
							}
						cuentatras.setText(seg);
						cuentatras.setOpaque(true);
					}
					else{
						cuentatras.setText(seg);
						cuentatras.setOpaque(true);
					}
				}
			}			
	});
		hilo.start();	
	}
	
	/*
	 * Inicializa el componente que reproduce los videos
	 */
	private void iniciarVideo() {
	
		internalFrame.setContentPane(mediaPlayer);
		internalFrame.setVisible(true);
		estado = Estado.STOP;
	}
	
	/*
	 * Reproduce/pausa el archivo de video
	 */
	private void reproducirVideo() {
		
		
		int random = (int) (Math.random() * 10) + 1;
		if(random==1)
		{
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\LoryMoney.mp4");
		internalFrame.setTitle("Anuncio especial de Black Friday de Lory Money. Reproduzca el anuncio para seguir.");
		}
		if(random==2)
		{
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\Damn.mp4");
		internalFrame.setTitle("Anuncio especial Estrella Damn. Reproduzca el anuncio para seguir.");
		}
		if(random==3)
		{
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\Giratoria.mp4");
		internalFrame.setTitle("Anuncio especial de Mixta. Reproduzca el anuncio para seguir.");
		}
		if(random==4)
		{
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\Mixta.mp4");
		internalFrame.setTitle("Anuncio especial de Mixta. Reproduzca el anuncio para seguir.");
		}
		if(random==5)
		{
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\888.mp4");
		internalFrame.setTitle("Anuncio especial de apuestas 888. Reproduzca el anuncio para seguir.");
		}
		if(random==6)
		{
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\Pagafantas.mp4");
		internalFrame.setTitle("Anuncio especial de Fanta. Reproduzca el anuncio para seguir.");
		}
		if(random==7)
		{
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\Trina.mp4");
		internalFrame.setTitle("Anuncio especial de Trina. Reproduzca el anuncio para seguir.");
		}
		if(random==8)
		{
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\Piojo.mp4");
		internalFrame.setTitle("Anuncio especial de OTC. Reproduzca el anuncio para seguir.");
		}
		if(random==9)
		{
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\Famosa.mp4");
		internalFrame.setTitle("Anuncio especial de Las Muñecas de Famosa. Reproduzca el anuncio para seguir.");
		}
		if(random==10)
		{
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\Kelly.mp4");
		internalFrame.setTitle("Anuncio especial de Lelly Kelly. Reproduzca el anuncio para seguir.");
		}
		
		// El reproductor está parado
		if (estado == Estado.STOP) {
			mediaPlayer.getMediaPlayer().playMedia(ficheroVideo.getAbsolutePath());
			estado = Estado.PLAY;	
			btPlay.setText("||");
			logger.log( Level.INFO, "Anuncio parado.");
			
		}
		// El reproductor está pausado
		else if (estado == Estado.PAUSE) {
			mediaPlayer.getMediaPlayer().play();
			estado = Estado.PLAY;
			btPlay.setText("||");
			logger.log( Level.INFO, "Anuncio en pausa.");
		}
		// El reproductor está reproduciendo
		else {
			mediaPlayer.getMediaPlayer().pause();
			estado = Estado.PAUSE;
			btPlay.setText(">");
			logger.log( Level.INFO, "Anuncio en reproducción.");
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

		this.setTitle("Anuncio");
		this.setBounds(0, 0, 585, 370);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		panelBotones = new JPanel();
		this.getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		btPlay = new JButton(">");
		btPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reproducirVideo();
			}
		});
		panelBotones.add(btPlay);
		
		b = new JButton("5 segundos");
		b.setActionCommand("BotonSalir");
		b.setEnabled(false);
	    b.addActionListener(this);
	    panelBotones.add(b);
		
		internalFrame = new JInternalFrame("");
		internalFrame.setFrameIcon(null);
		internalFrame.setBorder(null);
		this.getContentPane().add(internalFrame, BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand())
		{
			case "BotonSalir":			
					b.setEnabled(true);
					mediaPlayer.getMediaPlayer().stop();
					estado = Estado.STOP;
					this.dispose();	
			break;
		}	
	}
}