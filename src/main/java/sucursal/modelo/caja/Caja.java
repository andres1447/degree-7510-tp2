package sucursal.modelo.caja;

import sucursal.modelo.ResumenVentas;
import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ProveedorFechaActual;
import sucursal.modelo.ofertas.ProveedorOfertas;
import sucursal.modelo.productos.ProveedorProductos;
import sucursal.modelo.puntos.ProveedorPuntos;
import sucursal.utilities.Evento;

/**
 * Domain class which represents a {@link Caja}. Manages the cash register state
 * and allows clients to create new {@link Compra} instances.
 */
public class Caja {
	private final ResumenVentas resumenVentas = new ResumenVentas();
	private final Evento<Caja> onCajaAbierta = new Evento<>(this);
	private final Evento<Caja> onCajaCerrada = new Evento<>(this);
	private final Evento<Caja> onCompraIniciada = new Evento<>(this);

	private final ProveedorOfertas proveedorOfertas;
	private final ProveedorProductos proveedorProductos;
	private final ProveedorFechaActual proveedorFechaActual;
	private final ProveedorPuntos proveedorPuntos;
	
	private EstadoCaja estado = new EstadoCajaCerrada();
	private Compra compraActual = null;

	private void procesarResumenVentas() {
		if (compraActual.fueCancelada()) {
			return;
		}
		resumenVentas.registrarCompra(compraActual);
	}

	public Caja(final ProveedorOfertas proveedorOfertas,
			final ProveedorProductos proveedorProductos,
			final ProveedorFechaActual proveedorFechaActual,
			final ProveedorPuntos proveedorPuntos) {
		this.proveedorOfertas = proveedorOfertas;
		this.proveedorProductos = proveedorProductos;
		this.proveedorFechaActual = proveedorFechaActual;
		this.proveedorPuntos = proveedorPuntos;
	}

	/**
	 * Opens the cash register, changing the {@link Caja} to "open" state, ready
	 * to create new {@link Compra} instances. You can only call this method
	 * when the {@link Caja} is "closed".
	 */
	public void abrir() {
		estado.checkPuedeAbrir();
		estado = new EstadoCajaAbierta();
		onCajaAbierta.notificar();
	}

	/**
	 * Closes the cash register, changing the {@link Caja} to "closed" state.
	 * You can only call this method while the {@link Caja} is "open" or
	 * "buying". If the {@link Caja} is in "buying" state, the associated
	 * {@link Compra} instance will be automatically cancelled.
	 */
	public void cerrar() {
		estado.checkPuedeCerrar();
		if (compraActual != null) {
			compraActual.cancelar();
		}
		estado = new EstadoCajaCerrada();
		onCajaCerrada.notificar();
	}

	/**
	 * Checks if the {@link Caja} is not in "closed" state, meaning it can be
	 * either "open" or "buying". Returns true if it is not in "closed" state,
	 * false otherwise.
	 */
	public boolean estaAbierta() {
		return estado.estaAbierta();
	}

	/**
	 * Prepares the cashier to register a new {@link Compra}. Changes the
	 * {@link Caja} to "buying" state. Creates and returns a new {@link Compra}
	 * instance which can be used to manage the bought items. Can only be called
	 * if the {@link Caja} is in "open" state.
	 */
	public Compra iniciarCompra() {
		estado.checkPuedeIniciarCompra();
		estado = new EstadoCajaComprando();
		compraActual = new Compra(this, proveedorOfertas, proveedorProductos,
				proveedorFechaActual, proveedorPuntos);
		onCompraIniciada.notificar();
		return compraActual;
	}

	/**
	 * Terminates the current {@link Compra} instance created by
	 * {@link Caja#iniciarCompra()}. Does not cancel or confirm the
	 * {@link Compra}, but changes the {@link Caja} back to "open" state.
	 */
	public void terminarCompra() {
		estado.checkPuedeTerminarCompra();
		procesarResumenVentas();
		estado = new EstadoCajaAbierta();
		compraActual = null;
	}

	/**
	 * Checks if the {@link Caja} is in "buying" state. Returns true if the
	 * {@link Caja} is in "buying" state, false otherwise.
	 */
	public boolean estaComprando() {
		return estado.estaComprando();
	}

	/**
	 * Obtains a sales summary for all sales done through {@link Caja}.
	 */
	public ResumenVentas getResumenVentas() {
		return resumenVentas;
	}

	/**
	 * Event which can be watched to get notified when the {@link Caja} changes
	 * to "open" state.
	 */
	public Evento<Caja> getOnCajaAbierta() {
		return onCajaAbierta;
	}

	/**
	 * Event which can be watched to get notified when the {@link Caja} changes
	 * to "closed" state.
	 */
	public Evento<Caja> getOnCajaCerrada() {
		return onCajaCerrada;
	}

	/**
	 * Event which can be watched to get notified when the {@link Caja} changes
	 * to "buying" state.
	 */
	public Evento<Caja> getOnCompraIniciada() {
		return onCompraIniciada;
	}
}
