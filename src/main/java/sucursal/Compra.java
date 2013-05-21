package sucursal;

import java.util.Stack;

public class Compra {

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

}
