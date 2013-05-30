package sucursal.modelo.productos;

/**
 * Category to which a {@link Producto} belongs.
 */
public class Rubro {
	private final String nombre;
	private final String codigo;

	public Rubro(final String nombre, final String codigo) {
		this.nombre = nombre;
		this.codigo = codigo;
	}

	/**
	 * Obtains the name of the {@link Rubro}.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Obtains the code which identifies this {@link Rubro}
	 */
	public String getCodigo() {
		return codigo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Rubro))
			return false;

		Rubro actual = (Rubro) obj;
		return actual.getCodigo() == getCodigo();

	}

	@Override
	public int hashCode() {
		return getCodigo().hashCode();
	}

}
