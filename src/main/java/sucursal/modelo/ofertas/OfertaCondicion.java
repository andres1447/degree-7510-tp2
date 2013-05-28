package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

/**
 * Represents a condition on a {@link Compra} which, if met, should mean that an
 * {@link OfertaDescuento} should be applied to it.
 */
public interface OfertaCondicion {
	/**
	 * Checks if the represented condition is met on a given {@link Compra},
	 * returning true if it is and false otherwise.
	 */
	boolean corresponde(final Compra compra);
}
