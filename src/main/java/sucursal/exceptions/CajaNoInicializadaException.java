package sucursal.exceptions;

public class CajaNoInicializadaException extends Exception {

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
