package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

public interface IOferta {
	public void aplicarSiCorresponde(final Compra compra);
}
