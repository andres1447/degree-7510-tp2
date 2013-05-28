package sucursal.application;

import sucursal.modelo.ofertas.ProveedorOfertas;
import sucursal.modelo.ofertas.ProveedorOfertasMemoria;
import sucursal.modelo.productos.ProveedorProductos;
import sucursal.modelo.productos.ProveedorProductosMemoria;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * {@link Injector} {@link Module} in charge of defining bindings for
 * persistence-related interfaces.
 */
public class PersistenceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ProveedorOfertas.class).to(ProveedorOfertasMemoria.class);
		bind(ProveedorProductos.class).to(ProveedorProductosMemoria.class);
	}
}
