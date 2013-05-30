package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.modelo.compras.MedioPago;

public class OfertaUnicaDebitoPorcentual extends Oferta{

	private final int porcentaje;
	private final MedioPago pago;
	
	public OfertaUnicaDebitoPorcentual(String descripcion, int porcentaje) {
		super(descripcion);
		this.porcentaje = porcentaje;
		this.pago = MedioPago.TARJETA_DEBITO;
		}

	@Override
	protected Float obtenerDescuento(Compra compra) {
		float descuento = 0.0f;
		for (ItemProducto item : compra.getItems()) {
			if(!item.getProducto().hasDescuento() & compra.getMedioPago().name() == this.pago.name()){
				item.getProducto().confirmarDescuento();
				descuento += item.getTotal();
			}
		}
		if(descuento == 0.0f) return null;
		return descuento * this.porcentaje / 100;
	}

}
