package sucursal.modelo;

public class Marca {

	private String nombre;

	public Marca(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String toString() {
		return getNombre();
	}
}
