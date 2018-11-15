package LN;

public class clsUsuarioRepetido extends Exception{

	
	private final String MENSAJE= "El usuario que quiere dar de alta ya ha sido dado de alta previamente.";
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return MENSAJE;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return MENSAJE;
	}
}
