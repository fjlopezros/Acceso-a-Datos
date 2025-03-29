package tema1.lecturaEscrituraXML_DOM;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Escritura {

    public static void main(String[] args) {
        try {
            // Crear una instancia de DocumentBuilderFactory.
            DocumentBuilderFactory convertidorXMLaDOM = DocumentBuilderFactory.newInstance();
            //Crear un nuevo DocumentBuilder usando la fábrica. Proporciona metodos para la manipulacion de XML
            DocumentBuilder textoInsertado = convertidorXMLaDOM.newDocumentBuilder();
            //Crea un DOM vacio representa un XML
            Document datosTexto = textoInsertado.newDocument();

            Element elementoRaiz = datosTexto.createElement("concesionario");
            datosTexto.appendChild(elementoRaiz);

            Element elementoNodo = datosTexto.createElement("coche");
            elementoRaiz.appendChild(elementoNodo);

            Attr atributo = datosTexto.createAttribute("id");
            atributo.setValue("1");
            elementoNodo.setAttributeNode(atributo);

            Element elementoMarca = datosTexto.createElement("marca");
            elementoMarca.appendChild(datosTexto.createTextNode("Renault"));
            elementoNodo.appendChild(elementoMarca);

            Element elementoModelo = datosTexto.createElement("modelo");
            elementoModelo.appendChild(datosTexto.createTextNode("Megano"));
            elementoNodo.appendChild(elementoModelo);

            Element elementoCilindrada = datosTexto.createElement("cilindrada");
            elementoCilindrada.appendChild(datosTexto.createTextNode("1.5"));
            elementoNodo.appendChild(elementoCilindrada);

            TransformerFactory convertidor = TransformerFactory.newInstance();
            Transformer cambiar = convertidor.newTransformer();
            DOMSource fuenteDOM = new DOMSource(datosTexto);
            StreamResult resultado = new StreamResult(new File("H:\\Otros ordenadores\\Mi PC\\IdeaProjects\\accesoADatos\\tema1y2\\src\\main\\java\\tema1\\lecturaEscrituraXML_DOM\\pruebaEscritura.xml"));

            cambiar.setOutputProperty(OutputKeys.INDENT, "yes");
            
            cambiar.transform(fuenteDOM, resultado);
            

        } catch (IllegalArgumentException | ParserConfigurationException | TransformerException | DOMException e) {
            System.out.println("Error " + e.getMessage());
            //e.printStackTrace();
        }
    }
}
