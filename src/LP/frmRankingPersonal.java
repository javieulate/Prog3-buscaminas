
package LP;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import LD.clsBaseDeDatos;
import LN.clsAudio;
import LN.clsGestor;
import LN.clsPartidaUsuario;
import LN.clsUsuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class frmRankingPersonal extends JInternalFrame implements ActionListener {
    private boolean DEBUG = false;
    public JComboBox jc_mod;
    public String [] listaJC = {"Todas", "Principiante", "Amateur", "Experto"};
    public int tipoJC = 0;
    private static Logger logger = Logger.getLogger(frmRankingPersonal.class.getName() );
    
    public frmRankingPersonal()  {

    	
    	this.setResizable(false);
    	this.setClosable(true);
    	this.setIconifiable(true);
    	this.setSize(584,370);
        this.setBackground(Color.red);
        this.setTitle("Lista de las 10 puntuaciones máximas del usuario");
        this.setOpaque(true);
        this.toFront();
        
    	panelPuntuaciones();
    	
    }
    
    public JPanel panelPuntuaciones()
    {
    	this.getContentPane().removeAll();
    	
        JPanel ranking_personal = new JPanel(new BorderLayout());
        ranking_personal.setOpaque(true);
        ranking_personal.setLayout(new BorderLayout());
        this.setContentPane(ranking_personal);
        
        JPanel PanelSur= new JPanel(new FlowLayout());
        ranking_personal.add(PanelSur,BorderLayout.SOUTH);
        JPanel PanelNorte = new JPanel(new FlowLayout());
        ranking_personal.add(PanelNorte, BorderLayout.NORTH);
        
        JButton b = new JButton("Salir");
    	b.setActionCommand("BotonSalir");
    	b.addActionListener(this);
    	PanelSur.add(b);
    	
    	jc_mod = new JComboBox(listaJC);
    	jc_mod.setActionCommand("JComboBox");
    	jc_mod.setSelectedItem(listaJC[tipoJC]);
    	jc_mod.addActionListener(this);
    	PanelNorte.add(jc_mod);
    	
        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        
        repaint();
        return ranking_personal;
    }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand())
		{
			case "BotonSalir": this.dispose();
					break;
			case "JComboBox" :
				if(jc_mod.getSelectedItem().equals("Todas"))
				{
					tipoJC = 0;
					this.setContentPane(panelPuntuaciones());
					this.getContentPane().revalidate();
					logger.log( Level.INFO, "Mostrando todas las puntuaciones.");
				}
				if(jc_mod.getSelectedItem().equals("Principiante"))
				{
					tipoJC = 1;
					this.setContentPane(panelPuntuaciones());
					this.getContentPane().revalidate();
					logger.log( Level.INFO, "Mostrando las puntuaciones principiante.");
				}
				if(jc_mod.getSelectedItem().equals("Amateur"))
				{
					tipoJC = 2;
					this.setContentPane(panelPuntuaciones());
					this.getContentPane().revalidate();
					logger.log( Level.INFO, "Mostrando las puntuaciones amateur.");
				}
				if(jc_mod.getSelectedItem().equals("Experto"))
				{
					tipoJC = 3;
					this.setContentPane(panelPuntuaciones());
					this.getContentPane().revalidate();
					logger.log( Level.INFO, "Mostrando las puntuaciones experto.");
				}
				break;
		}
	} 
    
    class MyTableModel extends AbstractTableModel  {
        private String[] columnNames = {"Nombre de usuario",
	            						"Dificultad",
	            						"Puntuacion",
        								"Tiempo"};
        Object[][] lista= new Object[80][4];
		 int cont =0;
		
		
		 ArrayList <clsPartidaUsuario>listapartidas = clsBaseDeDatos.cargarOrdenadosPorPuntuacion3(clsBaseDeDatos.getStatement(), tipoJC);
	{
		 clsUsuario usuariosesion= new clsUsuario();					
		
		
		 try {
			usuariosesion.setNomUsuario(clsGestor.NomUsuario());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		
					
					 
			 for(clsPartidaUsuario a: listapartidas)
			 {
				 if (a.getNomUsuario().equals((usuariosesion).getNomUsuario()))
				 {
					 lista[cont][0]= a.getNomUsuario();
					 lista[cont][1]= a.getDificultad();
					 lista[cont][2]= a.getPuntuacion();
					 lista[cont][3]= a.getTiempo();
					 cont++; 
				 }	 
			 }
	}

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return lista.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return lista[row][col];
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }
//    public void usuario(){
//    	
//		usu2=clsBaseDeDatos.leerDeFicheroSerializado2(".\\data\\sesion.dat");
//		usu1=clsBaseDeDatos.cargarDeTabla2(clsBaseDeDatos.getStatement(), usu2.getNomUsuario(), usu2.getMail(), usu2.getContrasena());
//	}
}
