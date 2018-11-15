package LN;

import static COMUN.clsConstantes.*;

import java.io.Serializable;

import COMUN.clsPropertyException;

public class clsUsuario implements Serializable, itfProperty {
	
	private String nombre;
	private String apellido;
	private String mail;
	private String nomUsuario;
	private String contrasena;
	private int numeroVidas;
	private int puntuacion;
	
	public clsUsuario(){
		nomUsuario="";
		mail="";
		contrasena="";
		numeroVidas=5;
		puntuacion=0;				
	}
	
//	public clsUsuario(String nomUsuario, String mail, String contrasena, int numeroVidas, int puntuacion){
//		this.nomUsuario=nomUsuario;
//		this.mail=mail;
//		this.contrasena=contrasena;
//		this.numeroVidas=numeroVidas;
//		this.puntuacion=puntuacion;
//	}
	
	public clsUsuario(String nombre, String apellido, String mail,
			String nomUsuario, String contrasena) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.nomUsuario = nomUsuario;
		this.contrasena = contrasena;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getNumeroVidas() {
		return numeroVidas;
	}

	public void setNumeroVidas(int numeroVidas) {
		this.numeroVidas = numeroVidas;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public static String getEmailAddress() {
		return EMAIL_ADDRESS;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result
				+ ((nomUsuario == null) ? 0 : nomUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		clsUsuario other = (clsUsuario) obj;
		if (mail == null) {
			if (other.mail != null) {
				return false;
			}
		} else if (!mail.equals(other.mail)) {
			return false;
		}
		if (nomUsuario == null) {
			if (other.nomUsuario != null) {
				return false;
			}
		} else if (!nomUsuario.equals(other.nomUsuario)) {
			return false;
		}
		return true;
	}

	public static final String EMAIL_ADDRESS = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public String getStringProperty(String propiedad) throws clsPropertyException {
		
		String retorno="";
		
		switch(propiedad)
		{
			case PROP_USUARIO_NOMBRE:
				retorno= this.getNombre();
			break;
			case PROP_USUARIO_APELLIDO:
				retorno= this.getMail();
			break;
			case PROP_USUARIO_NOMUSUARIO:
				retorno= this.getNomUsuario();
			break;
			case PROP_USUARIO_CORREOELEC:
				retorno= this.getMail();
			break;
			case PROP_USUARIO_CONTRASENA:
				retorno= this.getContrasena();
			break;
				
			default : throw new clsPropertyException(propiedad);
		}	
		return retorno;
		
		
	}

	@Override
	public Integer getIntegerProperty(String propiedad) throws clsPropertyException {
		
	int retorno=0;
		
		switch(propiedad)
		{
			case PROP_USUARIO_NUMEROVIDAS:
				retorno= this.getNumeroVidas();
			break;
			case PROP_USUARIO_PUNTUACION:
				retorno= this.getPuntuacion();
			break;
				
			default : throw new clsPropertyException(propiedad);
		}	
		return retorno;
		
		
	}

	@Override
	public Float getFloatProperty(String propiedad) throws clsPropertyException {
		throw new clsPropertyException(propiedad);
	}

	@Override
	public Double getDoubleProperty(String propiedad) throws clsPropertyException {
		throw new clsPropertyException(propiedad);
	}

	@Override
	public char getCharProperty(String propiedad) {
		throw new clsPropertyException(propiedad);
	}
}