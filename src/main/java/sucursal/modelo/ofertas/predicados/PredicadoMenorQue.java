package sucursal.modelo.ofertas.predicados;

import com.google.common.base.Predicate;

/**
 * Predicate function which returns true if the {@link Integer} is equal or
 * greater to a given minimum, and false otherwise.
 */
public class PredicadoMenorQue implements Predicate<Integer> {
	private final int min;

	private PredicadoMenorQue(int min) {
		this.min = min;
	}

	@Override
	public boolean apply(final Integer input) {
		return input >= min;
	}

}
