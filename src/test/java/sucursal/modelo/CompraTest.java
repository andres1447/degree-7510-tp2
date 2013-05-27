package sucursal.modelo;

import org.junit.Before;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import sucursal.utilities.Observador;

@RunWith(MockitoJUnitRunner.class)
public class CompraTest {
	private Compra subject;

	@Mock
	private Observador<Compra> mockObservador;

	@Mock
	private Caja mockCaja;

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

		subject.agregarItem(new LineProducto(new Producto(), 2));

		Mockito.verify(mockObservador).notificar(subject);

	}
	
	@Test
	public void deshacerUltimoItemAgregadoDeberiaNotificarItemsCambiados() {
		subject.agregarItem(new LineProducto(new Producto(), 2));
		subject.getOnItemsCambiados().registrar(mockObservador);
		
		subject.quitarUltimoItemAgregado();
		
		Mockito.verify(mockObservador).notificar(subject);
	}
	
	@Test
	public void tieneItemsDevuelveTrueCuandoHayItems() {
		subject.agregarItem(new LineProducto(new Producto(), 1));
		
		assertThat(subject.tieneItems(), is(true));
	}
	
	@Test
	public void tieneItemsDevuelveFalsoCuandoNoHayItems() {
		assertThat(subject.tieneItems(), is(false));
	}
}
