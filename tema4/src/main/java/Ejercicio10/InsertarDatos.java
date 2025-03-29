package Ejercicio10;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

/**
 * Clase que inserta datos en la base de datos Neodatis "EQUIPOS.DB".
 * Se crean dos países y dos jugadores asociados a esos países, y se almacenan en la base de datos.
 */
public class InsertarDatos {
    public static void main(String[] args) {
        // Abrir la conexión con la base de datos (o crearla si no existe)
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        // Crear objetos de la clase Paises
        Paises pais1 = new Paises(1, "España");
        Paises pais2 = new Paises(2, "Francia");

        // Crear objetos de la clase Jugadores, asociando cada jugador a un país
        Jugadores jugador1 = new Jugadores(pais1);
        Jugadores jugador2 = new Jugadores(pais2);

        // Insertar los países en la base de datos
        odb.store(pais1);
        odb.store(pais2);

        // Insertar los jugadores en la base de datos
        odb.store(jugador1);
        odb.store(jugador2);

        // Cerrar la conexión con la base de datos
        odb.close();
    }
}
