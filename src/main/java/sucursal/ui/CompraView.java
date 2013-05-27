package sucursal.ui;

import sucursal.modelo.Compra;
import sucursal.utilities.Evento;

public interface CompraView {

	void observar(Compra compra);

	void displayView();

	void hideView();

	Evento<CompraView> getOnConfirmarCompra();

	Evento<CompraView> getOnCancelarCompra();

	Evento<CompraView> getOnAgregarProducto();

	Evento<CompraView> getOnDeshacer();
}
