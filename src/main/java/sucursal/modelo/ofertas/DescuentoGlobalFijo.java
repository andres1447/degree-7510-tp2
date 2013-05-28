package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

public class DescuentoGlobalFijo implements DescuentoGlobal {
	private final float valor;

	public DescuentoGlobalFijo(float valor) {
		super();
		this.valor = valor;
	}

	@Override
	public Float calcularPara(Compra compra) {
		return valor;
	}
}
