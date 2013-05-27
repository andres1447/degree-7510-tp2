package sucursal.ui.swing;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sucursal.exceptions.CompraNoIniciadaException;
import sucursal.modelo.Caja;
import sucursal.ui.MainView;
import sucursal.utilities.Evento;
import sucursal.utilities.Observador;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@Singleton
public class SwingMainView extends JFrame implements MainView {
	private static final long serialVersionUID = -3714802935785385343L;

	private final static String CAJA_CERRADA_PANEL = "CAJA_CERRADA_PANEL";
	private final static String CAJA_ABIERTA_PANEL = "CAJA_ABIERTA_PANEL";
	private final static String COMPRA_PANEL = "COMPRA_PANEL";

	private final Evento<MainView> onAbrirCaja = new Evento<MainView>(this);
	private final Evento<MainView> onCerrarCaja = new Evento<MainView>(this);
	private final Evento<MainView> onIniciarCompra = new Evento<MainView>(this);

	private JPanel pnlCajaCerrada;
	private JButton btnAbrirCaja;

	private JPanel pnlCajaAbierta;
	private JButton btnIniciarCompra;
	private JButton btnCerrarCaja;

	private JPanel pnlCompra;
	private JButton btnCancelarCompra;
	private JButton btnConfirmarCompra;

	private Caja caja;

	private final Observador<Caja> onCajaAbierta = new Observador<Caja>() {
		@Override
		public void notificar(Caja observable) {
			showCajaAbierta();
		}
	};

	private final Observador<Caja> onCajaCerrada = new Observador<Caja>() {
		@Override
		public void notificar(Caja observable) {
			showCajaCerrada();
		}
	};

	private final Observador<Caja> onCompraIniciada = new Observador<Caja>() {
		@Override
		public void notificar(Caja observable) {
			showCompra();
		}
	};

	@Inject
	public SwingMainView() {
		initialize();
	}

	private void initialize() {
		setTitle("Caja");
		setResizable(false);
		setBounds(100, 100, 700, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		getContentPane().add(getPnlCajaCerrada(), CAJA_CERRADA_PANEL);
		getContentPane().add(getPnlCajaAbierta(), CAJA_ABIERTA_PANEL);

	}

	private void showCajaCerrada() {
		((CardLayout) getContentPane().getLayout()).show(getContentPane(),
				CAJA_CERRADA_PANEL);

	}

	private void showCajaAbierta() {
		((CardLayout) getContentPane().getLayout()).show(getContentPane(),
				CAJA_ABIERTA_PANEL);

	}

	private void showCompra() {
		getContentPane().add(getPnlCompra(), COMPRA_PANEL);
		((CardLayout) getContentPane().getLayout()).show(getContentPane(),
				COMPRA_PANEL);

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
					onAbrirCaja.notificar();
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
					onIniciarCompra.notificar();
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
					onCerrarCaja.notificar();
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
					} catch (CompraNoIniciadaException e1) {
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
					} catch (CompraNoIniciadaException e1) {
						JOptionPane.showMessageDialog(null,
								"No hay compra para cancelar.", "Error",
								JOptionPane.WARNING_MESSAGE);
					}

				}
			});
		}
		return btnCancelarCompra;
	}

	@Override
	public void display() {
		setVisible(true);
	}

	@Override
	public void observar(final Caja caja) {
		if (this.caja != null) {
			this.caja.getOnCajaAbierta().desregistrar(onCajaAbierta);
			this.caja.getOnCajaCerrada().desregistrar(onCajaCerrada);
			this.caja.getOnCompraIniciada().desregistrar(onCompraIniciada);
		}
		this.caja = caja;
		this.caja.getOnCajaAbierta().registrar(onCajaAbierta);
		this.caja.getOnCajaCerrada().registrar(onCajaCerrada);
		this.caja.getOnCompraIniciada().registrar(onCompraIniciada);
	}

	@Override
	public Evento<MainView> getOnAbrirCaja() {
		return onAbrirCaja;
	}

	@Override
	public Evento<MainView> getOnCerrarCaja() {
		return onCerrarCaja;
	}

	@Override
	public Evento<MainView> getOnIniciarCompra() {
		return onIniciarCompra;
	}
}
