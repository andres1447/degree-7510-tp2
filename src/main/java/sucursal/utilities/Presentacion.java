package sucursal.utilities;

public class Presentacion {
	public static String truncar(final String que, int cantidad) {
		int termina = cantidad > que.length() ? que.length() : cantidad;
		String elipsis = cantidad < que.length() ? "..." : "";
		return que.substring(0, termina) + elipsis;
	}
}
