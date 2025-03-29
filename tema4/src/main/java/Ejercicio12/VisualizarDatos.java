package Ejercicio12;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

/**
 * Clase que permite visualizar los datos almacenados en la base de datos "EQUIPOS.DB".
 * Muestra todos los países y jugadores registrados en la base de datos Neodatis.
 */
public class VisualizarDatos {
    public static void main(String[] args) {
        // Abrir la base de datos Neodatis
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        // Recuperar y mostrar todos los países almacenados
        Objects<Paises> paises = odb.getObjects(Paises.class);
        System.out.println("Países:");
        while (paises.hasNext()) {
            Paises pais = paises.next();
            System.out.println(pais);
        }

        // Recuperar y mostrar todos los jugadores almacenados
        Objects<Jugadores> jugadores = odb.getObjects(Jugadores.class);
        System.out.println("\nJugadores:");
        while (jugadores.hasNext()) {
            Jugadores jugador = jugadores.next();
            System.out.println(jugador);
        }

        // Cerrar la conexión con la base de datos
        odb.close();
    }
}
