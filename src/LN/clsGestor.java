package LN;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import COMUN.clsElementoRepetido;
import COMUN.clsEmailNoValido;
import COMUN.clsUsuarioNoRegistrado;
import COMUN.clsConstantes.enFicDatos;
import LD.clsBaseDeDatos;
import LD.clsDatos;
import LD.itfDatos;

public class clsGestor 
{
	
	/**
	 * Método en el que se recoge todo la lista de usuarios de tipo clsUsuario.
	 * @return
	 */
	public static ArrayList<clsUsuario> ListaUsuariosclsUsuarios()
	{
		ArrayList<clsUsuario>listausuarios = new ArrayList<clsUsuario>();
		listausuarios = clsBaseDeDatos.cargarVariosDeTabla2(clsBaseDeDatos.getStatement());	
		return listausuarios;
	}	
		
	/**
	 * Método para comprobar si un usuario existe en fichero.	
	 * @param nomusuario nombre de usuario a buscar
	 * @param correoelec correo del usuario a buscar
	 * @throws clsUsuarioNoRegistrado excepción que salta si no existe
	 */
	public static void ComprobarUsuario(String nomusuario, String correoelec) throws clsUsuarioNoRegistrado
		{
			boolean marca = true;
			ArrayList<clsUsuario>listausuarios = ListaUsuariosclsUsuarios();
			
			for (clsUsuario aux : listausuarios) 
			{
				if(aux.getMail().toUpperCase().equals(correoelec.toUpperCase()) && aux.getNomUsuario().toUpperCase().equals(nomusuario.toUpperCase()))
				{
					marca = false;
				}
			}
			if (marca == true)
			{
				throw new clsUsuarioNoRegistrado();
			}
		}
	
	
	 public static boolean validarEmail(String email) throws clsEmailNoValido
	  {
		  Pattern pattern = Pattern.compile(clsUsuario.EMAIL_ADDRESS); 
		  
		  if(pattern.matcher(email).matches()==true)
		  {
			  return pattern.matcher(email).matches();
			  
		  }
		  else
		  {
			  throw new clsEmailNoValido();
		  }
	  }

/**
		 * Método usado para guardar los datos en fichero sesion del usuario que ha inciado su sesión en la 
		 * aplicación.
		 * @param nomusuario nombre del usuario
		 * @param correoelec correo del usuario
		 */
		public static void IniciarSesion(String nomusuario, String correoelec)
			{
				
				ArrayList<clsUsuario>listaususarios = ListaUsuariosclsUsuarios();
				clsUsuario a = null;
				
				for (clsUsuario aux : listaususarios) 
				{
					if(aux.getMail().toUpperCase().equals(correoelec.toUpperCase()) && aux.getNomUsuario().toUpperCase().equals(nomusuario.toUpperCase()))
					{
						a = aux;
					}
				}
				
				itfDatos ObjDatos = new clsDatos();
					
				ObjDatos.ComenzarSave(enFicDatos.FICHEROSESION);
				ObjDatos.Save((Serializable)a);
				ObjDatos.TerminarSave();
				
			}
		
		
		/**
		 * Método que permite el envío de correos automáticos a la hora de registrarse en la aplicación.
		 * @param destinatario
		 * @param asunto
		 * @param cuerpo
		 */
		public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
			   
		    String remitente = "buscaminas.deusto";   // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
		    String clave="buscaminas18";
		    
		    Properties props = System.getProperties();
		    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
		    props.put("mail.smtp.user", remitente);
		    props.put("mail.smtp.clave", "miClaveDeGMail");    //La clave de la cuenta
		    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
		    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
		    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

		    Session session = Session.getDefaultInstance(props);
		    MimeMessage message = new MimeMessage(session);

		    try {
	
		        message.setFrom(new InternetAddress(remitente));
		        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
		        message.setDescription("Buscaminas Deusto");
		        message.setSubject(asunto);
		        message.setText(cuerpo);
		        Transport transport = session.getTransport("smtp");
		        transport.connect("smtp.gmail.com", remitente, clave);
		        transport.sendMessage(message, message.getAllRecipients());
		        transport.close();
		    }
		    catch (MessagingException me) {
		        me.printStackTrace();   //Si se produce un error
		    }
		}
		
		
		
		/**
		 * Método que borra el fichero sesion cuando el usuario abandona la aplicación.
		 */
		public static void CerrarSesion()
			{
				itfDatos ObjDatos = new clsDatos();
				ObjDatos.ResetFile(enFicDatos.FICHEROSESION);
			}
		
		/**
		 * Método para que a la hora de entrar en la aplicación con tu usuario, te de una bienvenida personalizada.
		 * @return
		 * @throws IOException
		 */
		public static String NomUsuario() throws IOException
		{
			String nomusu ="";
			itfProperty b = null;
			itfDatos ObjDatos = new clsDatos();
			
			ObjDatos.ComenzarRead(enFicDatos.FICHEROSESION);
			b =  (itfProperty)ObjDatos.ReadSerializable();
			ObjDatos.TerminarRead();
			
			nomusu = b.getStringProperty(COMUN.clsConstantes.PROP_USUARIO_NOMUSUARIO);
			
			return nomusu;
		}
}
