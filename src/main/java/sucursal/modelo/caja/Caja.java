package sucursal.modelo.caja;

import java.util.HashMap;
import java.util.Map;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.modelo.ofertas.ProveedorOfertas;
import sucursal.modelo.productos.ProveedorProductos;
import sucursal.utilities.Evento;

/**
 * Domain class which represents a {@link Caja}. Manages the cash register state
 * and allows clients to create new {@link Compra} instances.
 */
public class Caja {
	private final Map<String, Integer> ventasPorProducto = new HashMap<String, Integer>();
	private final Evento<Caja> onCajaAbierta = new Evento<>(this);
	private final Evento<Caja> onCajaCerrada = new Evento<>(this);
	private final Evento<Caja> onCompraIniciada = new Evento<>(this);

	private final ProveedorOfertas proveedorOfertas;
	private final ProveedorProductos proveedorProductos;

	private EstadoCaja estado = new EstadoCajaCerrada();
	private Compra compraActual = null;

	public Caja(final ProveedorOfertas proveedorOfertas,
			final ProveedorProductos proveedorProductos) {
		this.proveedorOfertas = proveedorOfertas;
		this.proveedorProductos = proveedorProductos;
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
		compraActual = new Compra(this, proveedorOfertas, proveedorProductos);
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
		estado = new EstadoCajaAbierta();
		procesarVentasCompra();
		compraActual = null;
	}

	private void procesarVentasCompra() {
		if (compraActual.fueCancelada()) {
			return;
		}

		for (ItemProducto item : compraActual.getItems()) {
			String key = item.getProducto().getCodigo();
			if (ventasPorProducto.containsKey(key)) {
				Integer cantidad = ventasPorProducto.get(key);
				cantidad += item.getCantidad();
				ventasPorProducto.put(key, cantidad);
			} else {
				ventasPorProducto.put(key, item.getCantidad());
			}
		}
	}

	/**
	 * Checks if the {@link Caja} is in "buying" state. Returns true if the
	 * {@link Caja} is in "buying" state, false otherwise.
	 */
	public boolean estaComprando() {
		return estado.estaComprando();
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

	public Map<String, Integer> getResumenVentas() {
		return ventasPorProducto;
	}
}
