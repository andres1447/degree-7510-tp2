package sucursal.modelo.ofertas.predicados;

import sucursal.modelo.compras.Compra;

import com.google.common.base.Predicate;

/**
 * Predicate function which returns true if the {@link Compra} has an elderly
 * customer, and false otherwise.
 */
public class PredicadoJubilado implements Predicate<Compra> {
	@Override
	public boolean apply(Compra input) {
		return input.esJubilado();
	}
}
