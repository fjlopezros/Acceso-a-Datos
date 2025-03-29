package tema2.repaso.persona;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonaDAO {
    public static void select() {
        String select = "SELECT * FROM persona";
        try (PreparedStatement pst = MainPersona.conn.prepareStatement(select)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Edad: " + rs.getInt("edad"));
                System.out.println("Altura: " + rs.getDouble("altura"));
            }
            System.out.println("Datos Mostrados");
        } catch (SQLException e) {
            System.out.println("Error select() " + e.getMessage());
        }
    }

    public static void selectId(int id) {
        String selectId = "SELECT * FROM persona WHERE id = ?";
        try (PreparedStatement pst = MainPersona.conn.prepareStatement(selectId)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No esta esa persona con ese id: " + id);
            } else {
                while (rs.next()) {
                    System.out.println("id: " + rs.getInt("id"));
                    System.out.println("Nombre: " + rs.getString("nombre"));
                    System.out.println("Edad: " + rs.getInt("edad"));
                    System.out.println("Altura: " + rs.getDouble("altura"));
                }
            }
            System.out.println("Dato mostrado");
        } catch (SQLException e) {
            System.out.println("Error selectId(int id) " + e.getMessage());
        }
    }

    public static void deleteId(int id) {
        String delete = "DELETE FROM persona WHERE id = ?";
        try (PreparedStatement pst = MainPersona.conn.prepareStatement(delete)) {
            pst.setInt(1, id);
            System.out.println("Fila Eliminada: " + pst.executeUpdate() +
                    " con ID " + id);
        } catch (SQLException e) {
            System.out.println("Error deleteId(int id) " + e.getMessage());
        }
    }

    public static void insert(Persona persona) {
        String insert = "INSERT INTO persona (nombre, edad, altura) VALUES (?,?,?)";
        try (PreparedStatement pst = MainPersona.conn.prepareStatement(insert)) {
            pst.setString(1, persona.getNombre());
            pst.setInt(2, persona.getEdad());
            pst.setDouble(3, persona.getAltura());
            System.out.println("Datos insertados " + pst.executeUpdate());
        } catch (SQLException e) {
            System.out.println("Error insert(Persona persona) " + e.getMessage());
        }
    }

    public static void updateId(int id, Persona persona) {
        String update = "UPDATE persona SET nombre=?, edad=?, altura=? WHERE id = ?";
        try (PreparedStatement pst = MainPersona.conn.prepareStatement(update)) {
            pst.setString(1, persona.getNombre());
            pst.setInt(2, persona.getEdad());
            pst.setDouble(3, persona.getAltura());
            pst.setInt(4, id);
            System.out.println("Filas actualizadas " + pst.executeUpdate());
        } catch (SQLException e) {
            System.out.println("Error update(int id,Persona persona) " + e.getMessage());
        }
    }
}
