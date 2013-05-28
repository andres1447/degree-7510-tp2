package sucursal.modelo.caja;

import sucursal.modelo.exceptions.CajaNoAbiertaException;
import sucursal.modelo.exceptions.CompraNoIniciadaException;

/**
 * {@link EstadoCaja} implementation which represents the "closed" state of a
 * {@link Caja}.
 */
public class EstadoCajaCerrada implements EstadoCaja {
	@Override
	public boolean estaAbierta() {
		return false;
	}

	@Override
	public boolean estaCerrada() {
		return true;
	}

	@Override
	public boolean estaComprando() {
		return false;
	}

	@Override
	public void checkPuedeAbrir() {
		return;
	}

	@Override
	public void checkPuedeCerrar() {
		throw new CajaNoAbiertaException();
	}

	@Override
	public void checkPuedeIniciarCompra() {
		throw new CajaNoAbiertaException();
	}

	@Override
	public void checkPuedeTerminarCompra() {
		throw new CompraNoIniciadaException();
	}

}
