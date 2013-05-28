package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

/**
 * Represents an actual discount strategy which calculates the amount to
 * discount from a {@link Compra}.
 */
public interface OfertaDescuento {
	/**
	 * Applies the discount to the given {@link Compra}, returning the amount
	 * that should be discounted from it.
	 */
	float aplicar(final Compra compra);
}
