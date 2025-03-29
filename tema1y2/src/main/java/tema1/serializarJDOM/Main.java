package tema1.serializarJDOM;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String ruta = "H:\\Otros ordenadores\\Mi PC\\IdeaProjects\\accesoADatos\\tema1y2\\src\\main\\java\\tema1\\serializarJDOM\\clase.xml";
        String rutaDestino ="H:\\Otros ordenadores\\Mi PC\\IdeaProjects\\accesoADatos\\tema1y2\\src\\main\\java\\tema1\\serializarJDOM\\claseEscrito.xml";

        List<Clase> estudiantes = new ArrayList<>();

        estudiantes.add(new Clase("1", "Juan", "Perez", "JP", 90));
        estudiantes.add(new Clase("2", "Maria", "Lopez", "ML", 85));
        estudiantes.add(new Clase("3", "Pedro", "Gonzalez", "PG", 88));

        Serializable.desSerializarDesdeXml(estudiantes, rutaDestino);

        Serializable.serializarAXml(rutaDestino);
    }
}
