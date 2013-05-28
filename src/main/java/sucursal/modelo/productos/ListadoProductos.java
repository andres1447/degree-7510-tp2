package sucursal.modelo.productos;

import sucursal.modelo.exceptions.ProductoInexistenteException;

/**
 * Represents something which provides functionality to search through a listing
 * of {@link Producto} instances.
 */
public interface ListadoProductos {
	/**
	 * Looks for a {@link Producto} instance corresponding to a given code. May
	 * rise {@link ProductoInexistenteException} if no matching {@link Producto}
	 * instance is found.
	 */
	Producto buscarPorCodigo(final String codigo);
}
