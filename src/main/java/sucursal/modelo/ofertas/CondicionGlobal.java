package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

public interface CondicionGlobal {

	boolean aplicaEn(Compra compra);

}
