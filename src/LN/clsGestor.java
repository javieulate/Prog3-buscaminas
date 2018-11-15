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
import LD.clsDatos;
import LD.itfDatos;

public class clsGestor {
	
	public static void altaUsuario(clsUsuario usuario,  ArrayList <clsUsuario> listaUsu) throws clsUsuarioRepetido {
		clsUsuario a = new clsUsuario();
		a=usuario;
		HashSet<clsUsuario>miSet = new HashSet<clsUsuario>();
		for (clsUsuario aux : listaUsu) {
			miSet.add(aux);
		}
		if(miSet.add(a)==true){
			System.out.println("El usuario se ha añadido");
			listaUsu.add(a);
		}
		else{
			throw new clsUsuarioRepetido();
		}
	}
	
	/**
	 * Este es el método de alta de un usuario. Es un método que usa la lógica de presentación cuando quiere registrar
	 * un usuario nuevo en nuestra aplicación. Dispone de la funcionalidad del Hashset para eliminar aquellos usuarios 
	 * que estén repetidos (por mail). Los guarda en el fichero de usuarios que hemos definido en clsConstantes.
	 * @param nombre nombre del usuario
	 * @param apellido apellido del usuario
	 * @param correoelectronico mail del usuario
	 * @param nomusu nombre de usuario
	 */
	public static void AltaUsuario(String nombre, String apellido, String mail, String nomUsu, String contrasena) throws clsElementoRepetido
	{
			itfProperty a = new clsUsuario(nombre, apellido, mail, nomUsu, contrasena);
			itfDatos ObjDatos = new clsDatos();
			
			HashSet<itfProperty>miSet = new HashSet<itfProperty>();
			
			ArrayList<itfProperty>milista;
			milista = ListaUsuarios();
		
			
			for (itfProperty aux : milista) 
			{
				miSet.add((clsUsuario)aux);
			}
			if(miSet.add(a)==true)
			{
				ObjDatos.ComenzarSave(enFicDatos.FICHEROUSU);
				ObjDatos.Save((Serializable)a);
				ObjDatos.TerminarSave();
				
//				ObjDatos.ComenzarSave(enFicDatos.FICHEROSESION);
//				ObjDatos.Save((Serializable)a);
//				ObjDatos.TerminarSave();
				
			    String asunto = "Bienvenido a BuscaminasDeusto";
			    String cuerpo = "¡Gracias! Tu registro en BuscaminasDeusto se ha realizado con éxito.";
			    enviarConGMail(mail, asunto, cuerpo);
			}
			else
			{
				throw new clsElementoRepetido();
			}		
	}
	
	
	/**
	 * Método en el que se recoge todo la lista de usuarios de tipo clsUsuario.
	 * @return
	 */
	public static ArrayList<clsUsuario> ListaUsuariosclsUsuarios()
	{
		ArrayList<Serializable>leido;
		ArrayList<clsUsuario>retorno;
		clsDatos objDatos;
		
		retorno=null;
		objDatos=new clsDatos();
		
		try
		{
			objDatos.ComenzarRead(enFicDatos.FICHEROUSU);
			leido=objDatos.Read();
			retorno=new ArrayList<clsUsuario>();
			
			for(Serializable s: leido)
			{
				retorno.add((clsUsuario)s);
			}
			
		}
		catch(IOException e)
		{
			//Si hay error devuelvo una lista vacía.
			retorno=new ArrayList<clsUsuario>();
		}
		finally
		{
			objDatos.TerminarRead();
		}
		
		objDatos=null;
		return retorno;
	}	
		
	/**
	 * Este es el método del clsGestor que elabora una lista de usuarios de nuestra aplicación. Lo que hace es
	 * leer toda la información que existe de los usuarios registrados entrando en el fichero de estos. Mete los
	 * usuarios registrados en una lista y la manda a donde queramos, normalmente se usa en el menú principal.
	 * 
	 * @return lista llena de información de los usuarios.
	 */
	public static ArrayList<itfProperty> ListaUsuarios()
	{
		ArrayList<Serializable>leido;
		ArrayList<itfProperty>retorno;
		clsDatos objDatos;
		
		retorno=null;
		objDatos=new clsDatos();
		
		try
		{
			objDatos.ComenzarRead(enFicDatos.FICHEROUSU);
			leido=objDatos.Read();
			retorno=new ArrayList<itfProperty>();
			
			for(Serializable s: leido)
			{
				retorno.add((itfProperty)s);
			}
			
		}
		catch(IOException e)
		{
			//Si hay error devuelvo una lista vacía.
			retorno=new ArrayList<itfProperty>();
		}
		finally
		{
			objDatos.TerminarRead();
		}
		
		objDatos=null;
		return retorno;
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
		private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
			   
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
