package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

/**
 * Represents a business rule which indicates that, under certain conditions in
 * a buying session, some discount should be applied. Both concepts (the
 * condition under which the discount should be applied and the meaning of
 * applying the actual discount) are ortogonal, represented by the
 * {@link OfertaCondicion} and {@link OfertaDescuento} clases. The
 * {@link Oferta} works as a manager, containing an {@link OfertaCondicion} and
 * an {@link OfertaDescuento}, and it applies the {@link OfertaDescuento} if the
 * {@link OfertaCondicion} is met.
 */
public class Oferta {
	private final String descripcion;
	private final OfertaCondicion condicion;
	private final OfertaDescuento descuento;

	public Oferta(final String descripcion, final OfertaCondicion condicion,
			final OfertaDescuento descuento) {
		this.descripcion = descripcion;
		this.condicion = condicion;
		this.descuento = descuento;
	}

	/**
	 * If the associated conditions are met, applies the discount to a buying
	 * session.
	 */
	public void aplicarSiCorresponde(final Compra compra) {
		if (condicion.corresponde(compra)) {
			float valorDescuento = descuento.aplicar(compra);
			compra.agregarDescuento(descripcion, valorDescuento);
		}
	}
}
