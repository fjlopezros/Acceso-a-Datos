package Ejercicio10;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

/**
 * Clase que permite visualizar los datos almacenados en la base de datos Neodatis.
 * Recupera y muestra la lista de países y jugadores almacenados en "EQUIPOSDB".
 */
public class VisualizarDatos {
    public static void main(String[] args) {

        // Abrir la conexión con la base de datos Neodatis
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        // Recuperar y mostrar todos los países almacenados en la base de datos
        Objects<Paises> paises = odb.getObjects(Paises.class);
        System.out.println("Países:");

        while (paises.hasNext()) { // Iterar sobre los resultados obtenidos
            Paises pais = paises.next();
            System.out.println(pais); // Mostrar la información del país
        }

        // Recuperar y mostrar todos los jugadores almacenados en la base de datos
        Objects<Jugadores> jugadores = odb.getObjects(Jugadores.class);
        System.out.println("\nJugadores:");

        while (jugadores.hasNext()) { // Iterar sobre los resultados obtenidos
            Jugadores jugador = jugadores.next();
            System.out.println(jugador); // Mostrar la información del jugador
        }

        // Cerrar la conexión con la base de datos
        odb.close();
    }
}
