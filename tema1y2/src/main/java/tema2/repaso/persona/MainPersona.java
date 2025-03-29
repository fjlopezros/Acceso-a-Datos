package tema2.repaso.persona;

import java.sql.*;

public class MainPersona {
    private static String url = "jdbc:mysql://localhost:3306";
    private static String user ="root";
    private static String pass = "";

    protected static Connection conn;

    private static Persona p1 = new Persona("Maykel",33,133);

    public static void main(String[] args) {
        try {
            conection();
            createData();
            //PersonaDAO.select();
            //PersonaDAO.deleteId(15);
            //PersonaDAO.updateId(16, p1);
            PersonaDAO.selectId(15);
        }finally {
            closeConection();
        }
    }

    public static void conection(){
        try{
            conn = DriverManager.getConnection(url,user,pass);
        }catch(SQLException e){
            System.out.println("Error conection() " + e.getMessage());
        }
    }

    public static void closeConection(){
        try{
            if(conn!=null) conn.close();
        }catch(SQLException e){
            System.out.println("Error closeConection() " + e.getMessage());
        }
    }

    public static void createData(){
        String createDB = "CREATE DATABASE IF NOT EXISTS Persona";
        String useDB = "USE Persona";
        String createTable = "CREATE TABLE IF NOT EXISTS persona (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(20) NOT NULL," +
                "edad INT NOT NULL," +
                "altura INT);";
        String insertData = "INSERT INTO persona (nombre, edad, altura) VALUES " +
                "('pepe', 45, 180)," + "('anselmo', 60, 165)," +
                "('juan', 23, 175);";
        try (Statement st = conn.createStatement()) {
            st.execute(createDB);
            st.execute(useDB);
            //st.execute(createTable);
            //st.execute(insertData);
            System.out.println("BBDD " + conn.getCatalog() + "creada\n" +
                    "Usando " + conn.getCatalog() + "\n" +
                    "Tabla creada\n" +
                    "Datos insertados");
        }catch(SQLException e){
            System.out.println("Error createData() " + e.getMessage());
        }
    }
}
