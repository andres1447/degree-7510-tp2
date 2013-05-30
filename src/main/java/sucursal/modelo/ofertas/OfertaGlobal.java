package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.ofertas.Condiciones.CondicionGlobal;

public class OfertaGlobal extends Oferta {
	private final CondicionGlobal condicion;
	private final DescuentoGlobal descuento;

	public OfertaGlobal(final String descripcion,
			final CondicionGlobal condicion, final DescuentoGlobal descuento) {
		super(descripcion);
		this.condicion = condicion;
		this.descuento = descuento;
	}

	@Override
	protected Float obtenerDescuento(Compra compra) {
		if (condicion.aplicaEn(compra)) {
			return descuento.calcularPara(compra);
		} else {
			return null;
		}
	}

}
