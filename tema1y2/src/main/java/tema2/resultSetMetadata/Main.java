package tema2.resultSetMetadata;

import java.sql.*;

public class Main {
    public static Connection conn = null;
    public static Statement st;
    public static ResultSetMetaData rsmd;
    public static ResultSet rs;

    public static void main(String[] args) {
        String url = "jdbc:hsqldb:mem:persona";
        String user = "FJLR";
        String pass = "";

        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado a la BBDD " + conn.getCatalog());
            st = conn.createStatement();

            crearTabla();
            crearDatos();

            ResultSetMetaData personas = seleccionarTabla("Personas");

            int numeroColumnasPersonas = consultarColumnas(personas);
            System.out.println("Numero de columnas " + numeroColumnasPersonas);

            consultarNombreYTipos(numeroColumnasPersonas);

            System.out.println("Son mayores de edad: ");
            consultarEdad("Personas", 18);

            System.out.println("Conuslta por nombre: ");
            consultarNombre("Personas", "Pepe");

        } catch (SQLException e) {
            System.out.println("Error al conectarse a la BBDD " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos " + e.getMessage());
            }
        }
    }

    /*
        public static void crearBBDD() {
            try {
                st.execute("CREATE DATABASE IF NOT EXISTS persona;");
                System.out.println("Base de datos creada");
                st.execute("USE persona;");
            } catch (SQLException e) {
                System.out.println("Error en el metodo crearBBDD!!" + e.getMessage());
                // ex.printStackTrace();
            }
        }
    */
    public static void crearTabla() {
        try {
            st.execute("CREATE TABLE IF NOT EXISTS Personas (" +
                    "id INT PRIMARY KEY, " +
                    "nombre VARCHAR(50), " +
                    "edad INT)");
            System.out.println("Tabla creada correctamente");
        } catch (SQLException e) {
            System.out.println("Error en el metodo crearTabla!!" + e.getMessage());
            //e.printStackTrace();
        }
    }

    public static void crearDatos() {
        try {
            st.execute("INSERT INTO Personas VALUES" +
                    "   (1,'Pepe', 23)," +
                    "   (2,'Jose', 18)," +
                    "   (3,'Juan', 21)," +
                    "   (4,'Manuel', 16)," +
                    "   (5,'Kaka', 22)");
            System.out.println("Datos insertados correctamente");
        } catch (SQLException e) {
            System.out.println("Error en el metodo crearDatos!!" + e.getMessage());
            // e.printStackTrace();
        }
    }

    public static void consultarNombreYTipos(int numColumn){
        try {
            for (int i = 1; i <= numColumn; i++) {
                System.out.println("Nombre de la columna " + i + ": " + rsmd.getColumnName(i));
                System.out.println("Tipo de datos de la columna " + i + ": " + rsmd.getColumnTypeName(i));
            }
        }catch(SQLException e){
            System.out.println("Error al consultarNombreYTipos de la tabla " + e.getMessage());
            //e.printStackTrace();
        }
    }

    public static ResultSetMetaData seleccionarTabla(String tabla){
        try {
            rs = st.executeQuery("SELECT * FROM " + tabla);
            rsmd = rs.getMetaData();
        }catch (SQLException e){
            System.out.println("Error al seleccionar la tabla " + e.getMessage());
        }
        return rsmd;
    }

    public static int consultarColumnas(ResultSetMetaData tabla) {
        int numColumn = 0;
        try {
            numColumn = tabla.getColumnCount();
        } catch (SQLException e) {
            System.out.println("Error al contar el numero de columnas " + e.getMessage());
        }
        return numColumn;
    }

    public static void consultarEdad(String tabla, int edadMinima){
        String query = "SELECT * FROM " + tabla + " WHERE edad > ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, edadMinima);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad);
            }

        }catch (SQLException e) {
            System.out.println("Error al consultar personas por edad: " + e.getMessage());
        }
    }

    public static void consultarNombre(String tabla, String nombreConsulta){
        String query = "SELECT * FROM " + tabla + " WHERE nombre = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, nombreConsulta);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad);
            }

        }catch (SQLException e) {
            System.out.println("Error al consultar personas por nombre: " + e.getMessage());
        }
    }
}
