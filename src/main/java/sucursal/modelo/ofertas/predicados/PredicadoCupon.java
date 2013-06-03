package sucursal.modelo.ofertas.predicados;

import sucursal.modelo.compras.Compra;

import com.google.common.base.Predicate;

/**
 * Predicate function which returns true if the {@link Compra} has a given
 * coupon associated to it, and false otherwise.
 */
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
