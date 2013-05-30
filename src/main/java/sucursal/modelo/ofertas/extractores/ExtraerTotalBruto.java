package sucursal.modelo.ofertas.extractores;

import sucursal.modelo.compras.Compra;

import com.google.common.base.Function;

public class ExtraerTotalBruto implements Function<Compra, Float> {
	public static ExtraerTotalBruto instance() {
		return new ExtraerTotalBruto();
	}

	@Override
	public Float apply(Compra input) {
		return input.getTotalBruto();
	}
}
