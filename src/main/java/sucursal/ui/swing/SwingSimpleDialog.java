package sucursal.ui.swing;

import javax.swing.JOptionPane;

import sucursal.ui.views.SimpleDialog;

import com.google.inject.Singleton;

/**
 * {@link SimpleDialog} implementation using Swing. This code uses the SWING
 * built-in {@link JOptionPane} to provide the dialogs.
 */
@Singleton
public class SwingSimpleDialog implements SimpleDialog {
	@Override
	public void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error",
				JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void showInfo(String message) {
		JOptionPane.showMessageDialog(null, message, "Informacion",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
