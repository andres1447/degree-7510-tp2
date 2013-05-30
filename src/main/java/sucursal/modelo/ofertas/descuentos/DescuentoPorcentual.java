package sucursal.modelo.ofertas.descuentos;

import com.google.common.base.Function;

public class DescuentoPorcentual implements Function<Float, Float> {
	private final float porcentaje;

	public static Function<Float, Float> instance(float porcentaje) {
		return new DescuentoPorcentual(porcentaje);
	}

	public DescuentoPorcentual(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public Float apply(final Float input) {
		return input * porcentaje / 100;
	}
}
