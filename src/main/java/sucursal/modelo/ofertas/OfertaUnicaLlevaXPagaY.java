package sucursal.modelo.ofertas;


import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.modelo.productos.Producto;

public class OfertaUnicaLlevaXPagaY extends Oferta{
	
	private final int aComprar;
	private final int aPagar;
	private final String productoOfertado;
	private float precioProducto;

	public OfertaUnicaLlevaXPagaY(String descripcion, int cantidadCompra, int cantidadAPagar, String nombreProducto) {
		super(descripcion);
		this.aComprar = cantidadCompra;
		this.aPagar = cantidadAPagar;
		this.productoOfertado = nombreProducto;
	}

	@Override
	protected Float obtenerDescuento(Compra compra) {
		int cantidadDeProducto = 0;
		for (ItemProducto item : compra.getItems()) {
			if(esItemDeProducto(item) & !productoTieneDescuento(item.getProducto()) & item.getCantidad()>=3){
				cantidadDeProducto += item.getCantidad();
				this.precioProducto = item.getProducto().getPrecioUnitario();
				item.getProducto().confirmarDescuento();
			}
		}
		int productosConOfertas = cantidadDeProducto - (cantidadDeProducto % this.aComprar);
		if(productosConOfertas == 0) return null;
		
		return (this.precioProducto * productosConOfertas * (this.aComprar - this.aPagar) / this.aComprar);
	}

	private boolean productoTieneDescuento(Producto producto) {
		return producto.hasDescuento();
	}

	private boolean esItemDeProducto(ItemProducto item) {
		return (item.getProducto().getNombre() == productoOfertado);
	}

}
