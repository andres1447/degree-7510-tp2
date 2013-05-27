package sucursal.modelo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OfertaTest {
	@Mock
	private Compra mockCompra;
	
	@Mock
	private OfertaCondicion mockCondicion;
	
	@Mock
	private OfertaDescuento mockDescuento;

	@Test
	public void testOfertaAplicaDescuentoSiMatcheaCondicion() {
		Mockito.stub(mockCondicion.corresponde(mockCompra)).toReturn(true);
		Mockito.stub(mockDescuento.aplicar(mockCompra)).toReturn(10.0f);
		Oferta subject = new Oferta("description", mockCondicion, mockDescuento);

		subject.aplicarSiCorresponde(mockCompra);

		Mockito.verify(mockCompra).agregarDescuento("description", 10.0f);
	}
	
	@Test
	public void testOfertaNoAplicaDescuentosSiNoMatcheaCondicion() {
		Mockito.stub(mockCondicion.corresponde(mockCompra)).toReturn(false);
		Oferta subject = new Oferta("description", mockCondicion, mockDescuento);
		
		subject.aplicarSiCorresponde(mockCompra);
		
		Mockito.verifyZeroInteractions(mockCompra);
	}
}
