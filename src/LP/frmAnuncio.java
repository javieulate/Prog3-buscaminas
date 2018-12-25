package LP;



import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JInternalFrame;


public class frmAnuncio extends JInternalFrame implements ActionListener{

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;

	private JPanel panelBotones;
	private JButton btPlay;
	private JInternalFrame internalFrame;
	
	// Componente que permite gestionar los ficheros de video
	private EmbeddedMediaPlayerComponent mediaPlayer;
	
	// Ubicación de las librerías de VLC
	private static final String LIB_VLC = "C:\\Program Files\\libsVLC-64bits\\";
	private enum Estado {PLAY, PAUSE, STOP};
	private Estado estado;
	private File ficheroVideo;


	/**
	 * Create the application.
	 */
	public frmAnuncio() {
		this.setResizable(false);
    	this.setClosable(false);
    	this.setMaximizable(false);
    	this.setIconifiable(false); 
        this.setOpaque(true);
        this.toFront();

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), LIB_VLC);
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		mediaPlayer = new EmbeddedMediaPlayerComponent();
		
		initialize();
		iniciarVideo();
		this.setVisible(true);
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
		
		JButton b = new JButton("Salir");
		b.setActionCommand("BotonSalir");
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
				if (estado == Estado.STOP)
				{
					//No hace nada
				}
				else
				{
					mediaPlayer.getMediaPlayer().stop();
					estado = Estado.STOP;
					this.dispose();
				}
		
			break;
		}	
	}
}