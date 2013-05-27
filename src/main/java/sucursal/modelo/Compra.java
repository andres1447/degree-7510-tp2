package sucursal.modelo;

import java.util.Stack;

public class Compra {
	public boolean cancelada;
	private Stack<LineProducto> items;
	private Stack<Descuento> descuentos;

	public Compra() {
		items = new Stack<LineProducto>();
	}

	public void agregarProducto(LineProducto nuevoProducto) {
		items.push(nuevoProducto);
	}

	public void eliminarUltimoProducto() {
		items.pop();
	}

	public void cancelar() {
		cancelada = true;
	}

	public boolean estaCancelada() {
		return cancelada;
	}

	public void agregarDescuento(final String descripcion, float valor) {
		descuentos.push(new Descuento(descripcion, valor));
	}

}
