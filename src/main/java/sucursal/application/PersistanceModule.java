package sucursal.application;

import sucursal.modelo.ProveedorOfertas;
import sucursal.modelo.ProveedorOfertasMemoria;
import sucursal.modelo.ProveedorProductos;
import sucursal.modelo.ProveedorProductosMemoria;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * {@link Injector} {@link Module} in charge of defining bindings for
 * persistance-related interfaces.
 */
public class PersistanceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ProveedorOfertas.class).to(ProveedorOfertasMemoria.class);
		bind(ProveedorProductos.class).to(ProveedorProductosMemoria.class);
	}
}
