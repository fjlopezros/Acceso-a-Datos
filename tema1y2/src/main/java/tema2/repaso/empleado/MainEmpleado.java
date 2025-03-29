package tema2.repaso.empleado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainEmpleado {

    private static String url = "jdbc:mysql://localhost:3306";
    private static String user = "root";
    private static String pass = "";

    protected static Connection conn;

    private static Empleado e = new Empleado("Maykel", "bachiller", 1500);

    public static void main(String[] args) {
        try {
            conectarse();
            crearDatos();
            EmpleadoDAO.delete(4);
            EmpleadoDAO.select();
        } finally {
            cerrarConn();
        }
    }

    private static void conectarse() {
        try {
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Conectados");
        } catch (SQLException e) {
        }
    }

    private static void cerrarConn() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
    }

    private static void crearDatos(){
        String createBD = "CREATE DATABASE IF NOT EXISTS empleadosAC;";

        String userBD = "USE empleadosAC;";

        String createTable = "CREATE TABLE empleados(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(20) NOT NULL, " +
                "departamento VARCHAR(50) NOT NULL," +
                "salario decimal(10,2) NOT NULL);";

        String createData = "INSERT INTO empleados (nombre,departamento,salario) VALUES (" +
                "'juan','informatica',1100.50),(" +
                "'pepe','ESO',1200),(" +
                "'anselmo','bachiller',1100);";

        try(Statement st = conn.createStatement()){
            st.execute(createBD);
            System.out.println("BBDD creada");
            st.execute(userBD);
            System.out.println("BBDD usada");
//            st.execute(createTable);
//            System.out.println("tablas creadas");
//            st.execute(createData);
//            System.out.println("Datos insertados");
        }catch(SQLException e){}
    }

}
