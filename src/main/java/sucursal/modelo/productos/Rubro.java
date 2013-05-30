package sucursal.modelo.productos;

/**
 * Category to which a {@link Producto} belongs.
 */
public class Rubro {
	private String nombre;

	public Rubro(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtains the name of the {@link Rubro}.
	 */
	public String getNombre() {
		return this.nombre;
	}
	
	public boolean equals(Rubro rubro) {
		return this.nombre == rubro.nombre;
	}
}
