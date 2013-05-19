package sucursal;

public class Rubro {

	private String nombre;

	public Rubro(String nombre) {
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
