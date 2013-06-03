package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Represents a business rule which indicates that, under certain conditions in
 * a buying session, some discount should be applied.
 * 
 * The offer has a global condition which should be met if the offer should be
 * applied, and a particular discount strategy which calculates how the actual
 * discount is made.
 * 
 * Conditions are simple {@link Predicate} instances, which allows composing to
 * create complex condition combinations out of simple predicates. Check out the
 * {@link Predicates} class, which provides combinators and other
 * predicate-building mechanisms.
 * 
 * Discounts are {@link Function} implementations which map {@link Compra}
 * instances to {@link Float} instances. You can return null from a discount to
 * indicate that no discount should be made whatsoever. Check out the
 * {@link Functions} class, which provides function combinators to ease
 * composability.
 */
public class Oferta {
	private final String descripcion;
	private final Predicate<Compra> condicion;
	private final Function<Compra, Float> descuento;

	public Oferta(final String descripcion, final Predicate<Compra> condicion,
			final Function<Compra, Float> descuento) {
		this.descripcion = descripcion;
		this.condicion = condicion;
		this.descuento = descuento;
	}

	/**
	 * If the associated conditions are met, applies the discount to a buying
	 * session.
	 */
	public void aplicarSiCorresponde(final Compra compra) {
		if (condicion.apply(compra)) {
			Float valorDescuento = descuento.apply(compra);
			if (valorDescuento != null) {
				compra.agregarDescuento(descripcion, valorDescuento);
			}
		}
	}
}
