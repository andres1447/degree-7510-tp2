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
import sucursal.modelo.ofertas.descuentos.DescuentoPuntosCompra;
import sucursal.modelo.productos.ListadoProductosMapaMemoria;
import sucursal.modelo.productos.Marca;
import sucursal.modelo.productos.Producto;
import sucursal.modelo.productos.ProveedorProductos;
import sucursal.modelo.productos.Rubro;

@RunWith(MockitoJUnitRunner.class)
public class PagoConPuntosTest {
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
		ofertas.add(new OfertaPuntos(Predicates.<Compra>alwaysTrue(), new DescuentoPuntos()));
		ofertas.add(new OfertaPuntos(Predicates.<Compra>alwaysTrue(), new DescuentoPuntosCompra(50)));
		
		Mockito.stub(mockOfertas.proveer()).toReturn(ofertas);
		
		Calendar date = Calendar.getInstance();
		date.set(2013, 05, 06);
		Mockito.stub(mockFecha.proveer()).toReturn(date.getTime());
		
		Rubro bebidas = new Rubro("Bebidas", "1");
		Marca coca = new Marca("Coca", "11");
		cocaCola = new Producto(bebidas, coca, "111", "CocaCola", "", 10);
		
		ListaPuntos puntos = new ListaPuntos();
		puntos.agregarPuntaje(cocaCola.getCodigo(), new Puntaje(1, 1));
		Mockito.stub(mockPuntos.proveer()).toReturn(puntos);
		
		sucursal = new Sucursal(mockOfertas, mockProductos, mockFecha, mockPuntos);
	}
	
	@Test
	public void compro10CocasObtengo10PuntosDescuento50PuntosPago50Pesos() {
		Caja caja = sucursal.habilitarCaja();
		caja.abrir();
		
		Compra compra = caja.iniciarCompra();
		
		Cliente cliente = new Cliente("asasd");
		cliente.agregarPuntos(50);
		cliente.confirmar();
		
		compra.setCliente(cliente);
		compra.agregarItem(cocaCola, 10);
		compra.setMedioPago(MedioPago.TARJETA_DEBITO);
		
		compra.descontarPuntos();
		compra.confirmar();

		assertThat(compra.getTotal(), is(50.0f));
		assertThat(compra.getCliente().getPuntos(), is(10));
	}
}
