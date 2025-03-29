package tema2.sentencias;

import java.sql.*;

/**
 * Elige una de las bases de datos embebidas que has creado
 * e implementa un programa java que realice una consulta preparada,
 * una actualización preparada y una llamada a procedimiento
 * previamente definido en tu BD. El procedimiento puede ser
 * una consulta sencilla. Todo con su correcto tratamiento de
 * excepciones SQL que muestre información variada acerca de el
 * tipo de error ocurrido.
 */
public class  Main {
    private static final String url = "jdbc:mysql://localhost:3306/Prueba";
    private static final String user = "root";
    private static final String pass = "";

    private static Connection conn;

    public static void main(String[] args) {
        try {
            conectar();
            crearTablaUsuarios();
            insertarDatosIniciales();
            crearProcedimiento();

            consultaPreparada(25);
            actualizacionPreparada("NuevoNombre", "alice@example.com");
            llamarProcedimiento(30);
        } finally {
            closeConn();
        }
    }

    /**
     * Conecta a la base de datos MySQL.
     *
     * @throws SQLException si ocurre un error al establecer la conexión.
     */
    public static void conectar() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado a MySQL.");
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }

    /**
     * Crea la tabla 'Usuarios' en la base de datos si no existe.
     *
     * @throws SQLException si ocurre un error en la creación de la tabla.
     */
    public static void crearTablaUsuarios() {
        String sql = "CREATE TABLE IF NOT EXISTS Usuarios ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "nombre VARCHAR(50) NOT NULL,"
                + "email VARCHAR(100) NOT NULL,"
                + "edad INT"
                + ");";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
            System.out.println("Tabla 'Usuarios' creada correctamente.");
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }

    /**
     * Inserta datos iniciales en la tabla 'Usuarios'.
     *
     * @throws SQLException si ocurre un error al insertar los datos.
     */
    public static void insertarDatosIniciales() {
        String sql = "INSERT INTO Usuarios (nombre, email, edad) VALUES "
                + "('Alice', 'alice@example.com', 25), "
                + "('Bob', 'bob@example.com', 30), "
                + "('Charlie', 'charlie@example.com', 25);";
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Datos iniciales insertados en la tabla 'Usuarios'.");
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }

    /**
     * Ejecuta una consulta preparada para buscar usuarios con una edad específica.
     *
     * @param edadBusqueda La edad de los usuarios a buscar.
     * @throws SQLException si ocurre un error en la consulta.
     */
    public static void consultaPreparada(int edadBusqueda) {
        String query = "SELECT nombre, email FROM Usuarios WHERE edad = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, edadBusqueda);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Consulta de usuarios con edad " + edadBusqueda + ":");
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }

    /**
     * Ejecuta una actualización preparada para cambiar el nombre de un usuario basado en su email.
     *
     * @param nuevoNombre El nuevo nombre del usuario.
     * @param email El email del usuario a actualizar.
     * @throws SQLException si ocurre un error en la actualización.
     */
    public static void actualizacionPreparada(String nuevoNombre, String email) {
        String update = "UPDATE Usuarios SET nombre = ? WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(update)) {
            pstmt.setString(1, nuevoNombre);
            pstmt.setString(2, email);
            int filasActualizadas = pstmt.executeUpdate();
            System.out.println("Filas actualizadas: " + filasActualizadas);
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }

    /**
     * Crea el procedimiento almacenado 'obtenerUsuarioPorEdad' en la base de datos.
     * Este procedimiento selecciona usuarios según la edad proporcionada.
     *
     * @throws SQLException si ocurre un error en la creación del procedimiento.
     */
    public static void crearProcedimiento() {
        String sql = "CREATE PROCEDURE obtenerUsuarioPorEdad(OUT p_edad INT) "
                + "BEGIN "
                + "SELECT nombre, email FROM Usuarios WHERE edad = p_edad; "
                + "END;";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
            System.out.println("Procedimiento 'obtenerUsuarioPorEdad' creado correctamente.");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1304) { // Código de error 1304: Procedimiento ya existe
                System.out.println("El procedimiento 'obtenerUsuarioPorEdad' ya existe.");
            } else {
                manejarExcepcion(e);
            }
        }
    }

    /**
     * Llama al procedimiento almacenado 'obtenerUsuarioPorEdad' para obtener usuarios con una edad específica.
     *
     * @param edad La edad de los usuarios a buscar.
     * @throws SQLException si ocurre un error al llamar al procedimiento.
     */
    public static void llamarProcedimiento(int edad) {
        String call = "{CALL obtenerUsuarioPorEdad(?)}";
        try (CallableStatement cstmt = conn.prepareCall(call)) {
            cstmt.setInt(1, edad);
            ResultSet rs = cstmt.executeQuery();

            System.out.println("Resultados del procedimiento almacenado para edad " + edad + ":");
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }

    /**
     * Cierra la conexión a la base de datos.
     *
     * @throws SQLException si ocurre un error al cerrar la conexión.
     */
    public static void closeConn() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                manejarExcepcion(e);
            }
        }
    }

    /**
     * Maneja y muestra información detallada de una excepción SQL.
     *
     * @param e La excepción SQLException a manejar.
     */
    public static void manejarExcepcion(SQLException e) {
        System.out.println("Error SQL: " + e.getMessage());
        System.out.println("Código de error SQL: " + e.getErrorCode());
        System.out.println("Estado SQL: " + e.getSQLState());
        e.printStackTrace();
    }
}
