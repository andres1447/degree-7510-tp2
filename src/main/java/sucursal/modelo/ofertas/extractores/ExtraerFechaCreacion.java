package sucursal.modelo.ofertas.extractores;

import java.util.Date;

import sucursal.modelo.compras.Compra;

import com.google.common.base.Function;

/**
 * Extractor function which, given a {@link Compra}, provides its
 * {@link Compra#getFechaCreacion()}.
 */
public class ExtraerFechaCreacion implements Function<Compra, Date> {
	@Override
	public Date apply(final Compra input) {
		return input.getFechaCreacion();
	}
}
