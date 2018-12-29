package LP;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JList;

import COMUN.clsElementoRepetido;
import COMUN.clsUsuarioNoRegistrado;
import LN.clsGestor;
import LN.clsUsuario;
import LN.itfProperty;
import static COMUN.clsConstantes.*;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JScrollPane;

import java.awt.SystemColor;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;



public class frmMenuPrincipal extends JFrame implements ActionListener, InternalFrameListener
{
	 JTextArea display;
	 JTextField buscador;
	 JDesktopPane desktop;
	 JInternalFrame subirpunt;
	 private static Logger logger = Logger.getLogger( frmMenuPrincipal.class.getName() );
	 String newline = "\n"; 
	 static final int desktopWidth = 600;
	 static final int desktopHeight = 431;

	/**
	 * Con este método creamos la ventana, incluyendo todos los componentes que van a ser
	 * visualizados.
	 */
	public frmMenuPrincipal() 
	{
		loggeo();
		 desktop = new JDesktopPane();
		 desktop.setBackground(SystemColor.LIGHT_GRAY);
		 desktop.setPreferredSize(this.getPreferredSize());
	     this.setContentPane(desktop);
		
		 
		this.setTitle("Menu Principal");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			  public void windowClosing(WindowEvent we){
			    clsGestor.CerrarSesion();;
			  }
			});
		this.setBounds(100, 100, desktopWidth, desktopHeight);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(createMenuBar());
		
		JTextArea txtrMenuUsuario = new JTextArea();
		txtrMenuUsuario.setForeground(Color.BLACK);
		txtrMenuUsuario.setEditable(false);
		txtrMenuUsuario.setBounds(150, 20, 309, 48);
		txtrMenuUsuario.setBackground(Color.LIGHT_GRAY);
		txtrMenuUsuario.setFont(new Font("Microsoft Tai Le", Font.BOLD, 17));
		
				try 
		{
			txtrMenuUsuario.setText("Bienvenid@, "+clsGestor.NomUsuario());
		} 
		catch (IOException e1) 
		{
			txtrMenuUsuario.setText("Bienvenid@");
		}
		desktop.add(txtrMenuUsuario);
		
		JLabel label = new JLabel("");
		label.setBounds(140, 53, 537, 203);
		ImageIcon logo= new ImageIcon("src/imagenes/buscaminas.png");
		desktop.setLayout(null);
		label.setIcon(logo);
		getContentPane().add(label);
		
	}
	/**
	 * Este es le método para crear la barra de herramientas que vemos en la parte de arriba de la ventana.
	 * @return barra de herramientas creada.
	 */
	protected JMenuBar createMenuBar() 
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu nivel = new JMenu("Nivel");
		nivel.setMnemonic(KeyEvent.VK_N); 
		menuBar.add(nivel);
			
		JMenuItem principiante = new JMenuItem("Principiante");
		principiante.setMnemonic(KeyEvent.VK_P);
		principiante.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.ALT_MASK));
		principiante.setActionCommand(CMD_BTN_PRINCIPIANTE);
		principiante.addActionListener(this);
		nivel.add(principiante);
		
		JMenuItem amateur = new JMenuItem("Amateur");
		amateur.setMnemonic(KeyEvent.VK_A);
		amateur.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.ALT_MASK));
		amateur.setActionCommand(CMD_BTN_AMATEUR);
		amateur.addActionListener(this);
		nivel.add(amateur);
		
		JMenuItem experto = new JMenuItem("Experto");
		experto.setMnemonic(KeyEvent.VK_E);
		experto.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.ALT_MASK));
		experto.setActionCommand(CMD_BTN_EXPERTO);
		experto.addActionListener(this);
		nivel.add(experto);
		
		JMenu ayuda = new JMenu("Ayuda");
		ayuda.setMnemonic(KeyEvent.VK_A);
		menuBar.add(ayuda);
		
		URLabel label = new URLabel();
        label.setURL("https://drive.google.com/open?id=18fz2OB2qhgtiEDcL0h9mDG0mb023lVfH");
        label.setLocation(10, 0); 
        label.setText("Manual");
        ayuda.add(label);
        
        ayuda.addSeparator();
        
        URLabel label2 = new URLabel();
        label2.setURL("https://www.youtube.com/watch?v=5Qu-AjHkQ54");
        label2.setLocation(10, 0); 
        label2.setText("Tutorial de youtube");
        ayuda.add(label2);
        
