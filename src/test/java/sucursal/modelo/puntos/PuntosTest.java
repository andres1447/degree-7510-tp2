package sucursal.modelo.puntos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import sucursal.modelo.Sucursal;
import sucursal.modelo.caja.Caja;
import sucursal.modelo.cliente.Cliente;
import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.MedioPago;
import sucursal.modelo.compras.ProveedorFechaActual;
import sucursal.modelo.ofertas.IOferta;
import sucursal.modelo.ofertas.OfertaPuntos;
import sucursal.modelo.ofertas.ProveedorOfertas;
import sucursal.modelo.ofertas.descuentos.DescuentoPuntos;
import sucursal.modelo.productos.ListadoProductosMapaMemoria;
import sucursal.modelo.productos.Marca;
import sucursal.modelo.productos.Producto;
import sucursal.modelo.productos.ProveedorProductos;
import sucursal.modelo.productos.Rubro;

@RunWith(MockitoJUnitRunner.class)
public class PuntosTest {
	@Mock
	private ProveedorFechaActual mockFecha;
	
	@Mock
	private ProveedorProductos mockProductos;
	
	@Mock
	private ProveedorOfertas mockOfertas;
	
	@Mock
	private ProveedorPuntos mockPuntos;

	private Sucursal sucursal;
	private Producto cocaCola;
	
	@Before
	public void setup() {
		ListadoProductosMapaMemoria listadoProductos = new ListadoProductosMapaMemoria();
		Mockito.stub(mockProductos.proveer()).toReturn(listadoProductos);
		
		List<IOferta> ofertas = new ArrayList<IOferta>();
		Mockito.stub(mockOfertas.proveer()).toReturn(ofertas);
		
		Calendar date = Calendar.getInstance();
		date.set(2013, 05, 06);
		Mockito.stub(mockFecha.proveer()).toReturn(date.getTime());
		
		Rubro bebidas = new Rubro("Bebidas", "1");
		Marca coca = new Marca("Coca", "11");
		cocaCola = new Producto(bebidas, coca, "111", "CocaCola", "", 1);

		ListaPuntos puntos = new ListaPuntos();
		puntos.agregarPuntaje(cocaCola.getCodigo(), new Puntaje(2, 1));
		Mockito.stub(mockPuntos.proveer()).toReturn(puntos);
		
		Predicate<Compra> condicion = Predicates.alwaysTrue();
		Function<Compra, Integer> descuento = new DescuentoPuntos();
		ofertas.add(new OfertaPuntos(condicion, descuento));		

		sucursal = new Sucursal(mockOfertas, mockProductos, mockFecha, mockPuntos);
	}
	
	@Test
	public void compro10CocasObtengo5Puntos() {
		Caja caja = sucursal.habilitarCaja();
		caja.abrir();
		
		Compra compra = caja.iniciarCompra();
		compra.setCliente(new Cliente("asasd"));
		compra.agregarItem(cocaCola, 10);
		compra.setMedioPago(MedioPago.TARJETA_DEBITO);
		compra.confirmar();

		assertThat(compra.getCliente().getPuntos(), is(5));
	}
	
	@Test
	public void compro1CocasObtengo0Puntos() {
		Caja caja = sucursal.habilitarCaja();
		caja.abrir();
		
		Compra compra = caja.iniciarCompra();
		compra.setCliente(new Cliente("asasd"));
		compra.agregarItem(cocaCola, 1);
		compra.setMedioPago(MedioPago.TARJETA_DEBITO);
		compra.confirmar();

		assertThat(compra.getCliente().getPuntos(), is(0));
	}
}
