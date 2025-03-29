package tema2.repaso.producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static tema2.repaso.producto.MainProducto.conn;

public class ProductoDao {

    public static void select(String nombreTabla){
        String query = "SELECT * FROM " + nombreTabla;
        try(PreparedStatement pst = conn.prepareStatement(query)){
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.println("id: " + rs.getString("id"));
                System.out.println("nombre: " + rs.getString("nombre"));
                System.out.println("descripcion: " + rs.getString("descripcion"));
                System.out.println("precio: " + rs.getString("precio"));
                System.out.println("cantidad: " + rs.getString("cantidad"));
                System.out.println("--------------------------------------");
            }
        }catch(SQLException e){
            System.out.println("Error select(String nombreTabla) " + e.getMessage());
        }
    }

    public static void select(String nombreTabla, int id) {
        String query = "SELECT * FROM " + nombreTabla + " WHERE id = ?";
        try(PreparedStatement pst = conn.prepareStatement(query)){
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.println("id: " + rs.getString("id"));
                System.out.println("nombre: " + rs.getString("nombre"));
                System.out.println("descripcion: " + rs.getString("descripcion"));
                System.out.println("precio: " + rs.getString("precio"));
                System.out.println("cantidad: " + rs.getString("cantidad"));
                System.out.println("--------------------------------------");
            }
        }catch(SQLException e){
            System.out.println("Error select(String nombreTabla, int id) " + e.getMessage());
        }
    }

    public static void delete(String nombreTabla, int id){
        String query = "DELETE FROM " + nombreTabla + " WHERE id = ?";
        try(PreparedStatement pst = conn.prepareStatement(query)){
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Han sido borradas " + pst.getUpdateCount() + " id");
        }catch(SQLException  e){
            System.out.println("Error delete(int id) " + e.getMessage());
        }
    }

    public static void insert(String nombreTabla, Producto producto){
        String query = "INSERT INTO " + nombreTabla + " (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
        try(PreparedStatement pst = conn.prepareStatement(query)){
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getCantidad());
            pst.executeUpdate();
            System.out.println("Han sido insertadas " + pst.getUpdateCount() + " id nuevo");
        }catch(SQLException e){
            System.out.println("Error insert(String nombreTabla) " + e.getMessage());
        }
    }

    public static void update(String nombreTabla, int id, Producto producto){
        String query = "UPDATE " + nombreTabla + " SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE id = ?";
        try(PreparedStatement pst = conn.prepareStatement(query)){
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getCantidad());
            pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("Han sido actualizadas " + pst.getUpdateCount() + " id");
        }catch(SQLException e){
            System.out.println("Error update(String nombreTabla) " + e.getMessage());
        }
    }
}
