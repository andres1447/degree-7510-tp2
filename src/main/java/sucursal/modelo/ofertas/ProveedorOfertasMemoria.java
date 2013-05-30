package sucursal.modelo.ofertas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sucursal.modelo.compras.MedioPago;
import sucursal.modelo.ofertas.Condiciones.CondicionGlobalConjuntiva;
import sucursal.modelo.ofertas.Condiciones.CondicionGlobalFechaCreacion;
import sucursal.modelo.ofertas.Condiciones.CondicionGlobalMedioPago;
import sucursal.modelo.ofertas.Condiciones.CondicionGlobalNumeroItemsMinimo;
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
		ofertas.add(new OfertaGlobal("$10 llevando más de 5 unidades",
				new CondicionGlobalNumeroItemsMinimo(5), 
				new DescuentoGlobalFijo(10.0f)));

		ofertas.add(new OfertaGlobal("15% llevando más de 10 unidades",
				new CondicionGlobalNumeroItemsMinimo(10),
				new DescuentoGlobalPorcentual(15.0f)));
		
		ofertas.add(new OfertaGlobal("10% descuento pago en efectivo",
				new CondicionGlobalMedioPago(MedioPago.EFECTIVO),
				new DescuentoGlobalPorcentual(10.0f)));
		
		ofertas.add(new OfertaGlobal("5% descuento pago debito miercoles", 
				new CondicionGlobalConjuntiva(
					new CondicionGlobalMedioPago(MedioPago.TARJETA_DEBITO),
					new CondicionGlobalFechaCreacion(new MatcherFechaDiaSemana(Calendar.WEDNESDAY))), 
				new DescuentoGlobalPorcentual(5.0f)));
	}

	@Override
	public List<Oferta> proveer() {
		return ofertas;
	}

}
