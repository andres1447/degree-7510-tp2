package sucursal;

import java.util.Iterator;

import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

public class Oferta {

	private static final String MARCA = "marca";

	private static final String RUBRO = "rubro";

	private static final Object PORC = "porcentaje";

	private static final Object PROD = "producto";

	private String marca = null;
	private int porcentajeDescuento = 0;
	private String rubro = null;
	private String producto = null;

	public Oferta() {

	}

	public Oferta(StartElement startElement) {
		Iterator<Attribute> iter = startElement.getAttributes();
		while (iter.hasNext()) {
			Attribute attr = iter.next();
			if (attr.getName().getLocalPart().equals(MARCA)) {
				this.setMarca(attr.getValue());
			}
			if (attr.getName().getLocalPart().equals(RUBRO)) {
				this.setMarca(attr.getValue());
			}
			if (attr.getName().getLocalPart().equals(PORC)) {
				this.setPorcentajeDescuento(Integer.valueOf(attr.getValue()));
			}
			if (attr.getName().getLocalPart().equals(PROD)) {
				this.setProducto(attr.getValue());
			}
		}

	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(int porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

}
