package tema1.lecturaXMLconSAX;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParseCochesSAX {
    private static final String RUTA = "H:\\Otros ordenadores\\Mi PC\\IdeaProjects\\accesoADatos\\tema1y2\\src\\main\\java\\tema1\\lecturaXMLconSAX\\ArchivoXML.xml";

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            UserHandler userHandler = new UserHandler();

            saxParser.parse(RUTA, userHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}