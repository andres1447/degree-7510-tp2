package sucursal.modelo.productos;

import java.util.HashMap;
import java.util.Map;

import sucursal.modelo.exceptions.ProductoInexistenteException;

public class ListadoProductosMapaMemoria implements ListadoProductos {
	private final Map<String, Producto> productos = new HashMap<>();

	@Override
	public Producto buscarPorCodigo(String codigo) {
		if (productos.containsKey(codigo)) {
			return productos.get(codigo);
		} else {
			throw new ProductoInexistenteException();
		}
	}

	public void add(final Producto producto) {
		productos.put(producto.getCodigo(), producto);
	}
}
