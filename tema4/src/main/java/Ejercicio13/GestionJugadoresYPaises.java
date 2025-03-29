package Ejercicio13;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que gestiona la consulta de jugadores por país, mostrando el número de jugadores por ciudad
 * y calculando la media de edad de los mismos.
 */
public class GestionJugadoresYPaises {

    /**
     * Método que visualiza los jugadores de un país determinado, agrupándolos por ciudad
     * y calculando la media de edad.
     *
     * @param nombrePais Nombre del país cuyos jugadores se quieren consultar.
     */
    public static void visualizarJugadoresPorCiudadYMediaEdad(String nombrePais) {
        // Abrir la base de datos Neodatis
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        // Buscar el país en la base de datos
        IQuery queryPais = new CriteriaQuery(Paises.class, Where.equal("nombrepais", nombrePais));
        Objects<Paises> paises = odb.getObjects(queryPais);

        if (paises.isEmpty()) {
            System.out.println("El país " + nombrePais + " no existe.");
        } else {
            // Buscar los jugadores asociados al país
            IQuery queryJugadores = new CriteriaQuery(Jugadores.class, Where.equal("pais.nombrepais", nombrePais));
            Objects<Jugadores> jugadores = odb.getObjects(queryJugadores);

            if (jugadores.isEmpty()) {
                System.out.println("El país " + nombrePais + " no tiene jugadores.");
            } else {
                // Mapa para almacenar la cantidad de jugadores por ciudad
                Map<String, Integer> jugadoresPorCiudad = new HashMap<>();

                // Variables para calcular la media de edad
                int totalEdad = 0;
                int totalJugadores = 0;

                // Recorrer la lista de jugadores y recopilar datos
                while (jugadores.hasNext()) {
                    Jugadores jugador = jugadores.next();
                    String ciudad = jugador.getCiudad();
                    int edad = jugador.getEdad();

                    // Contar jugadores por ciudad
                    jugadoresPorCiudad.put(ciudad, jugadoresPorCiudad.getOrDefault(ciudad, 0) + 1);

                    // Acumular la edad total
                    totalEdad += edad;
                    totalJugadores++;
                }

                // Mostrar el número de jugadores por ciudad
                System.out.println("Número de jugadores por ciudad en " + nombrePais + ":");
                for (Map.Entry<String, Integer> entry : jugadoresPorCiudad.entrySet()) {
                    System.out.println("Ciudad: " + entry.getKey() + " | Jugadores: " + entry.getValue());
                }

                // Calcular y mostrar la media de edad de los jugadores
                double mediaEdad = (double) totalEdad / totalJugadores;
                System.out.println("Media de edad de los jugadores en " + nombrePais + ": " + mediaEdad);
            }
        }

        // Cerrar la base de datos
        odb.close();
    }
}