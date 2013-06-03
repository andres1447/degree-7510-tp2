package sucursal.modelo.ofertas.predicados;

import sucursal.modelo.compras.ItemProducto;

import com.google.common.base.Predicate;

/**
 * Predicate function which returns true if the {@link ItemProducto} has a given
 * category, and false otherwise.
 */
public class PredicadoRubro implements Predicate<ItemProducto> {
	private final String codigo;

	public PredicadoRubro(final String codigo) {
		this.codigo = codigo;
	}

	@Override
	public boolean apply(ItemProducto input) {
		return input.getProducto().getRubro().getCodigo().equals(codigo);
	}
}
