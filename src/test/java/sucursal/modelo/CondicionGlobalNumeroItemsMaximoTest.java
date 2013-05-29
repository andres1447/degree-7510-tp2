package sucursal.modelo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.modelo.ofertas.CondicionGlobalNumeroItemsMinimo;

@RunWith(MockitoJUnitRunner.class)
public class CondicionGlobalNumeroItemsMaximoTest {
	@Mock
	private Compra mockCompra;

	@Test
	public void deberiaAplicarCuandoHayMasItemsQueElMinimo() {
		List<ItemProducto> items = new ArrayList<ItemProducto>();
		ItemProducto primerItem = Mockito.mock(ItemProducto.class);
		Mockito.stub(primerItem.getCantidad()).toReturn(2);
		items.add(primerItem);
		ItemProducto segundoItem = Mockito.mock(ItemProducto.class);
		Mockito.stub(segundoItem.getCantidad()).toReturn(3);
		items.add(segundoItem);
		Mockito.stub(mockCompra.getItems()).toReturn(items);
		CondicionGlobalNumeroItemsMinimo subject = new CondicionGlobalNumeroItemsMinimo(
				3);

		boolean resultado = subject.aplicaEn(mockCompra);

		assertThat(resultado, is(true));
	}

	@Test
	public void noDeberiaAplicarCuandoMenosItemsQueElMinimo() {
		CondicionGlobalNumeroItemsMinimo subject = new CondicionGlobalNumeroItemsMinimo(
				3);

		boolean resultado = subject.aplicaEn(mockCompra);

		assertThat(resultado, is(false));
	}
}