package sucursal.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import sucursal.modelo.caja.Caja;
import sucursal.modelo.ofertas.ProveedorOfertas;
import sucursal.modelo.productos.ProveedorProductos;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Domain class representing a single sucursal or subsidiary, in charge of
 * creating and providing {@link Caja} instances.
 */
@Singleton
public class Sucursal {
	private final ProveedorOfertas proveedorOfertas;
	private final ProveedorProductos proveedorProductos;
	private List<Caja> cajas = new ArrayList<>();

	@Inject
	public Sucursal(final ProveedorOfertas proveedorOfertas,
			final ProveedorProductos proveedorProductos) {
		this.proveedorOfertas = proveedorOfertas;
		this.proveedorProductos = proveedorProductos;
	}

	/**
	 * Creates a new {@link Caja} using the same configuration as all
	 * {@link Caja} instances created by this {@link Sucursal}.
	 */
	public Caja habilitarCaja() {
		Caja caja = new Caja(proveedorOfertas, proveedorProductos);
		cajas.add(caja);
		return caja;
	}
	
	public List<Entry<String, Integer>> generarResumenVentas() {
		SortedMap<String, Integer> resultado = new TreeMap<>();
		for (Caja caja : cajas) {
			Map<String, Integer> resumenVentas = caja.getResumenVentas();
			for (String key : resumenVentas.keySet()) {
				if (resultado.containsKey(key)) {
					Integer cantidad = resultado.get(key);
					cantidad += resumenVentas.get(key);
					resultado.put(key, cantidad);
				} else {
					resultado.put(key, resumenVentas.get(key));
				}
			}
		}
		
		ArrayList<Entry<String, Integer>> ranking = new ArrayList<Entry<String, Integer>>(resultado.entrySet());
		Collections.sort(ranking, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> first,
					Entry<String, Integer> second) {
				return first.getValue().compareTo(second.getValue());
			}
		});
		return ranking;
	}
}
