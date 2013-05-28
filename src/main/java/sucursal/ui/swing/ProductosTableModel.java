package sucursal.ui.swing;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;

/**
 * {@link AbstractTableModel} implementation which adapts the list of added
 * {@link ItemProducto} in a {@link Compra} to the interface required by
 * {@link JTable}.
 */
public class ProductosTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 6556701685006955405L;
	private final Compra compra;

	public ProductosTableModel(final Compra compra) {
		this.compra = compra;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Cantidad";
		case 1:
			return "Producto";
		case 2:
			return "Precio Un.";
		case 3:
			return "Total";
		default:
			return null;
		}
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return compra.getItems().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ItemProducto item = compra.getItems().get(rowIndex);

		switch (columnIndex) {
		case 0:
			return item.getCantidad();
		case 1:
			return item.getProducto().getNombre();
		case 2:
			return item.getProducto().getPrecioUnitario();
		case 3:
			return item.getTotal();
		default:
			return null;
		}
	}
}