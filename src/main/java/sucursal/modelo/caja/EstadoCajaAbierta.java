package sucursal.modelo.caja;

import sucursal.modelo.exceptions.CajaYaAbiertaException;
import sucursal.modelo.exceptions.CompraNoIniciadaException;

/**
 * {@link EstadoCaja} implementation which represents the "open" state in a
 * {@link Caja}.
 */
public class EstadoCajaAbierta implements EstadoCaja {
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
		return false;
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
		return;
	}

	@Override
	public void checkPuedeTerminarCompra() {
		throw new CompraNoIniciadaException();
	}
}
