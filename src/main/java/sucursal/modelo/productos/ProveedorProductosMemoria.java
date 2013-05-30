package sucursal.modelo.productos;

import java.util.HashMap;
import java.util.Map;

import sucursal.modelo.exceptions.ProductoInexistenteException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * {@link ProveedorProductos} implementation which provides a hardcoded,
 * in-memory set of {@link Producto} instances.
 */
@Singleton
public class ProveedorProductosMemoria implements ProveedorProductos {
	private final Map<String, Producto> productos = new HashMap<>();

	@Inject
	public ProveedorProductosMemoria() {
		Rubro comidas = new Rubro("Comida");
		Marca cocaCola = new Marca("Coca Cola");
		Marca villaDelSur = new Marca("Villa Del Sur");
		
		Rubro electronica = new Rubro("Electronica");
		Marca sony = new Marca("Sony");
		
		Rubro limpieza = new Rubro("Limpieza");
		Marca elite = new Marca("Elite");
		Marca skip = new Marca("Skip");


		productos.put("11-111-1111", new Producto(comidas, cocaCola,
				"Coca-Cola", "Bebida gasificada sabor Cola", 15.5f,"11-111-1111"));
		productos.put("11-111-1112", new Producto(comidas, cocaCola, "SevenUp",
				"Bebida gasificada sabor Limon", 12.5f, "11-111-1112"));
		productos.put("12-111-1111", new Producto(comidas, villaDelSur, "Agua",
				"Agua mineral sin gas", 10.0f, "12-111-1111"));
		productos.put("12-111-1112", new Producto(comidas, villaDelSur, "Levite Anana",
				"Agua mineral sin gas saborizada sabor a Anana", 11.0f, "12-111-1112"));
		
		productos.put("21-131-1121", new Producto(electronica, sony, "Parlantes 100W",
				"2 parlantes Stereo con potencia 100 Watt", 50.5f, "21-131-1121"));
		productos.put("21-131-1131", new Producto(electronica, sony, "Reproductor de DVD",
				"Reproductor de DVD Sony con entrada USB y formato de salida RSA", 108.0f, "21-131-1131"));
		
		productos.put("11-211-1122", new Producto(limpieza, elite, "Pa�uelos descartables x3",
				"3 packs de 5 pa�uelos descartables", 8.5f, "11-211-1122"));
		productos.put("15-211-1432", new Producto(limpieza, skip, "Jabon en polvo",
				"Botella de jabon en polvo para ropa, 1Kg.", 22.5f, "15-211-1432"));
		
	}

	@Override
	public ListadoProductos proveer() {
		return new ListadoProductos() {
			@Override
			public Producto buscarPorCodigo(final String codigo) {
				if (productos.containsKey(codigo)) {
					return productos.get(codigo);
				} else {
					throw new ProductoInexistenteException();
				}

			}
		};
	}
}
