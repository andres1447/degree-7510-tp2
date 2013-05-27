package sucursal.ui.swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import sucursal.modelo.Compra;
import sucursal.ui.CompraView;
import sucursal.utilities.Evento;
import sucursal.utilities.Observador;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class SwingCompraView extends JDialog implements CompraView {
	private static final long serialVersionUID = 3673191686104250105L;

	private final Evento<CompraView> onConfirmarCompra = new Evento<CompraView>(
			this);

	private final Evento<CompraView> onCancelarCompra = new Evento<CompraView>(
			this);

	private final Evento<CompraView> onAgregarProducto = new Evento<CompraView>(
			this);

	protected Evento<CompraView> onDeshacer = new Evento<CompraView>(this);

	private JTable tblProductos;
	private JScrollPane pnlProductos;
	private JPanel pnlAcciones;
	private JButton btnCancelarCompra;
	private JButton btnConfirmarCompra;
	private JPanel pnlInformacion;
	private JPanel pnlInfoProducto;
	private JButton btnAgregarProducto;
	private JButton btnDeshacer;

	private Compra compra;

	private Observador<Compra> onItemsCambiados = new Observador<Compra>() {
		@Override
		public void notificar(Compra observable) {
			AbstractTableModel model = (AbstractTableModel) tblProductos
					.getModel();
			model.fireTableDataChanged();
			btnDeshacer.setEnabled(compra.tieneItems());
		}
	};

	public SwingCompraView() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		initComponents();
	}

	private void initComponents() {
		setTitle("Nueva Compra");
		setResizable(false);
		setBounds(100, 100, 700, 400);
		setModal(true);
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						ColumnSpec.decode("max(230dlu;default)"),
						ColumnSpec.decode("5dlu"),
						ColumnSpec.decode("right:default:grow"), },
						new RowSpec[] { RowSpec.decode("default:grow"), }));

		getContentPane().add(getPnlProductos(), "1, 1, fill, fill");
		getContentPane().add(getPnlInformacion(), "3, 1, fill, fill");
	}

	private JScrollPane getPnlProductos() {
		if (pnlProductos == null) {
			pnlProductos = new JScrollPane();
			pnlProductos.setViewportBorder(new TitledBorder(null,
					"Productos en esta compra", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			pnlProductos.setViewportView(getTblProductos());
		}
		return pnlProductos;
	}

	private JTable getTblProductos() {
		if (tblProductos == null) {
			tblProductos = new JTable();
		}
		return tblProductos;
	}

	private JPanel getPnlAcciones() {
		if (pnlAcciones == null) {
			pnlAcciones = new JPanel();
			pnlAcciones.setLayout(new GridLayout(0, 1, 5, 5));
			pnlAcciones.add(getBtnAgregarProducto());
			pnlAcciones.add(getBtnDeshacer());
			pnlAcciones.add(getBtnCancelarCompra());
			pnlAcciones.add(getBtnConfirmarCompra());
		}
		return pnlAcciones;
	}

	private JButton getBtnCancelarCompra() {
		if (btnCancelarCompra == null) {
			btnCancelarCompra = new JButton("Cancelar Compra");
			btnCancelarCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onCancelarCompra.notificar();
				}
			});
		}
		return btnCancelarCompra;
	}

	private JButton getBtnConfirmarCompra() {
		if (btnConfirmarCompra == null) {
			btnConfirmarCompra = new JButton("Confirmar Compra");
			btnConfirmarCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					onConfirmarCompra.notificar();
				}
			});
		}
		return btnConfirmarCompra;
	}

	private JPanel getPnlInformacion() {
		if (pnlInformacion == null) {
			pnlInformacion = new JPanel();
			pnlInformacion.setLayout(new GridLayout(2, 1, 0, 0));
			pnlInformacion.add(getPnlInfoProducto());
			pnlInformacion.add(getPnlAcciones());
		}
		return pnlInformacion;
	}

	private JPanel getPnlInfoProducto() {
		if (pnlInfoProducto == null) {
			pnlInfoProducto = new JPanel();
			pnlInfoProducto.setBorder(new TitledBorder(null, "Producto",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return pnlInfoProducto;
	}

	private JButton getBtnAgregarProducto() {
		if (btnAgregarProducto == null) {
			btnAgregarProducto = new JButton("Agregar Producto");
			btnAgregarProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onAgregarProducto.notificar();
				}
			});
		}
		return btnAgregarProducto;
	}

	private JButton getBtnDeshacer() {
		if (btnDeshacer == null) {
			btnDeshacer = new JButton("Deshacer");
			btnDeshacer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onDeshacer.notificar();
				}
			});
		}
		return btnDeshacer;
	}

	@Override
	public void observar(final Compra compra) {
		if (this.compra != null) {
			this.compra.getOnItemsCambiados().desregistrar(onItemsCambiados);
		}
		this.compra = compra;
		getTblProductos().setModel(new ProductosTableModel(compra));
		btnDeshacer.setEnabled(compra.tieneItems());
		compra.getOnItemsCambiados().registrar(onItemsCambiados);
	}

	@Override
	public Evento<CompraView> getOnConfirmarCompra() {
		return onConfirmarCompra;
	}

	@Override
	public Evento<CompraView> getOnCancelarCompra() {
		return onCancelarCompra;
	}

	@Override
	public Evento<CompraView> getOnAgregarProducto() {
		return onAgregarProducto;
	}

	@Override
	public Evento<CompraView> getOnDeshacer() {
		return onDeshacer;
	}

	@Override
	public void displayView() {
		setVisible(true);
	}

	@Override
	public void hideView() {
		setVisible(false);
	}
}
