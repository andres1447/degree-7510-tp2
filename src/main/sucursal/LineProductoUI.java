package sucursal;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class LineProductoUI extends JDialog {

	private static final long serialVersionUID = 1L;

	private JFormattedTextField txtCant, txtCodigo;

	/**
	 * Create the dialog.
	 */
	public LineProductoUI() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Agregar Producto");
		setBounds(100, 100, 350, 140);
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.UNRELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.UNRELATED_GAP_COLSPEC, }, new RowSpec[] {
						FormFactory.UNRELATED_GAP_ROWSPEC,
						RowSpec.decode("48px"),
						FormFactory.NARROW_LINE_GAP_ROWSPEC,
						RowSpec.decode("35px"),
						FormFactory.UNRELATED_GAP_ROWSPEC, }));
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, "2, 2, fill, top");
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 53, 73, 0 };
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0,
					Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JLabel label = new JLabel("Codigo:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				panel.add(label, gbc_label);
			}
			{
				try {
					txtCodigo = new JFormattedTextField(new MaskFormatter(
							"##-###-####"));
				} catch (ParseException e) {
				}
				GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
				gbc_txtCodigo.anchor = GridBagConstraints.WEST;
				gbc_txtCodigo.insets = new Insets(0, 0, 5, 0);
				gbc_txtCodigo.gridx = 1;
				gbc_txtCodigo.gridy = 0;
				panel.add(txtCodigo, gbc_txtCodigo);
				txtCodigo.setColumns(10);
			}
			{
				JLabel lblCantidad = new JLabel("Cantidad:");
				GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
				gbc_lblCantidad.anchor = GridBagConstraints.EAST;
				gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
				gbc_lblCantidad.gridx = 0;
				gbc_lblCantidad.gridy = 1;
				panel.add(lblCantidad, gbc_lblCantidad);
			}
			{
				try {
					txtCant = new JFormattedTextField(new MaskFormatter("####"));
				} catch (ParseException e) {
				}
				GridBagConstraints gbc_txtCant = new GridBagConstraints();
				gbc_txtCant.anchor = GridBagConstraints.WEST;
				gbc_txtCant.insets = new Insets(0, 0, 5, 0);
				gbc_txtCant.gridx = 1;
				gbc_txtCant.gridy = 1;
				panel.add(txtCant, gbc_txtCant);
				txtCant.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, "2, 4, fill, top");
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						/*
						 * TODO: Devolver LineProducto
						 */
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*
						 * TODO: Cancelar LineProducto
						 */
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
