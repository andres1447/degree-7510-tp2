package sucursal.modelo.caja;

import sucursal.modelo.exceptions.CajaYaAbiertaException;
import sucursal.modelo.exceptions.CompraEnProcesoException;

/**
 * {@link EstadoCaja} implementation which represents the "buying" state in a
 * {@link Caja}.
 */
public class EstadoCajaComprando implements EstadoCaja {

	@Override
	public boolean estaAbierta() {
		return true;
	}

	@Override
	public boolean estaCerrada() {
		return false;
	}

	@Override
	public boolean estaComprando() {
		return true;
	}

	@Override
	public void checkPuedeAbrir() {
		throw new CajaYaAbiertaException();
	}

	@Override
	public void checkPuedeCerrar() {
		return;
	}

	@Override
	public void checkPuedeIniciarCompra() {
		throw new CompraEnProcesoException();
	}

	@Override
	public void checkPuedeTerminarCompra() {
		return;
	}

}
