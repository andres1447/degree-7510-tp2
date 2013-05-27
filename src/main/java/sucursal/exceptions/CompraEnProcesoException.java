package sucursal.exceptions;

import sucursal.modelo.Caja;
import sucursal.modelo.Compra;

/**
 * Represents an error which occurs when trying to initiate a new {@link Compra}
 * in a {@link Caja} which has a pending {@link Compra}.
 * 
 * @see Caja#iniciarCompra()
 * @see Caja#terminarCompra()
 * @see Caja#estaComprando()
 */
public class CompraEnProcesoException extends RuntimeException {
	private static final long serialVersionUID = 4171704337051255396L;
}
