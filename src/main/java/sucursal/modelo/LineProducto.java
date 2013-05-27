package sucursal.modelo;

public class LineProducto {
	private Producto producto;
	private int cantidad;

	public LineProducto(Producto producto, int cant) {
		this.producto = producto;
		this.cantidad = cant;
	}

	public Producto getProducto() {
		return producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public float getTotal() {
		return producto.calcularPrecioTotal(cantidad);
	}
}
