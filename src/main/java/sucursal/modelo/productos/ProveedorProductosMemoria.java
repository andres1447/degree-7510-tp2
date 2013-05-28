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

		productos.put("11-111-1111", new Producto(comidas, cocaCola,
				"Coca-Cola", "Bebida gasificada sabor Cola", 15.5f));
		productos.put("11-111-1112", new Producto(comidas, cocaCola, "SevenUp",
				"Bebida gasificada sabor Limon", 12.5f));
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
