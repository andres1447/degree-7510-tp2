package sucursal.ui.views;

/**
 * Common dialogs provider
 */
public interface SimpleDialog {
	/**
	 * Shows a small error view with a given message.
	 */
	void showError(final String message);

	/**
	 * Shows a small information view with a given message.
	 */
	void showInfo(final String message);
}
