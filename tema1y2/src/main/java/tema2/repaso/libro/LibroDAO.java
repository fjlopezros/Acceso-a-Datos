package tema2.repaso.libro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibroDAO {

    public static void select(){
        String query = ("SELECT * FROM libro");
        try (PreparedStatement pst = MainLibro.conn.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            int numColumn = 0;
            while(rs.next()){
                numColumn++;
                System.out.println("Columna " + numColumn + ": ");
                System.out.println("id " + rs.getInt(1));
                System.out.println("titulo " + rs.getString(2));
                System.out.println("autor " + rs.getString(3));
                System.out.println("disponible " + rs.getBoolean(4));
            }
            System.out.println("total de columnas " + numColumn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update(int id, Libro libro){
        String query = "UPDATE libro SET titulo = ?, autor = ?, disponible = ? WHERE id_libro = ?;";
        try (PreparedStatement pst = MainLibro.conn.prepareStatement(query)) {
            pst.setString(1, libro.getTitulo());
            pst.setString(2, libro.getAutor());
            pst.setBoolean(3, libro.isDisponible());
            pst.setInt(4,id);

            System.out.println("filas actualizadas " + pst.executeUpdate());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete(int id){
        String query = "DELETE FROM libro WHERE id_libro = ?;";
        try (PreparedStatement pst = MainLibro.conn.prepareStatement(query)) {
            pst.setInt(1,id);

            System.out.println("filas borradas " + pst.executeUpdate());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(Libro libro){
        String query = "INSERT INTO libro (titulo,autor,disponible) VALUES (?,?,?);";
        try (PreparedStatement pst = MainLibro.conn.prepareStatement(query)) {
            pst.setString(1, libro.getTitulo());
            pst.setString(2, libro.getAutor());
            pst.setBoolean(3,libro.isDisponible());

            System.out.println("filas insertadas " + pst.executeUpdate());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
