package sucursal.modelo.ofertas.extractores;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * Complex extractor function which, given a {@link Compra}, provides its
 * {@link Compra#getItems()}, only for those items satisfying a given condition.
 */
public class ExtraerTotalBrutoProductos implements Function<Compra, Float> {
	private final Predicate<ItemProducto> condicion;

	public ExtraerTotalBrutoProductos(final Predicate<ItemProducto> condicion) {
		this.condicion = condicion;
	}

	@Override
	public Float apply(final Compra input) {
		Float resultado = 0.0f;
		for (ItemProducto item : input.getItems()) {
			if (condicion.apply(item)) {
				resultado += item.getTotal();
			}
		}
		return resultado;
	}

}
