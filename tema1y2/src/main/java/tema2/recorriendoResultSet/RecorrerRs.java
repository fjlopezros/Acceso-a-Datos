package tema2.recorriendoResultSet;

import java.sql.*;

public class RecorrerRs {

    public static void main(String[] args) {
        String query = "SELECT id, firstname, lastname FROM tblperson";
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/personadb", "root", "");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);

            // Contar el número de registros
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            System.out.println("Total number of records: " + rowCount);
            rs.beforeFirst(); // Regresar al inicio del ResultSet

            // Mostrar el contenido de los registros
            int rowNum = 1;
            while (rs.next()) {
                System.out.print("Row " + rowNum + ": ");
                displayData(rs);
                rowNum++;
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void displayData(ResultSet rs) throws SQLException {
        System.out.print("ID: " + rs.getInt("id"));
        System.out.print(", First Name: " + rs.getString("firstname"));
        System.out.println(", Last Name: " + rs.getString("lastname"));
    }
}
