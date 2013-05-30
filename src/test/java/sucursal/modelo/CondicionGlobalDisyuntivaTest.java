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
import sucursal.modelo.ofertas.Condiciones.CondicionGlobalDisyuntiva;

@RunWith(MockitoJUnitRunner.class)
public class CondicionGlobalDisyuntivaTest {
	@Mock
	private Compra mockCompra;

	@Test
	public void deberiaAplicarCuandoAlgunaCondicionEsVerdadera() {
		CondicionGlobal primerCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(primerCondicion.aplicaEn(mockCompra)).toReturn(false);
		CondicionGlobal segundaCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(segundaCondicion.aplicaEn(mockCompra)).toReturn(false);
		CondicionGlobal tercerCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(tercerCondicion.aplicaEn(mockCompra)).toReturn(true);
		CondicionGlobalDisyuntiva subject = new CondicionGlobalDisyuntiva(
				primerCondicion, segundaCondicion, tercerCondicion);

		boolean resultado = subject.aplicaEn(mockCompra);

		assertThat(resultado, is(true));
	}

	@Test
	public void noDeberiaAplicarCuandoTodasLasCondicionSonFalsas() {
		CondicionGlobal primerCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(primerCondicion.aplicaEn(mockCompra)).toReturn(false);
		CondicionGlobal segundaCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(segundaCondicion.aplicaEn(mockCompra)).toReturn(false);
		CondicionGlobal tercerCondicion = Mockito.mock(CondicionGlobal.class);
		Mockito.stub(tercerCondicion.aplicaEn(mockCompra)).toReturn(false);
		CondicionGlobalDisyuntiva subject = new CondicionGlobalDisyuntiva(
				primerCondicion, segundaCondicion, tercerCondicion);

		boolean resultado = subject.aplicaEn(mockCompra);

		assertThat(resultado, is(false));
	}
}