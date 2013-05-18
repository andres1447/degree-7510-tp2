package sucursal;

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

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String[] getTuplaDeDatos() {
		String[] tupla = new String[] { producto.getCodigo(),
				producto.getNombre(), producto.getPrecioUnitario(),
				Integer.toString(cantidad) };
		return tupla;
	}
}
