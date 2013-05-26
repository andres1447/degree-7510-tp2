package sucursal.ui.swing;

import javax.swing.JOptionPane;

import com.google.inject.Singleton;

import sucursal.ui.SimpleDialog;

@Singleton
public class SwingSimpleDialog implements SimpleDialog {
	@Override
	public void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error",
				JOptionPane.WARNING_MESSAGE);
	}
}
