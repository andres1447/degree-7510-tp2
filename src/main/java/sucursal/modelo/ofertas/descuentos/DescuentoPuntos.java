package sucursal.modelo.ofertas.descuentos;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

import com.google.common.base.Function;

public class DescuentoPuntos implements Function<Compra, Integer> {

	@Override
	public Integer apply(Compra input) {
		int total = 0;
		for (ItemProducto item : input.getItems()) {
			total += input.getListadoPuntos().getPuntaje(item);
		}
		return total;
	}
}
