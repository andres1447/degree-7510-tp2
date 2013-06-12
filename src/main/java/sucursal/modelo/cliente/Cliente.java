package sucursal.modelo.cliente;

public class Cliente {
	private String id;
	private int puntosTotales;
	private int deltaPuntos;
	
	public Cliente(String id) {
		this.id = id;
	}
	
	public void agregarPuntos(int puntos) {
		deltaPuntos += puntos;
	}
	
	public void removerPuntos(int puntos) {
		deltaPuntos -= puntos;
	}
	
	public int getPuntos() {
		return puntosTotales;
	}
	
	public String getId() {
		return id;
	}

	public void confirmar() {
		puntosTotales += deltaPuntos;
		deltaPuntos = 0;
	}
}
