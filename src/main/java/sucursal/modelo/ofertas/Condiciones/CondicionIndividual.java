package sucursal.modelo.ofertas.Condiciones;

import sucursal.modelo.compras.ItemProducto;

public interface CondicionIndividual {

	public boolean aplicaEn(ItemProducto producto);
	
}
