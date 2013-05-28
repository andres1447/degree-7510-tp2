package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

public class CondicionGlobalNumeroItems implements CondicionGlobal {
	private final int numeroItems;

	public CondicionGlobalNumeroItems(int numeroItems) {
		this.numeroItems = numeroItems;
	}

	@Override
	public boolean aplicaEn(Compra compra) {
		int cantidad = 0;
		for (ItemProducto item : compra.getItems()) {
			cantidad += item.getCantidad();
		}
		return cantidad >= numeroItems;
	}

}
