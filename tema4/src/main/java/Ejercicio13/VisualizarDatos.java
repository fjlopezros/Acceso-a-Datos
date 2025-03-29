package Ejercicio13;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

/**
 * Clase que permite visualizar los datos almacenados en la base de datos Neodatis.
 * Se muestran los países y jugadores registrados.
 */
public class VisualizarDatos {
    public static void main(String[] args) {
        // Abrir la base de datos "EQUIPOS.DB"
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        // Recuperar y mostrar todos los países almacenados
        Objects<Ejercicio13.Paises> paises = odb.getObjects(Ejercicio13.Paises.class);
        System.out.println("Países:");
        while (paises.hasNext()) {
            Paises pais = paises.next();
            System.out.println(pais); // Se asume que la clase Paises tiene un método toString()
        }

        // Recuperar y mostrar todos los jugadores almacenados
        Objects<Ejercicio13.Jugadores> jugadores = odb.getObjects(Ejercicio13.Jugadores.class);
        System.out.println("\nJugadores:");
        while (jugadores.hasNext()) {
            Jugadores jugador = jugadores.next();
            System.out.println(jugador); // Se asume que la clase Jugadores tiene un método toString()
        }

        // Cerrar la base de datos para evitar bloqueos o pérdida de datos
        odb.close();
    }
}