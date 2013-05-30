package sucursal.modelo.compras;

import sucursal.modelo.exceptions.CantidadNoAplicableADescuentoExclusivoException;
import sucursal.modelo.productos.Producto;

/**
 * Represents an item in the buying session reflecting an acquired product.
 */
public class ItemProducto {
	private Producto producto;
	private int cantidad;
	private int cantidadConDescuentoExclusivo;

	public ItemProducto(Producto producto, int cant) {
		this.producto = producto;
		this.cantidad = cant;
		this.cantidadConDescuentoExclusivo = 0;
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

	/**
	 * Applies an exclusive discount to a given amount of the bought product
	 * quantity. Other exclusive discounts may not be applied to this
	 * {@link ItemProducto} if they are trying to apply an exclusive discount to
	 * an amount greater than what is left.
	 */
	public void aplicarDescuentoExclusivoA(final int cantidad) {
		if (cantidad > getRemanenteSinDescuentoExclusivo()) {
			throw new CantidadNoAplicableADescuentoExclusivoException();
		}
		cantidadConDescuentoExclusivo += cantidad;
	}

	/**
	 * Obtains the amount of product quantity which does not have an exclusive
	 * discount applied.
	 */
	public int getRemanenteSinDescuentoExclusivo() {
		return cantidad - cantidadConDescuentoExclusivo;
	}

	/**
	 * Checks if the amount of product quantity which does not have an exclusive
	 * discount applied is greater than a given amount. Returns true if it is,
	 * false otherwise.
	 */
	public boolean tieneRemanenteSinDescuentoExclusivo(int i) {
		return getRemanenteSinDescuentoExclusivo() >= i;
	}

	/**
	 * Increments the amount of product quantity purchased in this
	 * {@link ItemProducto}.
	 */
	public void incrementarCantidad(int i) {
		this.cantidad += i;
	}
}
