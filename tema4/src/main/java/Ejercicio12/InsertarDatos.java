package Ejercicio12;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;


public class InsertarDatos {
    public static void main(String[] args) {
        // Crear la base de datos
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        // Crear países
        Paises pais1 = new Paises(1, "España");
        Paises pais2 = new Paises(2, "Francia");

        // Crear jugadores
        Jugadores jugador1 = new Jugadores(pais1, "futbol");
        Jugadores jugador2 = new Jugadores(pais2, "baloncesto");

        // Insertar países y jugadores en la base de datos
        odb.store(pais1);
        odb.store(pais2);
        odb.store(jugador1);
        odb.store(jugador2);

        // Cerrar la base de datos
        odb.close();
    }
}
