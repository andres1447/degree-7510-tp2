package sucursal.modelo.ofertas;

import java.util.List;

import sucursal.modelo.compras.Compra;

/**
 * Represents something which provides {@link Oferta} instances to be applied to
 * a given {@link Compra}.
 */
public interface ProveedorOfertas {
	/**
	 * Obtains the list of {@link Oferta} instances which should be applied on a
	 * {@link Compra}.
	 */
	List<Oferta> proveer();
}
