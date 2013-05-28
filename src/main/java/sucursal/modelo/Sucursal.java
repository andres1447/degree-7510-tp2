package sucursal.modelo;

import sucursal.modelo.caja.Caja;
import sucursal.modelo.ofertas.ProveedorOfertas;
import sucursal.modelo.productos.ProveedorProductos;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Domain class representing a single sucursal or subsidiary, in charge of
 * creating and providing {@link Caja} instances.
 */
@Singleton
public class Sucursal {
	private final ProveedorOfertas proveedorOfertas;
	private final ProveedorProductos proveedorProductos;

	@Inject
	public Sucursal(final ProveedorOfertas proveedorOfertas,
			final ProveedorProductos proveedorProductos) {
		this.proveedorOfertas = proveedorOfertas;
		this.proveedorProductos = proveedorProductos;
	}

	/**
	 * Creates a new {@link Caja} using the same configuration as all
	 * {@link Caja} instances created by this {@link Sucursal}.
	 */
	public Caja habilitarCaja() {
		return new Caja(proveedorOfertas, proveedorProductos);
	}
}
