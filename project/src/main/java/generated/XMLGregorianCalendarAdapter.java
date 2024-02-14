package generated;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class XMLGregorianCalendarAdapter extends XmlAdapter<String, XMLGregorianCalendar> {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String marshal(XMLGregorianCalendar xmlGregorianCalendar) throws Exception {
		return dateFormat.format(xmlGregorianCalendar.toGregorianCalendar().getTime());
	}

	@Override
	public XMLGregorianCalendar unmarshal(String s) throws Exception {
	    // Intentamos parsear la cadena en el formato esperado
	    try {
	        Date date = dateFormat.parse(s);
	        GregorianCalendar cal = new GregorianCalendar();
	        cal.setTime(date);
	        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	    } catch (ParseException e) {
	        // Si la cadena no puede ser parseada correctamente, mostramos un mensaje de error
	        throw new IllegalArgumentException("No se pudo parsear la fecha en el formato esperado.");
	    }
	}

}
