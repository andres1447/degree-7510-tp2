package sucursal;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

public interface ActualizadorOfertas {

	public abstract List<Oferta> getOfertas() throws FileNotFoundException,
			XMLStreamException;

}