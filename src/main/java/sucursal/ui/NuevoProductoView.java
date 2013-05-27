package sucursal.ui;

import sucursal.utilities.Evento;
import sucursal.utilities.EventoParametrizado;

public interface NuevoProductoView {
	public static class NuevoProductoParams {
		private final String codigo;
		private final String cantidad;

		public NuevoProductoParams(String codigo, String cantidad) {
			this.codigo = codigo;
			this.cantidad = cantidad;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getCantidad() {
			return cantidad;
		}
	}
	
	void displayView();
	
	void hideView();

	EventoParametrizado<NuevoProductoView, NuevoProductoParams> getOnAceptar();

	Evento<NuevoProductoView> getOnCancelar();
}
