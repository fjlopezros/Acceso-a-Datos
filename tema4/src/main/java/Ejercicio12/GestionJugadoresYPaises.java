package Ejercicio12;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 * Clase que gestiona jugadores y países en la base de datos Neodatis "EQUIPOS".
 * Proporciona métodos para buscar jugadores según su país y deporte,
 * así como eliminar un país y actualizar los jugadores asociados.
 */
public class GestionJugadoresYPaises {

    /**
     * Busca jugadores por país y deporte en la base de datos.
     *
     * @param nombrePais Nombre del país del jugador.
     * @param deporte    Deporte que practica el jugador.
     */
    public static void buscarJugadoresPorPaisYDeporte(String nombrePais, String deporte) {
        // Abrir la base de datos
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        // Crear una consulta para obtener jugadores por país y deporte
        IQuery query = new CriteriaQuery(Jugadores.class, Where.and()
                .add(Where.equal("pais.nombrepais", nombrePais))
                .add(Where.equal("deporte", deporte)));

        // Ejecutar la consulta
        Objects<Jugadores> jugadores = odb.getObjects(query);

        // Verificar si hay resultados
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores de " + nombrePais + " que practiquen " + deporte + ".");
        } else {
            System.out.println("Jugadores de " + nombrePais + " que practican " + deporte + ":");
            while (jugadores.hasNext()) {
                Jugadores jugador = jugadores.next();
                System.out.println(jugador);
            }
        }
        // Cerrar la base de datos
        odb.close();
    }

    /**
     * Borra un país de la base de datos. Si hay jugadores asociados a ese país,
     * les asigna un valor nulo en el campo de país antes de eliminar el país.
     *
     * @param nombrePais Nombre del país a eliminar.
     */
    public static void borrarPais(String nombrePais) {
        // Abrir la base de datos
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        // Buscar el país por nombre
        IQuery queryPais = new CriteriaQuery(Paises.class, Where.equal("nombrepais", nombrePais));
        Objects<Paises> paises = odb.getObjects(queryPais);

        // Si no se encuentra el país, se muestra un mensaje y se termina el proceso
        if (paises.isEmpty()) {
            System.out.println("No se encontró el país: " + nombrePais);
        } else {
            Paises pais = paises.next();

            // Buscar jugadores asociados al país
            IQuery queryJugadores = new CriteriaQuery(Jugadores.class, Where.equal("pais.nombrepais", nombrePais));
            Objects<Jugadores> jugadores = odb.getObjects(queryJugadores);

            // Si no hay jugadores asociados, se elimina el país directamente
            if (jugadores.isEmpty()) {
                System.out.println("El país " + nombrePais + " no tiene jugadores. Borrando país..");
                odb.delete(pais);
            } else {
                System.out.println("El país " + nombrePais + " tiene jugadores. Asignando null..");

                // Iterar sobre los jugadores para actualizar su país a null
                while (jugadores.hasNext()) {
                    Jugadores jugador = jugadores.next();
                    jugador.setPais(null); // Asignar null al país del jugador
                    odb.store(jugador); // Guardar los cambios en la base de datos
                }

                // Borrar el país después de actualizar los jugadores
                odb.delete(pais);
                System.out.println("País " + nombrePais + " borrado correctamente.");
            }
        }
        // Cerrar la base de datos
        odb.close();
    }
}