package sucursal.modelo;

public class Descuento {
	private final String descripcion;
	private final float valor;

	public Descuento(String descripcion, float valor) {
		this.descripcion = descripcion;
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public float getValor() {
		return valor;
	}
}
