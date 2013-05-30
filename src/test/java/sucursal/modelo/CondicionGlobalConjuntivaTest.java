package sucursal.modelo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.ofertas.Condiciones.CondicionGlobal;
import sucursal.modelo.ofertas.Condiciones.CondicionGlobalConjuntiva;

@RunWith(MockitoJUnitRunner.class)
public class CondicionGlobalConjuntivaTest {
	@Mock
	private Compra mockCompra;

	@Test
	public void deberiaAplicarCuandoTodasLasCondicionesSonVerdaderas() {
		CondicionGlobal primerCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(primerCondicion.aplicaEn(mockCompra)).toReturn(true);
		CondicionGlobal segundaCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(segundaCondicion.aplicaEn(mockCompra)).toReturn(true);
		CondicionGlobal tercerCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(tercerCondicion.aplicaEn(mockCompra)).toReturn(true);
		CondicionGlobalConjuntiva subject = new CondicionGlobalConjuntiva(
				primerCondicion, segundaCondicion, tercerCondicion);

		boolean resultado = subject.aplicaEn(mockCompra);

		assertThat(resultado, is(true));
	}

	@Test
	public void noDeberiaAplicarCuandoUnaCondicionEsFalsa() {
		CondicionGlobal primerCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(primerCondicion.aplicaEn(mockCompra)).toReturn(true);
		CondicionGlobal segundaCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(segundaCondicion.aplicaEn(mockCompra)).toReturn(true);
		CondicionGlobal tercerCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(tercerCondicion.aplicaEn(mockCompra)).toReturn(false);
		CondicionGlobalConjuntiva subject = new CondicionGlobalConjuntiva(
				primerCondicion, segundaCondicion, tercerCondicion);

		boolean resultado = subject.aplicaEn(mockCompra);

		assertThat(resultado, is(false));
	}
}