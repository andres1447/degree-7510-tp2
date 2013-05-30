package sucursal.modelo.exceptions;

import sucursal.modelo.compras.ItemProducto;

/**
 * Represents an error which occurs when trying to apply a exclusive discount to
 * an {@link ItemProducto} which does not have any quantity left without an
 * already applied exclusive discount.
 * 
 * @see ItemProducto#aplicarDescuentoExclusivoA(int)
 */
public class CantidadNoAplicableADescuentoExclusivoException extends
		RuntimeException {
	private static final long serialVersionUID = -8437343327703940058L;
}
