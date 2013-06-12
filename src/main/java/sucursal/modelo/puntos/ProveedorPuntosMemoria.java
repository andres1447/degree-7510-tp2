package sucursal.modelo.puntos;

public class ProveedorPuntosMemoria implements ProveedorPuntos {

	@Override
	public ListaPuntos proveer() {
		ListaPuntos lista = new ListaPuntos();
		lista.agregarPuntaje("11-111-1111", new Puntaje(10, 1));
		lista.agregarPuntaje("11-111-1112", new Puntaje(1, 100));
		return lista;
	}

}
