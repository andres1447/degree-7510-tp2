package sucursal.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

public class ResumenVentas {
	private final Map<String, Integer> ventasPorProducto = new HashMap<String, Integer>();

	public void registrarCompra(final String clave, final int cantidad) {
		int cantidadOriginal = 0;

		if (ventasPorProducto.containsKey(clave)) {
			cantidadOriginal = ventasPorProducto.get(clave);
		}

		ventasPorProducto.put(clave, cantidadOriginal + cantidad);
	}

	public void registrarCompra(final Compra compra) {
		for (ItemProducto item : compra.getItems()) {
			final String clave = item.getProducto().getCodigo();
			final int cantidad = item.getCantidad();
			registrarCompra(clave, cantidad);
		}
	}

	public void incorporar(final ResumenVentas resumenVentas) {
		for (EntradaResumenVentas entrada : resumenVentas.toReporte()) {
			final String clave = entrada.getCodigoProducto();
			final int cantidad = entrada.getCantidad();
			registrarCompra(clave, cantidad);
		}
	}

	private List<EntradaResumenVentas> toReporte() {
		List<EntradaResumenVentas> resultado = new ArrayList<>();
		for (Entry<String, Integer> entrada : ventasPorProducto.entrySet()) {
			resultado.add(new EntradaResumenVentas(entrada.getKey(), entrada
					.getValue()));
		}
		return resultado;
	}

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
