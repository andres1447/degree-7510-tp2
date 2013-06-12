package sucursal.modelo.ofertas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.MedioPago;
import sucursal.modelo.ofertas.descuentos.DescuentoEnSegundoProducto;
import sucursal.modelo.ofertas.descuentos.DescuentoFijo;
import sucursal.modelo.ofertas.descuentos.DescuentoLlevaXPagaY;
import sucursal.modelo.ofertas.descuentos.DescuentoPorcentual;
import sucursal.modelo.ofertas.descuentos.DescuentoPuntos;
import sucursal.modelo.ofertas.extractores.ExtraerFechaCreacion;
import sucursal.modelo.ofertas.extractores.ExtraerMedioPago;
import sucursal.modelo.ofertas.extractores.ExtraerTotalBruto;
import sucursal.modelo.ofertas.extractores.ExtraerTotalBrutoProductos;
import sucursal.modelo.ofertas.predicados.PredicadoCupon;
import sucursal.modelo.ofertas.predicados.PredicadoDiaSemana;
import sucursal.modelo.ofertas.predicados.PredicadoJubilado;
import sucursal.modelo.ofertas.predicados.PredicadoRubro;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Simple {@link ProveedorOfertas} implementation which provides hardcoded,
 * in-memory {@link OfertaDinero} instances.
 */
@Singleton
public class ProveedorOfertasMemoria implements ProveedorOfertas {
	private final List<IOferta> ofertas = new ArrayList<>();

	@Inject
	public ProveedorOfertasMemoria() {
		ofertas.add(buildOfertaEjemplo1());
		ofertas.add(buildOfertaEjemplo2());
		ofertas.add(buildOfertaEjemplo3());
		ofertas.add(buildOfertaEjemplo4());
		ofertas.add(buildOfertaEjemplo5());
		ofertas.add(buildOfertaEjemplo6());
		ofertas.add(buildOfertaEjemplo7());
		ofertas.add(buildOfertaPuntos());
	}

	private IOferta buildOfertaEjemplo1() {
		// 5% de descuento en todos los productos si pagas en
		// efectivo
		Predicate<Compra> condicion = Predicates.compose(
				Predicates.equalTo(MedioPago.EFECTIVO),
				new ExtraerMedioPago());

		Function<Compra, Float> descuento = Functions.compose(
				new DescuentoPorcentual(5.0f),
				new ExtraerTotalBruto());

		return new OfertaDinero("5% descuento pago en efectivo", condicion, descuento);
	}

	private IOferta buildOfertaEjemplo2() {
		// 10% de descuento en todos los productos del rubro comida si es
		// miercoles
		Predicate<Compra> condicion = Predicates.compose(
				new PredicadoDiaSemana(Calendar.THURSDAY),
				new ExtraerFechaCreacion());
		Function<Compra, Float> descuento = Functions.compose(
				new DescuentoPorcentual(10.0f), 
				new ExtraerTotalBrutoProductos(new PredicadoRubro("11")));
		return new OfertaDinero("10% descuento comida los jueves", condicion,
				descuento);
	}
	
	private IOferta buildOfertaEjemplo3() {
		// Te devolvemos $10 los sabados si compras con tarjeta de debito
		Predicate<Compra> condicion = Predicates.compose(
				new PredicadoDiaSemana(Calendar.SATURDAY),
				new ExtraerFechaCreacion());
		Function<Compra, Float> descuento = new DescuentoFijo<>(10.0f);
		return new OfertaDinero("10$ descuento sabados", condicion,
				descuento);
	}
	
	private IOferta buildOfertaEjemplo4() {
		// Llevas 3 cocas pagas 2
		Predicate<Compra> condicion = Predicates.alwaysTrue();
		Function<Compra, Float> descuento = new DescuentoLlevaXPagaY("11-111-1111", 3, 2);
		return new OfertaDinero("Lleva 3 paga 2 en Coca-Cola", condicion,
				descuento);
	}
	
	private IOferta buildOfertaEjemplo5() {
		// 5% de descuento en todos los productos si pagas en
		// efectivo
		Predicate<Compra> condicion = new PredicadoJubilado();

		Function<Compra, Float> descuento = Functions.compose(
				new DescuentoPorcentual(10.0f),
				new ExtraerTotalBruto());

		return new OfertaDinero("10% descuento jubilados", condicion, descuento);
	}
	
	private IOferta buildOfertaEjemplo6() {
		// $10 si tiene el cupon 11111
		Predicate<Compra> condicion = new PredicadoCupon("11111");

		Function<Compra, Float> descuento = Functions.compose(
				new DescuentoFijo<>(10.0f),
				new ExtraerTotalBruto());

		return new OfertaDinero("$10 cupon", condicion, descuento);
	}
	
	private IOferta buildOfertaEjemplo7() {
		// Descuento en el 2do producto distinto al 1ro
		Predicate<Compra> condicion = Predicates.alwaysTrue();
		Function<Compra, Float> descuento = new DescuentoEnSegundoProducto("11-111-1111", "11-111-1112", 50);
		return new OfertaDinero("50% en Sprite, comprando 1 Coca", condicion,	descuento);
	}
	
	private IOferta buildOfertaPuntos() {
		Predicate<Compra> condicion = Predicates.alwaysTrue();
		Function<Compra, Integer> descuento = new DescuentoPuntos();
		return new OfertaPuntos(condicion, descuento);
	}

	@Override
	public List<IOferta> proveer() {
		return ofertas;
	}

}
