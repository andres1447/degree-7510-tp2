package sucursal.modelo.ofertas.predicados;

import com.google.common.base.Predicate;

public class PredicadoMenorQue implements Predicate<Integer> {
	public static PredicadoMenorQue valor(int min) {
		return new PredicadoMenorQue(min);
	}
	private final int min;

	private PredicadoMenorQue(int min) {
		this.min = min;
	}

	@Override
	public boolean apply(final Integer input) {
		return input >= min;
	}

}
