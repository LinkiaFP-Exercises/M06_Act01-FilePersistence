package generated;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * La clase Traspaso es responsable de procesar y manejar las incidencias leídas
 * desde un archivo de texto, imprimir las incidencias en la consola y
 * guardarlas en un archivo XML.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @version 1.0
 * @since 2024-02-14
 */
public class Traspaso {

	/**
	 * El Logger para registrar los mensajes de la aplicación.
	 */
	private static final Logger logger = Logger.getLogger(Traspaso.class.getName());

	public static void main(String[] args) {

		configLoggerHandler();

		// Definir la ruta del archivo de texto
		String filePath = filePathToIncidenciasTxt();

		if (filePath != null) {

			// Lista para almacenar las incidencias
			Incidencias incidencias = leerIncidenciasDesdeArchivo(filePath);

			// Verificar si se leyeron las incidencias correctamente
			if (incidencias != null) {
				printIncidencias(incidencias);
				escribirIncidenciasEnXML(incidencias);
			} else {
				logger.severe("Error al leer las incidencias desde el archivo.");
			}
		}
	}

	/**
	 * Configura los manejadores de registro para el Logger. El archivo "logs.txt"
	 * estará en la raiz del proyecto.
	 */
	private static void configLoggerHandler() {
		try {
			// Configurar el nivel de registro mínimo
			logger.setLevel(Level.ALL);

			// Crear un FileHandler para guardar los registros en un archivo de texto
			FileHandler fileHandler = new FileHandler("logs.txt");
			fileHandler.setLevel(Level.ALL);
			// Establecer el formato del registro
			fileHandler.setFormatter(new java.util.logging.SimpleFormatter());

			// Crear un ConsoleHandler para imprimir en cosola
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(Level.ALL);

			// Agregar el FileHandler y ConsoleHandler al logger
			logger.addHandler(fileHandler);
			logger.addHandler(consoleHandler);

		} catch (IOException e) {
			logger.severe("Error al configurar el manejador de archivo de registro: " + e.getMessage());
		}
	}

	/**
	 * Obtiene la ruta del archivo de texto que contiene las incidencias.
	 *
	 * @return La ruta del archivo de texto si se encuentra, o null si no se
	 *         encuentra.
	 */
	private static String filePathToIncidenciasTxt() {
		// Obtener la ruta base del proyecto
		String basePath = System.getProperty("user.dir");

		// Definir el nombre de la carpeta
		String folderName = "fuentesTxt";

		// Crear un objeto File para la carpeta
		File folder = new File(basePath, folderName);

		// Verificar si la carpeta no existe y crearla si es necesario
		if (!folder.exists()) {
			if (folder.mkdir()) {
				logger.fine("Se ha creado la carpeta '" + folderName + "'.");
			} else {
				logger.severe("Error: No se pudo crear la carpeta '" + folderName + "'.");
				return null;
			}
		}

		// Verificar si la carpeta está vacía
		if (folder.listFiles() == null || folder.listFiles().length == 0) {
			logger.severe("Error: La carpeta '" + folderName + "' está vacía.");
			return null;
		}

		// Definir la ruta del archivo de texto
		String filePath = folder.getAbsolutePath() + File.separator + "incidencias.txt";

		return filePath;
	}


	/**
	 * Lee las incidencias desde el archivo de texto y las devuelve como un objeto
	 * Incidencias.
	 *
	 * @param filePath La ruta del archivo de texto que contiene las incidencias.
	 * @return Un objeto Incidencias que contiene las incidencias leídas, o null si
	 *         hay un error.
	 */
	private static Incidencias leerIncidenciasDesdeArchivo(String filePath) {
        Incidencias incidencias = new Incidencias();
        List<Incidencias.Incidencia> listaIncidencias = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while ((line = br.readLine()) != null) {
                if (line.startsWith("%")) {
                    String[] parts = line.split("\\s+");

                    // Obtenemos la fecha y hora
                    String fechaHoraStr = parts[1] + " " + parts[2];
					Date fechaHoraDate = dateFormat.parse(fechaHoraStr);

					// Convertimos la fecha de Date a XMLGregorianCalendar
					XMLGregorianCalendar fechaHora = toXMLGregorianCalendar(fechaHoraDate);

                    // Obtenemos el origen y destino
                    String origen = parts[3];
                    String destino = parts[4];

                    // Leemos el detalle
                    String detalle = br.readLine();

                    // Leemos el tipo
                    String tipo = br.readLine();

                    // Creamos una nueva incidencia y la agregamos a la lista
                    Incidencias.Incidencia incidencia = new Incidencias.Incidencia();
                    incidencia.setFechahora(fechaHora);
                    incidencia.setOrigen(origen);
                    incidencia.setDestino(destino);
                    incidencia.setDetalle(detalle);
                    incidencia.setTipo(tipo);
                    listaIncidencias.add(incidencia);
                }
            }

			incidencias.incidencia = listaIncidencias;
        } catch (IOException | ParseException e) {
			logger.severe(e.getLocalizedMessage());
            return null;
        }

        return incidencias;
    }

	/**
	 * Convierte un objeto Date en un objeto XMLGregorianCalendar.
	 *
	 * @param date El objeto Date a convertir.
	 * @return El objeto XMLGregorianCalendar resultante.
	 */
	private static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {
			logger.severe(e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * Imprime las incidencias en la consola.
	 *
	 * @param incidencias El objeto Incidencias a imprimir.
	 */
	private static void printIncidencias(Incidencias incidencias) {
		// Imprimimos las incidencias leídas
		// Verificamos si se han leído correctamente las incidencias
		if (incidencias != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// Imprimimos las incidencias leídas en log y consola
			for (Incidencias.Incidencia incidencia : incidencias.getIncidencia()) {
				logger.finest("Incidencia de fecha : "
						+ dateFormat.format(incidencia.getFechahora().toGregorianCalendar().getTime())
							+ " convertida correctamente!");
				System.out.println(incidencia.toString());
			}
		} else {
			logger.severe("Error al leer las incidencias desde el archivo.");
		}
	}

	/**
	 * Escribe las incidencias en un archivo XML.
	 *
	 * @param incidencias El objeto Incidencias a escribir en el archivo XML.
	 */
	private static void escribirIncidenciasEnXML(Incidencias incidencias) {
		try {
			// Crear el contexto JAXB
			JAXBContext context = JAXBContext.newInstance(Incidencias.class);

			// Crear el marshaller
			Marshaller marshaller = context.createMarshaller();

			// Formatear la salida XML
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Crear la carpeta "resultsXml" en la raíz del proyecto si no existe
			String basePath = System.getProperty("user.dir");
			File resultsFolder = new File(basePath, "resultsXml");
			if (!resultsFolder.exists()) {
				if (resultsFolder.mkdir()) {
					logger.config("Se ha creado la carpeta 'resultsXml'.");
				} else {
					logger.severe("Error: No se pudo crear la carpeta 'resultsXml'.");
					return;
				}
			}

			// Crear el archivo "incidencias.xml" en la carpeta "resultsXml"
			File file = new File(resultsFolder, "incidencias.xml");

			// Sobrescribir el archivo si ya existe
			if (file.exists()) {
				file.delete();
				logger.config("Se ha borrado el anterior archivo 'incidencias.xml'.");
			}

			// Escribir las incidencias en el archivo XML
			marshaller.marshal(incidencias, file);
			logger.fine("Se han guardado las incidencias en el archivo 'incidencias.xml'.");
		} catch (JAXBException e) {
			logger.severe(e.getLocalizedMessage());
		}
	}

}
