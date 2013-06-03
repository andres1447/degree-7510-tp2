package sucursal.modelo.ofertas.extractores;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.MedioPago;

import com.google.common.base.Function;

/**
 * Extractor function which, given a {@link Compra}, provides its
 * {@link Compra#getMedioPago()}.
 */
public class ExtraerMedioPago implements Function<Compra, MedioPago> {
	@Override
	public MedioPago apply(final Compra input) {
		return input.getMedioPago();
	}
}
