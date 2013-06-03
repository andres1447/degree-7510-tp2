package sucursal.modelo.ofertas.descuentos;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

import com.google.common.base.Function;

/**
 * Complex exclusive discount strategy which calculates the discount to apply to
 * a {@link Compra} by examining the amount of bought items for a given product
 * and allowing the customer to take Y items by paying only X of them.
 */
public class DescuentoLlevaXPagaY implements Function<Compra, Float> {
	private final String codigoProducto;
	private final int x;
	private final int y;

	public DescuentoLlevaXPagaY(final String codigoProducto, final int x,
			final int y) {
		this.codigoProducto = codigoProducto;
		this.x = x;
		this.y = y;
	}

	@Override
	public Float apply(Compra input) {
		Float resultado = 0.0f;
		ItemProducto item = buscarItemCorrespondiente(input);
		if (item != null) {
			while (item.tieneRemanenteSinDescuentoExclusivo(x)) {
				item.aplicarDescuentoExclusivoA(x);
				resultado += item.getProducto().getPrecioUnitario() * (x - y);
			}
		}

		return resultado > 0 ? resultado : null;
	}

	private ItemProducto buscarItemCorrespondiente(Compra input) {
		for (ItemProducto item : input.getItems()) {
			if (item.getProducto().getCodigo().equals(codigoProducto)) {
				return item;
			}
		}
		return null;
	}

}
