package sucursal.ui.swing;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sucursal.exceptions.CajaNoInicializadaException;
import sucursal.exceptions.CajaYaAbiertaException;
import sucursal.exceptions.CompraEnProcesoException;
import sucursal.exceptions.CompraNoInicializadaException;
import sucursal.exceptions.MaximoDeCajasYaHabilidatasException;
import sucursal.modelo.Caja;
import sucursal.modelo.Sucursal;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AppUI {
	private final static String CAJA_CERRADA_PANEL = "CAJA_CERRADA_PANEL";
	private final static String CAJA_ABIERTA_PANEL = "CAJA_ABIERTA_PANEL";
	private final static String COMPRA_PANEL = "COMPRA_PANEL";

	private JFrame frmCaja;

	private JPanel pnlCajaCerrada;
	private JButton btnAbrirCaja;

	private JPanel pnlCajaAbierta;
	private JButton btnIniciarCompra;
	private JButton btnCerrarCaja;

	private JPanel pnlCompra;
	private JButton btnCancelarCompra;
	private JButton btnConfirmarCompra;
	private Sucursal sucursal;

	private Caja caja;

	/**
	 * Create the application.
	 * 
	 * @throws MaximoDeCajasYaHabilidatasException
	 */
	public AppUI(Sucursal sucursal) throws MaximoDeCajasYaHabilidatasException {
		this.sucursal = sucursal;
		this.caja = this.sucursal.habilitarCaja();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCaja = new JFrame();
		frmCaja.setTitle("Caja");
		frmCaja.setResizable(false);
		frmCaja.setBounds(100, 100, 700, 400);
		frmCaja.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCaja.getContentPane().setLayout(new CardLayout(0, 0));
		frmCaja.getContentPane().add(getPnlCajaCerrada(), CAJA_CERRADA_PANEL);
		frmCaja.getContentPane().add(getPnlCajaAbierta(), CAJA_ABIERTA_PANEL);

	}

	private void showCajaCerrada() {
		((CardLayout) frmCaja.getContentPane().getLayout()).show(
				this.frmCaja.getContentPane(), CAJA_CERRADA_PANEL);

	}

	private void showCajaAbierta() {
		((CardLayout) frmCaja.getContentPane().getLayout()).show(
				this.frmCaja.getContentPane(), CAJA_ABIERTA_PANEL);

	}

	private void showCompra() {

		frmCaja.getContentPane().add(getPnlCompra(), COMPRA_PANEL);
		((CardLayout) frmCaja.getContentPane().getLayout()).show(
				this.frmCaja.getContentPane(), COMPRA_PANEL);

	}

	private Container getPnlCajaCerrada() {
		if (pnlCajaCerrada == null) {
			pnlCajaCerrada = new JPanel();
			pnlCajaCerrada.setLayout(new FormLayout(
					new ColumnSpec[] { ColumnSpec.decode("default:grow"), },
					new RowSpec[] { FormFactory.LINE_GAP_ROWSPEC,
							RowSpec.decode("default:grow"), }));
			pnlCajaCerrada.add(getBtnAbrirCaja(), "1, 2, center, center");
		}
		return pnlCajaCerrada;
	}

	private JButton getBtnAbrirCaja() {
		if (btnAbrirCaja == null) {
			btnAbrirCaja = new JButton("Abrir Caja");
			btnAbrirCaja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					try {
						caja.abrirCaja();
					} catch (CajaYaAbiertaException e) {
						JOptionPane.showMessageDialog(null,
								"La caja ya se encuentra abierta.", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
					showCajaAbierta();

				}
			});
		}
		return btnAbrirCaja;
	}

	private Container getPnlCajaAbierta() {
		if (pnlCajaAbierta == null) {
			pnlCajaAbierta = new JPanel();
			pnlCajaAbierta.setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("default:grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"), }, new RowSpec[] {
					FormFactory.LINE_GAP_ROWSPEC,
					RowSpec.decode("default:grow"), }));
			pnlCajaAbierta.add(getBtnCerrarCaja(), "1, 2, center, center");
			pnlCajaAbierta.add(getBtnIniciarCompra(), "3, 2, center, center");

		}
		return pnlCajaAbierta;
	}

	private Component getBtnIniciarCompra() {
		if (btnIniciarCompra == null) {
			btnIniciarCompra = new JButton("Iniciar Compra");
			btnIniciarCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						caja.iniciarCompra();
						showCompra();
					} catch (CompraEnProcesoException e) {
						JOptionPane.showMessageDialog(null,
								"Compra en proceso.", "Error",
								JOptionPane.WARNING_MESSAGE);
					} catch (CajaNoInicializadaException e) {
						JOptionPane.showMessageDialog(null,
								"La caja se encuentra cerrada", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
		return btnIniciarCompra;
	}

	private JButton getBtnCerrarCaja() {
		if (btnCerrarCaja == null) {
			btnCerrarCaja = new JButton("Cerrar Caja");
			btnCerrarCaja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						caja.cerrarCaja();
						showCajaCerrada();
					} catch (CajaNoInicializadaException e) {
						JOptionPane.showMessageDialog(null,
								"La caja ya se encuentra cerrada.", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
		return btnCerrarCaja;
	}

	private JPanel getPnlCompra() {
		if (pnlCompra == null) {
			pnlCompra = new JPanel();
			pnlCompra.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.UNRELATED_GAP_COLSPEC,
					ColumnSpec.decode("max(200dlu;min):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.GROWING_BUTTON_COLSPEC,
					FormFactory.UNRELATED_GAP_COLSPEC, }, new RowSpec[] {
					FormFactory.UNRELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.UNRELATED_GAP_ROWSPEC, }));
			pnlCompra.add(getBtnCancelarCompra(), "4, 2, right, default");
			pnlCompra.add(new CompraUI(caja), "2, 4, 3, 1, fill, fill");
			pnlCompra.add(getBtnConfirmarCompra(), "4, 6, right, default");

		}
		return pnlCompra;
	}

	private JButton getBtnConfirmarCompra() {
		if (btnConfirmarCompra == null) {
			btnConfirmarCompra = new JButton("Confirmar Compra");
			btnConfirmarCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						caja.confirmarCompra();
						pnlCompra = null;
					} catch (CompraNoInicializadaException e1) {
					}
					showCajaAbierta();
				}
			});
		}
		return btnConfirmarCompra;
	}

	private JButton getBtnCancelarCompra() {
		if (btnCancelarCompra == null) {
			btnCancelarCompra = new JButton("Cancelar Compra");
			btnCancelarCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showCajaAbierta();

					/*
					 * TODO: Cancelar compra en la caja
					 */
					try {
						caja.cancelarCompra();
						pnlCompra = null;
					} catch (CompraNoInicializadaException e1) {
						JOptionPane.showMessageDialog(null,
								"No hay compra para cancelar.", "Error",
								JOptionPane.WARNING_MESSAGE);
					}

				}
			});
		}
		return btnCancelarCompra;
	}

	public void show() {
		frmCaja.setVisible(true);
	}

}
