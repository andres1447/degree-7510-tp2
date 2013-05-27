package sucursal.modelo;

public interface EstadoCaja {
	boolean estaAbierta();

	boolean estaCerrada();

	boolean estaComprando();

	void checkPuedeAbrir();

	void checkPuedeCerrar();

	void checkPuedeIniciarCompra();

	void checkPuedeTerminarCompra();
}
