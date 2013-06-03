package sucursal.modelo.ofertas.descuentos;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

import com.google.common.base.Function;

public class DescuentoEnSegundoProducto implements Function<Compra, Float> {
	private String codigoProductoCondicion;
	private String codigoProductoDescuento;
	private int porcentaje;
	private float precioDescuento;

	public DescuentoEnSegundoProducto(String codigoProducto,
			String codigoDescuento, int porcentaje) {
		this.codigoProductoCondicion = codigoProducto;
		this.codigoProductoDescuento = codigoDescuento;
		this.porcentaje = porcentaje;
		this.precioDescuento = 0;
	}

	@Override
	public Float apply(Compra input) {
		int cantidadProductoCondicion = 0;
		int cantidadProductoDescuento = 0;
		for (ItemProducto item : input.getItems()) {
			cantidadProductoCondicion += sumarCantidadProductoCondicion(item);
			cantidadProductoDescuento += getCantidadYPrecioProductoDescuento(item);
		}
		// aplico descuento a todos los pares de Producto1-Producto2
		if (cantidadProductoCondicion <= cantidadProductoDescuento) {
			return cantidadProductoCondicion * this.precioDescuento
					* this.porcentaje / 100.0f;
		} else
			return cantidadProductoDescuento * this.precioDescuento
					* this.porcentaje / 100.0f;
	}

	private int sumarCantidadProductoCondicion(ItemProducto item) {
		if (item.getProducto().getCodigo().equals(this.codigoProductoCondicion)) {
			return item.getCantidad();
		}
		return 0;
	}

	private int getCantidadYPrecioProductoDescuento(ItemProducto item) {
		if (item.getProducto().getCodigo().equals(this.codigoProductoDescuento)) {
			this.precioDescuento = item.getProducto().getPrecioUnitario();
			return item.getCantidad();
		}
		return 0;
	}

}
