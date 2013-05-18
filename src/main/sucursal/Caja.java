package sucursal;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import sucursal.exceptions.CajaNoInicializadaException;
import sucursal.exceptions.CajaYaAbiertaException;
import sucursal.exceptions.CompraEnProcesoException;
import sucursal.exceptions.CompraNoInicializadaException;

public class Caja {

	private List<Compra> compras;
	private List<Oferta> ofertas;
	private EstadoCaja estado;
	private Compra compraActual;
	private Sucursal sucursal;

	public Caja(Sucursal sucursal) {
		compras = new ArrayList<Compra>();
		estado = new EstadoCaja();
		compraActual = null;
		this.sucursal = sucursal;
		try {
			ofertas = sucursal.getOfertas();
		} catch (FileNotFoundException | XMLStreamException e) {
			ofertas = new ArrayList<Oferta>();
		}
	}

	public void abrirCaja() throws CajaYaAbiertaException {
		estado.abrirCaja();
	}

	public void cerrarCaja() throws CajaNoInicializadaException {
		estado.cerrarCaja();
	}

	public boolean estaAbierta() {
		return estado.estaAbierta();
	}

	public void iniciarCompra() throws CompraEnProcesoException,
			CajaNoInicializadaException {
		if (!estaAbierta())
			throw new CajaNoInicializadaException();
		if (compraActual != null) {
			throw new CompraEnProcesoException();
		}
		compraActual = new Compra();
	}

	public void agregarProductos(Producto nuevoProducto)
			throws CompraNoInicializadaException, CajaNoInicializadaException {
		if (!estaAbierta())
			throw new CajaNoInicializadaException();
		if (compraActual == null) {
			throw new CompraNoInicializadaException();
		}
		compraActual.agregarProducto(nuevoProducto);
	}

	public void visualizarTotal() {
		try {
			ofertas = sucursal.getOfertas();
		} catch (FileNotFoundException | XMLStreamException e) {
			ofertas = new ArrayList<Oferta>();
		}
	}

	public void visualizarDescuentosAplicados() {

	}

	public void indicarMedioDePago(Pago pago) {
	}

	public void confirmarCompra() {
		aplicarDescuentosItems();
		compras.add(compraActual);
		compraActual = null;
	}

	private void aplicarDescuentosItems() {

	}
}
