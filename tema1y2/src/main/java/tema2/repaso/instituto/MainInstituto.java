package tema2.repaso.instituto;

import java.sql.*;

public class MainInstituto {

    private static String url = "jdbc:mysql://localhost:3306";
    private static String user = "root";
    private static String pass = "";

    private static Connection conn;
    private static ResultSet rs;

    private static Instituto inst = new Instituto("anselmo", "zapillo");
    private static Instituto inst1 = new Instituto("juan", "molinos");

    public static void main(String[] args) {
        try {
            conect();
            createData();
            //insert(inst);
            //update(1, inst1);
            //delete(2);
            //select();
            //crearProcedimiento();
            //llamarProcedimiento("anselmo");
            //infoTablas();
            infoBBDD();
        } finally {
            closeConn();
        }
    }

    public static void conect() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void closeConn() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void createData() {
        String createBD = "CREATE DATABASE IF NOT EXISTS institutobd;";

        String useBD = "USE institutobd;";

        String createTable = "CREATE TABLE IF NOT EXISTS instituto (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(50) NOT NULL," +
                "direccion VARCHAR(100) NOT NULL);";

        String createData = "INSERT INTO instituto(nombre, direccion) VALUES" +
                "('Safa','Barrio alto')," +
                "('La salle','Centro')," +
                "('Celia Viñas','Centro');";

        try (Statement st = conn.createStatement()) {

            st.execute(createBD);
            System.out.println("BD created");

            st.execute(useBD);
            System.out.println("BD used");

            st.execute(createTable);
            System.out.println("Table created");

//            st.execute(createData);
//            System.out.println("Data created");

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void select() {
        String query = "SELECT * FROM instituto";

        try (PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery();) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") +
                        " " + rs.getString("nombre") +
                        " " + rs.getString("direccion"));
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void update(int id, Instituto inst) {
        String query = "UPDATE instituto SET nombre =? , direccion = ? WHERE id = ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, inst.getNombre());
            pst.setString(2, inst.getDireccion());
            pst.setInt(3, id);

            System.out.println("Filas actualizados: " + pst.executeUpdate());

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void delete(int id) {
        String query = "DELETE FROM instituto WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            System.out.println("Filas eliminados: " + pst.executeUpdate());
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void insert(Instituto i) {
        String query = "INSERT INTO instituto (nombre,direccion) VALUES (?,?)";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, i.getNombre());
            pst.setString(2, i.getDireccion());
            System.out.println("Filas insertados: " + pst.executeUpdate());
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void crearProcedimiento() {
        String query = "CREATE PROCEDURE mostrarPorNombre(IN name VARCHAR(255)) " +
                "BEGIN " +
                "    SELECT * FROM instituto WHERE nombre = name; " +
                "END;";

        try (Statement st = conn.createStatement()) {
            st.execute(query);
            System.out.println("Procedimiento creado");
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void llamarProcedimiento(String nombre) {
        String query = "{CALL mostrarPorNombre(?)}";
        try (CallableStatement pst = conn.prepareCall(query)) {
            pst.setString(1, nombre);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") +
                        " " + rs.getString("nombre") +
                        " " + rs.getString("direccion"));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Error " + e.getMessage());
            }
        }
    }

    public static void infoTablas() {
        String query = "SELECT * FROM instituto";
        try (PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery();) {

            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println("Columna " + i + ":");
                System.out.println("  Nombre: " + rsmd.getColumnName(i));
                System.out.println("  Tipo: " + rsmd.getColumnTypeName(i));
                System.out.println("  Tamaño: " + rsmd.getColumnDisplaySize(i));
                System.out.println("  Es Nullable: " + (rsmd.isNullable(i) == ResultSetMetaData.columnNullable));
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void infoBBDD() {
        try {
            DatabaseMetaData dbmt = conn.getMetaData();

            System.out.println("Base de Datos: " + dbmt.getDatabaseProductName());
            System.out.println("Versión de la Base de Datos: " + dbmt.getDatabaseProductVersion());
            System.out.println("Driver: " + dbmt.getDriverName());
            System.out.println("Versión del Driver: " + dbmt.getDriverVersion());
            System.out.println("URL: " + dbmt.getURL());
            System.out.println("Usuario: " + dbmt.getUserName());

            System.out.println("\nTablas disponibles:");
            try (ResultSet rsTabla = dbmt.getTables(null,
                    null,
                    "%",
                    new String[]{"TABLE"});) {
                while (rsTabla.next()) {
                    String tabla = rsTabla.getString("TABLE_NAME");
                    System.out.println("- " + tabla);


                    try (ResultSet rsColumna = dbmt.getColumns(null,
                            null,
                            tabla,
                            null);) {
                        System.out.println("  Columnas de " + tabla + ":");
                        while (rsColumna.next()) {
                            System.out.println("    Nombre: " + rsColumna.getString("COLUMN_NAME"));
                            System.out.println("    Tipo: " + rsColumna.getString("TYPE_NAME"));
                            System.out.println("    Tamaño: " + rsColumna.getInt("COLUMN_SIZE"));
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}

class Instituto {
    private int id;
    private String nombre;
    private String direccion;

    public Instituto(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "instituto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
