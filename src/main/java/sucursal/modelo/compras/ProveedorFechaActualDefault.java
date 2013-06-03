package sucursal.modelo.compras;

import java.util.Date;

/**
 * Default implementation for {@link ProveedorFechaActual} which returns the
 * current date.
 */
public class ProveedorFechaActualDefault implements ProveedorFechaActual {
	@Override
	public Date proveer() {
		return new Date();
	}
}
