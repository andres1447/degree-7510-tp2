package sucursal.modelo.ofertas.predicados;

import sucursal.modelo.compras.Compra;

import com.google.common.base.Predicate;

public class PredicadoJubilado implements Predicate<Compra> {
	@Override
	public boolean apply(Compra input) {
		return input.esJubilado();
	}
}
