package Ejercicio13;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

/**
 * Clase que inserta datos de países y jugadores en la base de datos Neodatis.
 */
public class InsertarDatos {
    public static void main(String[] args) {
        // Abrir o crear la base de datos "EQUIPOS.DB"
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        // Crear instancias de países
        Paises pais1 = new Paises(1, "España");
        Paises pais2 = new Paises(2, "Francia");

        // Crear instancias de jugadores con su país, deporte, ciudad y edad
        Jugadores jugador1 = new Jugadores(pais1, "Fútbol", "Madrid", 17);
        Jugadores jugador2 = new Jugadores(pais2, "Baloncesto", "París", 23);

        // Almacenar países en la base de datos
        odb.store(pais1);
        odb.store(pais2);

        // Almacenar jugadores en la base de datos
        odb.store(jugador1);
        odb.store(jugador2);

        // Cerrar la base de datos para evitar pérdidas de datos
        odb.close();

        System.out.println("Datos insertados correctamente en la base de datos.");
    }
}