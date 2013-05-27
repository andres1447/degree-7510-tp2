package sucursal.exceptions;

public class CompraEnProcesoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4171704337051255396L;

	public CompraEnProcesoException() {
	}

	public CompraEnProcesoException(String message) {
		super(message);
	}

	public CompraEnProcesoException(Throwable cause) {
		super(cause);
	}

	public CompraEnProcesoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompraEnProcesoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
