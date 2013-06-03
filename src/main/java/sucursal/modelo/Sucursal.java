package sucursal.modelo;

import java.util.ArrayList;
import java.util.List;

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
	private List<Caja> cajas = new ArrayList<>();

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
		Caja caja = new Caja(proveedorOfertas, proveedorProductos);
		cajas.add(caja);
		return caja;
	}

	/**
	 * Obtains a summary of the sales made on all {@link Caja} instances created
	 * by this {@link Sucursal}.
	 */
	public List<EntradaResumenVentas> generarResumenVentas() {
		ResumenVentas resumenGeneral = new ResumenVentas();

		for (Caja caja : cajas) {
			resumenGeneral.incorporar(caja.getResumenVentas());
		}

		return resumenGeneral.toReporteOrdenado();
	}
}
