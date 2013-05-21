package sucursal.modelo;

import com.google.inject.Singleton;

@Singleton
public class Sucursal {
	public Caja habilitarCaja() {
		return new Caja();
	}
}
