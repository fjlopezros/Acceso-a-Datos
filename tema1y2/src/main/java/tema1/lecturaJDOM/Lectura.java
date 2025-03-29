package tema1.lecturaJDOM;


import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Lectura {

    public static void main(String[] args) {
        try {
            // Crear un objeto SAXBuilder
            SAXBuilder saxBuilder = new SAXBuilder();

            // Leer el XML
            File fichero = new File("H:\\Otros ordenadores\\Mi PC\\IdeaProjects\\accesoADatos\\tema1y2\\src\\main\\java\\tema1\\lecturaJDOM\\clase.xml");

            // Parsear el documento XML
            Document documento = saxBuilder.build(fichero);

            // Recuperar el elemento raíz
            Element elementoRaiz = documento.getRootElement();
            System.out.println("Elemento raíz: " + elementoRaiz.getName());

            // Recuperar los elementos hijo
            List<Element> listaElementos = elementoRaiz.getChildren("estudiante");
            System.out.println("----------------------------");

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
            System.out.println("Error " + e.getMessage());
            //e.printStackTrace();
        }
    }
}
