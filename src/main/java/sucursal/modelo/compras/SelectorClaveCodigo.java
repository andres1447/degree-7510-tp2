package sucursal.modelo.compras;

public class SelectorClaveCodigo implements SeleccionadorClave {

	@Override
	public String obtenerClave(ItemProducto item) {
		return item.getProducto().getCodigo();
	}

}
