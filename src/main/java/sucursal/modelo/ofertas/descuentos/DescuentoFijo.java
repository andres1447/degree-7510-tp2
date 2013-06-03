package sucursal.modelo.ofertas.descuentos;

import com.google.common.base.Function;

/**
 * Discount which applies a fixed value discount on anything.
 * 
 * @param <T>
 *            The type of the input value. This discount ignores its input, as
 *            it always returns the same discount value.
 */
public class DescuentoFijo<T> implements Function<T, Float> {
	private final float valor;

	public DescuentoFijo(float valor) {
		this.valor = valor;
	}

	@Override
	public Float apply(T compra) {
		return valor;
	}
}
