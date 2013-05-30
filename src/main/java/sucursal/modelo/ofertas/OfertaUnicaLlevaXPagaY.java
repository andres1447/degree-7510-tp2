package sucursal.modelo.ofertas;

import java.util.ArrayList;
import java.util.List;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.modelo.productos.Producto;

public class OfertaUnicaLlevaXPagaY extends Oferta{
	
	private final int aComprar;
	private final int aPagar;
	private final String productoOfertado;
	private List<Producto> productosDeOferta;
	private float precioProducto;

	public OfertaUnicaLlevaXPagaY(String descripcion, int cantidadCompra, int cantidadAPagar, String nombreProducto) {
		super(descripcion);
		this.aComprar = cantidadCompra;
		this.aPagar = cantidadAPagar;
		this.productoOfertado = nombreProducto;
		this.productosDeOferta = new ArrayList<Producto>();
	}

	@Override
	protected Float obtenerDescuento(Compra compra) {
		int cantidadDeProducto = 0;
		for (ItemProducto item : compra.getItems()) {
			if(esItemDeProducto(item) & !productoTieneDescuento(item.getProducto())){
				cantidadDeProducto += item.getCantidad();
				this.precioProducto = item.getProducto().getPrecioUnitario();
				this.productosDeOferta.add(item.getProducto());
			}
		}
		int productosConOfertas = cantidadDeProducto - (cantidadDeProducto % this.aComprar);
		if(productosConOfertas == 0) return null;
		
		for(Producto producto : this.productosDeOferta){
			producto.confirmarDescuento();
		}
		
		return (this.precioProducto * productosConOfertas * (this.aComprar - this.aPagar) / this.aComprar);
	}

	private boolean productoTieneDescuento(Producto producto) {
		return producto.hasDescuento();
	}

	private boolean esItemDeProducto(ItemProducto item) {
		return (item.getProducto().getNombre() == productoOfertado);
	}

}
