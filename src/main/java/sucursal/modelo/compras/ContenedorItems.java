package sucursal.modelo.compras;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import sucursal.modelo.exceptions.ContenedorItemsVacioException;

public class ContenedorItems {
	
	
	private Map<TipoConsultaProducto, IndexProductos> indexes;
	private Stack<ItemProducto> insercionesRecientes;
	private Map<String, ItemProducto> productosIngresados;
	
	public ContenedorItems() {
		insercionesRecientes = new Stack<ItemProducto>();
		productosIngresados = new HashMap<String,ItemProducto>();
		indexes = new HashMap<TipoConsultaProducto, IndexProductos>();
		indexes.put(TipoConsultaProducto.RUBRO, IndexProductos.indexPara(TipoConsultaProducto.RUBRO));
		indexes.put(TipoConsultaProducto.MARCA, IndexProductos.indexPara(TipoConsultaProducto.MARCA));
		
	}
	
	public void insertarProducto(ItemProducto item) {
		insercionesRecientes.push(item);
		productosIngresados.put(item.getProducto().getCodigo(), item);
		indexes.get(TipoConsultaProducto.RUBRO).add(item);
		indexes.get(TipoConsultaProducto.MARCA).add(item);
	}
	
	public void sacarUltimoAgregado() {
		if (insercionesRecientes.empty())
			throw new ContenedorItemsVacioException();
		ItemProducto ritem;
		do 
			ritem = insercionesRecientes.pop();
		while (!productosIngresados.containsKey(ritem.getProducto().getCodigo()));
		if (!productosIngresados.containsKey(ritem.getProducto().getCodigo()))
			return;
		productosIngresados.remove(ritem.getProducto().getCodigo());
		indexes.get(TipoConsultaProducto.RUBRO).remove(ritem);
		indexes.get(TipoConsultaProducto.MARCA).remove(ritem);
	}
	
	public ItemProducto getUltimoAgregado() {
		if (!tieneItems())
			throw new ContenedorItemsVacioException();
		ItemProducto ip = insercionesRecientes.peek();
		while (!productosIngresados.containsKey(ip.getProducto().getCodigo())){
			ip = insercionesRecientes.pop();
		}
		return ip;
	}
	
	public boolean tieneItems() {
		return !productosIngresados.isEmpty();
	}
	
	public ItemProducto obtenerProducto(String key) {
		return productosIngresados.get(key);
	}
	
	public List<ItemProducto> obtenerProductosPor(TipoConsultaProducto tp, String key) {
		return indexes.get(tp).get(key);
	}

	public List<ItemProducto> getItems() {
		return indexes.get(TipoConsultaProducto.CODIGO).getItems();
	}
	
}


enum TipoConsultaProducto {
	RUBRO, MARCA, CODIGO
}