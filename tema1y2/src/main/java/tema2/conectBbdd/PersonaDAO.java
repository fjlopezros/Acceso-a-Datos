package tema2.conectBbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class PersonaDAO {
    private Connection conn;

    public PersonaDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Persona> select() {
        String query = "SELECT dni, nombre, apellidos, fnac FROM persona";
        List<Persona> listaPersona = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            // Recorremos los resultados y agregamos a la lista
            while (rs.next()) {
                listaPersona.add(rellenaPersona(rs));
            }
            System.out.println(listaPersona);
        } catch (SQLException e) {
            System.out.println("Error en select(): " + e.getMessage());
        }
        return listaPersona;  // Devuelve la lista de personas correctamente
    }


    public Persona select(String dniConsulta) {
        String query = "SELECT dni, nombre, apellidos, fnac FROM persona WHERE dni = ?";
        Persona pers = null;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, dniConsulta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pers = rellenaPersona(rs);  // Asignamos el resultado a la variable pers
                }
            }
            System.out.println(pers != null ? pers : "Persona no encontrada.");
        } catch (SQLException e) {
            System.out.println("Error en select(String dniConsulta): " + e.getMessage());
        }

        return pers;  // Devuelve la persona o null si no la encuentra
    }


        public void insert(Persona pers) throws Exception {
            String query = "INSERT INTO persona (dni, nombre, apellidos, fnac) VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, pers.getDni());
                ps.setString(2, pers.getNombre());
                ps.setString(3, pers.getApellidos());
                ps.setDate(4, pers.getFnac());

                // Verifica si la inserción fue exitosa
                if (ps.executeUpdate() != 1) {
                    throw new Exception("La persona no se ha insertado.");
                }

                System.out.println("Persona insertada: " + pers);
            } catch (SQLException e) {
                System.out.println("Error en insert(): " + e.getMessage());
            }
        }

    //Metodo para cerrar el ResultSet y otro para el Statement
    //Metodo para no repetir codigo en el next

    public static Persona rellenaPersona(ResultSet rs) {
        Persona pers = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String dni = rs.getString("dni");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String fnacString = rs.getString("fnac");

            Date fnac = null;
            if (fnacString != null) {
                try {
                    fnac = new Date(dateFormat.parse(fnacString).getTime());
                } catch (Exception e) {
                    System.out.println("Error al parsear la fecha: " + e.getMessage());
                }
            }

            pers = new Persona(dni, nombre, apellidos, fnac);
        } catch (SQLException e) {
            System.out.println("Error en rellenaPersona(): " + e.getMessage());
        }

        return pers;
    }

    public static void cerrarRs(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e2) {
                System.out.println("Error en cerrarRs(): " + e2.getMessage());
            }
        }
    }

    public static void cerrarPs(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e2) {
                System.out.println("Error en cerrarPs(): " + e2.getMessage());
            }
        }
    }
}
