package sucursal.modelo;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import sucursal.utilities.Evento;

public class Compra {
	public boolean cancelada;
	private final Caja caja;
	private final Stack<LineProducto> items = new Stack<>();
	private final Stack<Descuento> descuentos = new Stack<>();

	private Evento<Compra> onItemsCambiados = new Evento<Compra>(this);

	public Compra(final Caja caja, final ProveedorOfertas proveedorOfertas,
			final ProveedorProductos proveedorProductos) {
		this.caja = caja;
	}

	public void agregarItem(LineProducto nuevoProducto) {
		items.push(nuevoProducto);
		onItemsCambiados.notificar();
	}
	
	public LineProducto getUltimoItemAgregado() {
		return items.peek();
	}

	public void quitarUltimoItemAgregado() {
		items.pop();
		onItemsCambiados.notificar();
	}

	public boolean tieneItems() {
		return !items.isEmpty();
	}

	public void cancelar() {
		cancelada = true;
		caja.terminarCompra();
	}

	public void confirmar() {
		caja.terminarCompra();
	}

	public boolean fueCancelada() {
		return cancelada;
	}

	public void agregarDescuento(final String descripcion, float valor) {
		descuentos.push(new Descuento(descripcion, valor));
	}

	public List<LineProducto> getItems() {
		return Collections.unmodifiableList(items);
	}

	public List<Descuento> getDescuentos() {
		return Collections.unmodifiableList(descuentos);
	}

	public Evento<Compra> getOnItemsCambiados() {
		return onItemsCambiados;
	}
}
