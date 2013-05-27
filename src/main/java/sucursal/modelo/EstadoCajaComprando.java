package sucursal.modelo;

import sucursal.exceptions.CajaYaAbiertaException;
import sucursal.exceptions.CompraEnProcesoException;

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
