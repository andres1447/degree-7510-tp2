package sucursal.modelo.ofertas.predicados;

import sucursal.modelo.compras.ItemProducto;

import com.google.common.base.Predicate;

/**
 * Predicate function which returns true if the {@link ItemProducto} has a given
 * make, and false otherwise.
 */
public class PredicadoMarca implements Predicate<ItemProducto> {
	private final String codigo;

	public PredicadoMarca(final String codigo) {
		this.codigo = codigo;
	}

	@Override
	public boolean apply(ItemProducto input) {
		return input.getProducto().getMarca().getCodigo().equals(codigo);
	}
}
