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
	private ActualizadorOfertas actualizador;
	
	
	public Caja(ActualizadorOfertas actualizador) {
		compras = new ArrayList<Compra>();
		estado = new EstadoCaja();
		compraActual = null;
		this.actualizador = actualizador;
		try {
			ofertas = actualizador.getOfertas();
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
	
	public void iniciarCompra() throws CompraEnProcesoException, CajaNoInicializadaException {
		if (!estaAbierta())
			throw new CajaNoInicializadaException();
		if (compraActual != null) {
			throw new CompraEnProcesoException();
		}
	}

	public void agregarProductos(Producto nuevoProducto) throws CompraNoInicializadaException, CajaNoInicializadaException {
		if (!estaAbierta())
			throw new CajaNoInicializadaException();
		if (compraActual == null) {
			throw new CompraNoInicializadaException();
		}
		compraActual.agregarProducto(nuevoProducto);
	}

	private void actualizarOfertas() {
		try {
			ofertas = actualizador.getOfertas();
		} catch (FileNotFoundException | XMLStreamException e) {
			ofertas = new ArrayList<Oferta>();
		}
	}
	
	public void visualizarTotal() {

	}

	public void visualizarDescuentosAplicados() {
		
	}

	public void indicarMedioDePago(Pago pago) {
	}


	public void confirmarCompra() {
		actualizarOfertas();
		aplicarDescuentosItems();
		compras.add(compraActual);
		compraActual = null;
	}
	
	private void aplicarDescuentosItems() {
		
	}	
}
