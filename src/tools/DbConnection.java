package tools;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author deanchristt
 */
public class DbConnection {

    private Connection con;

    public Connection getConncetion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_hr?zeroDateTimeBehavior=convertToNull", "root", "");
        } catch (Exception e) {
            System.out.println("Error + " + e.getMessage());
        }
        return con;
    }
}
