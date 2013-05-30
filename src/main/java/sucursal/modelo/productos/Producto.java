package sucursal.modelo.productos;

/**
 * Represents a sellable item in store which can be bought by a customer.
 */
public class Producto {
	private final String nombre;
	private float precio;
	private final Rubro rubro;
	private final Marca marca;
	private final String descripcion;
	private final String codigo;

	public Producto(final Rubro rubro, final Marca marca, final String codigo,
			final String nombre, final String descripcion, final float precio) {
		this.rubro = rubro;
		this.marca = marca;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.codigo = codigo;
	}

	/**
	 * Obtains a short code identifying the product
	 */
	public String getCodigo() {
		return rubro.getCodigo() + "-" + marca.getCodigo() + "-" + codigo;
	}

	/**
	 * Obtains the name of the {@link Producto}.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Calculates the total price, for a given quantity of the {@link Producto}.
	 */
	public float calcularPrecioTotal(int cantidad) {
		return precio * cantidad;
	}

	/**
	 * Obtains the {@link Rubro} of the {@link Producto}.
	 */
	public Rubro getRubro() {
		return rubro;
	}

	/**
	 * Obtains a short description explaining what the {@link Producto} is.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Obtains the {@link Marca} of the {@link Producto}.
	 */
	public Marca getMarca() {
		return marca;
	}

	/**
	 * Obtains the price for a single unit of the {@link Producto}.
	 */
	public float getPrecioUnitario() {
		return precio;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Producto))
			return false;

		Producto actual = (Producto) obj;
		return actual.getCodigo() == getCodigo();
	}

	@Override
	public int hashCode() {
		return getCodigo().hashCode();
	}

}
