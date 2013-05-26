package sucursal.modelo;

import java.util.ArrayList;
import java.util.List;

import sucursal.exceptions.CajaNoInicializadaException;
import sucursal.exceptions.CajaYaAbiertaException;
import sucursal.exceptions.CompraEnProcesoException;
import sucursal.exceptions.CompraNoInicializadaException;

public class Caja {
	/**
	 * Evento observable que se dispara cuando el estado pasa a abierta
	 */
	private final EventoObservable<Caja, Boolean> onCajaAbierta = new EventoObservable<>(
			this);

	/**
	 * Evento observable que se dispara cuando el estado pasa a cerrada
	 */
	private final EventoObservable<Caja, Boolean> onCajaCerrada = new EventoObservable<>(
			this);

	private List<Compra> compras;
	private EstadoCaja estado;
	private Compra compraActual;

	public Caja() {
		compras = new ArrayList<Compra>();
		estado = new EstadoCaja();
		compraActual = null;
	}

	public void abrirCaja() throws CajaYaAbiertaException {
		estado.abrirCaja();

		onCajaAbierta.notificar(true);
	}

	public void cerrarCaja() throws CajaNoInicializadaException {
		estado.cerrarCaja();

		onCajaCerrada.notificar(true);
	}

	public boolean estaAbierta() {
		return estado.estaAbierta();
	}

	public void iniciarCompra() throws CompraEnProcesoException,
			CajaNoInicializadaException {
		if (!estaAbierta())
			throw new CajaNoInicializadaException();
		if (compraActual != null) {
			throw new CompraEnProcesoException();
		}
		compraActual = new Compra();
	}

	public void agregarProductos(LineProducto nuevoProducto)
			throws CompraNoInicializadaException, CajaNoInicializadaException {
		if (!estaAbierta())
			throw new CajaNoInicializadaException();
		if (compraActual == null) {
			throw new CompraNoInicializadaException();
		}
		compraActual.agregarProducto(nuevoProducto);
	}

	public void eliminarUltimaEntradaDeCompra()
			throws CompraNoInicializadaException, CajaNoInicializadaException {
		if (!estaAbierta())
			throw new CajaNoInicializadaException();
		if (compraActual == null) {
			throw new CompraNoInicializadaException();
		}
		compraActual.eliminarUltimoProducto();
	}

	public void visualizarTotal() {

	}

	public void visualizarDescuentosAplicados() {

	}

	public void indicarMedioDePago(Pago pago) {
	}

	public void confirmarCompra() throws CompraNoInicializadaException {
		if (compraActual == null)
			throw new CompraNoInicializadaException();
		aplicarDescuentosItems();
		compras.add(compraActual);
		compraActual = null;
	}

	private void aplicarDescuentosItems() {

	}

	public void cancelarCompra() throws CompraNoInicializadaException {
		if (compraActual == null)
			throw new CompraNoInicializadaException();
		compraActual = null;

	}

	public EventoObservable<Caja, Boolean> getOnCajaAbierta() {
		return onCajaAbierta;
	}

	public EventoObservable<Caja, Boolean> getOnCajaCerrada() {
		return onCajaCerrada;
	}

}
