package sucursal.modelo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import sucursal.exceptions.CajaNoInicializadaException;
import sucursal.exceptions.CajaYaAbiertaException;
import sucursal.exceptions.CompraEnProcesoException;
import sucursal.modelo.eventos.Observador;

/**
 * Unit tests for the {@link Caja} class
 */
@RunWith(MockitoJUnitRunner.class)
public class CajaTest {
	private Caja subject;

	@Mock
	private Observador<Caja> mockObservador;

	@Before
	public void setup() {
		subject = new Caja();
	}

	@Test
	public void nuevaCajaDeberiaEstarCerrada() {
		assertThat(subject.estaAbierta(), is(false));
	}

	@Test
	public void abrirCajaCerradaDeberiaAbrirCaja() {
		subject.abrirCaja();

		assertThat(subject.estaAbierta(), is(true));
	}

	@Test(expected = CajaYaAbiertaException.class)
	public void abrirCajaYaAbiertaDeberiaFallar() {
		subject.abrirCaja();
		subject.abrirCaja();
	}

	@Test
	public void cerrarCajaAbiertaDeberiaCerrarCaja() {
		subject.abrirCaja();
		subject.cerrarCaja();

		assertThat(subject.estaAbierta(), is(false));
	}

	@Test(expected = CajaNoInicializadaException.class)
	public void cerrarCajaCerradaDeberiaFallar() {
		subject.cerrarCaja();
	}

	@Test
	public void iniciarCompraEnCajaAbiertaDeberiaIniciarCompra() {
		subject.abrirCaja();
		subject.iniciarCompra();

		assertThat(subject.estaCompraIniciada(), is(true));
	}

	@Test(expected = CajaNoInicializadaException.class)
	public void iniciarCompraEnCajaCerradaDeberiaFallar() {
		subject.iniciarCompra();
	}

	@Test(expected = CompraEnProcesoException.class)
	public void iniciarCompraConCompraIniciadaDeberiaFallar() {
		subject.abrirCaja();
		subject.iniciarCompra();
		subject.iniciarCompra();
	}

	@Test
	public void abrirCajaDeberiaNotificarEventoDeCajaAbierta() {
		subject.getOnCajaAbierta().registrar(mockObservador);
		subject.abrirCaja();

		Mockito.verify(mockObservador).notificar(subject);
	}

	@Test
	public void cerrarCajaDeberiaNotificarEventDeCajaCerrada() {
		subject.getOnCajaCerrada().registrar(mockObservador);
		subject.abrirCaja();
		subject.cerrarCaja();

		Mockito.verify(mockObservador).notificar(subject);
	}
}