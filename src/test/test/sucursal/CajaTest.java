package test.sucursal;

import org.junit.*;

import sucursal.Caja;
import sucursal.Pago;
import sucursal.Producto;
import sucursal.exceptions.CompraNoInicializadaException;
import sucursal.exceptions.CajaNoInicializadaException;
import sucursal.exceptions.CajaYaAbiertaException;
import static org.junit.Assert.*;

/**
 * The class <code>CajaTest</code> contains tests for the class <code>{@link Caja}</code>.
 *
 * @author nacho
 * @version $Revision: 1.0 $
 */
public class CajaTest {
	private Caja miCaja;
	
	/**
	 * Run the Caja() constructor test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testCajaCreadaCerrada()
		throws Exception {
		assertFalse(miCaja.estaAbierta());
	}

	
	/**
	 * Run the void abrirCaja() method test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testAbrirCajaCerrada()
		throws Exception {
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
	public void testAbrirCajaYaAbierta()
		throws Exception {
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
	public void testAgregarProductosCajaCerrada()
		throws Exception {
		Producto nuevoProducto = new Producto();
		miCaja.agregarProductos(nuevoProducto);
	}

	/**
	 * Run the void agregarProductos(Producto) method test.
	 *
	 * @throws Exception
	 *
	 */
	@Test(expected = sucursal.exceptions.CompraNoInicializadaException.class)
	public void testAgregarProductosCompraNoIniciada()
		throws Exception {
		miCaja.abrirCaja();
		Producto nuevoProducto = new Producto();
		miCaja.agregarProductos(nuevoProducto);
	}

	/**
	 * Run the void cerrarCaja() method test.
	 *
	 * @throws Exception
	 */
	@Test(expected = sucursal.exceptions.CajaNoInicializadaException.class)
	public void testCerrarCajaNoAbierta()
		throws Exception {
		miCaja.cerrarCaja();
	}

	/**
	 * Run the void cerrarCaja() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testAperturaYCierreCaja()
		throws Exception {
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
	@Ignore
	@Test
	public void testConfirmarCompra_1()
		throws Exception {
		Caja fixture = new Caja();
		fixture.indicarMedioDePago(new Pago());

		fixture.confirmarCompra();

		// add additional test code here
	}

	/**
	 * Run the void indicarMedioDePago(Pago) method test.
	 *
	 * @throws Exception
	 *
	 */
	@Ignore
	@Test
	public void testIndicarMedioDePago_1()
		throws Exception {
		Caja fixture = new Caja();
		fixture.indicarMedioDePago(new Pago());
		Pago pago = new Pago();

		fixture.indicarMedioDePago(pago);

		// add additional test code here
	}

	/**
	 * Run the void iniciarCompra() method test.
	 *
	 * @throws Exception
	 *
	 */
	@Ignore
	@Test
	public void testIniciarCompra_1()
		throws Exception {
		Caja fixture = new Caja();
		fixture.indicarMedioDePago(new Pago());

		fixture.iniciarCompra();

		// add additional test code here
	}

	/**
	 * Run the void iniciarCompra() method test.
	 *
	 * @throws Exception
	 *
	 */
	@Ignore
	@Test
	public void testIniciarCompra_2()
		throws Exception {
		Caja fixture = new Caja();
		fixture.indicarMedioDePago(new Pago());

		fixture.iniciarCompra();

		// add additional test code here
	}

	/**
	 * Run the void visualizarDescuentosAplicados() method test.
	 *
	 * @throws Exception
	 *
	 */
	@Ignore
	@Test
	public void testVisualizarDescuentosAplicados_1()
		throws Exception {
		Caja fixture = new Caja();
		fixture.indicarMedioDePago(new Pago());

		fixture.visualizarDescuentosAplicados();

		// add additional test code here
	}

	/**
	 * Run the void visualizarTotal() method test.
	 *
	 * @throws Exception
	 *
	 */
	@Ignore
	@Test
	public void testVisualizarTotal_1()
		throws Exception {
		Caja fixture = new Caja();
		fixture.indicarMedioDePago(new Pago());

		fixture.visualizarTotal();

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 */
	@Before
	public void setUp()
		throws Exception {
		miCaja = new Caja();
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 */
	@After
	public void tearDown()
		throws Exception {
		miCaja = null;
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CajaTest.class);
	}
}