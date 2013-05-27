package sucursal.modelo;

import sucursal.utilities.Evento;

public class Caja {
	private final Evento<Caja> onCajaAbierta = new Evento<>(this);
	private final Evento<Caja> onCajaCerrada = new Evento<>(this);
	private final Evento<Caja> onCompraIniciada = new Evento<>(this);

	private final ProveedorOfertas proveedorOfertas;
	private final ProveedorProductos proveedorProductos;

	private EstadoCaja estado = new EstadoCajaCerrada();
	private Compra compraActual = null;

	public Caja(final ProveedorOfertas proveedorOfertas,
			final ProveedorProductos proveedorProductos) {
		this.proveedorOfertas = proveedorOfertas;
		this.proveedorProductos = proveedorProductos;
	}

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
		compraActual = new Compra(this, proveedorOfertas, proveedorProductos);
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
