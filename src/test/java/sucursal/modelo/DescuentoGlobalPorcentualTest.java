package sucursal.modelo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.ofertas.DescuentoGlobalPorcentual;

@RunWith(MockitoJUnitRunner.class)
public class DescuentoGlobalPorcentualTest {
	@Mock
	private Compra mockCompra;

	@Test
	public void deberiaDescontarUnValorFijo() {
		DescuentoGlobalPorcentual subject = new DescuentoGlobalPorcentual(20.0f);
		Mockito.stub(mockCompra.getTotalBruto()).toReturn(200.0f);

		Float resultado = subject.calcularPara(mockCompra);

		assertThat(resultado, is(40.0f));
	}
}