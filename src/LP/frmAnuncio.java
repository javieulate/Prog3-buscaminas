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

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;


public class frmAnuncio extends JInternalFrame implements ActionListener{

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;

	private JPanel panelBotones;
	private JButton btPlay;
	public JButton b;
	private JInternalFrame internalFrame;
	public JLabel cuentatras;
	
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
	//    cuentatras.setBounds(530, 23, 26, 50);
	    
	//    this.add(cuentatras);
        
        
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
		internalFrame.setTitle("Anuncio especial Black Friday LoryMoney.mp4. Reproduzca el anuncio para seguir.");
		internalFrame.setVisible(true);
		estado = Estado.STOP;
	}
	
	/*
	 * Reproduce/pausa el archivo de video
	 */
	private void reproducirVideo() {
		
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\LoryMoney.mp4");

		// El reproductor está parado
		if (estado == Estado.STOP) {
			mediaPlayer.getMediaPlayer().playMedia(ficheroVideo.getAbsolutePath());
			estado = Estado.PLAY;	
			btPlay.setText("||");
		}
		// El reproductor está pausado
		else if (estado == Estado.PAUSE) {
			mediaPlayer.getMediaPlayer().play();
			estado = Estado.PLAY;
			btPlay.setText("||");
		}
		// El reproductor está reproduciendo
		else {
			mediaPlayer.getMediaPlayer().pause();
			estado = Estado.PAUSE;
			btPlay.setText(">");
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