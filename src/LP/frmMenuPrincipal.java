package LP;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

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

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JList;

import COMUN.clsElementoRepetido;
import COMUN.clsUsuarioNoRegistrado;
import LN.clsGestor;
import LN.clsUsuario;
import LN.itfProperty;
import static COMUN.clsConstantes.CMD_BTN_INICIAR_SESION;
import static COMUN.clsConstantes.CMD_BTN_INICIAR_SESION2;
import static COMUN.clsConstantes.CMD_BTN_REGISTRARSE;
import static COMUN.clsConstantes.CMD_BTN_REGISTRARSE2;
import static COMUN.clsConstantes.CMD_BTN_CERRARSESION;
import static COMUN.clsConstantes.CMD_BTN_ACTUALIZARLISTAS;
import static COMUN.clsConstantes.CMD_BTN_PRINCIPIANTE;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JScrollPane;

import java.awt.SystemColor;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;


public class frmMenuPrincipal extends JFrame implements ActionListener, InternalFrameListener
{
	 JTextArea display;
	 JTextField buscador;
	 JDesktopPane desktop;
	 JInternalFrame subirpunt;
	
	 
	private JList<String> rankingpersonal;
	private JList<String> rankingabsoluto;
	 
	 String newline = "\n";
	 
	    static final int desktopWidth = 750;
	    static final int desktopHeight = 500;


	/**
	 * Con este método creamos la ventana, incluyendo todos los componentes que van a ser
	 * visualizados.
	 */

	public frmMenuPrincipal() 
	{
		 desktop = new JDesktopPane();
		 desktop.setBackground(Color.WHITE);
		 desktop.setPreferredSize(this.getPreferredSize());
	     this.setContentPane(desktop);
		
		 
		this.setTitle("Menu Principal");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			  public void windowClosing(WindowEvent we){
			    clsGestor.CerrarSesion();;
			  }
			});
		this.setBounds(100, 100, 1000, 650);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(createMenuBar());
		
		JTextArea txtrMenuUsuario = new JTextArea();
		txtrMenuUsuario.setForeground(Color.BLACK);
		txtrMenuUsuario.setEditable(false);
		txtrMenuUsuario.setBounds(52, 60, 309, 48);
		txtrMenuUsuario.setBackground(SystemColor.text);
		txtrMenuUsuario.setFont(new Font("Microsoft Tai Le", Font.BOLD, 17));
		txtrMenuUsuario.setText("Bienvenid@");
		//		try 
//		{
//			txtrMenuUsuario.setText("Bienvenid@, "+clsGestor.NomUsuario());
//		} 
//		catch (IOException e1) 
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		desktop.add(txtrMenuUsuario);
		
		JLabel label = new JLabel("");
		label.setBounds(155, 152, 531, 168);
		ImageIcon logo= new ImageIcon("src/imagenes/buscaminas.png");
		desktop.setLayout(null);
		label.setIcon(logo);
		getContentPane().add(label);
		
		JLabel lblListaDePelculas = new JLabel("TOP 10 de mejores resultados obtenidos");
		lblListaDePelculas.setBounds(611, 26, 167, 14);
		lblListaDePelculas.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 13));
		desktop.add(lblListaDePelculas);
		
		JLabel lblListaDeSeries = new JLabel("Ranking general");
		lblListaDeSeries.setBounds(611, 289, 167, 14);
		lblListaDeSeries.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 13));
		desktop.add(lblListaDeSeries);
		
		buscador = new JTextField();
		buscador.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 13));
		buscador.setBounds(52, 309, 501, 31);
		desktop.add(buscador);
		buscador.setColumns(10);
		
		JButton buscarserie = new JButton("");
		buscarserie.setBounds(330, 356, 169, 22);
//		buscarserie.setActionCommand(CMD_BTN_BUSCARSERIE);
		buscarserie.addActionListener(this);
		desktop.add(buscarserie);
		
		JButton buscarpeli = new JButton("");
		buscarpeli.setBounds(119, 356, 169, 22);
//		buscarpeli.setActionCommand(CMD_BTN_);
		buscarpeli.addActionListener(this);
		desktop.add(buscarpeli);
//		ArrayList<itfProperty>listapelisArray = clsGestor.listaPelis();
		DefaultListModel<String> DLMPelis = new DefaultListModel<String>();
//		for(itfProperty o: listapelisArray)
		{
//			 DLMPelis.addElement(o.getStringProperty(COMUN.clsConstantes.PROP_METRAJE_TITULO));
		}
		
		rankingpersonal = new JList<String>();
//		ArrayList<itfProperty>listaseriesArray =  clsGestor.listaSeries();
		DefaultListModel<String> DLMSeries = new DefaultListModel<String>();
