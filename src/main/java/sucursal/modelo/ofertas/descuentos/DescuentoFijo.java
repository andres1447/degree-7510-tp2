package sucursal.modelo.ofertas.descuentos;

import com.google.common.base.Function;

public class DescuentoFijo<T> implements Function<T, Float> {
	public static <T> DescuentoFijo<T> instance(float valor) {
		return new DescuentoFijo<>(valor);
	}
	
	private final float valor;

	public DescuentoFijo(float valor) {
		this.valor = valor;
	}

	@Override
	public Float apply(T compra) {
		return valor;
	}
}
