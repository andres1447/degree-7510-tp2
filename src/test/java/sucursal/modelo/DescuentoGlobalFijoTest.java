package sucursal.modelo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.ofertas.DescuentoGlobalFijo;

@RunWith(MockitoJUnitRunner.class)
public class DescuentoGlobalFijoTest {
	@Mock
	private Compra mockCompra;

	@Test
	public void deberiaDescontarUnValorFijo() {
		DescuentoGlobalFijo subject = new DescuentoGlobalFijo(20.0f);

		Float resultado = subject.calcularPara(mockCompra);

		assertThat(resultado, is(20.0f));
	}
}