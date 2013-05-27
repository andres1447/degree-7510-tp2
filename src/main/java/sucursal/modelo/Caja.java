package sucursal.modelo;

import sucursal.exceptions.CajaNoAbiertaException;
import sucursal.exceptions.CompraNoIniciadaException;
import sucursal.utilities.Evento;

public class Caja {
	private final Evento<Caja> onCajaAbierta = new Evento<>(this);
	private final Evento<Caja> onCajaCerrada = new Evento<>(this);
	private final Evento<Caja> onCompraIniciada = new Evento<>(this);

	private EstadoCaja estado = new EstadoCajaCerrada();
	private Compra compraActual = null;

	public void abrir() {
		estado.checkPuedeAbrir();
		estado = new EstadoCajaAbierta();
		onCajaAbierta.notificar();
	}

	public void cerrar() {
		estado.checkPuedeCerrar();
		if (compraActual != null) {
			compraActual.cancelar();
		}
		estado = new EstadoCajaCerrada();
		onCajaCerrada.notificar();
	}

	public boolean estaAbierta() {
		return estado.estaAbierta();
	}

	public Compra iniciarCompra() {
		estado.checkPuedeIniciarCompra();
		estado = new EstadoCajaComprando();
		compraActual = new Compra();
		onCompraIniciada.notificar();
		return compraActual;
	}

	public void terminarCompra() {
		estado.checkPuedeTerminarCompra();
		estado = new EstadoCajaAbierta();
		compraActual = null;
	}

	public boolean estaComprando() {
		return estado.estaComprando();
	}

	// TODO: Mover estos métodos a la compra
	public void agregarProductos(LineProducto nuevoProducto) {
		if (!estaAbierta())
			throw new CajaNoAbiertaException();
		if (compraActual == null) {
			throw new CompraNoIniciadaException();
		}
		compraActual.agregarProducto(nuevoProducto);
	}

	// TODO: Mover estos métodos a la compra
	public void eliminarUltimaEntradaDeCompra() {
		if (!estaAbierta())
			throw new CajaNoAbiertaException();
		if (compraActual == null) {
			throw new CompraNoIniciadaException();
		}
		compraActual.eliminarUltimoProducto();
	}

	// TODO: Mover estos métodos a la compra
	public void confirmarCompra() {
		if (compraActual == null)
			throw new CompraNoIniciadaException();
		terminarCompra();
	}

	// TODO: Mover estos métodos a la compra
	public void cancelarCompra() {
		terminarCompra();
	}

	public Evento<Caja> getOnCajaAbierta() {
		return onCajaAbierta;
	}

	public Evento<Caja> getOnCajaCerrada() {
		return onCajaCerrada;
	}

	public Evento<Caja> getOnCompraIniciada() {
		return onCompraIniciada;
	}
}
