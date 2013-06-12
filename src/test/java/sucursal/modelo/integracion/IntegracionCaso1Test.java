package sucursal.modelo.integracion;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import sucursal.modelo.Sucursal;
import sucursal.modelo.caja.Caja;
import sucursal.modelo.cliente.Cliente;
import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.MedioPago;
import sucursal.modelo.compras.ProveedorFechaActual;
import sucursal.modelo.ofertas.IOferta;
import sucursal.modelo.ofertas.OfertaDinero;
import sucursal.modelo.ofertas.ProveedorOfertas;
import sucursal.modelo.ofertas.descuentos.DescuentoLlevaXPagaY;
import sucursal.modelo.ofertas.descuentos.DescuentoPorcentual;
import sucursal.modelo.ofertas.extractores.ExtraerFechaCreacion;
import sucursal.modelo.ofertas.extractores.ExtraerMedioPago;
import sucursal.modelo.ofertas.extractores.ExtraerTotalBruto;
import sucursal.modelo.ofertas.predicados.PredicadoDiaSemana;
import sucursal.modelo.productos.ListadoProductosMapaMemoria;
import sucursal.modelo.productos.Marca;
import sucursal.modelo.productos.Producto;
import sucursal.modelo.productos.ProveedorProductos;
import sucursal.modelo.productos.Rubro;
import sucursal.modelo.puntos.ProveedorPuntos;

import com.google.common.base.Functions;
import com.google.common.base.Predicates;

@RunWith(MockitoJUnitRunner.class)
public class IntegracionCaso1Test {
	@Mock
	private ProveedorFechaActual mockFecha;
	
	@Mock
	private ProveedorProductos mockProductos;
	
	@Mock
	private ProveedorOfertas mockOfertas;
	
	@Mock
	private ProveedorPuntos mockPuntos;

	@Test
	public void verifcarCaso() {
		// GIVEN:
		ListadoProductosMapaMemoria listadoProductos = new ListadoProductosMapaMemoria();
		Mockito.stub(mockProductos.proveer()).toReturn(listadoProductos);
		
		List<IOferta> ofertas = new ArrayList<IOferta>();
		Mockito.stub(mockOfertas.proveer()).toReturn(ofertas);

		// - es jueves
		Calendar date = Calendar.getInstance();
		date.set(2013, 05, 06);
		Mockito.stub(mockFecha.proveer()).toReturn(date.getTime());
		// - la coca sale 1 peso
		Rubro bebidas = new Rubro("Bebidas", "1");
		Marca coca = new Marca("Coca", "11");
		Producto cocaCola = new Producto(bebidas, coca, "111", "CocaCola", "", 1);
		listadoProductos.add(cocaCola);
		// - cepillo de dientes sale 3 pesos
		Rubro banio = new Rubro("Baño", "2");
		Marca colgate = new Marca("Colgate", "22");
		Producto cepillo = new Producto(banio, colgate, "222", "Cepillo de Dientes", "", 3);
		listadoProductos.add(cepillo);
		// - maceta sale 10 pesos
		Rubro jardin = new Rubro("Jardineria", "3");
		Marca generica = new Marca("Generica", "33");
		Producto maceta = new Producto(jardin, generica, "333", "Maceta", "", 10);
		listadoProductos.add(maceta);
		// - existe promo coca lleve 2 pague 1
		ofertas.add(new OfertaDinero("Coca 2X1", 
				Predicates.<Compra>alwaysTrue(),
				new DescuentoLlevaXPagaY("1-11-111", 2, 1)));		

		// - hay descuento 10% para tarjeta de debito los dias jueves
		ofertas.add(new OfertaDinero("10% descuento tarjeta debito jueves",
				Predicates.and(
						Predicates.compose(
								new PredicadoDiaSemana(Calendar.THURSDAY), 
								new ExtraerFechaCreacion()), 
						Predicates.compose(
								Predicates.equalTo(MedioPago.TARJETA_DEBITO),
								new ExtraerMedioPago())),
				Functions.compose(
						new DescuentoPorcentual(10.0f), 
						new ExtraerTotalBruto())));

		// WHEN

		// - se realiza una compra de 2 cocas, 1 cepillo y 1 maceta pagando con
		// tarjeta debito
		Sucursal sucursal = new Sucursal(mockOfertas, mockProductos, mockFecha, mockPuntos);
		Caja caja = sucursal.habilitarCaja();
		caja.abrir();
		Compra compra = caja.iniciarCompra();
		compra.setCliente(new Cliente("asasd"));
		compra.agregarItem(cocaCola, 2);
		compra.agregarItem(cepillo, 1);
		compra.agregarItem(maceta, 1);
		compra.setMedioPago(MedioPago.TARJETA_DEBITO);
		compra.confirmar();

		// THEN

		// - el precio final de la venta
		assertThat(compra.getTotal(), is(15.0f - (0.1f * (15.0f)) - 1.0f));
	}
}
