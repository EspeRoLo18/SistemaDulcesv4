package mx.edu.uacm.constantes;

public class Regla {
	public static final String REGEX_NOMBRE = "^[ A-Za-záéíóú]+$";
	public static final int LONG_MIN_NOMBRE = 4;
	public static final int LONG_MAX_NOMBRE = 20;
	public static final int LONG_MAX_NOMBRE_CATALOGO = 30;
	public static final int CANTIDAD_NEGATIVA = 0;
	
	public static final String REGEX_CORREO = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$";
}
