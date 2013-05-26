package sucursal.modelo;

import sucursal.exceptions.CajaNoInicializadaException;
import sucursal.exceptions.CajaYaAbiertaException;

enum EstCaja {
	CERRADA(), ABIERTA();

}

public class EstadoCaja {

	private EstCaja estado;
	
	public EstadoCaja(){
		this.estado = EstCaja.CERRADA;
	}
	
	public boolean estaAbierta() {
		return this.estado == EstCaja.ABIERTA;
	}
	
	public void abrirCaja() throws CajaYaAbiertaException {
		if (estaAbierta()) {
			throw new CajaYaAbiertaException();
		}
		this.estado = EstCaja.ABIERTA;
	}
	
	public void cerrarCaja() throws CajaNoInicializadaException {
		if (!estaAbierta()) {
			throw new CajaNoInicializadaException();
		}
		this.estado = EstCaja.CERRADA;
	}
}
