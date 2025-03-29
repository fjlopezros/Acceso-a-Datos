package tema3.hibernate.main;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tema3.hibernate.controller.Controller;
import tema3.hibernate.util.HibernateUtil;
import tema3.hibernate.view.View;

public class MainEj8 {
    private static Session session = Main.getSession();
    private static Transaction transaction = Main.getTransaction();

    public static void main(String[] args) {
        try {
            Main.sesion();

            new Controller(new View());

            //guarda los datos aplicados
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                //si pasa alguna excepcion desahcer todos los cambios realizados
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close(); // Cerrar la sesión si está inicializada
                HibernateUtil.shutdown();
            }
        }
        System.out.println("Operación completada.");
    }
}