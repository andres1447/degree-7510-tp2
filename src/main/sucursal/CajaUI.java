package sucursal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.List;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.GridLayout;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GridBagLayout;

public class CajaUI {

	private JFrame frmCaja;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CajaUI window = new CajaUI();
					window.frmCaja.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CajaUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCaja = new JFrame();
		frmCaja.setTitle("Caja");
		frmCaja.setBounds(100, 100, 594, 458);
		frmCaja.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		frmCaja.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Caja");
		mnNewMenu.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAbrirCaja = new JMenuItem("Abrir caja");
		mntmAbrirCaja.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		mnNewMenu.add(mntmAbrirCaja);
		
		JMenuItem mntmCerrarCaja = new JMenuItem("Cerrar caja");
		mntmCerrarCaja.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		mnNewMenu.add(mntmCerrarCaja);
		
		JMenu mnCompra = new JMenu("Compra");
		mnCompra.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		menuBar.add(mnCompra);
		
		JMenuItem mntmNuevaCompra = new JMenuItem("Nueva Compra");
		mntmNuevaCompra.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		mnCompra.add(mntmNuevaCompra);
		
		JMenuItem mntmCancelarCompra = new JMenuItem("Cancelar Compra");
		mntmCancelarCompra.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		mnCompra.add(mntmCancelarCompra);
		
		JMenuItem mntmCerrarCompra = new JMenuItem("Cerrar Compra");
		mntmCerrarCompra.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		mnCompra.add(mntmCerrarCompra);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		frmCaja.getContentPane().setLayout(gridBagLayout);
	}

}
