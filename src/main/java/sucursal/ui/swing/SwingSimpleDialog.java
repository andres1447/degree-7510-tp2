package sucursal.ui.swing;

import javax.swing.JOptionPane;

import sucursal.ui.SimpleDialog;

import com.google.inject.Singleton;

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
