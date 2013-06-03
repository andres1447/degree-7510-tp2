package sucursal.modelo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.modelo.productos.Producto;

@RunWith(MockitoJUnitRunner.class)
public class ResumenVentaTest {
	private ResumenVentas subject;
	
	@Mock
	private Compra mockCompra;
	
	@Before
	public void setup() {
		subject = new ResumenVentas();
	}
	
	@Test
	public void registrarCompraDeberiaSumarizarUnaUnicaCompra() {
		List<ItemProducto> itemList = new ArrayList<>();
		itemList.add(buildItem("11-111-1111", 3));
		Mockito.stub(mockCompra.getItems()).toReturn(itemList);
		subject.registrarCompra(mockCompra);
		
		List<EntradaResumenVentas> reporteOrdenado = subject.toReporteOrdenado();
		
		assertThat(reporteOrdenado.size(), is(1));
		assertThat(reporteOrdenado.get(0).getCodigoProducto(), is("11-111-1111"));
		assertThat(reporteOrdenado.get(0).getCantidad(), is(3));
	}
	
	@Test
	public void registrarCompraDeberiaMergearDosCompras() {
		List<ItemProducto> itemList = new ArrayList<>();
		itemList.add(buildItem("11-111-1111", 3));
		itemList.add(buildItem("11-111-1111", 5));
		Mockito.stub(mockCompra.getItems()).toReturn(itemList);
		subject.registrarCompra(mockCompra);
		
		List<EntradaResumenVentas> reporteOrdenado = subject.toReporteOrdenado();
		
		assertThat(reporteOrdenado.size(), is(1));
		assertThat(reporteOrdenado.get(0).getCodigoProducto(), is("11-111-1111"));
		assertThat(reporteOrdenado.get(0).getCantidad(), is(8));
	}
	
	@Test
	public void registrarCompraDeberiaOrdernarProductos() {
		List<ItemProducto> itemList = new ArrayList<>();
		itemList.add(buildItem("11-111-1111", 3));
		itemList.add(buildItem("11-111-1112", 5));
		itemList.add(buildItem("11-111-1113", 1));
		Mockito.stub(mockCompra.getItems()).toReturn(itemList);
		subject.registrarCompra(mockCompra);
		
		List<EntradaResumenVentas> reporteOrdenado = subject.toReporteOrdenado();
		
		assertThat(reporteOrdenado.size(), is(3));
		assertThat(reporteOrdenado.get(0).getCodigoProducto(), is("11-111-1112"));
		assertThat(reporteOrdenado.get(0).getCantidad(), is(5));
		assertThat(reporteOrdenado.get(1).getCodigoProducto(), is("11-111-1111"));
		assertThat(reporteOrdenado.get(1).getCantidad(), is(3));
		assertThat(reporteOrdenado.get(2).getCodigoProducto(), is("11-111-1113"));
		assertThat(reporteOrdenado.get(2).getCantidad(), is(1));
	}
	
	@Test
	public void incorporarResumenDeberiaIncorporarRegistros() {
		List<ItemProducto> itemList = new ArrayList<>();
		itemList.add(buildItem("11-111-1111", 3));
		itemList.add(buildItem("11-111-1112", 5));
		itemList.add(buildItem("11-111-1113", 1));
		Mockito.stub(mockCompra.getItems()).toReturn(itemList);
		subject.registrarCompra(mockCompra);
		ResumenVentas mockOtroResumen = Mockito.mock(ResumenVentas.class);
		List<EntradaResumenVentas> entradas = new ArrayList<>();
		entradas.add(new EntradaResumenVentas("11-111-1113", 9));
		entradas.add(new EntradaResumenVentas("11-111-1111", 5));
		entradas.add(new EntradaResumenVentas("11-111-1112", 2));
		entradas.add(new EntradaResumenVentas("11-111-1114", 2));
		Mockito.stub(mockOtroResumen.toReporteOrdenado()).toReturn(entradas);
		
		subject.incorporar(mockOtroResumen);
		List<EntradaResumenVentas> reporteOrdenado = subject.toReporteOrdenado();
		
		assertThat(reporteOrdenado.size(), is(4));
		assertThat(reporteOrdenado.get(0).getCodigoProducto(), is("11-111-1113"));
		assertThat(reporteOrdenado.get(0).getCantidad(), is(10));
		assertThat(reporteOrdenado.get(1).getCodigoProducto(), is("11-111-1111"));
		assertThat(reporteOrdenado.get(1).getCantidad(), is(8));
		assertThat(reporteOrdenado.get(2).getCodigoProducto(), is("11-111-1112"));
		assertThat(reporteOrdenado.get(2).getCantidad(), is(7));
		assertThat(reporteOrdenado.get(3).getCodigoProducto(), is("11-111-1114"));
		assertThat(reporteOrdenado.get(3).getCantidad(), is(2));
	}
	
	private ItemProducto buildItem(final String codigo, int cantidad) {
		Producto mockProducto = Mockito.mock(Producto.class);
		Mockito.stub(mockProducto.getCodigo()).toReturn(codigo);
		return new ItemProducto(mockProducto, cantidad);
	}

}