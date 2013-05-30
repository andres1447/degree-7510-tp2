package sucursal.modelo.compras;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IndexProductos {

	private SeleccionadorClave select;
	private Map<String, List<ItemProducto>> productos;
	
	
	public void add(ItemProducto item) {
		String key = select.obtenerClave(item);
		if (productos.containsKey(key)) {
			productos.get(key).add(item);
		} else {
			ArrayList<ItemProducto> nlista = new ArrayList<ItemProducto>();
			nlista.add(item);
			productos.put(key,nlista);
		}
	}
	
	public boolean tieneItems() {
		return !productos.isEmpty();
	}

	public List<ItemProducto> get(String key) {
		return productos.get(key);
	}

	public IndexProductos(SeleccionadorClave selec) {
		select = selec;
		productos = new HashMap<String, List<ItemProducto>>();
	}
	
	public static IndexProductos indexPara(TipoConsultaProducto tipoConsulta) {
		if (tipoConsulta == TipoConsultaProducto.CODIGO) {
			return new IndexProductos(new SelectorClaveCodigo());
		}
		if (tipoConsulta == TipoConsultaProducto.RUBRO) {
			return new IndexProductos(new SelectorClaveRubro());
		}
		if (tipoConsulta == TipoConsultaProducto.MARCA) {
			return new IndexProductos(new SelectorClaveMarca());
		}
		return null;
	}

	public boolean contains(ItemProducto item) {
		return productos.containsKey(select.obtenerClave(item));
	}

	public void remove(ItemProducto item) {
		List<ItemProducto> lip = productos.get(select.obtenerClave(item));
		lip.remove(item);
		if (lip.isEmpty())
			productos.remove(select.obtenerClave(item));
	}

	public List<ItemProducto> getItems() {
		Collection<List<ItemProducto>> aux = productos.values();
		List<ItemProducto> items = new ArrayList<ItemProducto>();
		for (List<ItemProducto> list : aux) {
			items.addAll(list);
		}
		return items;
	}

}
