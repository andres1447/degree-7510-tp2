package sucursal.modelo.ofertas;

import java.util.ArrayList;
import java.util.List;

import sucursal.modelo.compras.MedioPago;

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
	public ProveedorOfertasMemoria() {

		OfertaGlobal ofertaCredito = new OfertaGlobal(
				"15% descuento con tarjeta de credito",
				new CondicionGlobalConjuntiva(new CondicionGlobalMedioPago(
						MedioPago.TARJETA_CREDITO), // Condicion tarjeta de
													// credito
						new CondicionGlobalFechaCreacion(
								new MatcherFechaDiaSemana(4))), // Dia 5 =
																// Viernes
				new DescuentoGlobalPorcentual(15)); // Descuento porcentual
		ofertas.add(ofertaCredito);

		OfertaUnicaLlevaXPagaY oferta3x2 = new OfertaUnicaLlevaXPagaY(
				"3 productos al precio de 2", 3, 2, "11-111-1111"); // Descuento
																	// Producto
																	// gratis
																	// porcentual
																	// al total
		ofertas.add(oferta3x2);
	}

	@Override
	public List<Oferta> proveer() {
		return ofertas;
	}

}
