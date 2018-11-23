
package LP;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import LD.clsBaseDeDatos;
import LN.clsGestor;
import LN.clsUsuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class frmRankingPrincipal extends JInternalFrame implements ActionListener {
    private boolean DEBUG = false;

    public frmRankingPrincipal() {

    	this.setResizable(false);
    	this.setClosable(true);
    	this.setIconifiable(true);
    	this.setSize(584,370);
        this.setBackground(Color.red);
        this.setTitle("Lista de las puntuaciones máximas obtenidas por todos los usuarios ");
        this.setOpaque(true);
        this.toFront();
        JPanel ranking_principal = new JPanel(new BorderLayout());
        ranking_principal.setOpaque(true);
        ranking_principal.setLayout(new BorderLayout());
        this.setContentPane(ranking_principal);
        JPanel PanelSur= new JPanel(new FlowLayout());
        this.getContentPane().add(PanelSur,BorderLayout.SOUTH);
        
    	
        JTable table = new JTable(new MyTableModelo());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setOpaque(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
        
        JButton b = new JButton("Salir");
    	b.setActionCommand("BotonSalir");
    	b.addActionListener(this);
    	PanelSur.add(b);
    }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand())
		{
			case "BotonSalir": this.dispose();
					break;
		}
		
	} 
    
    class MyTableModelo extends AbstractTableModel {
        private String[] columnNames = {"Nombre",
	            						"Apellido",
	            						"Nombre de usuario",
        								"Puntuación"};
        Object[][] lista= new Object[80][4];
		 int cont =0;
		
		
		 ArrayList <clsUsuario>listausuarios = clsBaseDeDatos.cargarVariosDeTabla2(clsBaseDeDatos.getStatement());
	{
		 
			 for(clsUsuario a: listausuarios)
			 {
				 
					 lista[cont][0]= a.getNombre();
					 lista[cont][1]= a.getApellido();
					 lista[cont][2]= a.getNomUsuario();
					 lista[cont][3]= a.getPuntuacion();
					 cont++; 
			 
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
}
