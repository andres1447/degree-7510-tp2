package sucursal.modelo.ofertas.extractores;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class ExtraerTotalBrutoProductos implements Function<Compra, Float> {
	public static ExtraerTotalBrutoProductos instance(Predicate<ItemProducto> condicion) {
		return new ExtraerTotalBrutoProductos(condicion);
	}
	
	private final Predicate<ItemProducto> condicion;

	private ExtraerTotalBrutoProductos(Predicate<ItemProducto> condicion) {
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
