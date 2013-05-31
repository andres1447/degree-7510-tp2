package sucursal.modelo.ofertas.predicados;

import sucursal.modelo.compras.Compra;

import com.google.common.base.Predicate;

public class PredicadoCupon implements Predicate<Compra> {
	private final String codigoCupon;

	public PredicadoCupon(String codigoCupon) {
		this.codigoCupon = codigoCupon;
	}

	@Override
	public boolean apply(Compra input) {
		return codigoCupon.equals(input.getCodigoCupon());
	}
}
