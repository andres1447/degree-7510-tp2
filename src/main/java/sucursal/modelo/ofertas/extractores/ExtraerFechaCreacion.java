package sucursal.modelo.ofertas.extractores;

import java.util.Date;

import sucursal.modelo.compras.Compra;

import com.google.common.base.Function;

public class ExtraerFechaCreacion implements Function<Compra, Date> {
	public static ExtraerFechaCreacion instance() {
		return new ExtraerFechaCreacion();
	}
	
	@Override
	public Date apply(final Compra input) {
		return input.getFechaCreacion();
	}

}
