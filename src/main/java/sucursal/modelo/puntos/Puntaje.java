package sucursal.modelo.puntos;

import sucursal.modelo.compras.ItemProducto;

public class Puntaje {
	private int cantidad;
	private int puntos;
	
	public Puntaje(int cantidad, int puntos) {
		this.cantidad = cantidad;
		this.puntos = puntos;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	public int getPuntaje(ItemProducto item) {
		return (item.getCantidad() * puntos)/cantidad;
	}
}
