package sucursal.modelo;

/**
 * Small class which represents a single entry in a sales summary report
 */
public class EntradaResumenVentas {
	private final String codigoProducto;
	private final int cantidad;

	public EntradaResumenVentas(String codigoProducto, int cantidad) {
		this.codigoProducto = codigoProducto;
		this.cantidad = cantidad;
	}

	/**
	 * Obtains the product code of the product this entry summarizes.
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Obtains the amount of units bought for the given product.
	 */
	public int getCantidad() {
		return cantidad;
	}
}
