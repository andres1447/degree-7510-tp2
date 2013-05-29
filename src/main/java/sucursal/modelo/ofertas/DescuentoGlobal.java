package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

public interface DescuentoGlobal {
	Float calcularPara(Compra compra);
}
