package sucursal.modelo.exceptions;

import sucursal.modelo.compras.Compra;

/**
 * Represents an error which occurs when trying to erase a product from
 * the LineItem when there is not any item listed in the current {@link Compra}.
 * 
 * @see Compra#quitarUltimoItemAgregado()
 */
public class ListaCompraVaciaException extends RuntimeException {
	private static final long serialVersionUID = -5039669179491188087L;
}
