package sucursal;

import sucursal.exceptions.CajaNoInicializadaException;
import sucursal.exceptions.CajaYaAbiertaException;

public enum EstadoCaja {

	CERRADA(0), ABIERTA(1);

	private static int EST_ABIERTA = 1;
	private static int EST_CERRADA = 0;	
	
	private int estado;
	
	private EstadoCaja(int est){
		this.estado = est;
	}
	
	public boolean estaAbierta() {
		return this.estado == EstadoCaja.EST_ABIERTA;
	}
	
	public void abrirCaja() throws CajaYaAbiertaException {
		if (estaAbierta()) {
			throw new CajaYaAbiertaException();
		}
		this.estado = EstadoCaja.EST_ABIERTA;
	}
	
	public void cerrarCaja() throws CajaNoInicializadaException {
		if (!estaAbierta()) {
			throw new CajaNoInicializadaException();
		}
		this.estado = EstadoCaja.EST_CERRADA;
	}
}
