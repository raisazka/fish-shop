package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDatabase {

    /**
     * Change the port and database password if
     * You're using Windows operating system to connect to MySQL
     * **/
    public static Connection connect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/fish_shop?useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
