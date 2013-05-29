package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

/**
 * Represents a business rule which indicates that, under certain conditions in
 * a buying session, some discount should be applied.
 */
public abstract class Oferta {
	private final String descripcion;

	public Oferta(final String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * If the associated conditions are met, applies the discount to a buying
	 * session.
	 */
	public void aplicarSiCorresponde(final Compra compra) {
		Float descuento = obtenerDescuento(compra);
		if (descuento != null) {
			compra.agregarDescuento(descripcion, descuento.floatValue());
		}
	}

	/**
	 * Calculates the discount to apply for a given buying session. Returns the
	 * value to discount from the current buying session, if the offer applies
	 * to it, or null otherwise.
	 */
	protected abstract Float obtenerDescuento(final Compra compra);
}
