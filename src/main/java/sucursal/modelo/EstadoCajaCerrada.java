package sucursal.modelo;

import sucursal.exceptions.CajaNoInicializadaException;
import sucursal.exceptions.CompraNoInicializadaException;

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
		throw new CajaNoInicializadaException();
	}

	@Override
	public void checkPuedeIniciarCompra() {
		throw new CajaNoInicializadaException();
	}

	@Override
	public void checkPuedeTerminarCompra() {
		throw new CompraNoInicializadaException();
	}

}
