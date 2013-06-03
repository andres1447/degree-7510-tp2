package sucursal.modelo.ofertas.extractores;

import sucursal.modelo.compras.Compra;

import com.google.common.base.Function;

/**
 * Extractor function which, given a {@link Compra}, provides its
 * {@link Compra#getTotalBruto()}.
 */
public class ExtraerTotalBruto implements Function<Compra, Float> {
	@Override
	public Float apply(Compra input) {
		return input.getTotalBruto();
	}
}
