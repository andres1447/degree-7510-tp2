package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.MedioPago;

public class CondicionGlobalMedioPago implements CondicionGlobal {
	private final MedioPago medioPago;

	public CondicionGlobalMedioPago(final MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	@Override
	public boolean aplicaEn(final Compra compra) {
		return compra.getMedioPago().equals(medioPago);
	}

}
