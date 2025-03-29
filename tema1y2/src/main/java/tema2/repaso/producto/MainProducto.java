package tema2.repaso.producto;

import java.sql.*;

public class MainProducto {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "";

    protected static Connection conn;

    private static Producto producto = new Producto("bicicleta","muy mala",150,2);

    public static void main(String[] args) {
        try {
            conectarse();
            usarBBDD("repaso_examen");
//            crearYUsarBBDD("repaso_examen", "repaso_examen");
//            crearTablas();
//            crearDatos();
//            ProductoDao.select("productos",5);
//            ProductoDao.update("productos",11, producto);
            ProductoDao.select("productos");
        }finally {
            cerrarConn();
        }
    }

    public static void conectarse(){
        try{
            conn = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Conectado");
        }catch(SQLException e){
            System.out.println("Error conectarse() " + e.getMessage());
        }
    }

    public static void crearBBDD(String nombre){
        String query = "CREATE DATABASE IF NOT EXISTS " + nombre + ";";
        try(Statement st = conn.createStatement()){
            st.execute(query);
            System.out.println("Creado BBDD");
        }catch(SQLException e){
            System.out.println("Error crearBBDD() " + e.getMessage());
        }
    }

    public static void usarBBDD(String usar){
        String query = "USE " + usar + ";";
        try(Statement st = conn.createStatement()){
            st.execute(query);
            System.out.println("BBDD seleccionada");
        }catch(SQLException e){
            System.out.println("Error usarBBDD() " + e.getMessage());
        }
    }

    public static void crearYUsarBBDD(String nombre, String usar){
        crearBBDD(nombre);
        usarBBDD(usar);
    }

    public static void crearTablas(){
        String query = "CREATE TABLE productos (" +
                "id INT AUTO_INCREMENT PRIMARY KEY ," +
                "nombre VARCHAR (50) NOT NULL," +
                "descripcion TEXT," +
                "precio DOUBLE(10,2) NOT NULL," +
                "cantidad INT NOT NULL" +
                ");";
        try(Statement st = conn.createStatement()){
            st.execute(query);
            System.out.println("Tablas creadas");
        }catch(SQLException e){
            System.out.println("Error crearTablas() " + e.getMessage());
        }
    }

    public static void crearDatos(){
        String query = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES\n" +
                "('Laptop', 'Laptop de 15 pulgadas con 16GB de RAM y 512GB SSD', 1200.00, 10),\n" +
                "('Smartphone', 'Teléfono inteligente de última generación con 128GB de almacenamiento', 800.00, 25),\n" +
                "('Auriculares', 'Auriculares inalámbricos con cancelación de ruido', 150.00, 50),\n" +
                "('Teclado Mecánico', 'Teclado mecánico RGB con switches Cherry MX', 100.00, 30),\n" +
                "('Monitor 4K', 'Monitor de 27 pulgadas con resolución 4K UHD', 350.00, 20),\n" +
                "('Mouse Inalámbrico', 'Mouse ergonómico inalámbrico con alta precisión', 50.00, 40),\n" +
                "('Tablet', 'Tablet de 10 pulgadas con 64GB de almacenamiento y pantalla Full HD', 300.00, 15),\n" +
                "('Impresora Láser', 'Impresora láser monocromática con Wi-Fi', 200.00, 5),\n" +
                "('Disco Duro Externo', 'Disco duro externo de 1TB con conexión USB 3.0', 80.00, 60),\n" +
                "('Cámara Web', 'Cámara web HD con micrófono integrado', 75.00, 35);";

        try(Statement st = conn.createStatement()){
            st.execute(query);
            System.out.println("Datos insertados");
        }catch(SQLException e){
            System.out.println("Error crearDatos() " + e.getMessage());
        }
    }

    public static void cerrarConn(){
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                System.out.println("Error cerrarConn() " + e.getMessage());
            }
        }
    }
}

