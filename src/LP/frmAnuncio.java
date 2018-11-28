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


public class frmAnuncio extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmReproductorDeVideo;
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

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frmAnuncio window = new frmAnuncio();
//					window.frmReproductorDeVideo.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public frmAnuncio() {
		this.setResizable(false);
    	this.setClosable(true);
    	this.setIconifiable(true); 
        this.setOpaque(true);
        this.toFront();
		inicializarVLCJ();
		initialize();
		iniciarVideo();
		frmReproductorDeVideo.setVisible(true);
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
		
		ficheroVideo= new File("C:\\Users\\ALUMNO\\workspace\\Prog3-buscaminas\\anuncio\\LoryMoney.mp4");
		// El reproductor está parado
		if (estado == Estado.STOP) {
			internalFrame.setTitle("Anuncio especial Black Friday "+ficheroVideo.getName()+". Reproduzca el anuncio para seguir.");
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
	
	/*
	 * Inicializa la libreria VLCJ, cargando las librerías del sistema e instanciando el reproductor
	 */
	private void inicializarVLCJ() {
		
		cargaLibreria();
		mediaPlayer = new EmbeddedMediaPlayerComponent();
	}
	
	/*
	 * Carga la libreria libvlc.dll en la ruta indicada
	 * Es necesario instalar la aplicación VLC 2.X 
	 */
	
	private void cargaLibreria() {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), LIB_VLC);
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmReproductorDeVideo = new JFrame();
		frmReproductorDeVideo.setTitle("Anuncio");
		frmReproductorDeVideo.setBounds(100, 100, 550, 300);
		frmReproductorDeVideo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		panelBotones = new JPanel();
		frmReproductorDeVideo.getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		btPlay = new JButton(">");
		btPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reproducirVideo();
				frmReproductorDeVideo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		panelBotones.add(btPlay);
		
		internalFrame = new JInternalFrame("");
		internalFrame.setFrameIcon(null);
		internalFrame.setBorder(null);
		frmReproductorDeVideo.getContentPane().add(internalFrame, BorderLayout.CENTER);
		frmReproductorDeVideo.setVisible(true);
	}
}