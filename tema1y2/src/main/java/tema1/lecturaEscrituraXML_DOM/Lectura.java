package tema1.lecturaEscrituraXML_DOM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Lectura {

    private static final String RUTA = "H:\\Otros ordenadores\\Mi PC\\IdeaProjects\\accesoADatos\\tema1y2\\src\\main\\java\\tema1\\lecturaEscrituraXML_DOM\\ArchivoXML.xml";

    public static void main(String[] args) {
        leerFichero(RUTA);
    }

    public static void leerFichero(String ruta) {
        File fichero = new File(RUTA);
        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            DocumentBuilderFactory convertidorXMLaDOM = DocumentBuilderFactory.newInstance();
            DocumentBuilder textoInsertado = convertidorXMLaDOM.newDocumentBuilder();
            Document datosTexto = textoInsertado.parse(fichero);

            datosTexto.getDocumentElement().normalize(); // Accede al documento raíz y elimina nodos vacíos

            NodeList listaNodo = datosTexto.getElementsByTagName("coche");
            System.out.println("Número de coches: " + listaNodo.getLength());

            for (int i = 0; i < listaNodo.getLength(); i++) {
                Node nodo = listaNodo.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                    if (nodo.hasChildNodes()) {
                        NodeList listaNodoHijos = nodo.getChildNodes();
                        for (int j = 0; j < listaNodoHijos.getLength(); j++) {
                            Node nodoHijos = listaNodoHijos.item(j);
                            if (nodoHijos.getNodeType() == Node.ELEMENT_NODE) {
                                System.out.println(nodoHijos.getNodeName() + ": " + nodoHijos.getTextContent());
                            }
                        }
                    }
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}