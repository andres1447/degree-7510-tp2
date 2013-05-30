package sucursal.modelo.compras;

public class SelectorClaveMarca implements SeleccionadorClave {

	@Override
	public String obtenerClave(ItemProducto item) {
		return item.getProducto().getMarca().toString();
	}

}
