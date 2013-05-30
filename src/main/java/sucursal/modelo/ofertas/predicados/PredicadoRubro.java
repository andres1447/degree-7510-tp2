package sucursal.modelo.ofertas.predicados;

import sucursal.modelo.compras.ItemProducto;

import com.google.common.base.Predicate;

public class PredicadoRubro implements Predicate<ItemProducto> {
	public static Predicate<ItemProducto> instance(String codigo) {
		return new PredicadoRubro(codigo);
	}

	private final String codigo;

	public PredicadoRubro(final String codigo) {
		this.codigo = codigo;
	}

	@Override
	public boolean apply(ItemProducto input) {
		return input.getProducto().getRubro().getCodigo().equals(codigo);
	}
}
