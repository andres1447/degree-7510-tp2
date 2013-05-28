package sucursal.modelo.ofertas;

import java.util.ArrayList;
import java.util.List;

import sucursal.modelo.productos.ProveedorProductos;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Simple {@link ProveedorOfertas} implementation which provides hardcoded,
 * in-memory {@link Oferta} instances.
 */
@Singleton
public class ProveedorOfertasMemoria implements ProveedorOfertas {
	private final List<Oferta> ofertas = new ArrayList<>();

	@Inject
	public ProveedorOfertasMemoria(final ProveedorProductos productos) {
		ofertas.add(new OfertaGlobal(
				"$10 llevando más de 5 unidades",
				new CondicionGlobalNumeroItems(5), 
				new DescuentoGlobalFijo(10.0f)));
	}

	@Override
	public List<Oferta> proveer() {
		return ofertas;
	}

}
