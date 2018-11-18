package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import COMUN.clsConstantes;
import LD.clsBaseDeDatos;
import LN.clsGestor;
import LN.clsUsuario;


public class frmRankingPersonal  extends JInternalFrame implements ActionListener {
	
	clsGestor gestor;
	
	
	public frmRankingPersonal() throws IOException{
	
	this.setResizable(false);
	this.setClosable(true);
	this.setIconifiable(true);
	this.setSize(584,370);
    this.setBackground(Color.red);
    this.setTitle("Lista de las 10 puntuaciones máximas del usuario");
//    this.setLocationRelativeTo(null); 
//    //Esta línea nos permite que el boton "X" cerrar funcione.
//    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//Le ponemos un contentPane para poner todas las cosas
    this.setContentPane(new JPanel(new BorderLayout()));
    JPanel PanelSur= new JPanel(new FlowLayout());
    this.getContentPane().add(PanelSur,BorderLayout.SOUTH);
    
    String[] columnNames = {"Nombre",
            "Apellido",
            "Nombre de usuario",
            "Puntuación"};
	 Object[][] lista= new Object[80][4];
	 int cont =0;
	 clsUsuario usuariosesion= new clsUsuario();
	
		usuariosesion.setNomUsuario(clsGestor.NomUsuario());
		
	
	 ArrayList <clsUsuario>listausuarios = clsBaseDeDatos.cargarVariosDeTabla2(clsBaseDeDatos.getStatement());
	 for(clsUsuario a: listausuarios)
	 {
		 if (a.getNomUsuario().equals((usuariosesion).getNomUsuario()))
		 {
			 lista[cont][0]= a.getNombre();
			 lista[cont][1]= a.getApellido();
			 lista[cont][2]= a.getNomUsuario();
			 lista[cont][3]= a.getPuntuacion();
			 cont++; 
		 }	 
	 }
	
	
	final JTable table = new JTable(lista, columnNames);
	table.setPreferredScrollableViewportSize(new Dimension(500, 70));
   table.setFillsViewportHeight(true);


//   modelo2 m= new modelo2();
//      table.setModel(m);
   
   //Create the scroll pane and add the table to it.
   JScrollPane scrollPane = new JScrollPane(table);
   //Add the scroll pane to this panel.
   this.getContentPane().add(scrollPane);
   
   int numRows = table.getRowCount();
   int numCols = table.getColumnCount();
   javax.swing.table.TableModel model = table.getModel();


	JButton b2 = new JButton("Salir");
	b2.setActionCommand("BotonSalir");
	b2.addActionListener(this);
	

	PanelSur.add(b2);
	this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand())
		{

			case "BotonSalir": this.dispose();
					break;
		}
	}
}