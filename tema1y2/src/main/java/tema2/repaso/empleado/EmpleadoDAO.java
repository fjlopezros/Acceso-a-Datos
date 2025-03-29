package tema2.repaso.empleado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoDAO {
    public static void select(){
        String query ="SELECT * FROM empleados";
        try(PreparedStatement pst = MainEmpleado.conn.prepareStatement(query)){
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Departamento: " + rs.getString("departamento"));
                System.out.println("Salario: " + rs.getDouble("salario"));
            }
        }catch(SQLException e){}
    }
    public static void insert(Empleado empleado){
        String query = "INSERT INTO empleados (nombre, departamento, salario) VALUES (?,?,?)";
        try(PreparedStatement pst = MainEmpleado.conn.prepareStatement(query)){

            pst.setString(1, empleado.getNombre());
            pst.setString(2, empleado.getDepartamento());
            pst.setDouble(3, empleado.getSalario());

            System.out.println("filas insertadas " + pst.executeUpdate());
        }catch(SQLException e){}
    }
    public static void update(int id, Empleado empleado){
        String query = "UPDATE empleados SET nombre=?,departamento=?, salario=? WHERE id = ?";
        try(PreparedStatement pst = MainEmpleado.conn.prepareStatement(query)){
            pst.setString(1, empleado.getNombre());
            pst.setString(2,empleado.getDepartamento());
            pst.setDouble(3,empleado.getSalario());
            pst.setInt(4,id);

            System.out.println("filas actualizadas " + pst.executeUpdate());
        }catch(SQLException e){}
    }
    public static void delete(int id){
        String query = "DELETE FROM empleados WHERE id = ?";
        try(PreparedStatement pst = MainEmpleado.conn.prepareStatement(query)){
            pst.setInt(1, id);

            System.out.println("filas borradas " + pst.executeUpdate());

        }catch(SQLException e){}
    }
}
