package sucursal.exceptions;

public class CompraNoInicializadaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6959689078184257750L;

	public CompraNoInicializadaException() {
	}

	public CompraNoInicializadaException(String message) {
		super(message);
	}

	public CompraNoInicializadaException(Throwable cause) {
		super(cause);
	}

	public CompraNoInicializadaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompraNoInicializadaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
