package sucursal.modelo.ofertas;

import java.util.ArrayList;
import java.util.List;

import sucursal.modelo.compras.Compra;

public class CondicionGlobalDisyuntiva implements CondicionGlobal {
	private final List<CondicionGlobal> condiciones = new ArrayList<>();

	public CondicionGlobalDisyuntiva(final CondicionGlobal... condiciones) {
		for (CondicionGlobal condicion : condiciones) {
			this.condiciones.add(condicion);
		}
	}

	@Override
	public boolean aplicaEn(final Compra compra) {
		for (CondicionGlobal condicion : condiciones) {
			if (condicion.aplicaEn(compra)) {
				return true;
			}
		}
		return false;
	}
}
