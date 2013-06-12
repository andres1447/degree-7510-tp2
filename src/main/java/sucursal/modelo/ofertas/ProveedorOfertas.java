package sucursal.modelo.ofertas;

import java.util.List;

import sucursal.modelo.compras.Compra;

/**
 * Represents something which provides {@link OfertaDinero} instances to be applied to
 * a given {@link Compra}.
 */
public interface ProveedorOfertas {
	/**
	 * Obtains the list of {@link OfertaDinero} instances which should be applied on a
	 * {@link Compra}.
	 */
	List<IOferta> proveer();
}
