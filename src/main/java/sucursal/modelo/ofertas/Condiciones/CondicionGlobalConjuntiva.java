package sucursal.modelo.ofertas.Condiciones;

import java.util.ArrayList;
import java.util.List;

import sucursal.modelo.compras.Compra;

public class CondicionGlobalConjuntiva implements CondicionGlobal {
	private final List<CondicionGlobal> condiciones = new ArrayList<>();

	public CondicionGlobalConjuntiva(final CondicionGlobal... condiciones) {
		for (CondicionGlobal condicion : condiciones) {
			this.condiciones.add(condicion);
		}
	}

	@Override
	public boolean aplicaEn(final Compra compra) {
		for (CondicionGlobal condicion : condiciones) {
			if (!condicion.aplicaEn(compra)) {
				return false;
			}
		}
		return true;
	}
}
