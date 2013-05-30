package sucursal.modelo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import sucursal.modelo.caja.Caja;
import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.modelo.exceptions.ListaCompraVaciaException;
import sucursal.modelo.ofertas.ProveedorOfertas;
import sucursal.modelo.productos.Marca;
import sucursal.modelo.productos.Producto;
import sucursal.modelo.productos.ProveedorProductos;
import sucursal.modelo.productos.Rubro;
import sucursal.utilities.Observador;

@RunWith(MockitoJUnitRunner.class)
public class CompraTest {
	private Compra subject;

	@Mock
	private Observador<Compra> mockObservador;

	@Mock
	private Caja mockCaja;

	@Mock
	private ItemProducto mockLineProducto;

	@Mock
	private ProveedorOfertas mockProveedorOfertas;

	@Mock
	private ProveedorProductos mockProveedorProductos;

	@Before
	public void setup() {
		subject = new Compra(mockCaja, mockProveedorOfertas,
				mockProveedorProductos);
	}

	@Test
	public void agregarItemDeberiaNotificarItemsCambiados() {
		subject.getOnItemsCambiados().registrar(mockObservador);
		ItemProducto myMockLineProducto = new ItemProducto(new Producto(new Rubro("rubro"), new Marca("marca"), "producto", "description", 10, "11-111-1111"), 2);
				
		subject.agregarItem(myMockLineProducto);

		Mockito.verify(mockObservador).notificar(subject);

	}

	@Test
	public void deshacerUltimoItemAgregadoDeberiaNotificarItemsCambiados() {
		ItemProducto myMockLineProducto = new ItemProducto(new Producto(new Rubro("rubro"), new Marca("marca"), "producto", "description", 10, "11-111-1111"), 2);
		
		subject.agregarItem(myMockLineProducto);
		subject.getOnItemsCambiados().registrar(mockObservador);

		subject.quitarUltimoItemAgregado();

		Mockito.verify(mockObservador).notificar(subject);
	}

	@Test
	public void tieneItemsDevuelveTrueCuandoHayItems() {
		ItemProducto myMockLineProducto = new ItemProducto(new Producto(new Rubro("rubro"), new Marca("marca"), "producto", "description", 10, "11-111-1111"), 2);
		
		subject.agregarItem(myMockLineProducto);

		assertThat(subject.tieneItems(), is(true));
	}

	@Test
	public void tieneItemsDevuelveFalsoCuandoNoHayItems() {
		assertThat(subject.tieneItems(), is(false));
	}
	
	@Test
	public void tieneItemsDevuelveTrueCuandoNoDeshagoTodosItems() {
		ItemProducto myMockLineProducto = new ItemProducto(new Producto(new Rubro("rubro"), new Marca("marca"), "producto", "description", 10, "11-111-1111"), 2);
		ItemProducto myMockLineProducto2 = new ItemProducto(new Producto(new Rubro("rubro"), new Marca("marca2"), "producto", "description", 10, "12-111-1111"), 2);
		
		subject.agregarItem(myMockLineProducto);
		subject.agregarItem(myMockLineProducto2);

		subject.quitarUltimoItemAgregado();

		assertThat(subject.tieneItems(), is(true));
	}
	
	@Test
	public void tieneItemsDevuelveFalsoCuandoLimpioLineItems() {
		ItemProducto myMockLineProducto = new ItemProducto(new Producto(new Rubro("rubro"), new Marca("marca"), "producto", "description", 10, "11-111-1111"), 2);
		ItemProducto myMockLineProducto2 = new ItemProducto(new Producto(new Rubro("rubro"), new Marca("marca2"), "producto", "description", 10, "12-111-1111"), 2);
		ItemProducto myMockLineProducto3= new ItemProducto(new Producto(new Rubro("rubro"), new Marca("marca3"), "producto", "description", 10, "13-111-1111"), 2);
		
		subject.agregarItem(myMockLineProducto);
		subject.agregarItem(myMockLineProducto2);
		subject.agregarItem(myMockLineProducto3);

		subject.quitarUltimoItemAgregado();
		subject.quitarUltimoItemAgregado();
		subject.quitarUltimoItemAgregado();

		assertThat(subject.tieneItems(), is(false));
	}
	
	@Test(expected = ListaCompraVaciaException.class)
	public void deshacerItemInexistenteDeberiaLevantarErrorDeLineItemVacio() {		
		subject.quitarUltimoItemAgregado();
	}
	
	@Test
	public void getUltimoItemDeberiaDevolverUltimoItemAgregado() {
		ItemProducto primerMockLineProducto = new ItemProducto(new Producto(new Rubro("rubro"), new Marca("marca1"), "producto", "description", 10, "12-111-1111"), 2);
		
		ItemProducto segundoMockLineProducto = new ItemProducto(new Producto(new Rubro("rubro"), new Marca("marca"), "producto", "description", 10, "11-111-1111"), 2);
		
		subject.agregarItem(primerMockLineProducto);
		subject.agregarItem(segundoMockLineProducto);

		assertThat(subject.getUltimoItemAgregado().getProducto().getNombre(), is(segundoMockLineProducto.getProducto().getNombre()) );
	}
	
}
