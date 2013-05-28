package sucursal.modelo.exceptions;

import sucursal.modelo.productos.Producto;

/**
 * Represents an error which occurs when trying to access a {@link Producto}
 * instance which does not exist.
 */
public class ProductoInexistenteException extends RuntimeException {
	private static final long serialVersionUID = 6604398234191964342L;
}
