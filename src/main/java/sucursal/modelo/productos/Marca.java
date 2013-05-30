package sucursal.modelo.productos;

/**
 * Represents the make of a given {@link Producto}.
 */
public class Marca {
	private final String nombre;
	private final String codigo;

	public Marca(final String nombre, final String codigo) {
		this.nombre = nombre;
		this.codigo = codigo;
	}

	/**
	 * Obtains the identifying name of the {@link Marca}.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Obtains the code identifying this {@link Marca}
	 */
	public String getCodigo() {
		return codigo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Marca))
			return false;

		Marca actual = (Marca) obj;
		return actual.getCodigo() == getCodigo();
	}

	@Override
	public int hashCode() {
		return getCodigo().hashCode();
	}

}
