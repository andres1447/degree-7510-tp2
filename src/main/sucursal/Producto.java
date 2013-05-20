package sucursal;

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

	public String getNombre() {
		return nombre;
	}

	public float calcularPrecioTotal(int cantidad) {
		return precio * cantidad;
	}

	public static Producto getProductoPorCodigo(String text) {
		// TODO Auto-generated method stub
		return new Producto(new Rubro("rubro"), new Marca("marca"), "producto",
				text, 2.f);
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

}
