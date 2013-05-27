package sucursal.modelo;

public class Oferta {
	private final String descripcion;
	private final OfertaCondicion condicion;
	private final OfertaDescuento descuento;
	
	public Oferta(final String descripcion, final OfertaCondicion condicion, final OfertaDescuento descuento) {
		this.descripcion = descripcion;
		this.condicion = condicion;
		this.descuento = descuento;
	}

	public void aplicarSiCorresponde(final Compra compra) {
		if (condicion.corresponde(compra)) {
			float valorDescuento = descuento.aplicar(compra);
			compra.agregarDescuento(descripcion, valorDescuento);
		}
	}
}
