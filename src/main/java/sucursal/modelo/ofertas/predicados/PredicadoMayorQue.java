package sucursal.modelo.ofertas.predicados;

import com.google.common.base.Predicate;

public class PredicadoMayorQue implements Predicate<Integer> {
	public static PredicadoMayorQue valor(int max) {
		return new PredicadoMayorQue(max);
	}
	
	private final int max;

	private PredicadoMayorQue(int max) {
		this.max = max;
	}

	@Override
	public boolean apply(final Integer input) {
		return input <= max;
	}

}
