package sucursal.modelo;

public class Producto {

	private String nombre;
	private float precio;
	private Rubro rubro;
	private Marca marca;
	private String descripcion;

	private Producto(Rubro rubro, Marca marca, String nombre,
			String descripcion, float precio) {
		this.rubro = rubro;
		this.marca = marca;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	// TODO: Producto de ejemplo
	public Producto() {
		this(new Rubro("rubro"), new Marca("marca"), "producto", "COD", 2.f);
	}

	public String getNombre() {
		return nombre;
	}

	public float calcularPrecioTotal(int cantidad) {
		return precio * cantidad;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Object getMarca() {
		return marca;
	}

	public float getPrecioUnitario() {
		return precio;
	}

}
