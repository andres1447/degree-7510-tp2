package sucursal.modelo.productos;

/**
 * Represents a thing which provides a {@link ListadoProductos}.
 */
public interface ProveedorProductos {
	/**
	 * Obtains a {@link ListadoProductos} instance.
	 */
	ListadoProductos proveer();
}
