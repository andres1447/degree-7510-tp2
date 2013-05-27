package sucursal.ui.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sucursal.exceptions.CajaNoAbiertaException;
import sucursal.exceptions.CompraNoIniciadaException;
import sucursal.modelo.Caja;
import sucursal.modelo.LineProducto;
import sucursal.modelo.Producto;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class CompraUI extends JPanel implements ITicket {

	private static final long serialVersionUID = 1L;

	private JPanel pnlDescProducto;
	private JScrollPane scrollPane;
	private JTable table;

	private JButton btnAgregarProducto;
	private JButton btnBorrarUltimoProducto;

	private JLabel lblNombreProducto;
	private JLabel lblMarcaProducto;
	private JTextPane tpnDescripcionProducto;
	private JLabel lblRubroProducto;

	private Caja caja;
	private JPanel panel;
	private JLabel lblSubtotal;
	private JLabel lblSubtotalCompra;

	/**
	 * Create the frame.
	 */
	public CompraUI(Caja caja) {
		this.caja = caja;
		initComponents();
	}

	private void initComponents() {
		setBounds(0, 0, 700, 400);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(200dlu;min):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.GROWING_BUTTON_COLSPEC, }, new RowSpec[] {
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));
		add(getScrollPane(), "1, 1, 1, 7, fill, fill");
		add(getPnlDescProducto(), "3, 1, fill, fill");
		add(getBtnBorrarUltimoProducto(), "3, 3");
		add(getPanel_1(), "3, 7, fill, fill");
		add(getBtnAgregarProducto(), "1, 9");
	}

	private JButton getBtnAgregarProducto() {
		if (btnAgregarProducto == null) {
			btnAgregarProducto = new JButton("Agregar Producto");
			btnAgregarProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LineProductoUI lP = new LineProductoUI(CompraUI.this);
					lP.setVisible(true);
				}
			});
		}
		return btnAgregarProducto;
	}

	@Override
	public void agregarLineProducto(LineProducto lineProducto) {
		try {
			caja.agregarProductos(lineProducto);

			((CompraModelTable) table.getModel())
					.agregarLineProducto(lineProducto);
			table.setRowSelectionInterval(table.getRowCount() - 1,
					table.getRowCount() - 1);

		} catch (CompraNoIniciadaException | CajaNoAbiertaException e) {
		}
	}

	private void showProducto(Producto producto) {
		lblRubroProducto.setText((producto == null) ? "" : producto.getRubro()
				.toString());
		lblNombreProducto.setText((producto == null) ? "" : producto
				.getNombre());
		lblMarcaProducto.setText((producto == null) ? "" : producto.getMarca()
				.toString());
		tpnDescripcionProducto.setText((producto == null) ? "" : producto
				.getDescripcion());

	}

	private JButton getBtnBorrarUltimoProducto() {
		if (btnBorrarUltimoProducto == null) {
			btnBorrarUltimoProducto = new JButton("Deshacer");
			btnBorrarUltimoProducto.setEnabled(false);
			btnBorrarUltimoProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						caja.eliminarUltimaEntradaDeCompra();
						((CompraModelTable) table.getModel())
								.eliminarUltimoLineProducto();

						if (table.getRowCount() > 0) {
							table.setRowSelectionInterval(
									table.getRowCount() - 1,
									table.getRowCount() - 1);
						}
					} catch (CompraNoIniciadaException
							| CajaNoAbiertaException e1) {
					}
				}
			});
		}
		return btnBorrarUltimoProducto;
	}

	private JPanel getPnlDescProducto() {
		if (pnlDescProducto == null) {
			pnlDescProducto = new JPanel();
			GridBagLayout gbl_pnlDescProducto = new GridBagLayout();
			gbl_pnlDescProducto.columnWidths = new int[] { 53, 73, 0 };
			gbl_pnlDescProducto.rowHeights = new int[] { 15, 0, 0, 0, 0, 0, 0 };
			gbl_pnlDescProducto.columnWeights = new double[] { 1.0, 0.0,
					Double.MIN_VALUE };
			gbl_pnlDescProducto.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
					0.0, 1.0, Double.MIN_VALUE };
			pnlDescProducto.setLayout(gbl_pnlDescProducto);
			GridBagConstraints gbc_lblProducto = new GridBagConstraints();
			gbc_lblProducto.insets = new Insets(0, 0, 5, 5);
			gbc_lblProducto.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblProducto.gridx = 0;
			gbc_lblProducto.gridy = 0;
			pnlDescProducto.add(new JLabel("Producto:"), gbc_lblProducto);
			GridBagConstraints gbc_lblNombreProducto = new GridBagConstraints();
			gbc_lblNombreProducto.insets = new Insets(0, 0, 5, 0);
			gbc_lblNombreProducto.gridx = 1;
			gbc_lblNombreProducto.gridy = 0;
			pnlDescProducto.add(getLblNombreProducto(), gbc_lblNombreProducto);
			GridBagConstraints gbc_lblMarca = new GridBagConstraints();
			gbc_lblMarca.anchor = GridBagConstraints.WEST;
			gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
			gbc_lblMarca.gridx = 0;
			gbc_lblMarca.gridy = 1;
			pnlDescProducto.add(new JLabel("Marca:"), gbc_lblMarca);
			GridBagConstraints gbc_lblMarcaProducto = new GridBagConstraints();
			gbc_lblMarcaProducto.insets = new Insets(0, 0, 5, 0);
			gbc_lblMarcaProducto.gridx = 1;
			gbc_lblMarcaProducto.gridy = 1;
			pnlDescProducto.add(getLblMarcaProducto(), gbc_lblMarcaProducto);
			GridBagConstraints gbc_lblRubro = new GridBagConstraints();
			gbc_lblRubro.anchor = GridBagConstraints.WEST;
			gbc_lblRubro.insets = new Insets(0, 0, 5, 5);
			gbc_lblRubro.gridx = 0;
			gbc_lblRubro.gridy = 2;
			pnlDescProducto.add(new JLabel("Rubro:"), gbc_lblRubro);
			GridBagConstraints gbc_lblRubroProducto = new GridBagConstraints();
			gbc_lblRubroProducto.insets = new Insets(0, 0, 5, 0);
			gbc_lblRubroProducto.gridx = 1;
			gbc_lblRubroProducto.gridy = 2;
			pnlDescProducto.add(getLblRubroProducto(), gbc_lblRubroProducto);
			GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
			gbc_lblDescripcin.anchor = GridBagConstraints.WEST;
			gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescripcin.gridx = 0;
			gbc_lblDescripcin.gridy = 3;
			pnlDescProducto.add(new JLabel("Descripcion:"), gbc_lblDescripcin);
			GridBagConstraints gbc_tpnDescripcionProducto = new GridBagConstraints();
			gbc_tpnDescripcionProducto.gridheight = 2;
			gbc_tpnDescripcionProducto.gridwidth = 2;
			gbc_tpnDescripcionProducto.fill = GridBagConstraints.BOTH;
			gbc_tpnDescripcionProducto.gridx = 0;
			gbc_tpnDescripcionProducto.gridy = 4;
			pnlDescProducto.add(getTpnDescripcionProducto(),
					gbc_tpnDescripcionProducto);
		}
		return pnlDescProducto;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new CompraModelTable());
			table.setEnabled(false);
			table.setFillsViewportHeight(true);
		}
		return table;
	}

	private class CompraModelTable extends DefaultTableModel {

		private static final long serialVersionUID = 1L;
		Stack<LineProducto> productos;

		CompraModelTable() {
			super(new Object[][] {}, new String[] { "Cantidad", "Producto",
					"Precio Unid.", "Total" });
			productos = new Stack<LineProducto>();

		}

		public void agregarLineProducto(LineProducto prod) {
			this.productos.push(prod);
			showProducto(productos.peek().getProducto());
			getBtnBorrarUltimoProducto().setEnabled(true);

			getLblSubtotalCompra().setText(
					Float.toString((Float.parseFloat(getLblSubtotalCompra()
							.getText()) + prod.getTotal())));
		}

		public void eliminarUltimoLineProducto() {
			getLblSubtotalCompra().setText(
					Float.toString((Float.parseFloat(getLblSubtotalCompra()
							.getText()) - productos.pop().getTotal())));

			this.removeRow(getRowCount() - 1);
			if (productos.isEmpty()) {
				getBtnBorrarUltimoProducto().setEnabled(false);
				showProducto(null);
			} else
				showProducto(productos.peek().getProducto());
		}

	}

	private JLabel getLblNombreProducto() {
		if (lblNombreProducto == null) {
			lblNombreProducto = new JLabel("");
		}
		return lblNombreProducto;
	}

	private JLabel getLblMarcaProducto() {
		if (lblMarcaProducto == null) {
			lblMarcaProducto = new JLabel("");
		}
		return lblMarcaProducto;
	}

	private JLabel getLblRubroProducto() {
		if (lblRubroProducto == null) {
			lblRubroProducto = new JLabel("");
		}
		return lblRubroProducto;
	}

	private JTextPane getTpnDescripcionProducto() {
		if (tpnDescripcionProducto == null) {
			tpnDescripcionProducto = new JTextPane();
		}
		return tpnDescripcionProducto;
	}

	private JPanel getPanel_1() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLblSubtotal(), BorderLayout.NORTH);
			panel.add(getLblSubtotalCompra(), BorderLayout.CENTER);
		}
		return panel;
	}

	private JLabel getLblSubtotal() {
		if (lblSubtotal == null) {
			lblSubtotal = new JLabel("Subtotal:");
		}
		return lblSubtotal;
	}

	private JLabel getLblSubtotalCompra() {
		if (lblSubtotalCompra == null) {
			lblSubtotalCompra = new JLabel("0.0");
			lblSubtotalCompra.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSubtotalCompra.setFont(new Font("Dialog", Font.BOLD, 30));
		}
		return lblSubtotalCompra;
	}
}
