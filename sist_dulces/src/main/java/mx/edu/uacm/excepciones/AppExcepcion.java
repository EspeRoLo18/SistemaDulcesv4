package mx.edu.uacm.excepciones;

public class AppExcepcion extends Exception {
	private static final long serialVersionUID = 1L;	
	private String causa;
	
	public AppExcepcion(String mensaje, String causa) {
		super(mensaje);
		this.causa = causa;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}
}