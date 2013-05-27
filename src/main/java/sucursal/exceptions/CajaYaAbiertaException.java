package sucursal.exceptions;

public class CajaYaAbiertaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1705703936947127540L;

	public CajaYaAbiertaException() {
	}

	public CajaYaAbiertaException(String message) {
		super(message);
	}

	public CajaYaAbiertaException(Throwable cause) {
		super(cause);
	}

	public CajaYaAbiertaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CajaYaAbiertaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
