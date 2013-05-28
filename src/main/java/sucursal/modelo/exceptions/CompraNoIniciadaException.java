package sucursal.modelo.exceptions;

import sucursal.modelo.caja.Caja;
import sucursal.modelo.compras.Compra;

/**
 * Represents an error which occurs when trying to perform an operation which
 * requires a {@link Caja} to have a pending {@link Compra} when the
 * {@link Caja} doesn't have one.
 * 
 * @see Caja#iniciarCompra()
 * @see Caja#terminarCompra()
 * @see Caja#estaComprando()
 */
public class CompraNoIniciadaException extends RuntimeException {
	private static final long serialVersionUID = -6959689078184257750L;
}
