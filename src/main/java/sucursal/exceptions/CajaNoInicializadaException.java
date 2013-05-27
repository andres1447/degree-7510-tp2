package sucursal.exceptions;

// TODO: Convertir a unico constructor, el resto no se esta usando
public class CajaNoInicializadaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CajaNoInicializadaException() {
}

	public CajaNoInicializadaException(String message) {
		super(message);
	}

	public CajaNoInicializadaException(Throwable cause) {
		super(cause);
	}

	public CajaNoInicializadaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CajaNoInicializadaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
