package sucursal.modelo;

import com.google.inject.Inject;
import com.google.inject.Singleton;

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

	public Caja habilitarCaja() {
		return new Caja(proveedorOfertas, proveedorProductos);
	}
}
