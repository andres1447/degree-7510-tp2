package sucursal.utilities;

/**
 * Utility class which contains algorithms used for presentation purposes.
 */
public class Presentacion {
	/**
	 * Truncates a given string so that it does not exceed a given amount of
	 * characters, including an elipsis if it does.
	 * 
	 * For example, given the string "Hello world", truncating it to 5
	 * characters would return "Hello ..."
	 */
	public static String truncar(final String que, int cantidad) {
		int termina = cantidad > que.length() ? que.length() : cantidad;
		String elipsis = cantidad < que.length() ? "..." : "";
		return que.substring(0, termina) + elipsis;
	}
}
