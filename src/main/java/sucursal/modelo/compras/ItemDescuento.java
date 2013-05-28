package sucursal.modelo.compras;

/**
 * Represents an item in the buying session which reflects a discount.
 */
public class ItemDescuento {
	private final String descripcion;
	private final float valor;

	public ItemDescuento(String descripcion, float valor) {
		this.descripcion = descripcion;
		this.valor = valor;
	}

	/**
	 * Obtains a short text identifying the reason why the discount was applied.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Obtains the amount which should be substracted from the total amount of
	 * the buying session because of this discount.
	 */
	public float getValor() {
		return valor;
	}
}
