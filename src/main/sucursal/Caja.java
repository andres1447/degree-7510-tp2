package sucursal;

import java.util.LinkedList;
import java.util.List;

import sucursal.exceptions.CajaNoInicializadaException;
import sucursal.exceptions.CajaYaAbiertaException;
import sucursal.exceptions.CompraEnProcesoException;
import sucursal.exceptions.CompraNoInicializadaException;

public class Caja {

	private List<Compra> compras;
	private List<Oferta> ofertas;
	private EstadoCaja estado;
	private Compra compraActual;
	private Pago pagoActual;
	
	public Caja() {
		compras = new LinkedList<Compra>();
		estado = EstadoCaja.CERRADA;
		compraActual = null;
	}

	public void abrirCaja() throws CajaYaAbiertaException {
		estado.abrirCaja();
	}

	public void cerrarCaja() throws CajaNoInicializadaException {
		estado.cerrarCaja();
	}

	public void iniciarCompra() throws CompraEnProcesoException {
		if (compraActual != null) {
			throw new CompraEnProcesoException();
		}
	}

	public void agregarProductos(Producto nuevoProducto) throws CompraNoInicializadaException {
		if (compraActual == null) {
			throw new CompraNoInicializadaException();
		}
		compraActual.agregarProducto(nuevoProducto);
	}

	public void visualizarTotal() {
		
	}

	public void visualizarDescuentosAplicados() {
		
	}

	public void indicarMedioDePago(Pago pago) {
		aplicarDescuentosPago();
		pagoActual = pago;
	}

	private void aplicarDescuentosPago() {
	
	}

	public void confirmarCompra() {
		aplicarDescuentosItems();
		compras.add(compraActual);
		compraActual = null;
	}
	
	private void aplicarDescuentosItems() {
		
	}	
}
