package mejora;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class Actividad10_Main {
    public static void main(String[] args) {

        //DEFINO PAISES
        Paises pais1 = new Paises(1, "España");
        Paises pais2 = new Paises(2, "Italia");
        Paises pais3 = new Paises(3, "Suiza");
        Paises pais4 = new Paises(4, "EEUU");

        //DEFINO JUGADORES
        Jugadores j1 = new Jugadores("Maria", "voleibol", pais1, 14);
        Jugadores j2 = new Jugadores("Miguel", "tenis", pais2, 15);
        Jugadores j3 = new Jugadores("Mario", "baloncesto", pais3, 15);
        Jugadores j4 = new Jugadores("Alicia", "tenis", pais4, 14);

        //CREO O ABRO BBDD
        ODB odb = ODBFactory.open("d:\\EQUIPOS.DB");

        //AÑADO LOS JUGADORES
        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);

        //RECUPERO LOS JUGADORES
        Objects<Object> objectsJug = odb.getObjects(Jugadores.class);
        System.out.println("Hay " + objectsJug.size() + " Jugadores en la BD:");
        int i = 1; //contador para mostrar listados los objetos

        //VISUALIZAR LOS DATOS Y MOSTRAR
        while (objectsJug.hasNext()) {
            Jugadores jug = (Jugadores) objectsJug.next();
            System.out.println((i++) + " - " + "Nombre: " + jug.getNombre() + ", Deporte: " + jug.getDeporte() + ", Pais: " + jug.getPais().getNombrePais() + ", Edad: " + jug.getEdad());
        }

        //SALTO DE LINEA
        System.out.println("\n");

        //RECUPERO LOS PAISES
        Objects<Object> objectsPaises = odb.getObjects(Paises.class);
        System.out.println("Hay " + objectsPaises.size() + " Paises en la BD:");
        int j = 1; //contador para mostrar listados los objetos

        //VISUALIZAR LOS DATOS Y MOSTRAR
        while (objectsPaises.hasNext()) {
            Paises pais = (Paises) objectsPaises.next();
            System.out.println((j++) + " - " + "ID: " + pais.getId() + ", Pais: " + pais.getNombrePais());
        }

        //CERRAR CONEXION
        odb.close();
    }
}
