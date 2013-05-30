package sucursal.modelo.ofertas.extractores;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.MedioPago;

import com.google.common.base.Function;

public class ExtraerMedioPago implements Function<Compra, MedioPago> {
	public static ExtraerMedioPago instance() {
		return new ExtraerMedioPago();
	}

	@Override
	public MedioPago apply(final Compra input) {
		return input.getMedioPago();
	}
}
