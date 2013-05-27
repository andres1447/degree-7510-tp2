package sucursal.exceptions;

import sucursal.modelo.Caja;

/**
 * Represents an error which occurs when trying to invoke an operation which
 * requires the {@link Caja} to be in open state but the {@link Caja} is not in
 * open state.
 * 
 * @see Caja#abrir()
 * @see Caja#cerrar()
 * @see Caja#estaAbierta()
 */
public class CajaNoAbiertaException extends RuntimeException {
	private static final long serialVersionUID = -8437343327703940058L;
}
