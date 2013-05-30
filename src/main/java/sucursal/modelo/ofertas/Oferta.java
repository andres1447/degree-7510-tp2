package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * Represents a business rule which indicates that, under certain conditions in
 * a buying session, some discount should be applied.
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
