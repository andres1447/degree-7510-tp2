package sucursal.exceptions;

import sucursal.modelo.Caja;

/**
 * Represents an error which occurs when trying to open a {@link Caja} which is already in open state.
 * 
 * @see Caja#abrir()
 * @see Caja#cerrar()
 * @see Caja#estaAbierta()
 */
public class CajaYaAbiertaException extends RuntimeException {
	private static final long serialVersionUID = -1705703936947127540L;
}
