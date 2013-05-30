package sucursal.modelo.compras;

public class SelectorClaveRubro implements SeleccionadorClave {

	@Override
	public String obtenerClave(ItemProducto item) {
		return item.getProducto().getRubro().toString();
	}

}
