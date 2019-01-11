package LN;

public class clsPartidaUsuario {
	public String nomUsuario;
	public String dificultad;
	public int puntuacion;
	public String tiempo;
	public clsPartidaUsuario(String nomUsuario, String dificultad,
			int puntuacion, String tiempo) {
		super();
		this.nomUsuario = nomUsuario;
		this.dificultad = dificultad;
		this.puntuacion = puntuacion;
		this.tiempo = tiempo;
	}
	public clsPartidaUsuario() {
		// TODO Auto-generated constructor stub
		nomUsuario="";
		dificultad="";
		puntuacion=0;
		tiempo="";
	}
	public String getNomUsuario() {
		return nomUsuario;
	}
	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
	
}
