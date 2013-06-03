package sucursal.modelo.caja;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.exceptions.CajaNoAbiertaException;
import sucursal.modelo.exceptions.CajaYaAbiertaException;
import sucursal.modelo.exceptions.CompraEnProcesoException;
import sucursal.modelo.exceptions.CompraNoIniciadaException;
import sucursal.modelo.ofertas.ProveedorOfertas;
import sucursal.modelo.productos.ProveedorProductos;
import sucursal.utilities.Observador;

@RunWith(MockitoJUnitRunner.class)
public class CajaTest {
	private Caja subject;

	@Mock
	private Observador<Caja> mockObservador;

	@Mock
	private ProveedorOfertas mockProveedorOfertas;

	@Mock
	private ProveedorProductos mockProveedorProductos;

	@Before
	public void setup() {
		subject = new Caja(mockProveedorOfertas, mockProveedorProductos);
	}

	@Test
	public void nuevaCajaDeberiaEstarCerrada() {
		assertThat(subject.estaAbierta(), is(false));
	}

	@Test
	public void abrirCajaCerradaDeberiaAbrirCaja() {
		subject.abrir();

		assertThat(subject.estaAbierta(), is(true));
	}

	@Test(expected = CajaYaAbiertaException.class)
	public void abrirCajaYaAbiertaDeberiaFallar() {
		subject.abrir();
		subject.abrir();
	}

	@Test(expected = CajaYaAbiertaException.class)
	public void abrirCajaComprandoDeberiaFallar() {
		subject.abrir();
		subject.iniciarCompra();
		subject.abrir();
	}

	@Test
	public void cerrarCajaAbiertaDeberiaCerrarCaja() {
		subject.abrir();
		subject.cerrar();

		assertThat(subject.estaAbierta(), is(false));
	}

	@Test
	public void cerrarCajaComprandoDeberiaCerrarCaja() {
		subject.abrir();
		subject.iniciarCompra();
		subject.cerrar();

		assertThat(subject.estaAbierta(), is(false));
	}

	@Test
	public void cerrarCajaComprandoDeberiaTerminarCompra() {
		subject.abrir();
		subject.iniciarCompra();
		subject.cerrar();

		assertThat(subject.estaComprando(), is(false));
	}

	@Test
	public void cerrarCajaComprandoDeberiaCancelarCompra() {
		subject.abrir();
		Compra compraIniciada = subject.iniciarCompra();
		subject.cerrar();

		assertThat(compraIniciada.fueCancelada(), is(true));
	}

	@Test(expected = CajaNoAbiertaException.class)
	public void cerrarCajaCerradaDeberiaFallar() {
		subject.cerrar();
	}

	@Test
	public void iniciarCompraEnCajaAbiertaDeberiaIniciarCompra() {
		subject.abrir();
		subject.iniciarCompra();

		assertThat(subject.estaComprando(), is(true));
	}

	@Test(expected = CajaNoAbiertaException.class)
	public void iniciarCompraEnCajaCerradaDeberiaFallar() {
		subject.iniciarCompra();
	}

	@Test(expected = CompraEnProcesoException.class)
	public void iniciarCompraConCompraIniciadaDeberiaFallar() {
		subject.abrir();
		subject.iniciarCompra();
		subject.iniciarCompra();
	}

	@Test
	public void terminarCompraDeberiaTerminarCompra() {
		subject.abrir();
		subject.iniciarCompra();
		subject.terminarCompra();

		assertThat(subject.estaComprando(), is(false));
	}

	@Test(expected = CompraNoIniciadaException.class)
	public void terminarCompraConCajaAbiertaDeberiaFallar() {
		subject.abrir();
		subject.terminarCompra();
	}

	@Test(expected = CompraNoIniciadaException.class)
	public void terminarCompraConCajaCerradaDeberiaFallar() {
		subject.terminarCompra();
	}

	@Test
	public void abrirCajaDeberiaNotificarEventoDeCajaAbierta() {
		subject.getOnCajaAbierta().registrar(mockObservador);
		subject.abrir();

		Mockito.verify(mockObservador).notificar(subject);
	}

	@Test
	public void cerrarCajaDeberiaNotificarEventDeCajaCerrada() {
		subject.getOnCajaCerrada().registrar(mockObservador);
		subject.abrir();
		subject.cerrar();

		Mockito.verify(mockObservador).notificar(subject);
	}

	@Test
	public void iniciarCompraDeberiaNotificarEventoDeCompraIniciada() {
		subject.getOnCompraIniciada().registrar(mockObservador);
		subject.abrir();
		subject.iniciarCompra();

		Mockito.verify(mockObservador).notificar(subject);
	}
}