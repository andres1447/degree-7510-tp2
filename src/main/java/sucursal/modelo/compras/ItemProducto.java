package sucursal.modelo.compras;

import sucursal.modelo.productos.Producto;

/**
 * Represents an item in the buying session reflecting an acquired product.
 */
public class ItemProducto {
	private Producto producto;
	private int cantidad;

	public ItemProducto(Producto producto, int cant) {
		this.producto = producto;
		this.cantidad = cant;
	}

	/**
	 * Obtains the product which was acquired.
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * Obtains the amount of units of the product which were acquired.
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Obtains the total price amount which should be charged to the customer
	 * for acquiring the products represented by this item.
	 */
	public float getTotal() {
		return producto.calcularPrecioTotal(cantidad);
	}
	
	public boolean equals(ItemProducto oth) {
		return this.producto.getCodigo() == oth.producto.getCodigo();
	}
	
}
