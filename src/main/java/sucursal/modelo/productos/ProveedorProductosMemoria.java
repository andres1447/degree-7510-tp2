package sucursal.modelo.productos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		final List<Producto> rawProductos = new ArrayList<Producto>();
		
		Rubro comidas = new Rubro("Comida", "11");
		Marca cocaCola = new Marca("Coca Cola", "111");
		Marca villaDelSur = new Marca("Villa Del Sur", "112");
		
		Rubro electronica = new Rubro("Electronica", "22");
		Marca sony = new Marca("Sony", "221");
		
		Rubro limpieza = new Rubro("Limpieza", "33");
		Marca elite = new Marca("Elite", "331");
		Marca skip = new Marca("Skip", "332");
		
		rawProductos.add(new Producto(comidas, cocaCola, "1111",
				"Coca-Cola", "Bebida gasificada sabor Cola", 15.5f));
		rawProductos.add(new Producto(comidas, cocaCola, "1112", "Sprite",
				"Bebida gasificada sabor Limon", 12.5f));
		rawProductos.add(new Producto(comidas, villaDelSur, "1111", "Agua",
				"Agua mineral sin gas", 10.0f));
		rawProductos.add(new Producto(comidas, villaDelSur, "1112", "Levite Anana",
				"Agua mineral sin gas saborizada sabor a Anana", 11.0f));
		rawProductos.add(new Producto(electronica, sony, "1121", "Parlantes 100W",
				"2 parlantes Stereo con potencia 100 Watt", 50.5f));
		rawProductos.add(new Producto(electronica, sony, "1131", "Reproductor de DVD",
				"Reproductor de DVD Sony con entrada USB y formato de salida RSA", 108.0f));
		rawProductos.add(new Producto(limpieza, elite, "1122", "Panuelos descartables x3",
				"3 packs de 5 panuelos descartables", 8.5f));
		rawProductos.add(new Producto(limpieza, skip, "1432", "Jabon en polvo",
				"Botella de jabon en polvo para ropa, 1Kg.", 22.5f));
		
		procesarProductos(rawProductos);
	}

	private void procesarProductos(List<Producto> rawProductos) {
		for (Producto p : rawProductos) {
			productos.put(p.getCodigo(), p);
		}
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
