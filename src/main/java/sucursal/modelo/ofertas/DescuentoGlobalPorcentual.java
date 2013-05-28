package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

public class DescuentoGlobalPorcentual implements DescuentoGlobal {
	private final float porcentaje;

	public DescuentoGlobalPorcentual(float porcentaje) {
		super();
		this.porcentaje = porcentaje;
	}

	@Override
	public Float calcularPara(Compra compra) {
		float totalBruto = compra.getTotalBruto();
		return totalBruto * porcentaje / 100;
	}
}