//		for (itfProperty aux: listaseriesArray) 
		{
//			DLMSeries.addElement(aux.getStringProperty(COMUN.clsConstantes.PROP_METRAJE_TITULO));
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(611, 314, 350, 227);
		desktop.add(scrollPane);
		
		rankingpersonal.setModel(DLMSeries);
		desktop.add(rankingpersonal);
		rankingpersonal.setBounds(611, 314, 350, 227);
		rankingpersonal.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		rankingpersonal.setFont(new Font("Franklin Gothic Demi", Font.ITALIC, 15));
		rankingpersonal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rankingpersonal.setLayoutOrientation(JList.VERTICAL);
		scrollPane.setViewportView(rankingpersonal);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setBounds(611, 51, 350, 227);
		desktop.add(scrollPane2);
		
		rankingabsoluto = new JList<String>();
		rankingabsoluto.setModel(DLMPelis);
		desktop.add(rankingabsoluto);
		rankingabsoluto.setBounds(611, 51, 350, 227);
		rankingabsoluto.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		rankingabsoluto.setFont(new Font("Franklin Gothic Demi", Font.ITALIC, 15));
		rankingabsoluto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rankingabsoluto.setLayoutOrientation(JList.VERTICAL);
		rankingabsoluto.setVisibleRowCount(-1);
		scrollPane2.setViewportView(rankingabsoluto);
		
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
		amateur.addActionListener(this);
		nivel.add(amateur);
		
		JMenuItem experto = new JMenuItem("Experto");
		experto.setMnemonic(KeyEvent.VK_E);
		experto.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.ALT_MASK));
		experto.addActionListener(this);
		nivel.add(experto);
		
		JMenu ayuda = new JMenu("Ayuda");
		ayuda.setMnemonic(KeyEvent.VK_A);
		menuBar.add(ayuda);
		
	
		URLabel label = new URLabel();
        label.setURL("https://drive.google.com/open?id=18fz2OB2qhgtiEDcL0h9mDG0mb023lVfH");
        label.setLocation(5, 0); 
        label.setText("Manual");
        ayuda.add(label);
//		try {
//		     File path = new File ("carpeta/tuArchivo.pdf");
//		     Desktop.getDesktop().open(path);
//		}catch (IOException ex) {
//		     ex.printStackTrace();
//		}
		
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
				
				frmPartida NuevaPartida = new frmPartida();
				this.setVisible(false);
				NuevaPartida.setVisible(true);
				
				break;
			
//			case CMD_BTN_SUBIRSERIE:
//				
//				frmInternalSubirSerie subirserie = new frmInternalSubirSerie();
//				desktop.add(subirserie);
//				subirserie.setVisible(true);
//				break;
//				
//			case CMD_BTN_VALORARPELI:
//				frmInternalValorar valorarpeli = new frmInternalValorar(true);
//				desktop.add(valorarpeli);
//				valorarpeli.setVisible(true);
//				break;
//				
//			case CMD_BTN_VALORARSERIE:
//				frmInternalValorar valorarserie = new frmInternalValorar(false);
//				desktop.add(valorarserie);
//				valorarserie.setVisible(true);
//				break;
//				
//			case CMD_BTN_COMENTARPELI:
//				frmInternalComentar comentarpeli = new frmInternalComentar();
//				desktop.add(comentarpeli);
//				comentarpeli.setVisible(true);
//				break;
//			
//			case CMD_BTN_COMENTARSERIE:
//				frmInternalComentar comentarserie = new frmInternalComentar();
//				desktop.add(comentarserie);
//				comentarserie.setVisible(true);
//				break;
				
			case CMD_BTN_CERRARSESION:
				
				this.setVisible(false);
				clsGestor.CerrarSesion();
				frmPantalla vuelta = new frmPantalla();
				vuelta.setVisible(true);
				break;
				
			
				
//			case CMD_BTN_BUSCARPELI:
//				
//				String peliabuscar;
//				itfProperty b = null;
//				peliabuscar = buscador.getText();
//				try 
//				{
//					b = clsGestor.buscarPeli(peliabuscar);
//					frmInternalBuscarPeli buscarpeli = new frmInternalBuscarPeli(b);
//					desktop.add(buscarpeli);
//					buscarpeli.setVisible(true);
//				} 
//				catch (clsMetrajeNoExistente e1) 
//				{
//					JOptionPane.showMessageDialog(null, e1.getMessage());
//				}
//				break;
//				
//				
//			case CMD_BTN_BUSCARSERIE:
//				
//				String serieabuscar;
//				itfProperty c = null;
//				serieabuscar = buscador.getText();
//				try 
//				{
//					c = clsGestor.buscarSerie(serieabuscar);
//				} 
//				catch (clsMetrajeNoExistente e1) 
//				{
//					JOptionPane.showMessageDialog(null, e1.getMessage());
//				}
//				frmInternalBuscarSerie buscarserie = new frmInternalBuscarSerie(c);
//				desktop.add(buscarserie);
//				buscarserie.setVisible(true);
//				break;
//				
//			case CMD_BTN_VERPELIS:
//				frmVerPelis a = new frmVerPelis();
//				desktop.add(a);
//				a.setVisible(true);
//				break;
//				
//			case CMD_BTN_VERSERIES:
//				frmVerSeries z = new frmVerSeries();
//				desktop.add(z);
//				z.setVisible(true);
//				break;
				
			case "Resetear":
				int reply = JOptionPane.showConfirmDialog(this, "Si continua se borrarán todos los datos. ¿Desea continuar?", "Resetear", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		        	
					JOptionPane.showMessageDialog(this, "Datos reseteados con éxito.");
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
	       //     this.setText("Accede a UEFA.com");
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
	    
	    
}

