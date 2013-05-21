package sucursal.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import sucursal.exceptions.MaximoDeCajasYaHabilidatasException;

public class Sucursal implements ActualizadorOfertas {

	static final String RUTA_OFERTAS = "ofertas.xml";

	private static final String OFERTA = "oferta";
	private static final int CANTIDAD_CAJAS = 3;

	private List<Oferta> ofertas = null;

	private List<Caja> cajas = new ArrayList<Caja>();

	private Map<Integer, Producto> productos = new HashMap<Integer, Producto>();

	public Caja habilitarCaja() throws MaximoDeCajasYaHabilidatasException {
		if (cajas.size() < CANTIDAD_CAJAS) {
			Caja c = new Caja(this);
			cajas.add(c);
			return c;
		}
		throw new MaximoDeCajasYaHabilidatasException();
	}

	/**
	 * @see sucursal.modelo.ActualizadorOfertas#getOfertas()
	 */
	@Override
	public List<Oferta> getOfertas() throws FileNotFoundException,
			XMLStreamException {
		if (ofertas != null && !ofertas.isEmpty())
			return ofertas;
		ofertas = new ArrayList<Oferta>();

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream in = new FileInputStream(RUTA_OFERTAS);
		XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		Oferta oferta = null;
		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();

			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart() == (OFERTA)) {
					oferta = new Oferta(startElement);
				}
			}
			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart() == (OFERTA)) {
					ofertas.add(oferta);
				}
			}

		}
		return ofertas;
	}

}
