package universityManagement.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {

    private static Connection con;

    public static Connection getConnection () {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                con = DriverManager.getConnection (
                        "jdbc:mysql://localhost:3306/university_management", "root", "w3n33dd4tab!");

                return con;

            } catch (Exception e) {

                System.out.println(e);
                return null;

            }
    }

}
