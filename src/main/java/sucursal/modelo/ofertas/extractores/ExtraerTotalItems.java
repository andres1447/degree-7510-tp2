package sucursal.modelo.ofertas.extractores;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

import com.google.common.base.Function;

/**
 * Extractor function which, given a {@link Compra}, provides the total amount
 * of quantity of all its items.
 */
public class ExtraerTotalItems implements Function<Compra, Integer> {
	@Override
	public Integer apply(final Compra input) {
		int cantidad = 0;
		for (ItemProducto item : input.getItems()) {
			cantidad += item.getCantidad();
		}
		return cantidad;
	}

}
