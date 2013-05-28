package sucursal.modelo.productos;

/**
 * Represents a sellable item in store which can be bought by a customer.
 */
public class Producto {
	private String nombre;
	private float precio;
	private Rubro rubro;
	private Marca marca;
	private String descripcion;

	public Producto(Rubro rubro, Marca marca, String nombre,
			String descripcion, float precio) {
		this.rubro = rubro;
		this.marca = marca;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
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
}
