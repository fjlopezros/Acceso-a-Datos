package tema2.repaso.libro;

import java.sql.*;

public class MainLibro {
    private static String url = "jdbc:mysql://localhost:3306";
    private static String user = "root";
    private static String pass = "";

    protected static Connection conn;

    private static Libro l = new Libro("prueba", "prueba", false);
    private static Libro l1 = new Libro("popotterrrrrrrr", "kkkkkkkkkkkkk", false);

    public static void main(String[] args) {
        try{
            conectar();
            createData();
            LibroDAO.insert(l);
            LibroDAO.delete(1);
            LibroDAO.update(2,l1);
            LibroDAO.select();
        }finally {
            closeCon();
        }
    }
    public static void conectar(){
        try{
            conn = DriverManager.getConnection(url,user,pass);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void closeCon(){
        if(conn != null) {
            try {
            conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void createData(){
        String createBD = "CREATE DATABASE IF NOT EXISTS libreria";
        String useBD = "USE libreria";
        String createTable = "CREATE TABLE libro(" +
                "id_libro INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    titulo VARCHAR(255) NOT NULL,\n" +
                "    autor VARCHAR(255),\n" +
                "    disponible BOOLEAN DEFAULT TRUE\n);";
        String createData = "INSERT INTO libro (titulo,autor) VALUES (" +
                "'maukeldelacalle', 'mayk')," +
                "('libreodelaselva','coco');";
        try(Statement st = conn.createStatement()) {
            st.execute(createBD);
            System.out.println("BD creada");
            st.execute(useBD);
            System.out.println("BD usada");
//            st.execute(createTable);
//            System.out.println("Tabla creada");
//            st.execute(createData);
//            System.out.println("Datos creados");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

