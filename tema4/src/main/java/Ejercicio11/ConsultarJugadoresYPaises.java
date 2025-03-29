package Ejercicio11;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

/**
 * Clase que consulta y muestra los jugadores junto con sus países
 * almacenados en la base de datos Neodatis "EQUIPOS.DB".
 */
public class ConsultarJugadoresYPaises {
    public static void main(String[] args) {

        // Abrir la conexión con la base de datos "EQUIPOS.DB"
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        // Recuperar todos los jugadores almacenados en la base de datos
        Objects<Jugadores> jugadores = odb.getObjects(Jugadores.class);

        // Mostrar la lista de jugadores junto con sus respectivos países
        System.out.println("Jugadores y sus países:");
        while (jugadores.hasNext()) { // Iterar sobre cada jugador obtenido
            Jugadores jugador = jugadores.next(); // Obtener jugador
            Paises pais = jugador.getPais(); // Obtener el país asociado al jugador

            // Mostrar información del jugador y su país
            System.out.println("Jugador: " + jugador + " | País: " + pais);
        }

        // Cerrar la conexión con la base de datos
        odb.close();
    }
}
