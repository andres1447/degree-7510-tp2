package sucursal.modelo;

import java.util.Stack;

public class Compra {
	public boolean cancelada;
	Stack<LineProducto> items;

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

}
