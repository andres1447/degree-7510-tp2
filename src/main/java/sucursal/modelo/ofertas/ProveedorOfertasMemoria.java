package sucursal.modelo.ofertas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.MedioPago;
import sucursal.modelo.ofertas.descuentos.DescuentoFijo;
import sucursal.modelo.ofertas.descuentos.DescuentoPorcentual;
import sucursal.modelo.ofertas.extractores.ExtraerFechaCreacion;
import sucursal.modelo.ofertas.extractores.ExtraerMedioPago;
import sucursal.modelo.ofertas.extractores.ExtraerTotalBruto;
import sucursal.modelo.ofertas.extractores.ExtraerTotalBrutoProductos;
import sucursal.modelo.ofertas.predicados.PredicadoDiaSemana;
import sucursal.modelo.ofertas.predicados.PredicadoRubro;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
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
		ofertas.add(buildOfertaEjemplo1());
		ofertas.add(buildOfertaEjemplo2());
		ofertas.add(buildOfertaEjemplo3());
	}

	private Oferta buildOfertaEjemplo1() {
		// 5% de descuento en todos los productos si pagas en
		// efectivo
		Predicate<Compra> condicion = Predicates.compose(
				Predicates.equalTo(MedioPago.EFECTIVO),
				ExtraerMedioPago.instance());

		Function<Compra, Float> descuento = Functions.compose(
				DescuentoPorcentual.instance(5.0f),
				ExtraerTotalBruto.instance());

		return new Oferta("5% descuento pago en efectivo", condicion, descuento);
	}

	private Oferta buildOfertaEjemplo2() {
		// 10% de descuento en todos los productos del rubro comida si es
		// miercoles
		Predicate<Compra> condicion = Predicates.compose(
				PredicadoDiaSemana.instance(Calendar.THURSDAY),
				ExtraerFechaCreacion.instance());
		Function<Compra, Float> descuento = Functions.compose(
				DescuentoPorcentual.instance(10.0f), 
				ExtraerTotalBrutoProductos.instance(PredicadoRubro.instance("11")));
		return new Oferta("10% descuento comida los miercoles", condicion,
				descuento);
	}
	
	private Oferta buildOfertaEjemplo3() {
		// Te devolvemos $10 los sabados si compras con tarjeta de debito
		Predicate<Compra> condicion = Predicates.compose(
				PredicadoDiaSemana.instance(Calendar.SATURDAY),
				ExtraerFechaCreacion.instance());
		Function<Compra, Float> descuento = DescuentoFijo.instance(10.0f);
		return new Oferta("10$ descuento sabados", condicion,
				descuento);
		
	}

	@Override
	public List<Oferta> proveer() {
		return ofertas;
	}

}
