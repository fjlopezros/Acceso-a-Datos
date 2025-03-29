package tema1.escrituraJDOM;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Escritura {
    public static void main(String[] args) {
        try {
            // Creando objeto Document de JDOM
            Document documento = new Document();

            // Creando y añadiendo el Elemento Raíz
            Element elementoRaiz = new Element("autos");
            documento.setRootElement(elementoRaiz);

            // Creando elementos y atributos
            Element elementoNodo = getElement();

            // Añadiendo Elementos a la Raíz
            elementoRaiz.addContent(elementoNodo);

            // Escribiendo el contenido en un archivo XML
            XMLOutputter salidaXml = new XMLOutputter(Format.getPrettyFormat());
            try (FileOutputStream fos = new FileOutputStream("Jdomautos.xml");
                 OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
                salidaXml.output(documento, osw);
            }

            // Salida a consola para prueba
            salidaXml.output(documento, System.out);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Element getElement() {
        Element elementoNodo = new Element("superautos");
        elementoNodo.setAttribute("compañía", "Ferrari");

        Element elemento1 = new Element("nombreAuto");
        elemento1.setAttribute("tipo", "fórmula uno");
        elemento1.setText("Ferrari 101");

        Element elemento2 = new Element("nombreAuto");
        elemento2.setAttribute("tipo", "deportivos");
        elemento2.setText("Ferrari 202");

        elementoNodo.addContent(elemento1);
        elementoNodo.addContent(elemento2);
        return elementoNodo;
    }
}