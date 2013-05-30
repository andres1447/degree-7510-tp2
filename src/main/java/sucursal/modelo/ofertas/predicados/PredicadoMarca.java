package sucursal.modelo.ofertas.predicados;

import sucursal.modelo.compras.ItemProducto;

import com.google.common.base.Predicate;

public class PredicadoMarca implements Predicate<ItemProducto> {
	public static Predicate<ItemProducto> instance(String codigo) {
		return new PredicadoMarca(codigo);
	}

	private final String codigo;

	public PredicadoMarca(final String codigo) {
		this.codigo = codigo;
	}

	@Override
	public boolean apply(ItemProducto input) {
		return input.getProducto().getMarca().getCodigo().equals(codigo);
	}
}
