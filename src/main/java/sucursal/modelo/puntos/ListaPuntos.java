package sucursal.modelo.puntos;

import java.util.HashMap;

import sucursal.modelo.compras.ItemProducto;

public class ListaPuntos {
	private HashMap<String, Puntaje> puntajes = new HashMap<String, Puntaje>();
	
	public void agregarPuntaje(String codigoProducto, Puntaje puntaje) {
		puntajes.put(codigoProducto, puntaje);
	}
	
	public int getPuntaje(ItemProducto item) {
		if (puntajes.get(item.getCodigoProducto()) != null) {
			return puntajes.get(item.getCodigoProducto()).getPuntaje(item);
		}
		return 0;
	}

	public boolean contiene(String codigoProducto) {
		return puntajes.containsKey(codigoProducto);
	}
}
