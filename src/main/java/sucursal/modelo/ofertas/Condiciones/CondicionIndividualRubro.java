/**
 * 
 */
package sucursal.modelo.ofertas.Condiciones;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.modelo.productos.Rubro;

/**
 * @author nacho
 *
 */
public class CondicionIndividualRubro implements CondicionIndividual {

	
	private final Rubro rubro;
	
	
	/**
	 * @see sucursal.modelo.ofertas.Condiciones.CondicionGlobal#aplicaEn(sucursal.modelo.compras.Compra)
	 */
	public CondicionIndividualRubro(final Rubro rubro) {
		this.rubro = rubro;
	}
	
	@Override
	public boolean aplicaEn(ItemProducto producto) {
		return producto.getProducto().getRubro().equals(rubro);
	}

}
