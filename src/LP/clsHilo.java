package LP;

import javax.swing.JOptionPane;

public class clsHilo extends Thread 
{
	
		
	public void run()
	{
		try 
		{
			frmMenuPrincipal a = new frmMenuPrincipal();
			a.setVisible(true);
			Thread.sleep(30000);
			JOptionPane.showMessageDialog(null,"La aplicación va a refrescar su base de datos en 5 segundos, disculpe las molestias");
			Thread.sleep(5000);
			a.setVisible(false);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		clsHilo repeticion = new clsHilo();
		repeticion.start();
	}
	
	


}    
	   
