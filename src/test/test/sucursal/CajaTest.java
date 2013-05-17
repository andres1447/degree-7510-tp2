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
	/**
	 * Run the Caja() constructor test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testCajaCreadaCerrada()
		throws Exception {

		Caja miCaja = new Caja();
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
		Caja miCaja = new Caja();
		miCaja.abrirCaja();
		assertTrue(miCaja.estaAbierta());
	}

	/**
	 * Run the void abrirCaja() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 17/05/13 11:38
	 */
	@Test(expected = sucursal.exceptions.CajaYaAbiertaException.class)
	public void testAbrirCaja_2()
		throws Exception {
		Caja fixture = new Caja();
		fixture.indicarMedioDePago(new Pago());

		fixture.abrirCaja();

		// add additional test code here
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
		Caja miCaja = new Caja();
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
		Caja fixture = new Caja();
		fixture.abrirCaja();
		Producto nuevoProducto = new Producto();
		fixture.agregarProductos(nuevoProducto);
	}

	/**
	 * Run the void cerrarCaja() method test.
	 *
	 * @throws Exception
	 */
	@Test(expected = sucursal.exceptions.CajaNoInicializadaException.class)
	public void testCerrarCajaNoAbierta()
		throws Exception {
		Caja fixture = new Caja();
		fixture.cerrarCaja();
	}

	/**
	 * Run the void cerrarCaja() method test.
	 *
	 * @throws Exception
	 */
	@Test(expected = sucursal.exceptions.CajaNoInicializadaException.class)
	public void testCerrarCaja_2()
		throws Exception {
		Caja fixture = new Caja();
		fixture.indicarMedioDePago(new Pago());

		fixture.cerrarCaja();

		// add additional test code here
	}

	/**
	 * Run the void confirmarCompra() method test.
	 *
	 * @throws Exception
	 *
	 */
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
		// add additional set up code here
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
		// Add additional tear down code here
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