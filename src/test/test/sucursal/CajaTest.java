package test.sucursal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sucursal.Caja;
import sucursal.LineProducto;
import sucursal.Producto;
import sucursal.Sucursal;

/**
 * The class <code>CajaTest</code> contains tests for the class
 * <code>{@link Caja}</code>.
 * 
 * @author nacho
 * @version $Revision: 1.0 $
 */
public class CajaTest {
	/**
	 * Run the Caja() constructor test.
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testCajaCreadaCerrada() throws Exception {
		Caja miCaja = new Caja(new Sucursal());
		assertFalse(miCaja.estaAbierta());
	}

	/**
	 * Run the void abrirCaja() method test.
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testAbrirCajaCerrada() throws Exception {
		Caja miCaja = new Caja(new Sucursal());
		miCaja.abrirCaja();
		assertTrue(miCaja.estaAbierta());
	}

	/**
	 * Run the void abrirCaja() method test.
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testReabrirCajaCerrada() throws Exception {
		Caja miCaja = new Caja(new Sucursal());
		miCaja.abrirCaja();
		assertTrue(miCaja.estaAbierta());
		miCaja.cerrarCaja();
		assertFalse(miCaja.estaAbierta());
		miCaja.abrirCaja();
		assertTrue(miCaja.estaAbierta());
	}

	/**
	 * Run the void abrirCaja() method test.
	 * 
	 * @throws Exception
	 * 
	 */
	@Test(expected = sucursal.exceptions.CajaYaAbiertaException.class)
	public void testAbrirCajaYaAbierta() throws Exception {
		Caja miCaja = new Caja(new Sucursal());
		miCaja.abrirCaja();
		miCaja.abrirCaja();
	}

	/**
	 * Run the void agregarProductos(Producto) method test.
	 * 
	 * @throws Exception
	 * 
	 */
	@Test(expected = sucursal.exceptions.CajaNoInicializadaException.class)
	public void testAgregarProductosCajaCerrada() throws Exception {
		Caja miCaja = new Caja(new Sucursal());
		LineProducto nuevoProducto = new LineProducto(new Producto(), 1);
		miCaja.agregarProductos(nuevoProducto);
	}

	/**
	 * Run the void agregarProductos(Producto) method test.
	 * 
	 * @throws Exception
	 * 
	 */
	@Test(expected = sucursal.exceptions.CompraNoInicializadaException.class)
	public void testAgregarProductosCompraNoIniciada() throws Exception {
		Caja miCaja = new Caja(new Sucursal());
		miCaja.abrirCaja();
		LineProducto nuevoProducto = new LineProducto(new Producto(), 1);
		miCaja.agregarProductos(nuevoProducto);
	}

	/**
	 * Run the void cerrarCaja() method test.
	 * 
	 * @throws Exception
	 */
	@Test(expected = sucursal.exceptions.CajaNoInicializadaException.class)
	public void testCerrarCajaNoAbierta() throws Exception {
		Caja miCaja = new Caja(new Sucursal());
		miCaja.cerrarCaja();
	}

	/**
	 * Run the void cerrarCaja() method test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAperturaYCierreCaja() throws Exception {
		Caja miCaja = new Caja(new Sucursal());
		miCaja.abrirCaja();
		assertTrue(miCaja.estaAbierta());
		miCaja.cerrarCaja();
		assertTrue(!miCaja.estaAbierta());

		// add additional test code here
	}

	/**
	 * Run the void confirmarCompra() method test.
	 * 
	 * @throws Exception
	 * 
	 */
	/*
	 * @Ignore
	 * 
	 * @Test public void testConfirmarCompra_1() throws Exception { Caja fixture
	 * = new Caja(); fixture.indicarMedioDePago(new Pago());
	 * 
	 * fixture.confirmarCompra();
	 * 
	 * // add additional test code here }
	 *//**
	 * Run the void indicarMedioDePago(Pago) method test.
	 * 
	 * @throws Exception
	 * 
	 */
	/*
	 * @Ignore
	 * 
	 * @Test public void testIndicarMedioDePago_1() throws Exception { Caja
	 * fixture = new Caja(); fixture.indicarMedioDePago(new Pago()); Pago pago =
	 * new Pago();
	 * 
	 * fixture.indicarMedioDePago(pago);
	 * 
	 * // add additional test code here }
	 *//**
	 * Run the void iniciarCompra() method test.
	 * 
	 * @throws Exception
	 * 
	 */
	/*
	 * @Ignore
	 * 
	 * @Test public void testIniciarCompra_1() throws Exception { Caja fixture =
	 * new Caja(); fixture.indicarMedioDePago(new Pago());
	 * 
	 * fixture.iniciarCompra();
	 * 
	 * // add additional test code here }
	 *//**
	 * Run the void iniciarCompra() method test.
	 * 
	 * @throws Exception
	 * 
	 */
	/*
	 * @Ignore
	 * 
	 * @Test public void testIniciarCompra_2() throws Exception { Caja fixture =
	 * new Caja(); fixture.indicarMedioDePago(new Pago());
	 * 
	 * fixture.iniciarCompra();
	 * 
	 * // add additional test code here }
	 *//**
	 * Run the void visualizarDescuentosAplicados() method test.
	 * 
	 * @throws Exception
	 * 
	 */
	/*
	 * @Ignore
	 * 
	 * @Test public void testVisualizarDescuentosAplicados_1() throws Exception
	 * { Caja fixture = new Caja(); fixture.indicarMedioDePago(new Pago());
	 * 
	 * fixture.visualizarDescuentosAplicados();
	 * 
	 * // add additional test code here }
	 *//**
	 * Run the void visualizarTotal() method test.
	 * 
	 * @throws Exception
	 * 
	 */
	/*
	 * @Ignore
	 * 
	 * @Test public void testVisualizarTotal_1() throws Exception { Caja fixture
	 * = new Caja(); fixture.indicarMedioDePago(new Pago());
	 * 
	 * fixture.visualizarTotal();
	 * 
	 * // add additional test code here }
	 *//**
	 * Perform pre-test initialization.
	 * 
	 * @throws Exception
	 *             if the initialization fails for some reason
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Perform post-test clean-up.
	 * 
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * Launch the test.
	 * 
	 * @param args
	 *            the command line arguments
	 * 
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CajaTest.class);
	}
}