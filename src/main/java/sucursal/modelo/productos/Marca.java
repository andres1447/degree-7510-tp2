package sucursal.modelo.productos;

/**
 * Represents the make of a given {@link Producto}.
 */
public class Marca {
	private String nombre;

	public Marca(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtains the identifying name of the {@link Marca}.
	 */
	public String getNombre() {
		return this.nombre;
	}
}
