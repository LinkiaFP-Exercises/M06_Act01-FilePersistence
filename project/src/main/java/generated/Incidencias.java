//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.1 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.02.09 a las 02:55:42 PM CET 
//


package generated;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="incidencia" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="origen" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="detalle" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="tipo"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="Urgente"/&gt;
 *                         &lt;enumeration value="Normal"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="fechahora" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "incidencia"
})
@XmlRootElement(name = "incidencias")
public class Incidencias {

    protected List<Incidencias.Incidencia> incidencia;

    /**
     * Gets the value of the incidencia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the incidencia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncidencia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Incidencias.Incidencia }
     * 
     * 
     */
    public List<Incidencias.Incidencia> getIncidencia() {
        if (incidencia == null) {
            incidencia = new ArrayList<Incidencias.Incidencia>();
        }
        return this.incidencia;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="origen" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="detalle" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="tipo"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="Urgente"/&gt;
     *               &lt;enumeration value="Normal"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="fechahora" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "origen",
        "destino",
        "detalle",
        "tipo"
    })
    public static class Incidencia {

        @XmlElement(required = true)
        protected String origen;
        @XmlElement(required = true)
        protected String destino;
        @XmlElement(required = true)
        protected String detalle;
        @XmlElement(required = true)
        protected String tipo;
        @XmlAttribute(name = "fechahora")
        @XmlSchemaType(name = "dateTime")
		@XmlJavaTypeAdapter(XMLGregorianCalendarAdapter.class)
        protected XMLGregorianCalendar fechahora;

        /**
         * Obtiene el valor de la propiedad origen.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrigen() {
            return origen;
        }

        /**
         * Define el valor de la propiedad origen.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrigen(String value) {
            this.origen = value;
        }

        /**
         * Obtiene el valor de la propiedad destino.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDestino() {
            return destino;
        }

        /**
         * Define el valor de la propiedad destino.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDestino(String value) {
            this.destino = value;
        }

        /**
         * Obtiene el valor de la propiedad detalle.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDetalle() {
            return detalle;
        }

        /**
         * Define el valor de la propiedad detalle.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDetalle(String value) {
            this.detalle = value;
        }

        /**
         * Obtiene el valor de la propiedad tipo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipo() {
            return tipo;
        }

        /**
         * Define el valor de la propiedad tipo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipo(String value) {
            this.tipo = value;
        }

        /**
         * Obtiene el valor de la propiedad fechahora.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFechahora() {
            return fechahora;
        }

        /**
         * Define el valor de la propiedad fechahora.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setFechahora(XMLGregorianCalendar value) {
            this.fechahora = value;
        }

        @Override
        public String toString() {
//        	Habia intentado con este metodo pero sale así: 2019-09-21T15:27:14.000+02:00
//            String fechaHoraStr = fechahora.toXMLFormat();

			// Convertir XMLGregorianCalendar a Date
			Date fechaHoraDate = fechahora.toGregorianCalendar().getTime();

			// Formatear Date en el formato deseado
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fechaHoraStr = dateFormat.format(fechaHoraDate);

            return "Incidencia {" +
                    "fechahora=" + fechaHoraStr +
                    ", origen='" + origen + '\'' +
                    ", destino='" + destino + '\'' +
                    ", detalle='" + detalle + '\'' +
                    ", tipo='" + tipo + '\'' +
                    '}';
        }

    }

}
