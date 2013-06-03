package sucursal.modelo;

public class EntradaResumenVentas {
	private final String codigoProducto;
	private final int cantidad;

	public EntradaResumenVentas(String codigoProducto, int cantidad) {
		this.codigoProducto = codigoProducto;
		this.cantidad = cantidad;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public int getCantidad() {
		return cantidad;
	}
}
