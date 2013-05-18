package sucursal;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class CompraUI extends JPanel{

	private static final long serialVersionUID = 1L;

	private JPanel pnlDescProducto;
	private JScrollPane scrollPane;
	private JTable table;

	private JButton btnAgregarProducto;
	private JButton btnBorrarUltimoProducto;

	private JLabel lblNombreProducto;
	private JLabel lblMarcaProducto;
	private JLabel lblCodigoProducto;
	private JTextPane tpnDescripcionProducto;
	private JLabel lblRubroProducto;

	private Caja caja;

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
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));
		add(getScrollPane(), "1, 1, 1, 5, fill, fill");
		add(getPnlDescProducto(), "3, 1, fill, fill");
		add(getBtnBorrarUltimoProducto(), "3, 3");
		add(getBtnAgregarProducto(), "1, 7");
	}

	private JButton getBtnAgregarProducto() {
		if (btnAgregarProducto == null) {
			btnAgregarProducto = new JButton("Agregar Producto");
			btnAgregarProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LineProductoUI lP = new LineProductoUI();
					lP.setVisible(true);
					((DefaultTableModel) table.getModel())
							.addRow(new String[] {});
					getBtnBorrarUltimoProducto().setEnabled(true);
					table.setRowSelectionInterval(table.getRowCount() - 1,
							table.getRowCount() - 1);

					/*
					 * TODO: Agregar producto a la caja
					 */
				}
			});
		}
		return btnAgregarProducto;
	}

	private JButton getBtnBorrarUltimoProducto() {
		if (btnBorrarUltimoProducto == null) {
			btnBorrarUltimoProducto = new JButton("Deshacer");
			btnBorrarUltimoProducto.setEnabled(false);
			btnBorrarUltimoProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((DefaultTableModel) table.getModel()).removeRow(table
							.getRowCount() - 1);
					if (table.getRowCount() == 0)
						getBtnBorrarUltimoProducto().setEnabled(false);
					else
						table.setRowSelectionInterval(table.getRowCount() - 1,
								table.getRowCount() - 1);

					/*
					 * TODO: Borra ultimo producto agregado de la caja
					 */
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
			GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
			gbc_lblCodigo.anchor = GridBagConstraints.WEST;
			gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
			gbc_lblCodigo.gridx = 0;
			gbc_lblCodigo.gridy = 2;
			pnlDescProducto.add(new JLabel("Codigo:"), gbc_lblCodigo);
			GridBagConstraints gbc_lblCodigoProducto = new GridBagConstraints();
			gbc_lblCodigoProducto.insets = new Insets(0, 0, 5, 0);
			gbc_lblCodigoProducto.gridx = 1;
			gbc_lblCodigoProducto.gridy = 2;
			pnlDescProducto.add(getLblCodigoProducto(), gbc_lblCodigoProducto);
			GridBagConstraints gbc_lblRubro = new GridBagConstraints();
			gbc_lblRubro.anchor = GridBagConstraints.WEST;
			gbc_lblRubro.insets = new Insets(0, 0, 5, 5);
			gbc_lblRubro.gridx = 0;
			gbc_lblRubro.gridy = 3;
			pnlDescProducto.add(new JLabel("Rubro:"), gbc_lblRubro);
			GridBagConstraints gbc_lblRubroProducto = new GridBagConstraints();
			gbc_lblRubroProducto.insets = new Insets(0, 0, 5, 0);
			gbc_lblRubroProducto.gridx = 1;
			gbc_lblRubroProducto.gridy = 3;
			pnlDescProducto.add(getLblRubroProducto(), gbc_lblRubroProducto);
			GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
			gbc_lblDescripcin.anchor = GridBagConstraints.WEST;
			gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescripcin.gridx = 0;
			gbc_lblDescripcin.gridy = 4;
			pnlDescProducto.add(new JLabel("Descripcion:"), gbc_lblDescripcin);
			GridBagConstraints gbc_tpnDescripcionProducto = new GridBagConstraints();
			gbc_tpnDescripcionProducto.gridwidth = 2;
			gbc_tpnDescripcionProducto.fill = GridBagConstraints.BOTH;
			gbc_tpnDescripcionProducto.gridx = 0;
			gbc_tpnDescripcionProducto.gridy = 5;
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
			DefaultTableModel model = new DefaultTableModel(new Object[][] {},
					new String[] { "Cantidad", "Producto", "Precio Unid.",
							"Total" });
			table.setModel(model);
			table.setEnabled(false);
			table.setFillsViewportHeight(true);
		}
		return table;
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

	private JLabel getLblCodigoProducto() {
		if (lblCodigoProducto == null) {
			lblCodigoProducto = new JLabel("");
		}
		return lblCodigoProducto;
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

}
