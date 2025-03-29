package tema1.serializarJDOM;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Serializable {

    public static void serializarAXml(String ruta) {
        try {
            // Crear un objeto SAXBuilder
            SAXBuilder saxBuilder = new SAXBuilder();

            // Leer el XML
            File fichero = new File(ruta);

            // Parsear el documento XML
            Document documento = saxBuilder.build(fichero);

            // Recuperar el elemento raíz
            Element elementoRaiz = documento.getRootElement();
            System.out.println("Elemento raíz: " + elementoRaiz.getName());

            // Recuperar los elementos hijo
            List<Element> listaElementos = elementoRaiz.getChildren("estudiante");

            for (Element elemento : listaElementos) {
                System.out.println("\nElemento actual: " + elemento.getName());
                Attribute attribute = elemento.getAttribute("rollno");
                System.out.println("Número de matrícula del estudiante: " + attribute.getValue());
                System.out.println("Nombre: " + elemento.getChild("nombre").getText());
                System.out.println("Apellido: " + elemento.getChild("apellido").getText());
                System.out.println("Apodo: " + elemento.getChild("apodo").getText());
                System.out.println("Notas: " + elemento.getChild("notas").getText());
            }
        } catch (IOException | JDOMException e) {
            System.out.println("Error serializarAXml()" + e.getMessage());
            //e.printStackTrace();
        }
    }

    public static void desSerializarDesdeXml(List<Clase> estudiantes, String archivo) {
        try {
            // Creando objeto Document de JDOM
            Document documento = new Document();

            // Creando y añadiendo el Elemento Raíz
            Element elementoRaiz = new Element(Clase.class.getSimpleName());
            documento.setRootElement(elementoRaiz);

            for(Clase estudiante:estudiantes) {
                // Creando elementos y atributos
                Element elementoNodo = getElement(estudiante);
                // Añadiendo Elementos a la Raíz
                elementoRaiz.addContent(elementoNodo);
            }
            // Escribiendo el contenido en un archivo XML
            XMLOutputter salidaXml = new XMLOutputter(Format.getPrettyFormat());
            try (FileOutputStream fos = new FileOutputStream(archivo);
                 OutputStreamWriter osw = new OutputStreamWriter(fos)) {
                salidaXml.output(documento, osw);
            }

            // Salida a consola para prueba
            salidaXml.output(documento, System.out);
        } catch (IOException e) {
            System.out.println("Error desSerializarDesdeXml() " + e.getMessage());
            // e.printStackTrace();
        }
    }

    private static Element getElement(Clase estudiante) {
        Element elementoNodo = new Element(estudiante.getESTUDIANTE());
        elementoNodo.setAttribute(estudiante.getROLLNO(), estudiante.getRollno());

        Element elemento1 = new Element(estudiante.getNOMBRE());
        elemento1.setText(estudiante.getNombre());

        Element elemento2 = new Element(estudiante.getAPELLIDO());
        elemento2.setText(estudiante.getApellido());

        Element elemento3 = new Element(estudiante.getAPODO());
        elemento3.setText(estudiante.getApodo());

        Element elemento4 = new Element(estudiante.getNOTAS());
        elemento4.setText(String.valueOf(estudiante.getNotas()));

        elementoNodo.addContent(elemento1);
        elementoNodo.addContent(elemento2);
        elementoNodo.addContent(elemento3);
        elementoNodo.addContent(elemento4);
        return elementoNodo;
    }
}
