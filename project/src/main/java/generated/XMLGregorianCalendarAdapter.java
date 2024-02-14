package generated;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Esta clase proporciona un adaptador JAXB para convertir entre objetos
 * XMLGregorianCalendar y cadenas de fecha en formato de cadena legible.
 * 
 * <p>
 * El adaptador se utiliza durante el proceso de serialización y deserialización
 * JAXB para convertir entre objetos XMLGregorianCalendar y cadenas de fecha
 * legibles. Este adaptador permite que los objetos XMLGregorianCalendar se
 * serialicen a cadenas de fecha en formato "yyyy-MM-dd HH:mm:ss" y se
 * deserialicen desde cadenas de fecha en el mismo formato.
 * </p>
 * 
 * <p>
 * El adaptador implementa la interfaz
 * javax.xml.bind.annotation.adapters.XmlAdapter, que proporciona métodos para
 * realizar la conversión entre tipos de datos XML y tipos de datos de Java.
 * </p>
 * 
 * <p>
 * Para usar este adaptador, simplemente anótalo en tus campos JAXB utilizando
 * la anotación @XmlJavaTypeAdapter y especifica esta clase como el adaptador.
 * </p>
 * 
 * <p>
 * Ejemplo de uso:
 * </p>
 * 
 * <pre>
 * {
 * 	&#64;code
 * 	&#64;XmlRootElement
 * 	public class MyClass {
 * 
 * 		&#64;XmlElement
 * 		&#64;XmlJavaTypeAdapter(XMLGregorianCalendarAdapter.class)
 * 		private XMLGregorianCalendar fecha;
 * 
 * 		// getters y setters
 * 	}
 * }
 * </pre>
 * 
 * <p>
 * En este ejemplo, el campo "fecha" de la clase MyClass se serializará a un
 * elemento XML con el nombre "fecha" y su valor se convertirá en una cadena de
 * fecha legible en el formato "yyyy-MM-dd HH:mm:ss" utilizando este adaptador.
 * </p>
 * 
 * <p>
 * Este adaptador también admite la deserialización de cadenas de fecha en el
 * formato "yyyy-MM-dd HH:mm:ss" a objetos XMLGregorianCalendar.
 * </p>
 * 
 * @see XMLGregorianCalendarAdapter
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 */

public class XMLGregorianCalendarAdapter extends XmlAdapter<String, XMLGregorianCalendar> {

	/**
	 * Formato de fecha para la conversión de XMLGregorianCalendar a String y viceversa
	 */
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Convierte un objeto XMLGregorianCalendar en una cadena de fecha en formato
	 * legible.
	 * 
	 * @param xmlGregorianCalendar el objeto XMLGregorianCalendar a serializar
	 * @return una cadena de fecha en formato legible
	 * @throws Exception si ocurre un error durante la conversión
	 */
	@Override
	public String marshal(XMLGregorianCalendar xmlGregorianCalendar) throws Exception {
		return dateFormat.format(xmlGregorianCalendar.toGregorianCalendar().getTime());
	}

	/**
	 * Convierte una cadena de fecha en formato legible en un objeto
	 * XMLGregorianCalendar.
	 * 
	 * @param s la cadena de fecha a deserializar
	 * @return un objeto XMLGregorianCalendar
	 * @throws Exception si ocurre un error durante la conversión
	 */
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
