package tema1.xml_xsl;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Transformador {
    private static final String RUTAXML = "H:\\Otros ordenadores\\Mi PC\\IdeaProjects\\accesoADatos\\tema1y2\\src\\main\\java\\tema1\\xml_xsl\\Videojuegos.xml";
    private static final String RUTAXSL = "H:\\Otros ordenadores\\Mi PC\\IdeaProjects\\accesoADatos\\tema1y2\\src\\main\\java\\tema1\\xml_xsl\\Videojuegos.xsl";
    private static final String RUTAHTML = "H:\\Otros ordenadores\\Mi PC\\IdeaProjects\\accesoADatos\\tema1y2\\src\\main\\java\\tema1\\xml_xsl\\Videojuegos.html";
    
    public static void main(String[] args) {
        try {
            // Definimos las rutas del XML, XSL y HTML
            File xmlFile = new File(RUTAXML);
            File xslFile = new File(RUTAXSL);
            File outputFile = new File(RUTAHTML);

            //Representacion de las fuentes de entrada con el objeto StreamSource
            Source xmlSource = new StreamSource(xmlFile);
            Source xslt = new StreamSource(xslFile);

            // Creación del transformador basado en el XSL
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xslt);

            // Definimos el resultado de la transformación en el archivo de salida HTML
            Result output = new StreamResult(outputFile);       

            // Realiza la transformación
            transformer.transform(xmlSource, output);

            System.out.println("Transformación completada. Archivo generado: " + outputFile.getAbsolutePath());
        } catch (TransformerException e) {
            System.out.println("Error al transformarlo -> " + e.getMessage());
            e.printStackTrace();
        }
    }
}
