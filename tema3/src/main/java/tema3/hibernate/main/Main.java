package tema3.hibernate.main;

import tema3.hibernate.modelo.Equipo;
import tema3.hibernate.modelo.Estadistica;
import tema3.hibernate.modelo.Jugador;
import tema3.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Session session;
    private static Transaction transaction;

    public static void main(String[] args) {
        try {
            sesion();

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce un ejericio: ");
            int opcion = sc.nextInt();

            menu(opcion);

            //confirma todos los cambios hacia la base de datos
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                //si pasa alguna excepcion desahcer todos los cambios realizados
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close(); // Cerrar la sesión si está inicializada
            }
        }

        HibernateUtil.shutdown();
        System.out.println("Operación completada.");
    }

    public static void sesion() {
        // Abrir la sesión de Hibernate
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }


    public static void menu(int opcion) {
        switch (opcion) {
            case 5 -> ejercicio5();
            case 6 -> ejercicio6();
            case 7 -> ejercicio7();
            default -> System.out.println("Introduce un ejercicio correcto (5, 6, 7 u 8)");
        }
    }

    private static void ejercicio7() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el código del jugador:");
        int codigoJugador = scanner.nextInt();

        // Buscar al jugador en la base de datos
        Jugador jugador = session.get(Jugador.class, codigoJugador);

        if (jugador != null) {
            System.out.println("Introduce puntos por partido:");
            float puntos = scanner.nextFloat();

            System.out.println("Introduce asistencias por partido:");
            float asistencias = scanner.nextFloat();

            System.out.println("Introduce tapones por partido:");
            float tapones = scanner.nextFloat();

            System.out.println("Introduce rebotes por partido:");
            float rebotes = scanner.nextFloat();

            Estadistica estadistica = new Estadistica();
            estadistica.setJugador(jugador);
            estadistica.setPuntosPorPartido(puntos);
            estadistica.setAsistenciasPorPartido(asistencias);
            estadistica.setTaponesPorPartido(tapones);
            estadistica.setRebotesPorPartido(rebotes);

            // Guardar las estadísticas en la base de datos
            session.persist(estadistica);
        }
    }

    private static void ejercicio6() {
        // Consulta HQL para obtener todos los equipos
        List<Equipo> equipos = session.createQuery("FROM Equipo", Equipo.class).list();
        // Mostrar los resultants
        System.out.println("Equipos encontrados: " + equipos.size());
        for (Equipo equipo : equipos) {
            System.out.println("Nombre: " + equipo.getNombre());

            List<Jugador> jugadores = session.createQuery(
                            "FROM Jugador WHERE equipo.nombre = :nombreEquipo", Jugador.class)
                    .setParameter("nombreEquipo", equipo.getNombre())
                    .list();
            for (Jugador jugador : jugadores) {

                System.out.println(jugador.getCodigo() + ", " +
                        jugador.getNombre() + ", " +
                        jugador.getAlturaPies() + ", " +
                        jugador.getPosicion());
            }
            System.out.println("-------------------------");
        }
    }

    public static void ejercicio5() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un código de jugador: ");
        int codigo = sc.nextInt();

        // Consulta para buscar el jugador por código
        Jugador jugador = session.get(Jugador.class, codigo);

        // Validar si el jugador existe
        if (jugador != null) {
            // Mostrar los datos del jugador
            System.out.println("Jugador encontrado:");
            System.out.println("Código: " + jugador.getCodigo());
            System.out.println("Nombre: " + jugador.getNombre());
            System.out.println("Procedencia: " + jugador.getProcedencia());
            System.out.println("Altura (pies): " + jugador.getAlturaPies());
            System.out.println("Peso (libras): " + jugador.getPesoLibras());
            System.out.println("Posición: " + jugador.getPosicion());
            System.out.println("Equipo: " + jugador.getEquipo());
        } else {
            System.out.println("No se encontró ningún jugador con el código: " + codigo);
        }
    }

    public static Session getSession() {
        return session;
    }

    public static Transaction getTransaction() {
        return transaction;
    }
}
