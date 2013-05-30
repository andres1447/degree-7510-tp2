package sucursal.modelo.compras;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import sucursal.modelo.exceptions.ContenedorItemsVacioException;

public class ContenedorItems {
	
	
	private Map<TipoConsultaProducto, IndexProductos> indexes;
	private Stack<ItemProducto> insercionesRecientes;
	
	public ContenedorItems() {
		indexes = new HashMap<TipoConsultaProducto, IndexProductos>();
		indexes.put(TipoConsultaProducto.RUBRO, IndexProductos.indexPara(TipoConsultaProducto.RUBRO));
		indexes.put(TipoConsultaProducto.MARCA, IndexProductos.indexPara(TipoConsultaProducto.MARCA));
		indexes.put(TipoConsultaProducto.CODIGO, IndexProductos.indexPara(TipoConsultaProducto.CODIGO));
		
	}
	
	public void insertarProducto(ItemProducto item) {
		insercionesRecientes.push(item);
		indexes.get(TipoConsultaProducto.RUBRO).add(item);
		indexes.get(TipoConsultaProducto.MARCA).add(item);
		indexes.get(TipoConsultaProducto.CODIGO).add(item);	
	}
	
	public void sacarUltimoAgregado() {
		if (insercionesRecientes.empty())
			throw new ContenedorItemsVacioException();
		ItemProducto ritem;
		do 
			ritem = insercionesRecientes.pop();
		while (!indexes.get(TipoConsultaProducto.CODIGO).contains(ritem));
		if (!indexes.get(TipoConsultaProducto.CODIGO).contains(ritem))
			return;
		indexes.get(TipoConsultaProducto.RUBRO).remove(ritem);
		indexes.get(TipoConsultaProducto.MARCA).remove(ritem);
		indexes.get(TipoConsultaProducto.CODIGO).remove(ritem);
	}
	
	public ItemProducto getUltimoAgregado() {
		if (!tieneItems())
			throw new ContenedorItemsVacioException();
		return insercionesRecientes.peek(); 
	}
	
	public boolean tieneItems() {
		return indexes.get(TipoConsultaProducto.CODIGO).tieneItems();
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