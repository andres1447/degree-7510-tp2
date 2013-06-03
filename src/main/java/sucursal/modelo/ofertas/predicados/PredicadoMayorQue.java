package sucursal.modelo.ofertas.predicados;

import com.google.common.base.Predicate;

/**
 * Predicate function which returns true if the {@link Integer} is less or equal
 * to a given max, and false otherwise.
 */
public class PredicadoMayorQue implements Predicate<Integer> {
	private final int max;

	private PredicadoMayorQue(int max) {
		this.max = max;
	}

	@Override
	public boolean apply(final Integer input) {
		return input <= max;
	}

}
