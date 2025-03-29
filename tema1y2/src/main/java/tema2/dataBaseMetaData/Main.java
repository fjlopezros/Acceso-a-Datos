package tema2.dataBaseMetaData;

import java.sql.*;

public class Main {
    public static Connection conn = null;
    public static Statement st;
    public static ResultSetMetaData rsmd;
    public static ResultSet rs;

    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "FJLR";
        String pass = "";

        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado a la BBDD " + conn.getCatalog());
            st = conn.createStatement();

            crearTabla();
            crearDatos();

            visualizarInformacionBaseDatos();


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

    private static void visualizarInformacionBaseDatos() {
        try {
            // Obtiene el objeto DatabaseMetaData, que proporciona información sobre la base de datos y su estructura.
            DatabaseMetaData metaData = conn.getMetaData();

            // Muestra información básica del sistema de base de datos y el driver.
            System.out.println("Nombre del DBMS: " + metaData.getDatabaseProductName());
            System.out.println("Versión del DBMS: " + metaData.getDatabaseProductVersion());
            System.out.println("Nombre del Driver: " + metaData.getDriverName());
            System.out.println("Versión del Driver: " + metaData.getDriverVersion());

            // Obtiene la lista de tablas en la base de datos. El método getTables permite filtrar por esquema o tipo de tabla.
            ResultSet tablas = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("\nTablas en la base de datos:");

            // Recorre el resultado de tablas obtenidas.
            while (tablas.next()) {
                // Obtiene el nombre de la tabla actual
                String nombreTabla = tablas.getString("TABLE_NAME");
                System.out.println("- " + nombreTabla);

                // Obtiene las llaves primarias de la tabla actual
                ResultSet pk = metaData.getPrimaryKeys(null, null, nombreTabla);
                System.out.print("  Llave primaria: ");
                while (pk.next()) {
                    // Muestra el nombre de cada columna que forma la llave primaria.
                    System.out.print(pk.getString("COLUMN_NAME") + " ");
                }
                System.out.println();

                // Obtiene información sobre las columnas de la tabla actual
                ResultSet columnas = metaData.getColumns(null, null, nombreTabla, null);
                System.out.println("  Columnas:");
                while (columnas.next()) {
                    // Obtiene el nombre de la columna
                    String nombreColumna = columnas.getString("COLUMN_NAME");
                    // Obtiene el tipo de datos de la columna (por ejemplo, VARCHAR, INT, etc.)
                    String tipoColumna = columnas.getString("TYPE_NAME");
                    // Obtiene el tamaño máximo permitido en la columna (por ejemplo, para VARCHAR(50), el tamaño es 50)
                    int tamañoColumna = columnas.getInt("COLUMN_SIZE");

                    // Muestra la información de la columna actual
                    System.out.println("    - " + nombreColumna + " (" + tipoColumna + ", tamaño: " + tamañoColumna + ")");
                }
            }

        } catch (SQLException e) {
            // Si ocurre un error al obtener la información de la base de datos, se muestra un mensaje de error.
            System.out.println("Error al obtener información de la base de datos: " + e.getMessage());
            //e.printStackTrace();
        }
    }

}
