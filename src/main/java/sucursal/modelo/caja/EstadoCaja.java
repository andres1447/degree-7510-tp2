package sucursal.modelo.caja;

import sucursal.modelo.exceptions.CajaNoAbiertaException;
import sucursal.modelo.exceptions.CajaYaAbiertaException;
import sucursal.modelo.exceptions.CompraEnProcesoException;
import sucursal.modelo.exceptions.CompraNoIniciadaException;

/**
 * State which represents the different checks that need to be done on the
 * different states of the {@link Caja}
 */
public interface EstadoCaja {
	/**
	 * Checks if the {@link Caja} is in "open" or "buying" state
	 */
	boolean estaAbierta();

	/**
	 * Checks if the {@link Caja} is in "closed" state
	 */
	boolean estaCerrada();

	/**
	 * Checks if the {@link Caja} is in "buying" state
	 */
	boolean estaComprando();

	/**
	 * Checks if the {@link Caja} can change to "open" state, raising
	 * {@link CajaYaAbiertaException} if it can't.
	 */
	void checkPuedeAbrir();

	/**
	 * Checks if the {@link Caja} can change to "closed" state, raising
	 * {@link CajaNoAbiertaException} if it can't.
	 */
	void checkPuedeCerrar();

	/**
	 * Checks if the {@link Caja} can change to "buying" state, raising
	 * {@link CajaNoAbiertaException} or {@link CompraEnProcesoException}
	 * accordingly if it can't.
	 */
	void checkPuedeIniciarCompra();

	/**
	 * Checks if the {@link Caja} can change to "open" state after being in
	 * "buying" state, raising {@link CompraNoIniciadaException} if it can't.
	 */
	void checkPuedeTerminarCompra();
}
