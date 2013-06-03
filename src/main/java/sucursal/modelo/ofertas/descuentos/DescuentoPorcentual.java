package sucursal.modelo.ofertas.descuentos;

import com.google.common.base.Function;

/**
 * Simple discount strategy which discounts a percentage out of a quantity. This
 * discount should be combined with an extractor which specifies which quantity
 * should be the base out of which this discount discounts a percentage amount.
 */
public class DescuentoPorcentual implements Function<Float, Float> {
	private final float porcentaje;

	public DescuentoPorcentual(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public Float apply(final Float input) {
		return input * porcentaje / 100;
	}
}
