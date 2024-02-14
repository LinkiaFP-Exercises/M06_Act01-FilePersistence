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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class Traspaso {
	public static void main(String[] args) {

		// Definir la ruta del archivo de texto
		String filePath = filePathToIncidenciasTxt();

		if (filePath != null) {

			// Lista para almacenar las incidencias
			Incidencias incidencias = leerIncidenciasDesdeArchivo(filePath);

			printIncidencias(incidencias);
		}
	}

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
				System.out.println("Se ha creado la carpeta '" + folderName + "'.");
			} else {
				System.out.println("Error: No se pudo crear la carpeta '" + folderName + "'.");
				return null;
			}
		}

		// Verificar si la carpeta está vacía
		if (folder.listFiles() == null || folder.listFiles().length == 0) {
			System.out.println("Error: La carpeta '" + folderName + "' está vacía.");
			return null;
		}

		// Definir la ruta del archivo de texto
		String filePath = folder.getAbsolutePath() + File.separator + "incidencias.txt";

		return filePath;
	}


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
            e.printStackTrace();
            return null;
        }

        return incidencias;
    }

	private static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void printIncidencias(Incidencias incidencias) {
		// Imprimimos las incidencias leídas
		// Verificamos si se han leído correctamente las incidencias
		if (incidencias != null) {
			// Imprimimos las incidencias leídas
			for (Incidencias.Incidencia incidencia : incidencias.getIncidencia()) {
				System.out.println(incidencia);
			}
		} else {
			System.out.println("Error al leer las incidencias desde el archivo.");
		}
	}

}
