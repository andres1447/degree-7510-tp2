package sucursal.modelo.ofertas.descuentos;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

import com.google.common.base.Function;

public class DescuentoPuntosCompra implements Function<Compra, Integer> {
	private float montoParaUnPunto;
	
	public DescuentoPuntosCompra(float montoParaUnPunto) {
		this.montoParaUnPunto = montoParaUnPunto;
	}
	
	@Override
	public Integer apply(Compra input) {
		float total = 0;
		for (ItemProducto item : input.getItems()) {
			if (!input.getListadoPuntos().contiene(item.getCodigoProducto()))
				total += item.getTotal();
		}
		return (int) (total/montoParaUnPunto);
	}
}
