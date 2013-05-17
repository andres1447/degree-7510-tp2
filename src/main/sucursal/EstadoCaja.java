package sucursal;

public enum EstadoCaja {
	ABIERTA(1), CERRADA(0);
	
	private static int EST_ABIERTA = 1;
	private static int EST_CERRADA = 0;
	
	private int estado;
	
	private EstadoCaja(int est){
		estado = est;
	}
	
	public boolean estaAbierta() {
		return this.estado == EstadoCaja.EST_ABIERTA;
	}
	
	public void abrirCaja() throws CajaYaAbiertaException {
		if (estaAbierta()) {
			throw new CajaYaAbiertaException();
		}
		estado = EstadoCaja.EST_ABIERTA;
	}
	
	public void cerrarCaja() throws CajaNoInicializadaException {
		if (!estaAbierta()) {
			throw new CajaNoInicializadaException();
		}
		estado = EstadoCaja.EST_CERRADA;
	}
}
