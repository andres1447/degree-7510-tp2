package sucursal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class Sucursal {

	static final String RUTA_OFERTAS = "ofertas.xml";

	private static final String OFERTA = "oferta";

	private List<Oferta> ofertas = null;

	public List<Oferta> getOfertas() throws FileNotFoundException, XMLStreamException {
		if (ofertas != null && !ofertas.isEmpty())
			return ofertas;
		ofertas = new ArrayList<Oferta>();

		// First create a new XMLInputFactory
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		// Setup a new eventReader
		InputStream in = new FileInputStream(RUTA_OFERTAS);
		XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		// Read the XML document
		Oferta oferta = null;

		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();

			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				// If we have a item element we create a new item
				if (startElement.getName().getLocalPart() == (OFERTA)) {
					oferta = new Oferta(startElement);
				}
			}
			// If we reach the end of an item element we add it to the list
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
