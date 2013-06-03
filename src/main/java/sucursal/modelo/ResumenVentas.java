package sucursal.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sucursal.modelo.caja.Caja;
import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

/**
 * Represents a summary of the sales in a {@link Caja} or a set of them.
 */
public class ResumenVentas {
	private final Map<String, Integer> ventasPorProducto = new HashMap<String, Integer>();

	private void registrarCompra(final String clave, final int cantidad) {
		int cantidadOriginal = 0;

		if (ventasPorProducto.containsKey(clave)) {
			cantidadOriginal = ventasPorProducto.get(clave);
		}

		ventasPorProducto.put(clave, cantidadOriginal + cantidad);
	}

	private List<EntradaResumenVentas> toReporte() {
		List<EntradaResumenVentas> resultado = new ArrayList<>();
		for (Entry<String, Integer> entrada : ventasPorProducto.entrySet()) {
			resultado.add(new EntradaResumenVentas(entrada.getKey(), entrada
					.getValue()));
		}
		return resultado;
	}

	/**
	 * Processes all the items in a {@link Compra}, recording and registering
	 * the amount of units bought for each producto in the sales summary.
	 */
	public void registrarCompra(final Compra compra) {
		for (ItemProducto item : compra.getItems()) {
			final String clave = item.getProducto().getCodigo();
			final int cantidad = item.getCantidad();
			registrarCompra(clave, cantidad);
		}
	}

	/**
	 * Merges all entries in a given {@link ResumenVentas} with the ones
	 * recorded in this sales summary.
	 */
	public void incorporar(final ResumenVentas resumenVentas) {
		for (EntradaResumenVentas entrada : resumenVentas.toReporte()) {
			final String clave = entrada.getCodigoProducto();
			final int cantidad = entrada.getCantidad();
			registrarCompra(clave, cantidad);
		}
	}

	/**
	 * Outputs an ordered summary of {@link EntradaResumenVentas} with all
	 * records registered in this sales summary.
	 */
	public List<EntradaResumenVentas> toReporteOrdenado() {
		List<EntradaResumenVentas> resultado = toReporte();
		Collections.sort(resultado, new Comparator<EntradaResumenVentas>() {
			@Override
			public int compare(EntradaResumenVentas o1, EntradaResumenVentas o2) {
				return o2.getCantidad() - o1.getCantidad();
			}
		});

		return resultado;
	}
}
