package tema2.conectBbdd;

import java.sql.*;
import java.util.List;

public class Main {

    public static Connection conn = null;
    public static PreparedStatement stmt = null;
    public static ResultSet rs = null;

    public static void main(String[] args) {
        String url = "jdbc:sqlite:G:\\Otros ordenadores\\Mi PC\\IdeaProjects\\accesoADatos\\tema1y2\\src\\main\\java\\tema2\\conectBbdd\\AccesoADatosBBDD.db";

        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Conexión exitosa a la base de datos SQLite.");

            // Crear una instancia de PersonaDAO
            PersonaDAO personaDAO = new PersonaDAO(conn);

            List<Persona> listaPersonas = personaDAO.select();
            System.out.println("Lista de personas:");
            for (Persona p : listaPersonas) {
                System.out.println(p);  // Imprimir cada persona
            }

            String dniConsulta = "12345678A";  // Ejemplo de un DNI
            Persona persona = personaDAO.select(dniConsulta);
            if (persona != null) {
                System.out.println("Persona encontrada con DNI " + dniConsulta + ": " + persona);
            } else {
                System.out.println("No se encontró ninguna persona con el DNI " + dniConsulta);
            }

             //Crear una nueva persona
            Persona nuevaPersona = new Persona("87654321Z", "Nuevo", "Usuario", Date.valueOf("1990-01-01"));
            try {
                personaDAO.insert(nuevaPersona);  // Insertar la nueva persona en la base de datos
                System.out.println("Persona insertada correctamente: " + nuevaPersona);
            } catch (Exception e) {
                System.out.println("Error al insertar la persona: " + e.getMessage());
            }


        } catch (SQLException e) {
            System.out.println("Error!!" + e.getMessage());
            // ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error Finally -- conn");
                    // ex.printStackTrace();
                }
            }
            PersonaDAO.cerrarPs(stmt);
        }
    }
}