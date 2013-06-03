package sucursal.modelo.compras;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import sucursal.modelo.caja.Caja;
import sucursal.modelo.ofertas.Oferta;
import sucursal.modelo.ofertas.ProveedorOfertas;
import sucursal.modelo.productos.ListadoProductos;
import sucursal.modelo.productos.Producto;
import sucursal.modelo.productos.ProveedorProductos;
import sucursal.utilities.Evento;

/**
 * Represents the buying session of a customer in a {@link Caja}. Manages the
 * available {@link Producto} and {@link Oferta} instances for the session, the
 * bought {@link ItemProducto} instances and applies the corresponding
 * {@link Oferta} instances.
 */
public class Compra {
	private Evento<Compra> onItemsCambiados = new Evento<Compra>(this);
	private Evento<Compra> onCompraConfirmada = new Evento<Compra>(this);

	private boolean cancelada;
	private boolean esJubilado;
	private MedioPago medioPago;
	private final Date fechaCreacion;
	private final Caja caja;
	private final Stack<ItemProducto> items = new Stack<>();
	private final Stack<ItemDescuento> descuentos = new Stack<>();
	private final ListadoProductos productos;
	private final List<Oferta> ofertas;
	private String codigoCupon;

	public Compra(final Caja caja, final ProveedorOfertas proveedorOfertas,
			final ProveedorProductos proveedorProductos) {
		this.caja = caja;
		this.ofertas = proveedorOfertas.proveer();
		this.productos = proveedorProductos.proveer();
		this.fechaCreacion = new Date();
		this.medioPago = MedioPago.EFECTIVO;
		this.esJubilado = false;
	}

	/**
	 * Adds a new {@link ItemProducto} to the buying session.
	 */
	public void agregarItem(final Producto producto, final int cantidad) {
		ItemProducto existente = null;
		for (ItemProducto item : items) {
			if (item.getProducto().equals(producto)) {
				existente = item;
				break;
			}
		}
		if (existente == null) {
			items.add(new ItemProducto(producto, cantidad));
		} else {
			existente.incrementarCantidad(cantidad);
		}
		onItemsCambiados.notificar();
	}

	/**
	 * Obtains the last added {@link ItemProducto} in the buying session.
	 */
	public ItemProducto getUltimoItemAgregado() {
		return items.peek();
	}

	/**
	 * Checks if there is any {@link ItemProducto} instance in the buying
	 * session, returning true if there is.
	 */
	public boolean tieneItems() {
		return !items.isEmpty();
	}

	/**
	 * Cancels the buying session, marking it as cancelled and asking the parent
	 * {@link Caja} to switch back to "open" state.
	 */
	public void cancelar() {
		cancelada = true;
		caja.terminarCompra();
	}

	/**
	 * Confirms the buying session, marking it as completed and asking the
	 * parent {@link Caja} to switch back to "open" state.
	 */
	public void confirmar() {
		for (Oferta oferta : ofertas) {
			oferta.aplicarSiCorresponde(this);
		}
		onCompraConfirmada.notificar();
		caja.terminarCompra();
	}

	/**
	 * Checks if the buying session was cancelled, returning true if it was and
	 * false otherwise.
	 */
	public boolean fueCancelada() {
		return cancelada;
	}

	/**
	 * Obtains a flag indicating wether the {@link Compra} was requested for a
	 * elderly customer.
	 */
	public boolean esJubilado() {
		return esJubilado;
	}

	/**
	 * Adds an {@link ItemDescuento} to the buying session with a given
	 * description and value.
	 */
	public void agregarDescuento(final String descripcion, float valor) {
		descuentos.push(new ItemDescuento(descripcion, valor));
	}

	/**
	 * Obtains a list of the {@link ItemProducto} instances added to this buying
	 * session.
	 */
	public List<ItemProducto> getItems() {
		return Collections.unmodifiableList(items);
	}

	/**
	 * Obtains a list of the {@link ItemDescuento} instances added to this
	 * buying session.
	 */
	public List<ItemDescuento> getDescuentos() {
		return Collections.unmodifiableList(descuentos);
	}

	/**
	 * Event which can be watched to get notified when the list of
	 * {@link ItemProducto} of this buying session changes, either because an
	 * {@link ItemProducto} instance was added to the list or because one of the
	 * previously added {@link ItemProducto} instances was removed.
	 */
	public Evento<Compra> getOnItemsCambiados() {
		return onItemsCambiados;
	}

	/**
	 * Event which can be watched to get notified when the buying session has
	 * been confirmed and offers have been applied.
	 */
	public Evento<Compra> getOnCompraConfirmada() {
		return onCompraConfirmada;
	}

	/**
	 * Obtains the {@link ListadoProductos} applicable to this buying session.
	 */
	public ListadoProductos getListadoProductos() {
		return productos;
	}

	/**
	 * Obtains the net total amount that the customer should be charged for this
	 * {@link Compra}.
	 */
	public float getTotal() {
		float resultado = getTotalBruto();
		for (ItemDescuento item : getDescuentos()) {
			resultado -= item.getValor();
		}
		return resultado < 0 ? 0.0f : resultado;
	}

	/**
	 * Obtains the total amount, without discounts, that the customer should be
	 * charged for this {@link Compra}.
	 */
	public float getTotalBruto() {
		float resultado = 0.0f;
		for (ItemProducto item : getItems()) {
			resultado += item.getTotal();
		}
		return resultado;
	}

	/**
	 * Obtains the payment method.
	 */
	public MedioPago getMedioPago() {
		return medioPago;
	}

	/**
	 * Sets the payment method to a given one.
	 */
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	/**
	 * Obtains the date on which the {@link Compra} was started.
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Sets a flag indicating wether this {@link Compra} is for an elderly
	 * customer.
	 */
	public void setJubilado(boolean jubilado) {
		this.esJubilado = jubilado;
	}

	/**
	 * Sets the coupon code to apply to this {@link Compra}.
	 */
	public void setCodigoCupon(final String text) {
		codigoCupon = text;
	}

	/**
	 * Obtains the coupon code applied to this {@link Compra}.
	 */
	public String getCodigoCupon() {
		return codigoCupon;
	}
}
