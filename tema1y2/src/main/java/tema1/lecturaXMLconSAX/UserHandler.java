package tema1.lecturaXMLconSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler extends DefaultHandler {

    // Indican si se está en el contexto de un elemento específico del XML
    boolean hasMarca = false;
    boolean hasModelo = false;
    boolean hasCilindrada = false;

    // Si está en el XML, se muestra un mensaje o se cambia a true
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("coche")) {
            System.out.println("Elemento actual: " + qName);
        }
        if (qName.equalsIgnoreCase("marca")) {
            hasMarca = true;
        }
        if (qName.equalsIgnoreCase("modelo")) {
            hasModelo = true;
        }
        if (qName.equalsIgnoreCase("cilindrada")) {
            hasCilindrada = true;
        }
    }

    // Cuando el booleano correspondiente es true, se imprime el valor y
    // se restablece el booleano a false para evitar que se imprima varias veces.
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (hasMarca) {
            System.out.println("Marca: " + new String(ch, start, length));
            hasMarca = false;
        }
        if (hasModelo) {
            System.out.println("Modelo: " + new String(ch, start, length));
            hasModelo = false;
        }
        if (hasCilindrada) {
            System.out.println("Cilindrada: " + new String(ch, start, length));
            hasCilindrada = false;
        }
    }
}