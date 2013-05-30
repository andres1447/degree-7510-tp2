package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

public class OfertaUnicaLlevaXPagaY extends Oferta {

	private final int aComprar;
	private final int aPagar;
	private final String productoOfertado;
	private float precioProducto;

	public OfertaUnicaLlevaXPagaY(String descripcion, int cantidadCompra,
			int cantidadAPagar, String producto) {
		super(descripcion);
		this.aComprar = cantidadCompra;
		this.aPagar = cantidadAPagar;
		this.productoOfertado = producto;
	}

	@Override
	protected Float obtenerDescuento(Compra compra) {
		int cantidadDeProducto = 0;
		for (ItemProducto item : compra.getItems()) {
			if (esItemDeProducto(item) & !productoTieneDescuento(item)
					& item.getCantidad() >= 3) {
				cantidadDeProducto += item.getCantidad();
				this.precioProducto = item.getProducto().getPrecioUnitario();
				item.aplicarDescuentoExclusivoA(1);
			}
		}
		int productosConOfertas = cantidadDeProducto
				- (cantidadDeProducto % this.aComprar);
		if (productosConOfertas == 0)
			return null;

		return (this.precioProducto * productosConOfertas
				* (this.aComprar - this.aPagar) / this.aComprar);
	}

	private boolean productoTieneDescuento(ItemProducto item) {
		return item.tieneRemanenteSinDescuentoExclusivo(1);
	}

	private boolean esItemDeProducto(ItemProducto item) {
		return item.getProducto().getCodigo().equals(productoOfertado);
	}
}
