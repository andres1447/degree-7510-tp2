package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

public class CondicionGlobalNumeroItemsMaximo implements CondicionGlobal {
	private final int numeroItems;

	public CondicionGlobalNumeroItemsMaximo(int numeroItems) {
		this.numeroItems = numeroItems;
	}

	@Override
	public boolean aplicaEn(final Compra compra) {
		int cantidad = 0;
		for (ItemProducto item : compra.getItems()) {
			cantidad += item.getCantidad();
		}
		return cantidad <= numeroItems;
	}

}