//		try {
//		     File path = new File ("carpeta/tuArchivo.pdf");
//		     Desktop.getDesktop().open(path);
//		}catch (IOException ex) {
//		     ex.printStackTrace();
//		}
		
        JMenu rankings = new JMenu("Ranking");
		rankings.setMnemonic(KeyEvent.VK_N); 
		menuBar.add(rankings);
			
		JMenuItem rankingpersonal = new JMenuItem("Ranking personal");
		rankingpersonal.setMnemonic(KeyEvent.VK_P);
		rankingpersonal.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.ALT_MASK));
		rankingpersonal.setActionCommand(CMD_BTN_RANKINGPERSONAL);
		rankingpersonal.addActionListener(this);
		rankings.add(rankingpersonal);
		
		JMenuItem rankinggeneral = new JMenuItem("Ranking principal");
		rankinggeneral.setMnemonic(KeyEvent.VK_G);
		rankinggeneral.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_G, ActionEvent.ALT_MASK));
		rankinggeneral.setActionCommand(CMD_BTN_RANKINGPRINCIPAL);
		rankinggeneral.addActionListener(this);
		rankings.add(rankinggeneral);
        
        JMenu reset = new JMenu("Resetear");
        reset.setMnemonic(KeyEvent.VK_R);
        reset.getAccessibleContext().setAccessibleDescription(
                "Resetear los datos");
        menuBar.add(reset);
        JMenuItem res = new JMenuItem("Resetear", KeyEvent.VK_R);
        res.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.ALT_MASK));
        res.getAccessibleContext().setAccessibleDescription(
                "Resetear los datos.");
        res.setActionCommand("Resetear");
        res.addActionListener(this);
        reset.add(res); 
        	
		JMenu mnCuenta = new JMenu("Cuenta");
		menuBar.add(mnCuenta);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesión");
		mnCuenta.add(mntmCerrarSesion);
		mntmCerrarSesion.setActionCommand(CMD_BTN_CERRARSESION);
		mntmCerrarSesion.addActionListener(this);
		
		return menuBar;
	}
	
	
	/**
	 * Este es el método que ejecuta dieferentes acciones según lo que sea pulsado en la pantalla.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
			case CMD_BTN_PRINCIPIANTE:
				
				frmAnuncio AnPrincipiante = new frmAnuncio();
				this.setVisible(true);
				AnPrincipiante.setVisible(true);
				desktop.add(AnPrincipiante);
				AnPrincipiante.toFront();
				setSize(585, 370);
				
				AnPrincipiante.b.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						frmPartida NuevaPartida = new frmPartida(1, desktopWidth, desktopHeight);
						NuevaPartida.setVisible(true);
						desktop.add(NuevaPartida);
						NuevaPartida.toFront();
						logger.log( Level.INFO, "Iniciando partida principiante.");
						setSize(NuevaPartida.getWidth()+16, NuevaPartida.getHeight()+61);
					}
				});
				
				break;
			
			case CMD_BTN_AMATEUR:
				
				frmAnuncio AnAmateur = new frmAnuncio();
				this.setVisible(true);
				AnAmateur.setVisible(true);
				desktop.add(AnAmateur);
				AnAmateur.toFront();
				setSize(585, 370);
				
				AnAmateur.b.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						frmPartida NuevaPartidaAmateur = new frmPartida(2, desktopWidth, desktopHeight);
						NuevaPartidaAmateur.setVisible(true);
						desktop.add(NuevaPartidaAmateur);
						NuevaPartidaAmateur.toFront();
						logger.log( Level.INFO, "Iniciando partida Amateur.");
						setSize(NuevaPartidaAmateur.getWidth()+25, NuevaPartidaAmateur.getHeight()+);
					}
					
				});
				break;
				
			case CMD_BTN_EXPERTO:
				frmAnuncio AnExperto = new frmAnuncio();
				this.setVisible(true);
				AnExperto.setVisible(true);
				desktop.add(AnExperto);
				AnExperto.toFront();
				setSize(585, 370);
				
				AnExperto.b.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						frmPartida NuevaPartidaExperto = new frmPartida(3, desktopWidth, desktopHeight);
						NuevaPartidaExperto.setVisible(true);
						desktop.add(NuevaPartidaExperto);
						NuevaPartidaExperto.toFront();
						logger.log( Level.INFO, "Iniciando partida Amateur.");
						setSize(NuevaPartidaExperto.getWidth()+48, NuevaPartidaExperto.getHeight()+183);
					}
					
				});
				break;
				
			case CMD_BTN_RANKINGPERSONAL:
							
			try {
				clsGestor.NomUsuario();
				frmRankingPersonal rankingpersonal = new frmRankingPersonal();
				//t.createAndShowGUI();
				this.setVisible(true);
				rankingpersonal.setVisible(true);
				desktop.add(rankingpersonal);
				rankingpersonal.toFront();
				logger.log( Level.INFO, "Mostrando ranking personal.");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "El usuario se acaba de registrar, no hay puntuaciones disponibles.");
				logger.log( Level.SEVERE, "No hay información disponible para este usuario.", e1 );
			}
				
				
				break;
				
			case CMD_BTN_RANKINGPRINCIPAL:
				
				frmRankingPrincipal rankingprincipal = new frmRankingPrincipal();
				//t.createAndShowGUI();
				this.setVisible(true);
				rankingprincipal.setVisible(true);
				desktop.add(rankingprincipal);
				rankingprincipal.toFront();
				logger.log( Level.INFO, "Mostrando ranking principal.");
				
				break;
							
			case CMD_BTN_CERRARSESION:
				
				this.setVisible(false);
				clsGestor.CerrarSesion();
				frmPantalla vuelta = new frmPantalla();
				vuelta.setVisible(true);
				logger.log( Level.INFO, "Cerrando sesión.");
				break;
					
			case "Resetear":
				int reply = JOptionPane.showConfirmDialog(this, "Si continua se borrarán todos los datos. ¿Desea continuar?", "Resetear", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		        	
		 //TODO falta hacerlo      	
					JOptionPane.showMessageDialog(this, "Datos reseteados con éxito.");
					logger.log( Level.INFO, "Se han borrado los datos de la base de datos.");
		        }
		        else {}
			
	break;	
			
			default: break;
		}
	}
	
	 public void internalFrameClosing(InternalFrameEvent e) {
	        displayMessage("Internal frame closing", e);
	    }

	    public void internalFrameClosed(InternalFrameEvent e) {
	        displayMessage("Internal frame closed", e);
	    }

	    public void internalFrameOpened(InternalFrameEvent e) {
	        displayMessage("Internal frame opened", e);
	    }

	    public void internalFrameIconified(InternalFrameEvent e) {
	        displayMessage("Internal frame iconified", e);
	    }

	    public void internalFrameDeiconified(InternalFrameEvent e) {
	        displayMessage("Internal frame deiconified", e);
	    }

	    public void internalFrameActivated(InternalFrameEvent e) {
	        displayMessage("Internal frame activated", e);
	    }

	    public void internalFrameDeactivated(InternalFrameEvent e) {
	        displayMessage("Internal frame deactivated", e);
	    }

	    //Add some text to the text area.
	    void displayMessage(String prefix, InternalFrameEvent e) {
	        String s = prefix + ": " + e.getSource();
	        display.append(s + newline);
	        display.setCaretPosition(display.getDocument().getLength());
	    }
	
	    /**
	     * Clase para adjuntar un URL en el menu.
	     * @author ALUMNO
	     *
	     */
	    class URLabel extends JLabel implements MouseListener 
	    {

	        private URI url;
	        /**
	     * Constructor de clase
	     */
	        public URLabel(){        
	            //enlace por default
	            try {
	                url = new URI("http://www.uefa.com/");
	            } catch (URISyntaxException ex) {}
	            //propiedades de JLabel
	            this.setToolTipText( url.toString() );
	            this.setSize(100, 30);
	            this.setVisible(true);
	            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	            this.addMouseListener(this);

	        }

	        /**
	     * Metodo para asignar una direccion web
	     * @param url URL de pagina web
	     */
	        public void setURL( String url )
	        {
	            try {
	                this.url = new URI(url);
	                this.setToolTipText( url );
	            } catch (URISyntaxException ex) {
	                System.err.println( ex.getMessage() );
	            }
	        }

	        /**
	     * Retorna la direccion web que este asignado al objeto
	     * @return String Direccion URL
	     */
	        public String getURL()
	        {
	            return this.url.toString();
	        }

	       @Override
	       protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Rectangle r;            
	            r = g.getClipBounds();            
	            g.drawLine(0, r.height - getFontMetrics(getFont()).getDescent(), getFontMetrics(getFont())
	                    .stringWidth(getText()), r.height - getFontMetrics(getFont()).getDescent());        
	      }

	        /**
	     * Cuando se realice un clic sobre el componente JLabel, se abre el enlace en el navegador
	     * predefinido del sistema operativo
	     */
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	            if ( desktop != null && desktop.isSupported(Desktop.Action.BROWSE) ) {
	                try {
	                    desktop.browse( url );
	                } catch ( Exception ex ) {
	                    System.err.println( ex.getMessage() );
	                }
	            }    
	        }

	        @Override
	        public void mousePressed(MouseEvent e) {}

	        @Override
	        public void mouseReleased(MouseEvent e) {}

	        @Override
	        public void mouseEntered(MouseEvent e) {}

	        @Override
	        public void mouseExited(MouseEvent e) {}    

	    }
	    
	  public static void loggeo(){
		  logger.setLevel( Level.ALL );  // Cambiando esto se loggean más o menos mensajes
			// logger.addHandler( consoleHandler );
			try {
				// Al logger se le pueden añadir gestores (handler) que además
				// de a la consola de error saquen a fichero, xml, pantalla...
				Handler h = new StreamHandler( System.out, new SimpleFormatter() );
				h.setLevel( Level.FINEST );
				logger.addHandler( h );  // Saca todos los errores a out
			//	logger.addHandler( new FileHandler( "frmMenuPrincipal.log.xml") ); // Y también a un xml
			} catch (Exception e) {
				logger.log( Level.SEVERE, e.toString(), e );
			}
	  }
	    
}

