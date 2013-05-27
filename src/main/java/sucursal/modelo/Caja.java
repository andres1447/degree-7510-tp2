package sucursal.modelo;

import java.util.ArrayList;
import java.util.List;

import sucursal.exceptions.CajaNoInicializadaException;
import sucursal.exceptions.CajaYaAbiertaException;
import sucursal.exceptions.CompraEnProcesoException;
import sucursal.exceptions.CompraNoInicializadaException;
import sucursal.modelo.eventos.Evento;

public class Caja {
	/**
	 * Evento observable que se dispara cuando el estado pasa a abierta
	 */
	private final Evento<Caja> onCajaAbierta = new Evento<>(this);

	/**
	 * Evento observable que se dispara cuando el estado pasa a cerrada
	 */
	private final Evento<Caja> onCajaCerrada = new Evento<>(this);

	// TODO: Eliminar compras, mover listado de compras realizadas a clase historial
	private List<Compra> compras;
	
	// TODO: Cambiar estado de caja a jerarquía para eliminar los chequeos
	private EstadoCaja estado;
	
	private Compra compraActual;

	public Caja() {
		compras = new ArrayList<Compra>();
		estado = new EstadoCaja();
		compraActual = null;
	}

	public void abrirCaja() {
		estado.abrirCaja();

		onCajaAbierta.notificar();
	}

	public void cerrarCaja() {
		estado.cerrarCaja();

		onCajaCerrada.notificar();
	}

	public boolean estaAbierta() {
		return estado.estaAbierta();
	}

	public void iniciarCompra() {
		if (!estaAbierta())
			throw new CajaNoInicializadaException();
		if (compraActual != null) {
			throw new CompraEnProcesoException();
		}
		compraActual = new Compra();
	}
	
	public boolean estaCompraIniciada() {
		return compraActual != null;
	}

	// TODO: Mover estos métodos a la compra
	public void agregarProductos(LineProducto nuevoProducto) {
		if (!estaAbierta())
			throw new CajaNoInicializadaException();
		if (compraActual == null) {
			throw new CompraNoInicializadaException();
		}
		compraActual.agregarProducto(nuevoProducto);
	}

	// TODO: Mover estos métodos a la compra
	public void eliminarUltimaEntradaDeCompra() {
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

	// TODO: Mover estos métodos a la compra
	public void confirmarCompra() {
		if (compraActual == null)
			throw new CompraNoInicializadaException();
		aplicarDescuentosItems();
		compras.add(compraActual);
		compraActual = null;
	}

	private void aplicarDescuentosItems() {

	}
	
	// TODO: Mover estos métodos a la compra
	public void cancelarCompra() {
		if (compraActual == null)
			throw new CompraNoInicializadaException();
		compraActual = null;

	}

	public Evento<Caja> getOnCajaAbierta() {
		return onCajaAbierta;
	}

	public Evento<Caja> getOnCajaCerrada() {
		return onCajaCerrada;
	}

}
